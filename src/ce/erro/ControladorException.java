/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

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
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public ControladorException(Exception e, String nameClassCall){
        super(e, nameClassCall);
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
