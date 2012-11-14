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
    
    public RepositorioListarException(String userName, Exception e, String nameClassCall){
        super(userName, e, nameClassCall);
    }

    public RepositorioListarException(String userName, String s){
        super(userName, s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioListarException(String userName, String s, String nameClassCall){
        super(userName, s, nameClassCall);
    }

    public RepositorioListarException(String userName, Throwable t){
        super(userName, t);
    }
}
