/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.regra;

import ce.erro.*;
import ce.model.basica.Categoria;
import ce.model.basica.Produto;
import ce.model.dao.IRepositorioCategoria;
import ce.model.dao.RepositorioCategoria;
import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioProduto;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author andreluiz
 */
public class ControladorProduto {
    private IRepositorioCategoria rpCateg= new RepositorioCategoria();
    private IRepositorioProduto rpProd= new RepositorioProduto();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
    
    public void validarDados(Produto p) throws ControladorException{
        if (p.getDescProd() == null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
    }
    
    /*public void verificarSePodeExcluir(Produto p) throws ControladorException {
        try{
            Produto prod= rpProd.pesqCodProd(p.getCodProd(), false);
            if (prod == null){
                throw new ControladorException(rb.getString("CtrlProdNaoExiste"));
            }
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " produto.",
                    "ControladorProduto.verificarSePodeExcluir()");
        }
        catch(RepositorioPesquisarException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " produto.",
                    "ControladorProduto.verificarSePodeExcluir()");
        }
    }*/
    
    public void inserir(Produto p) throws ControladorException{
        try{
            rpProd.inserir(p);
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " produto.",
                    "ControladorProduto.inserir()");
        }
        catch(RepositorioInserirException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroInserir") + " produto.",
                    "ControladorProduto.inserir()");
        }
    }
    
    public void verificarSeExiste(Integer cod) throws ControladorException{
        Produto prod=null;
        try{
            prod= rpProd.pesqCodProd(cod, false);
            if (prod == null){
                throw new ControladorException(rb.getString("CtrlPordNaoExiste"),
                        "ControladorProduto.verificarSeExiste()");
            }
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " produto.",
                    "ControladorProduto.verificarSeExiste()");
        }
        catch(RepositorioPesquisarException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " produto.",
                    "ControladorProduto.verificarSeExiste()");
        }
    }
    
    public void verificarSeExiste(Produto p) throws ControladorException{
        verificarSeExiste(p.getCodProd());
    }
    
    public void alterar(Produto p) throws ControladorException{
        try{
            rpProd.alterar(p);
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " produto.",
                    "ControladorProduto.alterar()");
        }
        catch(RepositorioException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " produto.",
                    "ControladorProduto.alterar()");
        }
    }
    
    public void atualizarQtde(Produto p) throws ControladorException{
        try{
            rpProd.atualizarQtde(p);
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroAtlzQtdeIndisp"),
                    "ControladorProduto.altualizarQtde()");
        }
        catch(RepositorioException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroAtlzQtde"),
                    "ControladorProduto.altualizarQtde()");
        }
    }
    
    public void excluir(Produto p) throws ControladorException{
        try{
            rpProd.excluir(p.getCodProd());
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " produto.",
                    "ControladorProduto.excluir()");
        }
        catch(RepositorioForeignKeyException rfke){
            throw new ControladorException(
                    rb.getString("CtrlNaoPodeExcluirProd"),
                    "ControladorProduto.excluir()");
        }
        catch(RepositorioException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " produto.",
                    "ControladorProduto.excluir()");
        }
    }
    
    public List<Produto> listar() throws ControladorException{
        try{
            return rpProd.listar();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroListIndisp") + " produto.",
                    "ControladorProduto.listar()");
        }
        catch(RepositorioException e){
            throw new ControladorException(
                    rb.getString("CtrlErroListar") + " produto.",
                    "ControladorProduto.listar()");
        }
    }
    
    public List<Produto> pesquisar(String descricao) throws ControladorException{
        try{
            return rpProd.pesquisar(descricao);
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroPesqIndisp") + " produto.",
                    "ControladorProduto.pesquisar()");
        }
        catch(RepositorioException e){
            throw new ControladorException(
                    rb.getString("CtrlErroPesquisar") + " produto.",
                    "ControladorProduto.pesquisar()");
        }
    }
    
    public Produto trazer(Integer cod, boolean listarForns) throws ControladorException{
        try{
            return rpProd.pesqCodProd(cod, listarForns);
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " produto.",
                    "ControladorProduto.trazer()");
        }
        catch(RepositorioException e){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " produto.",
                    "ControladorProduto.trazer()");
        }
    }
    
    public void verificarSePodeInserir(Produto p) throws ControladorException{
        try{
            List<Produto> lista= rpProd.pesquisar(p.getDescProd());
            if (lista.size() > 0){
                throw new ControladorException(rb.getString("CtrlProdExiste"));
            }
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " produto.",
                    "ControladorProduto.verificarSePodeInserir()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " produto.",
                    "ControladorProduto.verificarSePodeInserir()");
        }
    }
}
