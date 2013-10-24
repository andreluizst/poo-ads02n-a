/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.gui;

import ce.erro.GeralException;
import ce.model.basica.Entrada;
import ce.model.basica.Saida;
import ce.model.fachada.Fachada;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Andre
 */
public class PropSaida extends javax.swing.JDialog {
    private Fachada f;
    private boolean isIns;
    private Resource res;
    private Saida saida;
    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form PropSaida
     */
    public PropSaida(java.awt.Frame parent, boolean modal, Saida obj) {
        super(parent, modal);
        initComponents();

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
        jtxtQtde.setDocument(new TeclasPermitidas("[^0-9|^,]", ","));
        f= Fachada.getInstancia();
        res= Resource.getInstancia();
        try{
            lstProdsDisp.addAll(f.listarEntradasDisponiveis());
        }catch (GeralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        if (obj == null){
            isIns= true;
            this.setTitle("NOVA saída");
            try {
                lblImg.setIcon(res.get("\\images\\Arquivo-Novo.jpg", lblImg.getWidth(), lblImg.getHeight()));
            } catch (GeralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            isIns= false;
            setFields(obj);
            this.setTitle("ALTERAR saída");
            try {
                lblImg.setIcon(res.get("\\images\\Arquivo-Alterar3.jpg", lblImg.getWidth(), lblImg.getHeight()));
            } catch (GeralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    /**
     * Preenche os campos do formulário com os dados do objeto Saida
     * @param obj 
     * Objeto do tipo Saida cujos dados serão exibidos nos campos texto da tela.
     */
    private void setFields(Saida obj){
        jtxtNumero.setText(obj.getCodSaida().toString());
        jftfData.setText(obj.getDataSaida());
        jtxtQtde.setText(obj.getQtde().toString().replaceAll("[.]", ","));
        if (lstProdsDisp.size()>0){
            for (int i=0;i<lstProdsDisp.size();i++){
                if (lstProdsDisp.get(i).getProduto().getCodProd() == obj.getEntrada().getProduto().getCodProd()){
                    jcbxProdsDisp.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    
    /**
     * Preenche o objeto do tipo Saida que receberá os dados constantes nos campos.
     * do formulário
     * @param obj 
     * Objeto do tipo Saida que receberá os dados dos campos da tela.
     */
    private void setSaida(Saida obj){
        obj.setDataSaida(jftfData.getText());
        try{
            obj.setCodSaida(Integer.parseInt(jtxtNumero.getText()));
        }catch(Exception e){
            obj.setCodSaida(0);
        }
        try{
            Double dbQtde= Double.parseDouble(jtxtQtde.getText().replaceAll("[,]", "."));
            obj.setQtde(dbQtde);
        }catch(Exception e){
            obj.setQtde(0.00);
        }
        obj.setEntrada(lstProdsDisp.get(jcbxProdsDisp.getSelectedIndex()));
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    /**
     * Retorna um objeto Saida preenchido.
     * @return 
     */
    public Saida getProperties(){
        return saida;
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

        lstProdsDisp = new LinkedList<Entrada>();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        lblImg = new javax.swing.JLabel();
        jcbxProdsDisp = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtNumero = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        javax.swing.text.MaskFormatter maskData=null;
        try{
            maskData= new javax.swing.text.MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
        }catch(java.text.ParseException e){}
        jftfData = new javax.swing.JFormattedTextField(maskData);
        jLabel7 = new javax.swing.JLabel();
        jtxtQtde = new javax.swing.JTextField();

        lstProdsDisp= org.jdesktop.observablecollections.ObservableCollections.observableList(lstProdsDisp);

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        lblImg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/Arquivo-Alterar3.jpg"))); // NOI18N
        lblImg.setText("lblImg");

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lstProdsDisp, jcbxProdsDisp);
        bindingGroup.addBinding(jComboBoxBinding);

        jLabel1.setText("Produtos disponíveis");

        jLabel2.setText("Número");

        jtxtNumero.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtNumero.setEditable(false);
        jtxtNumero.setEnabled(false);

        jLabel3.setText("Data");

        jLabel7.setText("Quantidade");

        jtxtQtde.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jtxtQtde.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbxProdsDisp, javax.swing.GroupLayout.PREFERRED_SIZE, 369, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jtxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addComponent(jftfData, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jtxtQtde, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSalvar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCancelar)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnCancelar, btnSalvar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jtxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jftfData, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jcbxProdsDisp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jtxtQtde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblImg, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnCancelar)
                            .addComponent(btnSalvar))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getRootPane().setDefaultButton(btnSalvar);

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        saida= new Saida();
        setSaida(saida);
        try{
            if (isIns){
                f.incluir(saida);
            }else{
                f.alterar(saida);
            }
            doClose(RET_OK);
        }catch(GeralException ex){
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btnSalvarActionPerformed
    
    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_btnCancelarActionPerformed

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        doClose(RET_CANCEL);
    }//GEN-LAST:event_closeDialog
    
    private void doClose(int retStatus) {
        returnStatus = retStatus;
        setVisible(false);
        dispose();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PropSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PropSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PropSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PropSaida.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                PropSaida dialog = new PropSaida(new javax.swing.JFrame(), true, null);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnSalvar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JComboBox jcbxProdsDisp;
    private javax.swing.JFormattedTextField jftfData;
    private javax.swing.JTextField jtxtNumero;
    private javax.swing.JTextField jtxtQtde;
    private javax.swing.JLabel lblImg;
    private java.util.List<Entrada> lstProdsDisp;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;
}
