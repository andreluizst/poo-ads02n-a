/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.gui;

import ce.erro.GeralException;
import ce.model.fachada.Fachada;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.beans.PropertyVetoException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class MainMDIApplication extends javax.swing.JFrame {
    private static JInternalFrame activeWindow;//IActionsGui activeWindow;
    private Fachada f;
    private Resource res;
    private ImageIcon fundo;
    private List<JInternalFrame> janelas;
    private boolean userIsAdm;
    //private JIFCategoria jifCategoria= new JIFCategoria();
    
    /**
     * Creates new form MainMDIApplication
     */
    public MainMDIApplication() {
        initComponents();
        res= Resource.getInstancia();
        lblImgShell.setVisible(false);
        try {
            fundo= res.get("\\images\\Fundo4.jpg");
        } catch (GeralException ex) {
            fundo=null;
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        janelas= new ArrayList();
        f= Fachada.getInstancia();
        userIsAdm= f.getUser().getPerfil().getNome().toLowerCase().compareTo("administrador") == 0;
        mnPerfil.setVisible(userIsAdm);
        mnUsuario.setVisible(userIsAdm);
    }
    
    /**
     * Registra a janela (JInternalFrame) que está sendo criado e vincula a mesma
     * a um item do menu janela
     * @param janela 
     * Janela que está sendo criada
     */
    public void registrarJanela(JInternalFrame janela){
        String sTexto="";
        janelas.add(janela);
        JMenuItem novoMenuItem= new JMenuItem();
        //novoMenuItem.setName("miJanela"+janelas.size());
        sTexto= ((IActionsGui)janela).getActivationName();
        //novoMenuItem.setText(janelas.size()+ " " + sTexto);
        //s= ((Integer)janelas.size()).toString();
        novoMenuItem.setName("mi"+sTexto);
        novoMenuItem.setText(sTexto);
        novoMenuItem.setMnemonic(sTexto.charAt(0));
        novoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activeWindow(evt);
            }
        });
        mnJanela.add(novoMenuItem);
        desktopPane.add(janela, javax.swing.JLayeredPane.DEFAULT_LAYER);
        janela.setVisible(true);
        setActiveWindow(janela);
        atlzMenu();
        
        /*
         * desktopPane.add(jifUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        registrarJanela(jifUsuario);
        jifUsuario.setVisible(true);
        setActiveWindow(jifUsuario);
         */
    }
    
    /*
    private void ordMenuJanela(){
        Integer j=0;
        String s;
        for (int i=3;i<mnJanela.getItemCount();i++){
            j= i-1;
            s= j.toString();
            mnJanela.getItem(i).setText(j.toString() + " " 
                    +((IActionsGui)janelas.get(j-1)).getActivationName());
            mnJanela.getItem(i).setMnemonic(s.charAt(0));
        }
    }*/
    
    /**
     * Desregistra uma janela (JInternalFrame) da aplicação. Também remove o
     * item do menu janela.
     * @param janela 
     * Janela que esta sendo fechada e destruida
     */
    public void desregistrarJanela(JInternalFrame janela){
        for(int i=0;i<janelas.size();i++){
            if (janelas.get(i).getTitle().compareTo(janela.getTitle()) == 0){
                janelas.remove(i);
                activeLastWindow();
                break;
            }
        }
        /*Deve-se começar do index 3 porque dá erro inesplicável (bug) ao tentar
         * ler o index 2 que é um Separator.
         * 
         */
        try{
            for(int i=3;i<mnJanela.getItemCount();i++){
                if (mnJanela.getItem(i).getText().contains(((IActionsGui)janela).getActivationName())){
                    mnJanela.remove(i);
                    break;
                }
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Erro: " + e.getMessage());
        }
        atlzMenu();
    }
    /**
     * Ativa a janela criada e registrada com o activationName especificado
     * @param activationName 
     * Nome de ativação da janela registrada, também é o texo do item do
     * menu janela
     */
    private void activeWindow(String activationName){
        //JOptionPane.showMessageDialog(null, "activeWindows(String activationName): " + activationName);
        JInternalFrame jifJanela;
        for(int i=0;i<janelas.size();i++){
            if (((IActionsGui)janelas.get(i)).getActivationName().compareTo(activationName) == 0){
                janelas.get(i).setVisible(false);
                janelas.get(i).setVisible(true);
                jifJanela= janelas.get(i);
                janelas.remove(i);
                janelas.add(jifJanela);
                break;
            }
        }
    }
    
    /**
     * Activa a próxima janela
     */
    private void activeNextWindow(){
        if (janelas.size() > 1){
            activeWindow(((IActionsGui)janelas.get(0)).getActivationName());
        }
        /*int i;
        int x;
        boolean active;
        JInternalFrame jifJanela;
        String activationName= ((IActionsGui)activeWindow).getActivationName();
        if (mnJanela.getItemCount() > 3){
            i=3;
            x=0;
            active=false;
            do
            {
                if (((IActionsGui)janelas.get(i)).getActivationName().compareTo(activationName) == 0){
                    if (active){
                        janelas.get(i).setVisible(false);
                        janelas.get(i).setVisible(true);
                        jifJanela= janelas.get(i);
                        janelas.remove(i);
                        janelas.add(jifJanela);
                        if (x > 10){
                            JOptionPane.showMessageDialog(null, "Erro de lógica em activeNextWindow");
                            break;
                        }
                    }
                    active=true;
                }
                i++;
                if (i == mnJanela.getItemCount()){
                    i= 3;
                }
            }
            while (!active);
        }*/
    }
    
    /**
     * Ativa a última janela registrada
     */
    private void activeLastWindow(){
        if (janelas.size() > 0){
            janelas.get(janelas.size()-1).setVisible(false);
            janelas.get(janelas.size()-1).setVisible(true);
        }
    }
    
    /**
     * 
     * @param evt 
     */
    private void activeWindow(java.awt.event.ActionEvent evt){
        activeWindow(evt.getActionCommand());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        desktopPane = new javax.swing.JDesktopPane();
        lblImgShell = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        jMenu1 = new javax.swing.JMenu();
        mnPerfil = new javax.swing.JMenuItem();
        mnUsuario = new javax.swing.JMenuItem();
        mnFuncionario = new javax.swing.JMenuItem();
        miCategora = new javax.swing.JMenuItem();
        mnProduto = new javax.swing.JMenuItem();
        mnFornecedor = new javax.swing.JMenuItem();
        mnLocalE = new javax.swing.JMenuItem();
        mnEntrada = new javax.swing.JMenuItem();
        mnSaida = new javax.swing.JMenuItem();
        mnUnidade = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mnSair = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        mnListar = new javax.swing.JMenuItem();
        mnPesquisar = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mnNovo = new javax.swing.JMenuItem();
        mnAlterar = new javax.swing.JMenuItem();
        mnExcluir = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mnIrPara = new javax.swing.JMenu();
        mnPrimeiro = new javax.swing.JMenuItem();
        mnAnterior = new javax.swing.JMenuItem();
        mnProximo = new javax.swing.JMenuItem();
        mnUltimo = new javax.swing.JMenuItem();
        mnJanela = new javax.swing.JMenu();
        jmnFecharAtual = new javax.swing.JMenuItem();
        jmnProximaJanela = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema simples de estoque");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                MainMDIApplicationOpened(evt);
            }
        });

        desktopPane.setBackground(new java.awt.Color(105, 105, 105));
        desktopPane.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                desktopPaneResized(evt);
            }
        });

        lblImgShell.setText("lblImgShell");
        lblImgShell.setBounds(10, 10, 60, 14);
        desktopPane.add(lblImgShell, javax.swing.JLayeredPane.DEFAULT_LAYER);

        fileMenu.setMnemonic('a');
        fileMenu.setText("Arquivo");

        jMenu1.setMnemonic('a');
        jMenu1.setText("Abrir");
        jMenu1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu1ActionPerformed(evt);
            }
        });

        mnPerfil.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnPerfil.setMnemonic('p');
        mnPerfil.setText("Perfil");
        mnPerfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPerfilActionPerformed(evt);
            }
        });
        jMenu1.add(mnPerfil);

        mnUsuario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnUsuario.setMnemonic('u');
        mnUsuario.setText("Usuário");
        mnUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUsuarioActionPerformed(evt);
            }
        });
        jMenu1.add(mnUsuario);

        mnFuncionario.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnFuncionario.setMnemonic('o');
        mnFuncionario.setText("Funcionário");
        mnFuncionario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFuncionarioActionPerformed(evt);
            }
        });
        jMenu1.add(mnFuncionario);

        miCategora.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miCategora.setMnemonic('c');
        miCategora.setText("Categoria");
        miCategora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCategoraActionPerformed(evt);
            }
        });
        jMenu1.add(miCategora);

        mnProduto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnProduto.setMnemonic('d');
        mnProduto.setText("Produto");
        mnProduto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProdutoActionPerformed(evt);
            }
        });
        jMenu1.add(mnProduto);

        mnFornecedor.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnFornecedor.setMnemonic('f');
        mnFornecedor.setText("Fornecedor");
        mnFornecedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnFornecedorActionPerformed(evt);
            }
        });
        jMenu1.add(mnFornecedor);

        mnLocalE.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnLocalE.setMnemonic('l');
        mnLocalE.setText("Local de estoque");
        jMenu1.add(mnLocalE);

        mnEntrada.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnEntrada.setMnemonic('e');
        mnEntrada.setText("Entrada");
        jMenu1.add(mnEntrada);

        mnSaida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnSaida.setMnemonic('s');
        mnSaida.setText("Saída");
        jMenu1.add(mnSaida);

        mnUnidade.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnUnidade.setMnemonic('i');
        mnUnidade.setText("Unidade");
        jMenu1.add(mnUnidade);

        fileMenu.add(jMenu1);
        fileMenu.add(jSeparator4);

        mnSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F12, java.awt.event.InputEvent.CTRL_MASK));
        mnSair.setMnemonic('s');
        mnSair.setText("Sair");
        mnSair.setName("mnSair"); // NOI18N
        mnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSairActionPerformed(evt);
            }
        });
        fileMenu.add(mnSair);
        mnSair.getAccessibleContext().setAccessibleDescription("");

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Editar");
        editMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editMenuActionPerformed(evt);
            }
        });

        mnListar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        mnListar.setMnemonic('l');
        mnListar.setText("Listar/Atualizar");
        mnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnListarActionPerformed(evt);
            }
        });
        editMenu.add(mnListar);

        mnPesquisar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mnPesquisar.setMnemonic('p');
        mnPesquisar.setText("Pesquisar");
        mnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPesquisarActionPerformed(evt);
            }
        });
        editMenu.add(mnPesquisar);
        editMenu.add(jSeparator3);

        mnNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnNovo.setMnemonic('n');
        mnNovo.setText("Novo...");
        mnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnNovoActionPerformed(evt);
            }
        });
        editMenu.add(mnNovo);

        mnAlterar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnAlterar.setMnemonic('a');
        mnAlterar.setText("Alterar...");
        mnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAlterarActionPerformed(evt);
            }
        });
        editMenu.add(mnAlterar);

        mnExcluir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnExcluir.setMnemonic('e');
        mnExcluir.setText("Excluir");
        mnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnExcluirActionPerformed(evt);
            }
        });
        editMenu.add(mnExcluir);
        editMenu.add(jSeparator2);

        mnIrPara.setMnemonic('i');
        mnIrPara.setText("Ir para");

        mnPrimeiro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_UP, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnPrimeiro.setMnemonic('p');
        mnPrimeiro.setText("Primeiro");
        mnPrimeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnPrimeiroActionPerformed(evt);
            }
        });
        mnIrPara.add(mnPrimeiro);

        mnAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_LEFT, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnAnterior.setMnemonic('a');
        mnAnterior.setText("Anterior");
        mnAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnAnteriorActionPerformed(evt);
            }
        });
        mnIrPara.add(mnAnterior);

        mnProximo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_RIGHT, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnProximo.setMnemonic('p');
        mnProximo.setText("Próximo");
        mnProximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnProximoActionPerformed(evt);
            }
        });
        mnIrPara.add(mnProximo);

        mnUltimo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DOWN, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mnUltimo.setMnemonic('m');
        mnUltimo.setText("Último");
        mnUltimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnUltimoActionPerformed(evt);
            }
        });
        mnIrPara.add(mnUltimo);

        editMenu.add(mnIrPara);

        menuBar.add(editMenu);

        mnJanela.setMnemonic('j');
        mnJanela.setText("Janela");

        jmnFecharAtual.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_MASK));
        jmnFecharAtual.setMnemonic('f');
        jmnFecharAtual.setText("Fechar");
        jmnFecharAtual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnFecharAtualActionPerformed(evt);
            }
        });
        mnJanela.add(jmnFecharAtual);

        jmnProximaJanela.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_PAGE_DOWN, java.awt.event.InputEvent.CTRL_MASK));
        jmnProximaJanela.setText("Próxima");
        jmnProximaJanela.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmnProximaJanelaActionPerformed(evt);
            }
        });
        mnJanela.add(jmnProximaJanela);
        mnJanela.add(jSeparator1);

        menuBar.add(mnJanela);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 876, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mnSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnSairActionPerformed
        System.exit(0);
    }//GEN-LAST:event_mnSairActionPerformed

    private void desktopPaneResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_desktopPaneResized
        atlzFundo();
    }//GEN-LAST:event_desktopPaneResized

    private void MainMDIApplicationOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_MainMDIApplicationOpened
        atlzFundo();
        atlzMenu();
    }//GEN-LAST:event_MainMDIApplicationOpened

    private void mnProdutoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProdutoActionPerformed
        //JIFProduto
    }//GEN-LAST:event_mnProdutoActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void miCategoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCategoraActionPerformed
        CategoriaJif jifCategoria= new CategoriaJif();
        //desktopPane.add(jifCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        //jifCategoria.setBounds(0, 0, 450, 350);
        registrarJanela(jifCategoria);
        //jifCategoria.setVisible(true);
       // setActiveWindow(jifCategoria);
    }//GEN-LAST:event_miCategoraActionPerformed

    private void mnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnNovoActionPerformed
        ((IActionsGui)activeWindow).novo();
    }//GEN-LAST:event_mnNovoActionPerformed

    private void mnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnExcluirActionPerformed
        ((IActionsGui)activeWindow).excluir();
    }//GEN-LAST:event_mnExcluirActionPerformed

    private void editMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editMenuActionPerformed
        mnNovo.setEnabled(activeWindow!=null);
        mnExcluir.setEnabled(activeWindow!=null);
        mnAlterar.setEnabled(activeWindow!=null);
    }//GEN-LAST:event_editMenuActionPerformed

    private void jmnFecharAtualActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnFecharAtualActionPerformed
        if (activeWindow != null){
            //activeWindow.setVisible(false);
            activeWindow.doDefaultCloseAction();
        }
    }//GEN-LAST:event_jmnFecharAtualActionPerformed

    private void mnPrimeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPrimeiroActionPerformed
        if (activeWindow != null){
            ((IActionsGui)activeWindow).firstRecord();
        }
    }//GEN-LAST:event_mnPrimeiroActionPerformed

    private void mnAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAnteriorActionPerformed
        if (activeWindow != null){
            ((IActionsGui)activeWindow).priorRecord();
        }
    }//GEN-LAST:event_mnAnteriorActionPerformed

    private void mnProximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnProximoActionPerformed
        if (activeWindow != null){
            ((IActionsGui)activeWindow).nextRecord();
        }
    }//GEN-LAST:event_mnProximoActionPerformed

    private void mnUltimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUltimoActionPerformed
        if (activeWindow != null){
            ((IActionsGui)activeWindow).lastRecord();
        }
    }//GEN-LAST:event_mnUltimoActionPerformed

    private void mnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnAlterarActionPerformed
        if (activeWindow != null){
            ((IActionsGui)activeWindow).alterar();
        }
    }//GEN-LAST:event_mnAlterarActionPerformed

    private void mnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPesquisarActionPerformed
        if (activeWindow != null){
            ((IActionsGui)activeWindow).pesquisar();
        }
    }//GEN-LAST:event_mnPesquisarActionPerformed

    private void mnFuncionarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFuncionarioActionPerformed
        JIFFuncionario jifFun= new JIFFuncionario();
        //desktopPane.add(jifFun, javax.swing.JLayeredPane.DEFAULT_LAYER);
        //jifCategoria.setBounds(0, 0, 450, 350);
        registrarJanela(jifFun);
        //jifFun.setVisible(true);
        //setActiveWindow(jifFun);
        try {
            jifFun.setMaximum(true);
        } catch (PropertyVetoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_mnFuncionarioActionPerformed

    private void mnPerfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnPerfilActionPerformed
        JIFPerfil jifPerfil= new JIFPerfil();
        //desktopPane.add(jifPerfil, javax.swing.JLayeredPane.DEFAULT_LAYER);
        registrarJanela(jifPerfil);
        //jifPerfil.setVisible(true);
        //setActiveWindow(jifPerfil);
    }//GEN-LAST:event_mnPerfilActionPerformed

    private void mnUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnUsuarioActionPerformed
        JIFUsuario jifUsuario= new JIFUsuario();
        //desktopPane.add(jifUsuario, javax.swing.JLayeredPane.DEFAULT_LAYER);
        registrarJanela(jifUsuario);
        //jifUsuario.setVisible(true);
        //setActiveWindow(jifUsuario);
    }//GEN-LAST:event_mnUsuarioActionPerformed

    private void mnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnListarActionPerformed
        if (activeWindow != null){
            ((IActionsGui)activeWindow).listar();
        }
    }//GEN-LAST:event_mnListarActionPerformed

    private void mnFornecedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mnFornecedorActionPerformed
        JIFFornecedor jifFornecedor = new JIFFornecedor();
        registrarJanela(jifFornecedor);
    }//GEN-LAST:event_mnFornecedorActionPerformed

    private void jmnProximaJanelaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmnProximaJanelaActionPerformed
        activeNextWindow();
    }//GEN-LAST:event_jmnProximaJanelaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainMDIApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMDIApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMDIApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMDIApplication.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                new MainMDIApplication().setVisible(true);
            }
        });
    }
    
    /**
     * Atualiza a imagem de fundo da aplicação
     */
    private void atlzFundo(){
        lblImgShell.setBounds(0, 0, this.getWidth(), this.getHeight());
        if (fundo != null){
            lblImgShell.setIcon(res.stretchImage(fundo, this.getWidth(), this.getHeight()));
            lblImgShell.setVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JMenuItem jmnFecharAtual;
    private javax.swing.JMenuItem jmnProximaJanela;
    private javax.swing.JLabel lblImgShell;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miCategora;
    private javax.swing.JMenuItem mnAlterar;
    private javax.swing.JMenuItem mnAnterior;
    private javax.swing.JMenuItem mnEntrada;
    private javax.swing.JMenuItem mnExcluir;
    private javax.swing.JMenuItem mnFornecedor;
    private javax.swing.JMenuItem mnFuncionario;
    private javax.swing.JMenu mnIrPara;
    private javax.swing.JMenu mnJanela;
    private javax.swing.JMenuItem mnListar;
    private javax.swing.JMenuItem mnLocalE;
    private javax.swing.JMenuItem mnNovo;
    private javax.swing.JMenuItem mnPerfil;
    private javax.swing.JMenuItem mnPesquisar;
    private javax.swing.JMenuItem mnPrimeiro;
    private javax.swing.JMenuItem mnProduto;
    private javax.swing.JMenuItem mnProximo;
    private javax.swing.JMenuItem mnSaida;
    private javax.swing.JMenuItem mnSair;
    private javax.swing.JMenuItem mnUltimo;
    private javax.swing.JMenuItem mnUnidade;
    private javax.swing.JMenuItem mnUsuario;
    // End of variables declaration//GEN-END:variables

    /**
     * Atribui a janela atualmente ativa para que a aplicação possa processar
     * as atualização e chamadas de menu para a janela ativa.
     * @param activeWindow the activeWindow to set
     */
    public static void setActiveWindow(JInternalFrame actvWin) {
        activeWindow = actvWin;
    }
    
    /**
     * Atualiza os menus da aplicação. Deve ser chamado sempre que uma janela
     * for criada, ativada ou destruida
     */
    public void atlzMenu(){
        mnListar.setEnabled(activeWindow!=null);
        mnPesquisar.setEnabled(activeWindow!=null);
        mnPesquisar.setVisible(activeWindow!=null?((IActionsGui)activeWindow).pesquisarExiste():true);
        mnNovo.setEnabled(activeWindow!=null);
        mnExcluir.setEnabled(activeWindow!=null);
        mnAlterar.setEnabled(activeWindow!=null);
        mnIrPara.setEnabled(activeWindow!=null);
        jmnFecharAtual.setEnabled(activeWindow!=null);
        jmnProximaJanela.setEnabled(activeWindow!=null?janelas.size()>1:false);
    }
}
