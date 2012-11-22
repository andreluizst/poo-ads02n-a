/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.gui;

import ce.erro.GeralException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Andre
 */
public class MainMDIApplication extends javax.swing.JFrame {
    private Resource res;
    private ImageIcon fundo;
    private JIFCategoria jifCategoria= new JIFCategoria();
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
        miCategora = new javax.swing.JMenuItem();
        openMenuItem = new javax.swing.JMenuItem();
        saveMenuItem = new javax.swing.JMenuItem();
        saveAsMenuItem = new javax.swing.JMenuItem();
        mnSair = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        editMenu = new javax.swing.JMenu();
        cutMenuItem = new javax.swing.JMenuItem();
        copyMenuItem = new javax.swing.JMenuItem();
        pasteMenuItem = new javax.swing.JMenuItem();
        deleteMenuItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        contentMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

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
            public void componentShown(java.awt.event.ComponentEvent evt) {
                desktopPaneShow(evt);
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

        miCategora.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        miCategora.setMnemonic('c');
        miCategora.setText("Categoria");
        miCategora.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCategoraActionPerformed(evt);
            }
        });
        jMenu1.add(miCategora);

        fileMenu.add(jMenu1);

        openMenuItem.setMnemonic('o');
        openMenuItem.setText("JInternalFrame");
        openMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openMenuItemActionPerformed(evt);
            }
        });
        fileMenu.add(openMenuItem);

        saveMenuItem.setMnemonic('s');
        saveMenuItem.setText("Salvar");
        fileMenu.add(saveMenuItem);

        saveAsMenuItem.setMnemonic('a');
        saveAsMenuItem.setText("Salvar como...");
        fileMenu.add(saveAsMenuItem);

        mnSair.setMnemonic('x');
        mnSair.setText("Sair");
        mnSair.setName("mnSair"); // NOI18N
        mnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSairActionPerformed(evt);
            }
        });
        fileMenu.add(mnSair);
        mnSair.getAccessibleContext().setAccessibleDescription("");

        jMenuItem1.setText("jMenuItem1");
        fileMenu.add(jMenuItem1);

        menuBar.add(fileMenu);

        editMenu.setMnemonic('e');
        editMenu.setText("Editar");

        cutMenuItem.setMnemonic('t');
        cutMenuItem.setText("Cut");
        editMenu.add(cutMenuItem);

        copyMenuItem.setMnemonic('y');
        copyMenuItem.setText("Copy");
        editMenu.add(copyMenuItem);

        pasteMenuItem.setMnemonic('p');
        pasteMenuItem.setText("Paste");
        editMenu.add(pasteMenuItem);

        deleteMenuItem.setMnemonic('d');
        deleteMenuItem.setText("Delete");
        editMenu.add(deleteMenuItem);

        menuBar.add(editMenu);

        helpMenu.setMnemonic('h');
        helpMenu.setText("Help");

        contentMenuItem.setMnemonic('c');
        contentMenuItem.setText("Contents");
        helpMenu.add(contentMenuItem);

        aboutMenuItem.setMnemonic('a');
        aboutMenuItem.setText("About");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

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

    private void desktopPaneShow(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_desktopPaneShow
        //atlzFundo();
    }//GEN-LAST:event_desktopPaneShow

    private void MainMDIApplicationOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_MainMDIApplicationOpened
        atlzFundo();
    }//GEN-LAST:event_MainMDIApplicationOpened

    private void openMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openMenuItemActionPerformed
        
    }//GEN-LAST:event_openMenuItemActionPerformed

    private void jMenu1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jMenu1ActionPerformed

    private void miCategoraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCategoraActionPerformed
        desktopPane.add(jifCategoria, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jifCategoria.setBounds(0, 0, 450, 350);
        jifCategoria.setVisible(true);
    }//GEN-LAST:event_miCategoraActionPerformed

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

            public void run() {
                new MainMDIApplication().setVisible(true);
            }
        });
    }
    
    private void atlzFundo(){
        lblImgShell.setBounds(0, 0, this.getWidth(), this.getHeight());
        if (fundo != null){
            lblImgShell.setIcon(res.stretchImage(fundo, this.getWidth(), this.getHeight()));
            lblImgShell.setVisible(true);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem contentMenuItem;
    private javax.swing.JMenuItem copyMenuItem;
    private javax.swing.JMenuItem cutMenuItem;
    private javax.swing.JMenuItem deleteMenuItem;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JMenu editMenu;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel lblImgShell;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miCategora;
    private javax.swing.JMenuItem mnSair;
    private javax.swing.JMenuItem openMenuItem;
    private javax.swing.JMenuItem pasteMenuItem;
    private javax.swing.JMenuItem saveAsMenuItem;
    private javax.swing.JMenuItem saveMenuItem;
    // End of variables declaration//GEN-END:variables
}
