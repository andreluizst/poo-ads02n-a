/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.gui;

import ce.Main;
import ce.erro.GeralException;
import ce.model.basica.Categoria;
import ce.model.fachada.Fachada;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Andre
 */
public class JIFCategoria extends javax.swing.JInternalFrame implements IActionsGui{
    private Fachada f;
    private List<Categoria> categorias;
    private Integer grdLineCount;
    public JFrame frmShell;
    /**
     * Creates new form jifCategoria
     */
    public JIFCategoria() {
        f= Fachada.getInstancia();
        grdLineCount=0;
        initComponents();
        /*DefaultTableModel tbModel= (DefaultTableModel)jTable1.getModel();
        //((DefaultTableModel)jTable1.getModel()).addRow(null);
        tbModel.removeRow(0);
        for (int i=0;i<categorias.size();i++){
            tbModel.addRow(new Object[]{categorias.get(i).getCodCateg(),
                categorias.get(i).getDescricao()});
        }*/
        try {
            categorias= f.listarCategoria();
            lstCategoria.addAll(f.listarCategoria());
        } catch (GeralException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        //DefaultTableCellRenderer centro= new DefaultTableCellRenderer();
        //centro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        DefaultTableCellRenderer dtcrEsquerda= new DefaultTableCellRenderer();
        dtcrEsquerda.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jTable1.getColumnModel().getColumn(0).setCellRenderer(dtcrEsquerda);
    }
    
    @Override
    public void novo(){
        Categoria categ;//= new Categoria();
        PropCategoria propCateg= new PropCategoria(null, true, null); 
        propCateg.setLocationRelativeTo(null);
        propCateg.setVisible(true);
        categ= propCateg.getProperties();
        //propCateg.getReturnStatus();
        if (categ != null){
            lstCategoria.clear();
            try {
                lstCategoria.addAll(f.listarCategoria());
                selectCateg(categ);
            } catch (GeralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    @Override
    public void excluir(){
        Categoria c;
        int row= jTable1.getSelectedRow();
        if ((row == jTable1.getRowCount()) || (row > 0)){
            row--;
        }else{
            row=0;
        }
        c= lstCategoria.get(jTable1.getSelectedRow());
        try {
            f.excluir(c);
            if (jTable1.getRowCount() > 0){
                lstCategoria.clear();
                lstCategoria.addAll(f.listarCategoria());
                /*if (jtxtDescricao.getText().compareTo("") == 0){
                    lstCategoria.addAll(f.listarCategoria());
                }else{
                //lstCategoria.addAll(f.pesquisarCategoria(jtxtDescricao.getText()));
                }*/
            }
            if (jTable1.getRowCount() > 0){
                jTable1.setRowSelectionInterval(row, row);
            }
        } catch (GeralException ex) {
            System.out.println(ex.getMessage());
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
    @Override
    public void alterar(){
        Categoria categ;//= new Categoria();
        //categ.setCodCateg(Integer.parseInt(jtxtCod.getText()));
        //categ.setDescricao(jtxtDescricao.getText());
        categ= lstCategoria.get(jTable1.getSelectedRow());
        PropCategoria propCateg= new PropCategoria(null, true, categ); 
        propCateg.setLocationRelativeTo(null);
        propCateg.setVisible(true);
        categ= propCateg.getProperties();
        //propCateg.getReturnStatus();
        if (categ != null){
            lstCategoria.clear();
            try {
                f.alterar(categ);
                lstCategoria.addAll(f.listarCategoria());
                //f.alterar(categ);
                selectCateg(categ);
            } catch (GeralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    @Override
    public void pesquisar(){
        listar();
    }
    
    @Override
    public void firstRecord(){
        if (lstCategoria.size() > 0){
            jTable1.setRowSelectionInterval(0, 0);
        }
    }
    
    @Override
    public void priorRecord(){
        int line= jTable1.getSelectedRow();
        if (line > 0){
            jTable1.setRowSelectionInterval(line-1, line-1);
        }
    }
    
    @Override
    public void nextRecord(){
        if (lstCategoria.size() > 0){
            if (jTable1.getSelectedRow() < jTable1.getRowCount()-1){
                jTable1.setRowSelectionInterval(jTable1.getSelectedRow()+1, jTable1.getSelectedRow()+1);
            }
        }
    }
    
    @Override
    public void lastRecord(){
        if (jTable1.getRowCount() > 0){
            jTable1.setRowSelectionInterval(jTable1.getRowCount()-1, jTable1.getRowCount()-1);
        }
    }
    
    @Override
    public void listar(){
        lstCategoria.clear();
        try {
            lstCategoria.addAll(f.listarCategoria());
        } catch (GeralException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        lstCategoria = new LinkedList<Categoria>();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jpupmnNovo = new javax.swing.JMenuItem();
        jpupmnAlterar = new javax.swing.JMenuItem();
        jpupmnExcluir = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();

        lstCategoria = org.jdesktop.observablecollections.ObservableCollections.observableList(lstCategoria);

        jpupmnNovo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_INSERT, java.awt.event.InputEvent.CTRL_MASK));
        jpupmnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnNovo.jpg"))); // NOI18N
        jpupmnNovo.setMnemonic('n');
        jpupmnNovo.setText("Novo");
        jpupmnNovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jpupmnNovoMouseClicked(evt);
            }
        });
        jPopupMenu1.add(jpupmnNovo);

        jpupmnAlterar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jpupmnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnAlterar.jpg"))); // NOI18N
        jpupmnAlterar.setText("jMenuItem1");
        jPopupMenu1.add(jpupmnAlterar);

        jpupmnExcluir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, java.awt.event.InputEvent.CTRL_MASK));
        jpupmnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnApagar.jpg"))); // NOI18N
        jpupmnExcluir.setText("jMenuItem1");
        jpupmnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jpupmnExcluirActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jpupmnExcluir);

        setClosable(true);
        setIconifiable(true);
        setTitle("Categorias de produtos");
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                jifCategoriaActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
                jifCategoriaClosed(evt);
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                jifCategoriaClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
                JIFCategoriaIconified(evt);
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        jTable1.setComponentPopupMenu(jPopupMenu1);
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lstCategoria, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codCateg}"));
        columnBinding.setColumnName("Cod Categ");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descricao}"));
        columnBinding.setColumnName("Descricao");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnNovo.jpg"))); // NOI18N
        btnNovo.setMnemonic('n');
        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnApagar.jpg"))); // NOI18N
        btnExcluir.setMnemonic('x');
        btnExcluir.setText("Excluir");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement!=null}"), btnExcluir, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnAlterar.jpg"))); // NOI18N
        btnAlterar.setMnemonic('t');
        btnAlterar.setText("Alterar");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement!=null}"), btnAlterar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(29, 29, 29)
                        .addComponent(btnExcluir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(btnAlterar)
                        .addGap(31, 31, 31)
                        .addComponent(btnAtualizar)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnAtualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JIFCategoriaIconified(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_JIFCategoriaIconified
        this.setVisible(false);
        this.setVisible(true);
    }//GEN-LAST:event_JIFCategoriaIconified

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        novo();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void jTableClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableClicked
        alterar();
    }//GEN-LAST:event_jTableClicked

    private void jpupmnNovoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jpupmnNovoMouseClicked
        novo();
    }//GEN-LAST:event_jpupmnNovoMouseClicked

    private void jpupmnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jpupmnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_jpupmnExcluirActionPerformed

    private void jifCategoriaActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jifCategoriaActivated
        //MainMDIApplication.setActiveWindow(this);
        Main.atlzShellMenu(this);
    }//GEN-LAST:event_jifCategoriaActivated

    private void jifCategoriaClosed(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jifCategoriaClosed
        //MainMDIApplication.setActiveWindow(null);
        //dispose();
    }//GEN-LAST:event_jifCategoriaClosed

    private void jifCategoriaClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_jifCategoriaClosing
        //MainMDIApplication.setActiveWindow(null);
        Main.atlzShellMenu(null);
        dispose();
    }//GEN-LAST:event_jifCategoriaClosing

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
        listar();
    }//GEN-LAST:event_btnAtualizarActionPerformed
    
    private void selectCateg(Categoria c){
        for(int i=0;i<lstCategoria.size();i++){
            if (c.getCodCateg() == lstCategoria.get(i).getCodCateg()){
                jTable1.setRowSelectionInterval(i, i);
                break;
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnNovo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JMenuItem jpupmnAlterar;
    private javax.swing.JMenuItem jpupmnExcluir;
    private javax.swing.JMenuItem jpupmnNovo;
    private java.util.List<Categoria> lstCategoria;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
