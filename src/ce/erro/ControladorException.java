/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

import java.util.logging.Logger;

/**
 *
 * @author Andre
 */
public class ControladorException extends GeralException {
    
    public ControladorException(){
        super();
    }
    
    /**
     * 
     * @param e 
     */
    public ControladorException(Exception e){
        super(e);
    }
    
    public ControladorException(Exception e, Logger logger){
        super(e, logger);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public ControladorException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }
    
    public ControladorException(String s, Logger logger){
        super(s, logger);
    }
    
    /**
     * 
     * @param s 
     */
    public ControladorException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public ControladorException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public ControladorException(Throwable t){
        super(t);
    }
    
}
