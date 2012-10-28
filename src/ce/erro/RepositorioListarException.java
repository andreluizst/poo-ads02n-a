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
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioListarException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public RepositorioListarException(Throwable t){
        super(t);
    }
}
