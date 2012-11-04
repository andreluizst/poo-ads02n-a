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
import ce.model.basica.LocalEstoque;
import ce.model.dao.RepositorioLocalEstoque;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Andre
 */
public class ControladorLocalEstoque {
    private RepositorioLocalEstoque rpLocalE= new RepositorioLocalEstoque();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.util.Erro");
    
    public void validarDados(LocalEstoque le) throws ControladorException{
        if (le.getDescricao() == null){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"),
                    "ControladorLocalEstoque.validarDados()");
        }
    }
    
    public void verificarSePodeInserir(LocalEstoque le) throws ControladorException{
        List<LocalEstoque> lista=null;
        try {
            lista = rpLocalE.pesquisar(le.getDescricao());
            if(lista.size() > 0){
                throw new ControladorException(rb.getString("CtrlErroLocalEstExiste"),
                        "ControladorLocalEstoque.verificarSePodeInserir()");
            }
        } catch (ConexaoException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " local de estoque.",
                    "ControladorLocalEstoque.verificarSePodeInserir()");
        } catch (RepositorioException ex) {
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " local de estoque.",
                    "ControladorLocalEstoque.verificarSePodeInserir()");
        }
    }
    
    public void inserir(LocalEstoque le) throws ControladorException {
        try {
            rpLocalE.inserir(le);
        } catch (ConexaoException ce) {
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " local de estoque.",
                    "ControladorLocalEstoque.inserir()");
        } catch (RepositorioInserirException re) {
            throw new ControladorException(
                    rb.getString("CtrlErroInseir") + " local de estoque.",
                    "ControladorLocalEstoque.inserir()");
        }
    }
    
    public void alterar(LocalEstoque le) throws ControladorException {
        try{
            rpLocalE.alterar(le);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " local de estoque.",
                    "ControladorLocalEstoque.alterar()");
        }
        catch(RepositorioAlterarException rae){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " local de estoque.",
                    "ControladorLocalEstoque.alterar()");
        }
    }
    
    public void verificarSePodeExcluir(LocalEstoque le) throws ControladorException{
        //É preciso verificar se alguma entrada está usando o local que será excluido
        //caso afirmativo, abortar exclusão
    }
    
    public void excluir(LocalEstoque le) throws ControladorException{
        try{
            rpLocalE.excluir(le.getCodLocal());
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " local de estoque.",
                    "ControladorLocalEstoque.excluir()");
        }
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " local de estoque.",
                    "ControladorLocalEstoque.excluir()");
        }
    }
    
    public List<LocalEstoque> pesquisar(String nome) throws ControladorException{
        try{
            return rpLocalE.pesquisar(nome);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroPesqIndisp") + " local de estoque.",
                    "ControladorLocalEstoque.pesquisar()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroPesquisar") + " local de estoque.",
                    "ControladorLocalEstoque.pesquisar()");
        }
    }
    
    public LocalEstoque trazer(Integer cod) throws ControladorException{
        try{
            return rpLocalE.pesqCod(cod);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " local de estoque.",
                    "ControladorLocalEstoque.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " local de estoque.",
                    "ControladorLocalEstoque.trazer()");
        }
    }
    
}
