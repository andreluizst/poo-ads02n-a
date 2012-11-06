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

    public RepositorioForeignKeyException(Exception e){
        super(e);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public RepositorioForeignKeyException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioForeignKeyException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioForeignKeyException(String s, String nameClassCall){
        super(s, nameClassCall);
    }

    public RepositorioForeignKeyException(Throwable t){
        super(t);
    }
    
}
