/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Fornecedor;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IRepositorioFornecedor {
    public void inserir(Fornecedor f) throws ConexaoException, RepositorioException;
    public void alterar(Fornecedor f) throws ConexaoException, RepositorioException;
    public void excluir(Integer codForn) throws ConexaoException, RepositorioException;
    public List<Fornecedor> listar() throws ConexaoException, RepositorioException;
    public List<Fornecedor> pesquisar(String descProd) throws ConexaoException, RepositorioException;
    public Fornecedor pesqCodForn(Integer codForn) throws ConexaoException, RepositorioException; 
}
