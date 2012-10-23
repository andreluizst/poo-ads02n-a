/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Produto;
import ce.model.basica.Fornecedor;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IRepositorioProduto {
    public void inserir(Produto p) throws ConexaoException, RepositorioException;
    public void alterar(Produto p) throws ConexaoException, RepositorioException;
    public void excluir(Integer codProd) throws ConexaoException, RepositorioException;
    public List<Produto> listar() throws ConexaoException, RepositorioException;
    public List<Produto> pesquisar(String descProd) throws ConexaoException, RepositorioException;
    public Produto pesqCodProd(Integer codprod) throws ConexaoException, RepositorioException;
    /*public List<Fornecedor> fornecedoresDoProd(Produto p) 
            throws ConexaoException, RepositorioException;*/
}
