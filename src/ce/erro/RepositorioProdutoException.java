/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioProdutoException extends Exception{
    public RepositorioProdutoException(){
        super();
    }

    public RepositorioProdutoException(Exception e){
        super(e);
    }

    public RepositorioProdutoException(String s){
        super(s);
    }

    public RepositorioProdutoException(Throwable t){
        super(t);
    }
}
