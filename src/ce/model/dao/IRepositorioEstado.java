/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Estado;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IRepositorioEstado {
    public void inserir(Estado e) throws ConexaoException, RepositorioException;
    public void alterar(Estado e) throws ConexaoException, RepositorioException;
    public void excluir(String uf) throws ConexaoException, RepositorioException;
    public List<Estado> listar() throws ConexaoException, RepositorioException;
    public List<Estado> pesquisar(String descricao) throws ConexaoException, RepositorioException;
    public Estado pesqPorUf(String uf) throws ConexaoException, RepositorioException; 
}
