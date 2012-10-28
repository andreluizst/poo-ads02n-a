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

    public RepositorioPesquisarException(Exception e){
        super(e);
    }
    
    public RepositorioPesquisarException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioPesquisarException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioPesquisarException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public RepositorioPesquisarException(Throwable t){
        super(t);
    }
}
