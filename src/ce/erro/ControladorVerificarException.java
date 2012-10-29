/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class ControladorVerificarException extends ControladorException {
    /**
     * Construtor padrão
     */
    public ControladorVerificarException(){
        super();
    }
    
    /**
     * 
     * @param e 
     */
    public ControladorVerificarException(Exception e){
        super(e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public ControladorVerificarException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }
    
    /**
     * 
     * @param s 
     */
    public ControladorVerificarException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public ControladorVerificarException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public ControladorVerificarException(Throwable t){
        super(t);
    }
}
