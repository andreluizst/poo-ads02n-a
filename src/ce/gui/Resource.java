/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.gui;

import ce.erro.GeralException;
import java.awt.Image;
//import ce.model.fachada.Fachada;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Andre
 */
public class Resource {
    private static Resource instancia;
    //private Fachada f;
    private ResourceBundle rb;
    
    private Resource(){
        //f= Fachada.getInstancia();
        rb= ResourceBundle.getBundle("ce.erro.Erro");
    }
    
    public static Resource getInstancia(){
        if (instancia == null){
            instancia= new Resource();
        }
        return instancia;
    }
    
    public BufferedImage getToBuffer(String fileName)throws GeralException{
        try {
            BufferedImage imagem= ImageIO.read(getClass().getResource(fileName));
            return imagem;
        } 
        catch (IOException | IllegalArgumentException ex) {
            throw new GeralException("Resource", 
                    rb.getString("ResourceError")+ ": "+ fileName,
                    Resource.class.getName()+".get()");
        }
    }
    
    public ImageIcon stretchImage(ImageIcon image, Integer width, Integer height){
        return new ImageIcon(image.getImage().getScaledInstance(
                width, height, Image.SCALE_DEFAULT));
    }
    
    public ImageIcon get(String fileName)throws GeralException{
        try {
            BufferedImage imagem= ImageIO.read(getClass().getResource(fileName));
            return new ImageIcon(imagem);
        } 
        catch (IOException | IllegalArgumentException ex) {
            throw new GeralException("Resource", 
                    rb.getString("ResourceError")+ ": "+ fileName,
                    Resource.class.getName()+".get()");
        }
    }
    
    public ImageIcon get(String fileName, Integer width, Integer height) 
            throws GeralException{
        try {
            BufferedImage imagem= ImageIO.read(getClass().getResource(fileName));
            return new ImageIcon(imagem.getScaledInstance(width, height, Image.SCALE_DEFAULT));
        } 
        catch (IOException | IllegalArgumentException ex) {
            throw new GeralException("Resource", 
                    rb.getString("ResourceError")+ ": "+ fileName,
                    Resource.class.getName()+".get()");
            //Logger.getLogger(Resource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
