/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author andreluiz
 */
public class RepositorioForeignKeyException extends RepositorioException{
    
    
    public RepositorioForeignKeyException(){
        super();
    }

    public RepositorioForeignKeyException(String userName, Exception e){
        super(userName, e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public RepositorioForeignKeyException(String userName, Exception e, String nameClassCall){
        super(userName, e, nameClassCall);
    }

    public RepositorioForeignKeyException(String userName, String s){
        super(userName, s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioForeignKeyException(String userName, String s, String nameClassCall){
        super(userName, s, nameClassCall);
    }

    public RepositorioForeignKeyException(String userName, Throwable t){
        super(userName, t);
    }
    
}
