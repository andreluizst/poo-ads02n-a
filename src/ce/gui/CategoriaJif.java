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
import javax.swing.JOptionPane;

/**
 *
 * @author andreluiz
 */
public class CategoriaJif extends javax.swing.JInternalFrame implements IActionsGui{
    private Fachada f;
    private String activationName;
    /**
     * Creates new form CategoriaJif
     */
    public CategoriaJif() {
        initComponents();
        f= Fachada.getInstancia();
        activationName= "Categoria";
    }
    
    private void selectCategoria(Categoria c){
        for(int i=0;i<lstCategorias.size();i++){
            if (c.getCodCateg() == lstCategorias.get(i).getCodCateg()){
                jTable1.setRowSelectionInterval(i, i);
                break;
            }
        }
    }

    @Override
    public void novo(){
        Categoria c;
        PropCategoria propCateg= new PropCategoria(null, true, null); 
        propCateg.setLocationRelativeTo(null);
        propCateg.setVisible(true);
        c= propCateg.getProperties();
        //propFun.getReturnStatus();
        if (c != null){
            lstCategorias.clear();
            try {
                lstCategorias.addAll(f.listarCategoria());
                selectCategoria(c);
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
        c= lstCategorias.get(jTable1.getSelectedRow());
        try {
            f.excluir(c);
            if (jTable1.getRowCount() > 0){
                lstCategorias.clear();
                lstCategorias.addAll(f.listarCategoria());
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
        Categoria c;
        c= lstCategorias.get(jTable1.getSelectedRow());
        PropCategoria propCateg= new PropCategoria(null, true, c); 
        propCateg.setLocationRelativeTo(null);
        propCateg.setVisible(true);
        c= propCateg.getProperties();
        if (c != null){
            try {
                f.alterar(c);
                lstCategorias.clear();
                lstCategorias.addAll(f.listarCategoria());
                selectCategoria(c);
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
        if (jTable1.getRowCount() > 0){
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
        if (jTable1.getRowCount() > 0){
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
        lstCategorias.clear();
        try {
            lstCategorias.addAll(f.listarCategoria());
        } catch (GeralException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
        priorRecord();
    }
    
    @Override
    public String getActivationName(){
        return activationName;
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

        lstCategorias = new LinkedList<Categoria>();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnNovo = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnAlterar = new javax.swing.JButton();
        btnListar = new javax.swing.JButton();

        lstCategorias= org.jdesktop.observablecollections.ObservableCollections.observableList(lstCategorias);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Categoria");
        setNormalBounds(new java.awt.Rectangle(0, 0, 570, 400));
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                CategoriaJifActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
                CategoriaJifClosing(evt);
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, lstCategorias, jTable1, "");
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${codCateg}"));
        columnBinding.setColumnName("Cod Categ");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${descricao}"));
        columnBinding.setColumnName("Descricao");
        columnBinding.setColumnClass(String.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);

        btnNovo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnNovo.jpg"))); // NOI18N
        btnNovo.setText("Novo");
        btnNovo.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnNovoDisable2.png"))); // NOI18N
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNovoActionPerformed(evt);
            }
        });

        btnExcluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnApagar.jpg"))); // NOI18N
        btnExcluir.setText("Excluir");
        btnExcluir.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnApagarDisable2.png"))); // NOI18N

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), btnExcluir, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnAlterar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnAlterar.jpg"))); // NOI18N
        btnAlterar.setText("Alterar");
        btnAlterar.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnAlterarDisable.png"))); // NOI18N

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, jTable1, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), btnAlterar, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        btnAlterar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAlterarActionPerformed(evt);
            }
        });

        btnListar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnAtualizarHot.png"))); // NOI18N
        btnListar.setText("Listar/Atualizar");
        btnListar.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/ce/gui/images/btnAtualizarDisable.png"))); // NOI18N
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnNovo)
                        .addGap(42, 42, 42)
                        .addComponent(btnExcluir)
                        .addGap(44, 44, 44)
                        .addComponent(btnAlterar)
                        .addGap(52, 52, 52)
                        .addComponent(btnListar)
                        .addGap(0, 26, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNovo)
                    .addComponent(btnExcluir)
                    .addComponent(btnAlterar)
                    .addComponent(btnListar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        listar();
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnNovoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNovoActionPerformed
        novo();
    }//GEN-LAST:event_btnNovoActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        excluir();
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnAlterarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAlterarActionPerformed
        alterar();
    }//GEN-LAST:event_btnAlterarActionPerformed

    private void CategoriaJifActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_CategoriaJifActivated
        Main.atlzShellMenu(this);
    }//GEN-LAST:event_CategoriaJifActivated

    private void CategoriaJifClosing(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_CategoriaJifClosing
        Main.atlzShellMenu(null);
        //Main.desregistrarJanela(this);
        dispose();
    }//GEN-LAST:event_CategoriaJifClosing

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAlterar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnListar;
    private javax.swing.JButton btnNovo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private java.util.List<Categoria> lstCategorias;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}