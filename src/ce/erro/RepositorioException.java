package ce.erro;

/**
 *
 * @author professor
 */
public class RepositorioException extends Exception{
    public RepositorioException(){
        super();
    }

    public RepositorioException(Exception e){
        super(e);
    }

    public RepositorioException(String s){
        super(s);
    }

    public RepositorioException(Throwable t){
        super(t);
    }
}
