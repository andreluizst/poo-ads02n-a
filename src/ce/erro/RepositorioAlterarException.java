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
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioAlterarException(String s, String nameClassCall){
        super(s, nameClassCall);
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
