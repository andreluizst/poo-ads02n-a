/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioCategoriaException extends RepositorioException{
    public RepositorioCategoriaException(){
        super();
    }

    public RepositorioCategoriaException(Exception e){
        super(e);
    }
    
    public RepositorioCategoriaException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioCategoriaException(String s, String nameClassCall){
        super(s, nameClassCall);
    }
    
    public RepositorioCategoriaException(Exception e, String nameClassCall){
        super(e, nameClassCall);
    }

    public RepositorioCategoriaException(Throwable t){
        super(t);
    }
}
