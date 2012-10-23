/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.LocalEstoque;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IRepositorioLocalEstoque {
    public void inserir(LocalEstoque le) throws ConexaoException, RepositorioException;
    public void alterar(LocalEstoque le) throws ConexaoException, RepositorioException;
    public void excluir(int codLocal) throws ConexaoException, RepositorioException;
    public List<LocalEstoque> listar() throws ConexaoException, RepositorioException;
    public List<LocalEstoque> pesquisar(String descricao) throws ConexaoException, RepositorioException;
    public LocalEstoque pesqCodForn(int codLocal) throws ConexaoException, RepositorioException;
}
