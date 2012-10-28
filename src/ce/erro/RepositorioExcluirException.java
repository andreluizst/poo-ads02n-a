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

    public RepositorioExcluirException(Throwable t){
        super(t);
    }
}
