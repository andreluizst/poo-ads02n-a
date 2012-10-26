package ce.erro;

/**
 *
 * @author professor
 */
public class RepositorioException extends Exception{
    private String pathClassCall= "";
    public RepositorioException(){
        super();
    }

    public RepositorioException(Exception e){
        super(e);
    }
    
    public RepositorioException(Exception e, String nameClassCall){
        super(e);
        //pathClassCall= nameClassCall;
        if (pathClassCall.compareTo("") == 0){
            pathClassCall= nameClassCall;
        }else{
            pathClassCall= nameClassCall + "." + pathClassCall;
        }
    }

    public RepositorioException(String s){
        super(s);
    }

    public RepositorioException(Throwable t){
        super(t);
    }
    
    public void addClassCallToPath(String name){
        if (pathClassCall.compareTo("") > 0){
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
