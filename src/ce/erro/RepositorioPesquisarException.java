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

    public RepositorioPesquisarException(Throwable t){
        super(t);
    }
}
