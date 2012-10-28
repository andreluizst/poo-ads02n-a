/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioExcluirException extends RepositorioException{
    public RepositorioExcluirException(){
        super();
    }

    public RepositorioExcluirException(Exception e){
        super(e);
    }
    
    public RepositorioExcluirException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioExcluirException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioExcluirException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public RepositorioExcluirException(Throwable t){
        super(t);
    }
}
