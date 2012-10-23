package ce.erro;

/**
 *
 * @author professor
 */
public class ConexaoException extends Exception{
    public ConexaoException(){
        super();
    }

    public ConexaoException(Exception e){
        super(e);
    }

    public ConexaoException(String s){
        super(s);
    }

    public ConexaoException(Throwable t){
        super(t);
    }
}
