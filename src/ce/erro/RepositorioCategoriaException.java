/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.erro;

/**
 *
 * @author Andre
 */
public class RepositorioCategoriaException extends Exception{
    public RepositorioCategoriaException(){
        super();
    }

    public RepositorioCategoriaException(Exception e){
        super(e);
    }

    public RepositorioCategoriaException(String s){
        super(s);
    }

    public RepositorioCategoriaException(Throwable t){
        super(t);
    }
}
