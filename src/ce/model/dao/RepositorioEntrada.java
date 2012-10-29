/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Fornecedor;
import ce.model.basica.Produto;
import ce.model.basica.Entrada;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import ce.model.dao.IRepositorioFornecedor;
import ce.model.dao.RepositorioFornecedor;
import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioProduto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioEntrada implements IRepositorioEntrada{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioEntrada(){
        gerenciadorConexao = GerenciadorConexao.getInstancia();
    }
    
    @Override
    public void inserir(Entrada e) throws ConexaoException, 
            RepositorioInserirException{
        
    }
    
    @Override
    public void alterar(Entrada e) throws ConexaoException, 
            RepositorioAlterarException{
        
    }
    
    @Override
    public void excluir(Entrada e) throws ConexaoException, 
            RepositorioExcluirException{
        
    }
    
    @Override
    public List<Entrada> listar() throws ConexaoException, 
            RepositorioListarException{
        List<Entrada> lista= new ArrayList<Entrada>();
        
        return lista;
    }
    
    @Override
    public Entrada pesqNum(Integer num) throws ConexaoException, 
            RepositorioPesquisarException{
        Entrada e= null;
        
        return e;
    }
}
