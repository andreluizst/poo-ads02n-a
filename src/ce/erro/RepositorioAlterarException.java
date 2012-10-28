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
    
    public RepositorioAlterarException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioAlterarException(String s){
        super(s);
    }

    public RepositorioAlterarException(Throwable t){
        super(t);
    }
}
