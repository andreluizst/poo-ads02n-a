/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.regra;

import ce.erro.ConexaoException;
import ce.erro.ControladorException;
import ce.erro.ControladorInserirException;
import ce.erro.ControladorAlterarException;
import ce.erro.ControladorExcluirException;
import ce.erro.ControladorValidarException;
import ce.erro.ControladorVerificarException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioInserirException;
import ce.model.basica.Categoria;
import ce.model.basica.Produto;
//import ce.model.dao.IRepositorioCategoria;
import ce.model.dao.RepositorioCategoria;
//import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioProduto;
import java.util.List;
/**
 *
 * @author andreluiz
 */
public class ControladorProduto {
    private RepositorioCategoria rpCateg= new RepositorioCategoria();
    private RepositorioProduto rpProd= new RepositorioProduto();
    
    public void validar(Produto p) throws ControladorValidarException{
        if (p.getDescProd() == null){
            throw new ControladorValidarException(
                    "O campo descrição deve ser preenchido", 
                    "ControladorProduto"); 
        }
    }
    
    //public void verificar(Produto p)
    
    public void inserir(Produto p) throws ControladorInserirException{
        try{
            rpProd.inserir(p);
        }
        catch(ConexaoException e){
            throw new ControladorInserirException("Não foi possível estabelecer conexão.");
        }
        catch(RepositorioInserirException ie){
            throw new ControladorInserirException(ie, "ControladorProduto.inserir()");
        }
    }
    
    public void alterar(Produto p) throws ControladorAlterarException{
        try{
            rpProd.alterar(p);
        }
        catch(ConexaoException e){
            throw new ControladorAlterarException("Não foi possível estabelecer conexão.");
        }
        catch(RepositorioException ie){
            throw new ControladorAlterarException(ie, "ControladorProduto.alterar()");
        }
    }
    
    public void atualizarQtde(Produto p) throws ControladorAlterarException{
        try{
            rpProd.atualizarQtde(p);
        }
        catch(ConexaoException e){
            throw new ControladorAlterarException("Não foi possível estabelecer conexão.");
        }
        catch(RepositorioException ie){
            throw new ControladorAlterarException(ie, "ControladorProduto.atualizarQtde()");
        }
    }
    
    public void excluir(Produto p) throws ControladorExcluirException{
        try{
            rpProd.excluir(p.getCodProd());
        }
        catch(ConexaoException e){
            throw new ControladorExcluirException("Não foi possível estabelecer conexão.");
        }
        catch(RepositorioException ie){
            throw new ControladorExcluirException(ie, "ControladorProduto.excluir()");
        }
    }
    
    public List<Produto> listar() throws ControladorException{
        try{
            return rpProd.listar();
        }
        catch(ConexaoException e){
            throw new ControladorException("Não foi possível estabelecer conexão.");
        }
        catch(RepositorioException e){
            throw new ControladorException(e, "ControladorProduto.listar()");
        }
    }
    
    public List<Produto> pesquisar(String descricao) throws ControladorException{
        try{
            return rpProd.pesquisar(descricao);
        }
        catch(ConexaoException e){
            throw new ControladorException("Não foi possível estabelecer conexão.");
        }
        catch(RepositorioException e){
            throw new ControladorException(e, "ControladorProduto.pesquisar()");
        }
    }
    
    public Produto pesqCod(Integer cod, boolean listarForns) throws ControladorException{
        try{
            return rpProd.pesqCodProd(cod, listarForns);
        }
        catch(ConexaoException e){
            throw new ControladorException("Não foi possível estabelecer conexão.");
        }
        catch(RepositorioException e){
            throw new ControladorException(e, "ControladorProduto.alterar()");
        }
    }
}
