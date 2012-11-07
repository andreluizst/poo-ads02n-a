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
import ce.erro.RepositorioForeignKeyException;
import ce.erro.RepositorioPesquisarException;
import ce.erro.RepositorioListarException;
import ce.model.basica.Fornecedor;
//import ce.model.dao.RepositorioProduto;
import ce.model.dao.RepositorioFornecedor;
import ce.model.dao.IRepositorioFornecedor;
import ce.util.VerificarCpfCnpj;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author andreluiz
 */
public class ControladorFornecedor {
    private IRepositorioFornecedor rpForn= new RepositorioFornecedor();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
    
    public void validarDados(Fornecedor f) throws ControladorException{
        /*boolean contemErro= false;
        List fieldsName= new ArrayList();
        List fieldsMsg= new ArrayList();
        if (f.getNome() == null){
            fieldsName.add("Nome");
            fieldsMsg.add("O campo não pode estar em branco.");
            contemErro=true;
        }*/
        String sPath="ControladorFornecedor.validarDados()";
        if ((f.getNome() == null) || (f.getNome().compareTo("")==0)){
            throw new ControladorException("O campo nome deve ser preenchido.",
                    sPath);
        }
        //Substituir pela linha abaixo após os testes
        //if (!VerificarCpfCnpj.executar(f.getCnpj())){
        if ((f.getCnpj() == null) || (f.getCnpj().compareTo("")==0)){
            throw new ControladorException("CNPJ inválido.", sPath);
        }
        if ((f.getLogradouro() == null)||(f.getLogradouro().compareTo("")==0)){
            throw new ControladorException("Informe o logradouro do fornecedor",
                    sPath);
        }
        if ((f.getBairro() == null) || (f.getBairro().compareTo("")==0)){
            throw new ControladorException("Informe o Bairro do fornecedor", 
                    sPath);
        }
        if ((f.getMunicipio() == null) || (f.getMunicipio().compareTo("")==0)){
            throw new ControladorException(
                    "Informe o municipio do fornecedor", sPath);
        }
        if ((f.getUf() == null) || (f.getUf().compareTo("")==0)){
            throw new ControladorException(
                    "Informe a sigla do estado do fornecedor", sPath);
        }
        if ((f.getCep() == null) || (f.getCep().compareTo("")==0)){
            throw new ControladorException("Informe o CEP", sPath);
        }
        if ((f.getFone() == null) || (f.getFone().compareTo("")==0)){
            throw new ControladorException(
                    "Informe o número do telefone do fornecedor", sPath);
        }
    }
    
    
    public void verificarSePodeInserir(Fornecedor f) throws ControladorException{
        try{
            Fornecedor forn= rpForn.pesqCnpj(f.getCnpj(), false);
            if (forn != null){
                throw new ControladorException(rb.getString("CtrlFornExiste"),
                        "ControladorFornecedor.verificarSePodeInserir()");
            }
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " fornecedor.",
                    "ControladorFornecedor.verificarSePodeInserir()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " fornecedor.",
                    "ControladorFornecedor.verificarSePodeInserir()");
        }
    }
    
    public void inserir(Fornecedor f) throws ControladorException{
        try{
            rpForn.inserir(f);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " fornecedor.",
                    "ControladorFornecedor.inserir()");
        }
        catch(RepositorioInserirException e){
            throw new ControladorException(
                    rb.getString("CtrlErroInserir") + " fornecedor.",
                    "ControladorFornecedor.inserir()");
        }
    }
    
    public void alterar(Fornecedor f) throws ControladorException{
        try{
            rpForn.alterar(f);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " fornecedor.",
                    "ControladorFornecedor.alterar()");
        }
        catch(RepositorioAlterarException e){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " fornecedor.",
                    "ControladorFornecedor.alterar()");
        }
    }
    
    public void verificarSeExiste(Fornecedor f) throws ControladorException {
        try{
            Fornecedor forn= rpForn.pesqCodForn(f.getCodForn(), false);
            if (forn == null){
                throw new ControladorException(rb.getString("CtrlFornNaoExiste"),
                        "ControladorFornecedor.verificarSeExiste()");
            }
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " fornecedor.",
                    "ControladorFornecedor.verificarSeExiste()");
        }
        catch(RepositorioPesquisarException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " fornecedor.",
                    "ControladorFornecedor.verificarSeExiste()");
        }
    }
    
    public void excluir(Fornecedor f) throws ControladorException{
        try{
            rpForn.excluir(f.getCodForn());
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " fornecedor.",
                    "ControladorFornecedor.excluir()");
        }
        catch(RepositorioForeignKeyException rfke){
            throw new ControladorException(
                    rb.getString("CtrlErroForeignKeyForn"),
                    "ControladorFornecedor.excluir()");
        }
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " fornecedor.",
                    "ControladorFornecedor.excluir()");
        }
    }
    
    public List<Fornecedor> listar() throws ControladorException{
        try{
            return rpForn.listar();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroListIndisp") + " fornecedor.",
                    "ControladorFornecedor.listar()");
        }
        catch(RepositorioListarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroListar") + " fornecedor.",
                    "ControladorFornecedor.listar()");
        }
    }
    
    public Fornecedor trazer(Integer cod, boolean comProds) throws ControladorException{
        try{
            Fornecedor forn= rpForn.pesqCodForn(cod, comProds);
            if (forn == null){
                throw new ControladorException(rb.getString("CtrlFornNaoExiste"),
                        "ControladorFornecedor.trazer()");
            }
            return forn;
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " fornecedor.",
                    "ControladorFornecedor.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " fornecedor.",
                    "ControladorFornecedor.trazer()");
        }
    }
    
    public Fornecedor trazer(String cnpj, boolean comProds) throws ControladorException{
        try{
            Fornecedor forn= rpForn.pesqCnpj(cnpj, comProds);
            if (forn == null){
                throw new ControladorException(rb.getString("CtrlFornNaoExiste"),
                        "ControladorFornecedor.trazer()");
            }
            return forn;
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " fornecedor.",
                    "ControladorFornecedor.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " fornecedor.",
                    "ControladorFornecedor.trazer()");
        }
    }
    
    public List<Fornecedor> pesquisar(String nome) throws ControladorException{
        try{
            return rpForn.pesquisar(nome);
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroPesqIndisp") + " fornecedor.",
                    "ControladorFornecedor.pesquisar()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroPesquisar") + " fornecedor.",
                    "ControladorFornecedor.pesquisar()");
        }
    }
}
