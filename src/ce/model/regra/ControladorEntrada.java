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
import ce.erro.RepositorioForeignKeyException;
import ce.model.basica.Entrada;
import ce.model.dao.RepositorioEntrada;
import ce.model.dao.IRepositorioEntrada;
import ce.model.dao.RepositorioProduto;
import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioFornecedor;
import ce.model.dao.IRepositorioFornecedor;
import ce.util.ValidarStringData;
import java.util.List;
import java.util.ResourceBundle;
/**
 *
 * @author Andre
 */
public class ControladorEntrada {
    private IRepositorioEntrada rpEnt= new RepositorioEntrada();
    private IRepositorioProduto rpProd= new RepositorioProduto();
    private IRepositorioFornecedor rpForn= new RepositorioFornecedor();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
    
    public void validarDados(Entrada e) throws ControladorException{
        /*O código/número da entrada é autoincrement
        if (e.getCodEntrada() == null || (e.getCodEntrada() == 0)){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }*/
        if (!ValidarStringData.execute(e.getDataEntrada())){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
        if ((e.getQtde() == null) || (e.getQtde() <= 0)){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
        if ((e.getFornecedor() == null) || (e.getFornecedor().getCodForn()==0)){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
        if ((e.getProduto()==null) || (e.getProduto().getCodProd()==0)){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
        if ((e.getLote() == null) || (e.getLote().compareTo("")==0)){
            throw new ControladorException(rb.getString("CtrlErroValInvalido"));
        }
    }
    
    public void inserir(Entrada e) throws ControladorException{
        try{
            rpEnt.inserir(e);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " entrada.",
                    "ControladorEntrada.inserir()");
        }
        catch(RepositorioInserirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroInserir") + " entrada.",
                    "ControladorEntrada.inserir()");
        }
    }
    
    public void alterar(Entrada e) throws ControladorException{
        try{
            rpEnt.alterar(e);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " entrada.",
                    "ControladorEntrada.alterar()");
        }
        catch(RepositorioAlterarException rae){
            String msg= rae.getMessage();
            if (msg == null || msg.contains(rb.getString("CtrlErroAtlzQtde"))){
                throw new ControladorException(rae, "ControladorEntrada.alterar()");
            }
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar" + " entrada."),
                    "ControladorEntrada.alterar()");
        }
    }
    
    public void verificarSeExiste(Entrada e) throws ControladorException {
        try{
            Entrada ent= rpEnt.pesqNum(e.getCodEntrada());
            if (ent == null){
                throw new ControladorException(rb.getString("CtrlEntNaoExiste"),
                        "ControladorEntrada.verificarSeExiste()");
            }
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " entrada.",
                    "ControladorEntrada.verificarSeExiste()");
        }
        catch(RepositorioPesquisarException pe){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " entrada.",
                    "ControladorEntrada.verificarSeExiste()");
        }
    }
    
    public void excluir(Entrada e) throws ControladorException{
        try{
            rpEnt.excluir(e);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " entrada.",
                    "ControladorEntrada.excluir()");
        }
        catch(RepositorioForeignKeyException rfke){
            throw new ControladorException(
                    rb.getString("CtrlErroForeignKeyForn"),
                    "ControladorEntrada.excluir()");
        }
        catch(RepositorioExcluirException re){
            String msg= re.getMessage();
            if (msg == null || msg.contains(rb.getString("CtrlErroAtlzQtde"))){
                throw new ControladorException(re, "ControladorEntrada.excluir()");
            }
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " entrada.",
                    "ControladorEntrada.excluir()");
        }
    }
    
    public List<Entrada> listar() throws ControladorException{
        try{
            return rpEnt.listar();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroListIndisp") + " entrada.",
                    "ControladorEntrada.listar()");
        }
        catch(RepositorioListarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroListar") + " entrada.",
                    "ControladorEntrada.listar()");
        }
    }
    
    public Entrada trazer(Integer num) throws ControladorException{
        try{
            Entrada ent= rpEnt.pesqNum(num);
            /*Para deixar o método atômico o mesmo não deve executar verificações
             * Usar o metódo verificarSeExiste(Entrada e) antes.
             * if (ent == null){
                throw new ControladorException(rb.getString("CtrlEntNaoExiste"),
                        "ControladorEntrada.trazer()");
            }*/
            return ent;
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " entrada.",
                    "ControladorEntrada.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " entrada.",
                    "ControladorEntrada.trazer()");
        }
    }
    
}
