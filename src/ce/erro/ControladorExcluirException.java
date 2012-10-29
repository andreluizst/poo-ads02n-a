/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class ControladorExcluirException extends ControladorException {
    /**
     * Construtor padrão
     */
    public ControladorExcluirException(){
        super();
    }
    
    /**
     * 
     * @param e 
     */
    public ControladorExcluirException(Exception e){
        super(e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public ControladorExcluirException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }
    
    /**
     * 
     * @param s 
     */
    public ControladorExcluirException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public ControladorExcluirException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public ControladorExcluirException(Throwable t){
        super(t);
    }
}
