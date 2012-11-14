/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

import ce.util.LogGenerator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andreluiz
 */
public class GeralException extends Exception {
    private LogGenerator log=null;//LogGenerator.getInstancia();
    /**
     * Esta propriedade contém a classe que lançou a exceção. Se a classe B
     * capturar a exceção da classe A e por sua vez relançar para uma outra
     * class, por exemplo C, que ao tratar esta exceção poderá verificar que
     * esta propriedade terá o seguinte caminho: A.B
     *
     */
    private String pathClassCall= "";
    
    public GeralException(){
        super();
    }
    
    /**
     * 
     * @param e 
     */
    public GeralException(Exception e){
        super(e);
    }
    
    public GeralException(Exception e, Logger logger){
        super(e);
        log= new LogGenerator(logger);
        if (pathClassCall.compareTo("")==0){
            log.log(Level.WARNING, getMessage());
        }else{
            log.log(Level.WARNING, pathClassCall +": " + getMessage());
        }
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public GeralException(Exception e, String nameClassCall){
        super(e);
        if (pathClassCall.compareTo("") == 0){
            pathClassCall= nameClassCall;
        }else{
            pathClassCall= nameClassCall + "." + pathClassCall;
        }
    }
    
    public GeralException(String s, Logger logger){
        super(s);
        log= new LogGenerator(logger);
        String msg= getStackTrace().toString() + "\n" + getLocalizedMessage();
        if (pathClassCall.compareTo("")==0){
            log.log(Level.WARNING, getMessage() + " - "+ msg);
        }else{
            log.log(Level.WARNING, pathClassCall +": " + getMessage() + " - "+ msg);
        }
    }
    
    /**
     * 
     * @param s 
     */
    public GeralException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public GeralException(String s, String nameClassCall){
        super(s);
        if (pathClassCall.compareTo("") == 0){
            pathClassCall= nameClassCall;
        }else{
            pathClassCall= nameClassCall + "." + pathClassCall;
        }
    }

    public GeralException(Throwable t){
        super(t);
    }

    /**
     * @return the pathClassCall
     */
    public String getPathClassCall() {
        return pathClassCall;
    }

    /**
     * @param pathClassCall the pathClassCall to set
     */
    public void setPathClassCall(String pathClassCall) {
        this.pathClassCall = pathClassCall;
    }
}
