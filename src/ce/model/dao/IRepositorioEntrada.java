/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Entrada;
import java.util.List;
/**
 *
 * @author andreluiz
 */
public interface IRepositorioEntrada {
    public void inserir(Entrada e) throws ConexaoException, 
            RepositorioInserirException;
    public void alterar(Entrada e) throws ConexaoException, 
            RepositorioAlterarException;
    public void excluir(Entrada e) throws ConexaoException, 
            RepositorioExcluirException;
    public List<Entrada> listar() throws ConexaoException, 
            RepositorioListarException;
    public Entrada pesqNum(Integer Num) 
            throws ConexaoException, RepositorioPesquisarException;
}
