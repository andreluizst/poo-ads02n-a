/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.regra;

import ce.erro.ConexaoException;
import ce.erro.ControladorException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioPesquisarException;
import ce.erro.RepositorioListarException;
import ce.model.basica.Saida;
import ce.model.dao.RepositorioSaida;
import ce.model.dao.IRepositorioSaida;
import ce.model.dao.RepositorioProduto;
import ce.model.dao.IRepositorioProduto;
import ce.util.ValidarStringData;
import java.util.List;
import java.util.ResourceBundle;
/**
 *
 * @author Andre
 */
public class ControladorSaida {
    private IRepositorioSaida rpSaida= new RepositorioSaida();
    private IRepositorioProduto rpProd= new RepositorioProduto();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
    
    public void validarDados(Saida s) throws ControladorException{
        if (!ValidarStringData.execute(s.getDataSaida())){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
        if ((s.getQtde() == null) || (s.getQtde() <= 0)){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
        if ((s.getEntrada() == null) || (s.getEntrada().getCodEntrada() == 0)){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
    }
    
    public void inserir(Saida s) throws ControladorException{
        try{
            rpSaida.inserir(s);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " saída.",
                    "ControladorSaida.inserir()");
        }
        catch(RepositorioInserirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroInserir") + " saída.",
                    "ControladorSaida.inserir()");
        }
    }
    
    public void alterar(Saida s) throws ControladorException{
        try{
            rpSaida.alterar(s);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " saída.",
                    "ControladorSaida.alterar()");
        }
        catch(RepositorioAlterarException rae){
            String msg= rae.getMessage();
            if (msg == null || msg.contains(rb.getString("CtrlErroAtlzQtde"))){
                throw new ControladorException(rae, "ControladorSaida.alterar()");
            }
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar" + " saída."),
                    "ControladorSaida.alterar()");
        }
    }
    
    public void verificarSeExiste(Saida s) throws ControladorException {
        try{
            Saida saida= rpSaida.pesqNum(s.getCodSaida());
            if (saida == null){
                throw new ControladorException(rb.getString("CtrlSaiNaoExiste"),
                        "ControladorSaida.verificarSeExiste()");
            }
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " saída.",
                    "ControladorSaida.verificarSeExiste()");
        }
        catch(RepositorioPesquisarException pe){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " saída.",
                    "ControladorSaida.verificarSeExiste()");
        }
    }
    
    public void excluir(Saida s) throws ControladorException{
        try{
            rpSaida.excluir(s);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " saída.",
                    "ControladorSaida.excluir()");
        }
        /*catch(RepositorioForeignKeyException rfke){
            throw new ControladorException(
                    rb.getString("CtrlNaoPodeExcluirEnt"),
                    "ControladorSaida.excluir()");
        }*/
        catch(RepositorioExcluirException re){
            String msg= re.getMessage();
            if (msg == null || msg.contains(rb.getString("CtrlErroAtlzQtde"))){
                throw new ControladorException(re, "ControladorEntrada.excluir()");
            }
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " saída.",
                    "ControladorSaida.excluir()");
        }
    }
    
    public List<Saida> listar() throws ControladorException{
        try{
            return rpSaida.listar();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroListIndisp") + " saída.",
                    "ControladorSaida.listar()");
        }
        catch(RepositorioListarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroListar") + " saída.",
                    "ControladorSaida.listar()");
        }
    }
    
    public Saida trazer(Integer num) throws ControladorException{
        try{
            Saida saida= rpSaida.pesqNum(num);
            return saida;
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " saída.",
                    "ControladorSaida.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " saída.",
                    "ControladorSaida.trazer()");
        }
    }
    
}
