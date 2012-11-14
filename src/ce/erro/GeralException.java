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
    private LogGenerator log= LogGenerator.getInstancia();
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
        if (pathClassCall.compareTo("")==0){
            log.log("root", GeralException.class.getName(), getMessage());
        }else{
            log.log("root",  pathClassCall, getMessage());
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
        log.log("root",  pathClassCall, getMessage());
    }
    
    public GeralException(String s, Logger logger){
        super(s);
        if (pathClassCall.compareTo("")==0){
            log.log("root", GeralException.class.getName(), getMessage());
        }else{
            log.log("root",  pathClassCall, getMessage());
        }
    }
    
    /**
     * 
     * @param s 
     */
    public GeralException(String s){
        super(s);
        if (pathClassCall.compareTo("") == 0){
            log.log("root",  GeralException.class.getName(), getMessage());
        }else{
            log.log("root",  pathClassCall, getMessage());
        }
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
        log.log("root",  pathClassCall, getMessage());
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
