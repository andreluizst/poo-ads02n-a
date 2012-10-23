package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Categoria;
import java.util.List;

/**
 *
 * @author professor
 */
public interface IRepositorioCategoria {
    public List listar() throws ConexaoException,RepositorioException;
    public void incluir(Categoria obj) throws ConexaoException,RepositorioException;
    public void alterar(Categoria obj) throws ConexaoException,RepositorioException;
    public void excluir(Integer codCateg) throws ConexaoException,RepositorioException;
    public Categoria pesquisar(String descricao) throws ConexaoException,RepositorioException;
    public Categoria pesqPorCod(Integer codCateg) throws ConexaoException,RepositorioException;
}
