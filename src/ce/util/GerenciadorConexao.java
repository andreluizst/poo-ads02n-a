package ce.util;

import ce.erro.ConexaoException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author professor
 */
public class GerenciadorConexao implements IGerenciadorConexao {
    private static IGerenciadorConexao instancia;
    private String driver;
    private String local;
    private String usuario;
    private String senha;
    private boolean loginNeeded;
    
    private void loadConfig(){
        //implementar
    }

    private GerenciadorConexao(){
        super();
        driver = "com.mysql.jdbc.Driver";
        local = "jdbc:mysql://localhost:3306/estoque";
        usuario = "root";
        senha = "root";
        //senha = "andre";
        loginNeeded=false;
    }

    public static IGerenciadorConexao getInstancia(){
        if(instancia==null){
            instancia=new GerenciadorConexao();
        }
        return instancia;
    }

    @Override
    public Connection conectar() throws ConexaoException{
        try{
            Class.forName(driver);
            Connection c = DriverManager.getConnection(local, usuario, senha);
            return c;
        }catch(ClassNotFoundException e){
            throw new ConexaoException(e);
        }catch(SQLException e){
            throw new ConexaoException(e);
        }
    }

    public Connection conectar(String usuario, String senha) 
            throws ConexaoException{
        try{
            Class.forName(driver);
            Connection c = DriverManager.getConnection(local, usuario, senha);
            return c;
        }catch(ClassNotFoundException e){
            throw new ConexaoException(e);
        }catch(SQLException e){
            throw new ConexaoException(e);
        }
    }
    
    @Override
    public void desconectar(Connection c) throws ConexaoException{
        try{
            c.close();
        }catch(SQLException e){
            throw new ConexaoException(e);
        }
    }
    
    public void setConfig(String driver, String local, String usuario,
            String senha){
        this.driver=driver;
        this.local=local;
        this.usuario=usuario;
        this.senha=senha;
    }

    /**
     * @return the loginNeeded
     */
    public boolean isLoginNeeded() {
        return loginNeeded;
    }

    /**
     * @param loginNeeded the loginNeeded to set
     */
    public void setLoginNeeded(boolean loginNeeded) {
        this.loginNeeded = loginNeeded;
    }
    
}
