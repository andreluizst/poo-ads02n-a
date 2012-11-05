/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.regra;

import ce.erro.ConexaoException;
import ce.erro.ControladorException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Categoria;
import ce.model.dao.RepositorioCategoria;
import java.util.ResourceBundle;
/**
 *
 * @author Andre
 */
public class ControladorCategoria {
    private RepositorioCategoria rpCateg = new RepositorioCategoria();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.util.Erro");
    
    public void validarDados(Categoria c) throws ControladorException{
        if(c.getDescricao()==null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
        if(c.getDescricao().compareTo("") == 0){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
    }

    public void verificarSePodeInserir(Categoria c) throws ControladorException{
        try {
            Categoria aux = rpCateg.pesquisar(c.getDescricao());
            if(aux!=null){
                throw new ControladorException(rb.getString("CtrlCategExiste"));
            }
        } catch (ConexaoException ex) {
            throw new ControladorException(rb.getString("CtrlErroVerifIndisp") + " categoria.");
        } catch (RepositorioException ex) {
            throw new ControladorException(rb.getString("CtrlErroVerificar") + " categoria.",
                    "ControladorCategoria.verificarSePodeInserir()");
        }
    }

    public void inserir(Categoria c) throws ControladorException {
        try {
            rpCateg.incluir(c);
        } catch (ConexaoException ex) {
            throw new ControladorException(rb.getString("CtrlErroInsIndisp") + " categoria.");
        } catch (RepositorioException ex) {
            throw new ControladorException(rb.getString("CtrlErroInseir") + " categoria.",
                    "ControladorCategoria.inserir()");
        }
    }
    
    public void alterar(Categoria c) throws ControladorException {
        try{
            rpCateg.alterar(c);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " categoria.",
                    "ControladorCategoria.alterar()");
        }
        catch(RepositorioAlterarException rae){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " categoria.",
                    "ControladorCategoria.alterar()");
        }
    }
    
    public void verificarSePodeExcluir(Categoria c) throws ControladorException{
        //É preciso verificar se algum produto pertence a categroria que será exluida
        //caso afirmativo, abortar exclusão
    }
    
    public void excluir(Categoria c) throws ControladorException{
        try{
            rpCateg.excluir(c.getCodCateg());
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " categoria.",
                    "ControladorCategoria.excluir()");
        }
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " categoria.",
                    "ControladorCategoria.excluir()");
        }
    }
    
    public Categoria pesquisar(String descCateg) throws ControladorException{
        try{
            return rpCateg.pesquisar(descCateg);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroPesqIndisp") + " categoria.",
                    "ControladorCategoria.pesquisar()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroPesquisar") + " categoria.",
                    "ControladorCategoria.pesquisar()");
        }
    }
    
    public Categoria trazer(Integer cod) throws ControladorException{
        try{
            return rpCateg.pesqPorCod(cod);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " categoria.",
                    "ControladorCategoria.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " categoria.",
                    "ControladorCategoria.trazer()");
        }
    }
}
