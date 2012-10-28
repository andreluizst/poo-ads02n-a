/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioInserirException extends RepositorioException{
    public RepositorioInserirException(){
        super();
    }

    public RepositorioInserirException(Exception e){
        super(e);
    }
    
    public RepositorioInserirException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioInserirException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioInserirException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public RepositorioInserirException(Throwable t){
        super(t);
    }
}
