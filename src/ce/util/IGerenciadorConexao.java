package ce.util;

import ce.erro.ConexaoException;
import java.sql.Connection;

/**
 *
 * @author professor
 */
public interface IGerenciadorConexao {
    public Connection conectar() throws ConexaoException;
    public void desconectar(Connection c) throws ConexaoException;
}
