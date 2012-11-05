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
import ce.model.basica.Perfil;
import ce.model.dao.IRepositorioPerfil;
import ce.model.dao.RepositorioPerfil;
import java.util.ResourceBundle;
/**
 *
 * @author Andre
 */
public class ControladorPerfil {
    private IRepositorioPerfil rpPer= new RepositorioPerfil();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.util.Erro");
    
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
    
    public void verificarSePodeExcluir(Perfil p) throws ControladorException{
        //É preciso verificar se algum produto pertence a categroria que será exluida
        //caso afirmativo, abortar exclusão
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
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " perfil.",
                    "ControladorPerfil.excluir()");
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
