/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.regra;

import ce.erro.ConexaoException;
import ce.erro.ControladorException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioPesquisarException;
import ce.erro.RepositorioListarException;
import ce.model.basica.Unidade;
import ce.model.dao.RepositorioUnidade;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Andre
 */
public class ControladorUnidade {
    private RepositorioUnidade rpUnid= new RepositorioUnidade();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.util.Erro");
    
    public void validarDados(Unidade u) throws ControladorException{
        if (u.getDescricao() == null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"),
                    "ControladorUnidade.validarDados()");
        }
    }
    
    public void verificarSePodeInserir(Unidade u) throws ControladorException{
        List<Unidade> lista=null;
        try {
            lista = rpUnid.pesquisar(u.getDescricao());
            if(lista.size() > 0){
                throw new ControladorException(rb.getString("CtrlUnidExiste"),
                        "ControladorUnidade.verificarSePodeInserir()");
            }
        } catch (ConexaoException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " unidade.",
                    "ControladorUnidade.verificarSePodeInserir()");
        } catch (RepositorioException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " unidade.",
                    "ControladorUnidade.verificarSePodeInserir()");
        }
    }
    
    public void inserir(Unidade u) throws ControladorException {
        try {
            rpUnid.inserir(u);
        } catch (ConexaoException ce) {
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " unidade.",
                    "ControladorUnidade.inserir()");
        } catch (RepositorioInserirException re) {
            throw new ControladorException(
                    rb.getString("CtrlErroInseir") + " unidade.",
                    "ControladorUnidade.inserir()");
        }
    }
    
    public void alterar(Unidade u) throws ControladorException {
        try{
            rpUnid.alterar(u);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " unidade.",
                    "ControladorUnidade.alterar()");
        }
        catch(RepositorioAlterarException rae){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " unidade.",
                    "ControladorUnidade.alterar()");
        }
    }
    
    public void verificarSePodeExcluir(Unidade u) throws ControladorException{
        //É preciso verificar se algum produto está usando o local que será excluido
        //caso afirmativo, abortar exclusão
    }
    
    public void excluir(Unidade u) throws ControladorException{
        try{
            rpUnid.excluir(u.getCodUnid());
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " unidade.",
                    "ControladorUnidade.excluir()");
        }
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " unidade.",
                    "ControladorUnidade.excluir()");
        }
    }
    
    public List<Unidade> pesquisar(String descricao) throws ControladorException{
        try{
            return rpUnid.pesquisar(descricao);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroPesqIndisp") + " unidade.",
                    "ControladorUnidade.pesquisar()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroPesquisar") + " unidade.",
                    "ControladorUnidade.pesquisar()");
        }
    }
    
    public Unidade trazer(Integer cod) throws ControladorException{
        try{
            return rpUnid.pesqCod(cod);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " unidade.",
                    "ControladorUnidade.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " unidade.",
                    "ControladorUnidade.trazer()");
        }
    }
    
}
