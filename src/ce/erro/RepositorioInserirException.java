/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioInserirException extends RepositorioException{
    public RepositorioInserirException(){
        super();
    }

    public RepositorioInserirException(Exception e){
        super(e);
    }
    
    public RepositorioInserirException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioInserirException(String s){
        super(s);
    }

    public RepositorioInserirException(Throwable t){
        super(t);
    }
}
