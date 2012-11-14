/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioPesquisarException extends RepositorioException{
    public RepositorioPesquisarException(){
        super();
    }

    public RepositorioPesquisarException(String userName, Exception e){
        super(userName, e);
    }
    
    public RepositorioPesquisarException(String userName, Exception e, String nameClassCall){
        super(userName, e, nameClassCall);
    }

    public RepositorioPesquisarException(String userName, String s){
        super(userName, s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioPesquisarException(String userName, String s, String nameClassCall){
        super(userName, s, nameClassCall);
    }

    public RepositorioPesquisarException(String userName, Throwable t){
        super(userName, t);
    }
}
