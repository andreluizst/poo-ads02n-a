/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Unidade;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IRepositorioUnidade {
    public void inserir(Unidade u) throws ConexaoException, RepositorioException;
    public void alterar(Unidade u) throws ConexaoException, RepositorioException;
    public void excluir(Integer codUnid) throws ConexaoException, RepositorioException;
    public List<Unidade> listar() throws ConexaoException, RepositorioException;
    public List<Unidade> pesquisar(String descricao) throws ConexaoException, RepositorioException;
    public Unidade pesqCodForn(Integer codUnid) throws ConexaoException, RepositorioException; 
}
