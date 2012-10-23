/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Funcionario;
import java.util.List;

/**
 *
 * @author Andre
 */
public interface IRepositorioFuncionario {
    public void inserir(Funcionario f) throws ConexaoException, RepositorioException;
    public void alterar(Funcionario f) throws ConexaoException, RepositorioException;
    public void excluir(String cpf) throws ConexaoException, RepositorioException;
    public List<Funcionario> listar() throws ConexaoException, RepositorioException;
    public List<Funcionario> pesquisar(String nome) throws ConexaoException, RepositorioException;
    public Funcionario pesqCpf(String cpf) throws ConexaoException, RepositorioException; 
}
