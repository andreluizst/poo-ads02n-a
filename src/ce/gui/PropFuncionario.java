/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.gui;

import ce.erro.GeralException;
import ce.model.basica.Funcionario;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Andre Luiz
 */
public class PropFuncionario extends javax.swing.JDialog {
    private Funcionario func;
    private Resource res;

    /**
     * A return status code - returned if Cancel button has been pressed
     */
    public static final int RET_CANCEL = 0;
    /**
     * A return status code - returned if OK button has been pressed
     */
    public static final int RET_OK = 1;

    /**
     * Creates new form PropFuncionario
     */
    public PropFuncionario(java.awt.Frame parent, boolean modal, Funcionario funcionario) {
        super(parent, modal);
        initComponents();

        // Close the dialog when Esc is pressed
        String cancelName = "cancel";
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();
        actionMap.put(cancelName, new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                doClose(RET_CANCEL);
            }
        });
        res= Resource.getInstancia();
        if (funcionario == null ){
            this.setTitle("INCLUIR funcionário");
            try {
                lblImg.setIcon(res.get("\\images\\Arquivo-Novo.jpg", lblImg.getWidth(), lblImg.getHeight()));
            } catch (GeralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }else{
            this.setTitle("ALTERAR funcionário");
            setFields(funcionario);
            try {
                lblImg.setIcon(res.get("\\images\\Arquivo-Alterar3.jpg", lblImg.getWidth(), lblImg.getHeight()));
            } catch (GeralException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage());
            }
        }
    }
    
    private void setFields(Funcionario fun){
        jftfCpf.setText(fun.getCpf());
        jtxtNome.setText(fun.getNome());
        jftfDataNasc.setText(fun.getDtNasc());
        jtxtLogradouro.setText(fun.getLogradouro());
        jtxtNum.setText(((Integer)fun.getNum()).toString());
        jtxtComp.setText(fun.getComp());
        jtxtBairro.setText(fun.getBairro());
        jtxtMunicipio.setText(fun.getMunicipio());
        jtxtUf.setText(fun.getUf());
        jftfCep.setText(fun.getCep());
        jftfFone.setText(fun.getFone());
        jtxtEmail.setText(fun.getEmail());
    }

    /**
     * @return the return status of this dialog - one of RET_OK or RET_CANCEL
     */
    public int getReturnStatus() {
        return returnStatus;
    }
    
    public Funcionario getProperties(){
        return func;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSalvar = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jtxtNome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jtxtLogradouro = new javax.swing.JTextField();
        javax.swing.text.MaskFormatter maskCpf= null;
        try{
            maskCpf = new javax.swing.text.MaskFormatter("###########");
            maskCpf.setPlaceholderCharacter('_');
        }
        catch(java.text.ParseException e){

        }
        jftfCpf = new javax.swing.JFormattedTextField(maskCpf);
        jLabel4 = new javax.swing.JLabel();
        jtxtNum = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jtxtComp = new javax.swing.JTextField();
        lblImg = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jtxtBairro = new javax.swing.JTextField();
        jtxtMunicipio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jtxtUf = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        javax.swing.text.MaskFormatter maskCep= null;
        try{
            maskCep = new javax.swing.text.MaskFormatter("##.###-###");
            maskCep.setPlaceholderCharacter('_');
        }
        catch(java.text.ParseException e){

        }
        jftfCep = new javax.swing.JFormattedTextField(maskCep);
        jLabel10 = new javax.swing.JLabel();
        javax.swing.text.MaskFormatter maskFone= null;
        try{
            maskFone = new javax.swing.text.MaskFormatter("(##)####-####");
            maskFone.setPlaceholderCharacter('_');
        }
        catch(java.text.ParseException e){

        }
        jftfFone = new javax.swing.JFormattedTextField(maskFone);
        jLabel11 = new javax.swing.JLabel();
        jtxtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        javax.swing.text.MaskFormatter maskData= null;
        try{
            maskData = new javax.swing.text.MaskFormatter("##/##/####");
            maskData.setPlaceholderCharacter('_');
        }
        catch(java.text.ParseException e){

        }
        jftfDataNasc = new javax.swing.JFormattedTextField(maskData);

        setBounds(new java.awt.Rectangle(0, 0, 614, 320));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });
        getContentPane().setLayout(null);

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalvarActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalvar);
        btnSalvar.setBounds(464, 283, 67, 23);
        getRootPane().setDefaultButton(btnSalvar);

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        getContentPane().add(cancelButton);
        cancelButton.setBounds(537, 283, 67, 23);

        jLabel1.setText("CPF");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(18, 21, 55, 14);

        jLabel2.setText("Nome");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(18, 72, 27, 14);
        getContentPane().add(jtxtNome);
        jtxtNome.setBounds(18, 91, 344, 20);

        jLabel3.setText("Logradouro:");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(18, 122, 59, 14);
        getContentPane().add(jtxtLogradouro);
        jtxtLogradouro.setBounds(18, 142, 344, 20);

        jftfCpf.setHorizontalAlignment(javax.swing.JFormattedTextField.RIGHT);
        getContentPane().add(jftfCpf);
        jftfCpf.setBounds(18, 41, 121, 20);

        jLabel4.setText("Número");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(380, 122, 37, 14);
        getContentPane().add(jtxtNum);
        jtxtNum.setBounds(380, 142, 45, 20);

        jLabel5.setText("Complemento");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(443, 122, 65, 14);
        getContentPane().add(jtxtComp);
        jtxtComp.setBounds(443, 142, 106, 20);

        lblImg.setText("lblImg");
        getContentPane().add(lblImg);
        lblImg.setBounds(484, 0, 120, 120);

        jLabel6.setText("Bairro");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(18, 173, 28, 14);
        getContentPane().add(jtxtBairro);
        jtxtBairro.setBounds(18, 193, 190, 20);
        getContentPane().add(jtxtMunicipio);
        jtxtMunicipio.setBounds(226, 193, 208, 20);

        jLabel7.setText("Município");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(226, 173, 43, 14);

        jLabel8.setText("UF");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(452, 173, 13, 14);
        getContentPane().add(jtxtUf);
        jtxtUf.setBounds(452, 193, 32, 20);

        jLabel9.setText("CEP");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(502, 173, 19, 14);
        getContentPane().add(jftfCep);
        jftfCep.setBounds(502, 193, 88, 20);

        jLabel10.setText("Fone");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(18, 224, 24, 14);
        getContentPane().add(jftfFone);
        jftfFone.setBounds(18, 244, 125, 20);

        jLabel11.setText("E-mail");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(161, 224, 28, 14);
        getContentPane().add(jtxtEmail);
        jtxtEmail.setBounds(161, 244, 429, 20);

        jLabel12.setText("Data de nascimento");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(174, 21, 95, 14);
        getContentPane().add(jftfDataNasc);
        jftfDataNasc.setBounds(174, 41, 121, 20);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalvarActionPerformed
        func= new Funcionario();
        func.setCpf(jftfCpf.getText());
        func.setNome(jtxtNome.getText());
        func.setDtNasc(jftfDataNasc.getText());
        func.setLogradouro(jtxtLogradouro.getText());
        try{
            func.setNum(Integer.parseInt(jtxtNum.getText()));
        }catch (Exception e){
            func.setNum(0);
        }
        func.setBairro(jtxtBairro.getText());
        func.setMunicipio(jtxtMunicipio.getText());
        func.setUf(jtxtUf.getText());
        func.setCep(jftfCep.getText());
        func.setFone(jftfFone.getText());
        func.setEmail(jtxtEmail.getText());
        doClose(RET_OK);
    }//GEN-LAST:event_btnSalvarActionPerformed
    
    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        doClose(RET_CANCEL);
    }//GEN-LAST:event_cancelButtonActionPerformed

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
            java.util.logging.Logger.getLogger(PropFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PropFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PropFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PropFuncionario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PropFuncionario dialog = new PropFuncionario(new javax.swing.JFrame(), true, null);
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
    private javax.swing.JButton btnSalvar;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JFormattedTextField jftfCep;
    private javax.swing.JFormattedTextField jftfCpf;
    private javax.swing.JFormattedTextField jftfDataNasc;
    private javax.swing.JFormattedTextField jftfFone;
    private javax.swing.JTextField jtxtBairro;
    private javax.swing.JTextField jtxtComp;
    private javax.swing.JTextField jtxtEmail;
    private javax.swing.JTextField jtxtLogradouro;
    private javax.swing.JTextField jtxtMunicipio;
    private javax.swing.JTextField jtxtNome;
    private javax.swing.JTextField jtxtNum;
    private javax.swing.JTextField jtxtUf;
    private javax.swing.JLabel lblImg;
    // End of variables declaration//GEN-END:variables
    private int returnStatus = RET_CANCEL;
}
