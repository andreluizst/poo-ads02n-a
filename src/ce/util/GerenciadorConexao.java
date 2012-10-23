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
    
    private void loadConfig(){
        //implementar
    }

    private GerenciadorConexao(){
        super();
    }

    public static IGerenciadorConexao getInstancia(){
        if(instancia==null) instancia=new GerenciadorConexao();
        return instancia;
    }

    @Override
    public Connection conectar() throws ConexaoException{
        String driver = "com.mysql.jdbc.Driver";
        String local = "jdbc:mysql://localhost:3306/bdpoo";
        String usuario = "root";
        String senha = "";
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
}
