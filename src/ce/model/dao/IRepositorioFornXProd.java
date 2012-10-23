/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.FornXProd;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IRepositorioFornXProd {
    public void inserir(FornXProd fxp) throws ConexaoException,
            RepositorioException;
    public void alterar(FornXProd fxpAnt, FornXProd fxpNovo) 
            throws ConexaoException, RepositorioException;
    public void excluir(FornXProd fxp) throws ConexaoException, 
            RepositorioException;
    public List<FornXProd> listar() throws ConexaoException, 
            RepositorioException;
    public List<FornXProd> pesqFornDoProd(Integer codProd) 
            throws ConexaoException, RepositorioException;
    public List<FornXProd> pesqProdDoForn(Integer codForn) 
            throws ConexaoException, RepositorioException;
    //public FornXProd pesqCodForn(Integer codForn) throws ConexaoException, RepositorioException; 
}
