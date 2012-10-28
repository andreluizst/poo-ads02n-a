/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioListarException extends RepositorioException{
    public RepositorioListarException(){
        super();
    }

    public RepositorioListarException(Exception e){
        super(e);
    }
    
    public RepositorioListarException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioListarException(String s){
        super(s);
    }

    public RepositorioListarException(Throwable t){
        super(t);
    }
}
