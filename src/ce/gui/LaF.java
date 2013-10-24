/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.gui;

import ce.erro.GeralException;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author andreluiz
 */
public class LaF {
    public static final String LAF_CROSS = "cross";
    public static final String LAF_METAL = "javax.swing.plaf.metal.MetalLookAndFeel";
    public static final String LAF_NIMBUS = "javax.swing.plaf.nimbus.NimbusLookAndFeel";
    public static final String LAF_MOTIF = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
    public static final String LAF_WINDOWS = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    //public static final String LAF_MAC = "javax.swing.plaf.mac.MacLookAndFeel";
    public static final String LAF_WINDOWSCLASSICO = "com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel";
    
    public static void setNativeLookAndFeel() throws GeralException {
        try{
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e){
            throw new GeralException(e.getMessage(),
                    LaF.class.getName()+".setNativeLookAndFeel()");
        }
    }
    
    public static void setCrossLookAndFeel() throws GeralException {
        try{
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        }
        catch (ClassNotFoundException | InstantiationException | 
                IllegalAccessException | UnsupportedLookAndFeelException e){
            throw new GeralException(e.getMessage(),
                    LaF.class.getName()+".setNativeLookAndFeel()");
        }
    }
    
    public static void setLookAndFeel(String estilo) throws GeralException{
        try{
            if (estilo.equals("cross")){
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            }else{
                for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()){
                    if (estilo.equals(info.getClassName())){
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        //JOptionPane.showMessageDialog(null, info.getClassName() + " encontrado!");
                        break;
                    }
                }
            }
        }
        catch(ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException e){
            throw new GeralException(e.getMessage(),
                    LaF.class.getName()+".SetLookAndFeel()");
        }
    }
    
    public static void showLookAndFeel() throws GeralException{
            String lookAndFeel = "";
            for (javax.swing.UIManager.LookAndFeelInfo info: javax.swing.UIManager.getInstalledLookAndFeels()){
                lookAndFeel+= info.getClassName() + "\n";
            }
            JOptionPane.showMessageDialog(null, "Look and Feel encontrados: \n" + lookAndFeel);
    }
}
