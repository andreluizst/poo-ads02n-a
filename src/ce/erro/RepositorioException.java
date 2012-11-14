package ce.erro;

import ce.util.LogGenerator;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author professor
 * @author André Luiz S. Teotônio
 */
public class RepositorioException extends Exception{
    private LogGenerator log= LogGenerator.getInstancia();
    /**
     * Esta propriedade contém a classe que lançou a exceção. Se a classe B
     * capturar a exceção da classe A e por sua vez relançar para uma outra
     * class, por exemplo C, que ao tratar esta exceção poderá verificar que
     * esta propriedade terá o seguinte caminho: A.B
     *
     */
    private String pathClassCall= "";
    
    public RepositorioException(){
        super();
    }

    public RepositorioException(Exception e){
        super(e);
    }
    
    public RepositorioException(Exception e, Logger logger){
        super(e);
        log.log(logger, Level.WARNING, getMessage());
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param nameClassCall
     * Nome da classe que está lançando a exceção
     * */
    public RepositorioException(Exception e, String nameClassCall){
        super(e);
        if (pathClassCall.compareTo("") == 0){
            pathClassCall= nameClassCall;
        }else{
            pathClassCall= nameClassCall + "." + pathClassCall;
        }
    }

    public RepositorioException(String s){
        super(s);
    }
    
    /**
     * Abilita o rastreamento da(s) classe(s) chamadora(s)
     * @param s
     * Mensagem
     * @param nameClassCall 
     * Nome da classe que está lançando a exceção
     */
    public RepositorioException(String s, String nameClassCall){
        super(s);
        if (pathClassCall.compareTo("") == 0){
            pathClassCall= nameClassCall;
        }else{
            pathClassCall= nameClassCall + "." + pathClassCall;
        }
    }

    public RepositorioException(Throwable t){
        super(t);
    }
    
    public void addClassCallToPath(String name){
        if (pathClassCall.compareTo("") == 0){
            pathClassCall= name;
        }else{
            pathClassCall= name + "." + pathClassCall;
        }
    }

    /**
     * @return the pathClassCall
     */
    public String getPathClassCall() {
        return pathClassCall;
    }

    /**
     * @param pathClassCall the pathClassCall to set
     */
    public void setPathClassCall(String pathClassCall) {
        this.pathClassCall = pathClassCall;
    }
    
}
