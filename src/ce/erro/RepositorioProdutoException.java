/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioProdutoException extends RepositorioException{
    public RepositorioProdutoException(){
        super();
    }

    public RepositorioProdutoException(Exception e){
        super(e);
    }
    
    public RepositorioProdutoException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioProdutoException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioProdutoException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public RepositorioProdutoException(Throwable t){
        super(t);
    }
}
