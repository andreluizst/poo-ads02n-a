/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class ControladorInserirException extends ControladorException{
    /**
     * Construtor padrão
     */
    public ControladorInserirException(){
        super();
    }
    
    /**
     * 
     * @param e 
     */
    public ControladorInserirException(Exception e){
        super(e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public ControladorInserirException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }
    
    /**
     * 
     * @param s 
     */
    public ControladorInserirException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public ControladorInserirException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public ControladorInserirException(Throwable t){
        super(t);
    }
}
