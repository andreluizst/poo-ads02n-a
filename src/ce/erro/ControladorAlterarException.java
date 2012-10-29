/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class ControladorAlterarException extends ControladorException {
    /**
     * Construtor padrão
     */
    public ControladorAlterarException(){
        super();
    }
    
    /**
     * 
     * @param e 
     */
    public ControladorAlterarException(Exception e){
        super(e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public ControladorAlterarException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }
    
    /**
     * 
     * @param s 
     */
    public ControladorAlterarException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public ControladorAlterarException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public ControladorAlterarException(Throwable t){
        super(t);
    }
}
