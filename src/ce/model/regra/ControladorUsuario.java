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
import ce.model.basica.Usuario;
import ce.model.dao.IRepositorioPerfil;
import ce.model.dao.RepositorioPerfil;
import ce.model.dao.IRepositorioUsuario;
import ce.model.dao.RepositorioUsuario;
import java.util.ResourceBundle;
import java.util.List;
/**
 *
 * @author Andre
 */
public class ControladorUsuario {
    private IRepositorioUsuario rpUsr= new RepositorioUsuario();
    private IRepositorioPerfil rpPer= new RepositorioPerfil();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
    private ControladorPerfil ctrlPer= new ControladorPerfil();
    
    public void validarDados(Usuario u) throws ControladorException{
        if (u.getNome() == null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"),
                    "ControladorUsuario.validarDados()");
        }
        /*if (u.getFuncionario() == null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"),
                    "ControladorUsuario.validarDados()");
        }
        if (ctrlPer.trazer(u.getPerfil().getCodPerfil()) == null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"),
                    "ControladorUsuario.validarDados()");
        }*/
    }
    
    public void verificarSePodeInserir(Usuario u) throws ControladorException{
        try {
            Usuario usu = rpUsr.pesqCpf(u.getFuncionario().getCpf());
            if(usu != null){
                throw new ControladorException(rb.getString("CtrlErroUsuExiste"),
                        "ControladorUsuario.verificarSePodeInserir()");
            }
        } catch (ConexaoException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " usuário.",
                    "ControladorUsuario.verificarSePodeInserir()");
        } catch (RepositorioException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " usuário.",
                    "ControladorUsuario.verificarSePodeInserir()");
        }
    }
    
    public void inserir(Usuario u) throws ControladorException {
        try {
            rpUsr.inserir(u);
        } catch (ConexaoException ce) {
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " usuário.",
                    "ControladorUsuario.inserir()");
        } catch (RepositorioInserirException re) {
            throw new ControladorException(
                    rb.getString("CtrlErroInseir") + " usuário.",
                    "ControladorUsuario.inserir()");
        }
    }
    
    public void alterar(Usuario u) throws ControladorException {
        try{
            rpUsr.alterar(u);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " usuário.",
                    "ControladorUsuario.alterar()");
        }
        catch(RepositorioAlterarException rae){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " usuário.",
                    "ControladorUsuario.alterar()");
        }
    }
    
    public void verificarSePodeExcluir(Usuario u) throws ControladorException{
        //É preciso verificar se algum produto pertence a categroria que será exluida
        //caso afirmativo, abortar exclusão
    }
    
    public void excluir(Usuario u) throws ControladorException{
        try{
            rpUsr.excluir(u);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " usuário.",
                    "ControladorUsuario.excluir()");
        }
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " usuário.",
                    "ControladorUsuario.excluir()");
        }
    }
    
    public List<Usuario> listar() throws ControladorException{
        try{
            return rpUsr.listar();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroListIndisp") + " usuários.",
                    "ControladorUsuario.listar()");
        }
        catch(RepositorioListarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroListar") + " usuários.",
                    "ControladorUsuario.listar()");
        }
    }
    
    public Usuario trazer(String cpf) throws ControladorException{
        try{
            Usuario usu= rpUsr.pesqCpf(cpf);
            if (usu == null){
                throw new ControladorException(rb.getString("CtrlUsuNaoExiste"),
                        "ControladorUsuario.trazer()");
            }
            return usu;
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " usuário.",
                    "ControladorUsuario.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " funcionario.",
                    "ControladorFuncionario.trazer()");
        }
    }
    
    public Usuario trazer(Integer cod) throws ControladorException{
        try{
            Usuario usu= rpUsr.pesqCod(cod);
            if (usu == null){
                throw new ControladorException(rb.getString("CtrlUsuNaoExiste"),
                        "ControladorUsuario.trazer()");
            }
            return usu;
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " usuário.",
                    "ControladorUsuario.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " funcionario.",
                    "ControladorFuncionario.trazer()");
        }
    }
    
}
