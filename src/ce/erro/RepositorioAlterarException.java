/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioAlterarException extends RepositorioException {
    public RepositorioAlterarException(){
        super();
    }

    public RepositorioAlterarException(Exception e){
        super(e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioAlterarException(String userName, String s, String nameClassCall){
        super(userName, s, nameClassCall);
    }
    
    public RepositorioAlterarException(String userName, Exception e, String nameClassCall){
        super(userName, e, nameClassCall);
    }

    public RepositorioAlterarException(String userName, String s){
        super(userName, s);
    }

    public RepositorioAlterarException(String userName, Throwable t){
        super(userName, t);
    }
}
