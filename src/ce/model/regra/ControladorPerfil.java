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
import ce.erro.RepositorioForeignKeyException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Perfil;
import ce.model.dao.IRepositorioPerfil;
import ce.model.dao.RepositorioPerfil;
import java.util.ResourceBundle;
import java.util.List;
/**
 *
 * @author Andre
 */
public class ControladorPerfil {
    private IRepositorioPerfil rpPer= new RepositorioPerfil();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
    
    public void validarDados(Perfil p) throws ControladorException{
        if (p.getNome() == null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"),
                    "ControladorPerfil.validarDados()");
        }
    }
    
    public void verificarSePodeInserir(Perfil p) throws ControladorException{
        try {
            Perfil per = rpPer.pesquisar(p.getNome());
            if(per != null){
                throw new ControladorException(rb.getString("CtrlErroPerExiste"),
                        "ControladorPerfil.verificarSePodeInserir()");
            }
        } catch (ConexaoException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " perfil.",
                    "ControladorPerfil.verificarSePodeInserir()");
        } catch (RepositorioException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " perfil.",
                    "ControladorPerfil.verificarSePodeInserir()");
        }
    }
    
    public void inserir(Perfil p) throws ControladorException {
        try {
            rpPer.inserir(p);
        } catch (ConexaoException ce) {
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " perfil.",
                    "ControladorPerfil.inserir()");
        } catch (RepositorioInserirException re) {
            throw new ControladorException(
                    rb.getString("CtrlErroInseir") + " perfil.",
                    "ControladorPerfil.inserir()");
        }
    }
    
    public void alterar(Perfil p) throws ControladorException {
        try{
            rpPer.alterar(p);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " perfil.",
                    "ControladorPerfil.alterar()");
        }
        catch(RepositorioAlterarException rae){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " perfil.",
                    "ControladorPerfil.alterar()");
        }
    }
    
    public void verificarSeExiste(Perfil p) throws ControladorException{
        Perfil per=null;
        try{
            per= rpPer.pesquisar(p.getNome());
            if (per == null){
                throw new ControladorException(rb.getString("CtrlPerNaoExiste"),
                        "ControladorPerfil.verificarSeExiste()");
            }
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " perfil.",
                    "ControladorPerfil.verificarSeExiste()");
        }
        catch(RepositorioPesquisarException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " perfil.",
                    "ControladorPerfil.verificarSeExiste()");
        }
    }
    
    public void excluir(Perfil p) throws ControladorException{
        try{
            rpPer.excluir(p);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " perfil.",
                    "ControladorPerfil.excluir()");
        }
        catch(RepositorioForeignKeyException rfke){
            throw new ControladorException(
                    rb.getString("CtrlNaoPodeExcluirPer"),
                    "ControladorPerfil.excluir()");
        }
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " perfil.",
                    "ControladorPerfil.excluir()");
        }
    }
    
    public List<Perfil> listar() throws ControladorException{
        try{
            return rpPer.listar();
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroListIndisp") + " perfil.",
                    "ControladorPerfil.listar()");
        }
        catch(RepositorioListarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroListar") + " perfil.",
                    "ControladorPerfil.listar()");
        }
    }
    
    public Perfil pesquisar(String nome) throws ControladorException{
        try{
            return rpPer.pesquisar(nome);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroPesqIndisp") + " perfil.",
                    "ControladorPerfil.pesquisar()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroPesquisar") + " perfil.",
                    "ControladorPerfil.pesquisar()");
        }
    }
    
    public Perfil trazer(Integer cod) throws ControladorException{
        try{
            return rpPer.pesqCod(cod);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " perfil.",
                    "ControladorPerfil.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " perfil.",
                    "ControladorPerfil.trazer()");
        }
    }
    
}
