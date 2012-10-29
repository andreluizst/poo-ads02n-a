/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class ControladorValidarException extends ControladorException{
    /**
     * Construtor padrão
     */
    public ControladorValidarException(){
        super();
    }
    
    /**
     * 
     * @param e 
     */
    public ControladorValidarException(Exception e){
        super(e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public ControladorValidarException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }
    
    /**
     * 
     * @param s 
     */
    public ControladorValidarException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public ControladorValidarException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public ControladorValidarException(Throwable t){
        super(t);
    }
}
