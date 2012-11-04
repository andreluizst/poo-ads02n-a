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
import ce.model.basica.Fornecedor;
//import ce.model.dao.RepositorioProduto;
import ce.model.dao.RepositorioFornecedor;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author andreluiz
 */
public class ControladorFornecedor {
    private RepositorioFornecedor rpForn= new RepositorioFornecedor();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.util.Erro");
    
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
        if (f.getNome() == null){
            throw new ControladorException("O campo nome deve ser preenchido.",
                    sPath);
        }
        if (f.getCnpj() == null){
            throw new ControladorException("CNPJ inválido.", sPath);
        }
        if (f.getLogradouro() == null){
            throw new ControladorException("Informe o logradouro do fornecedor",
                    sPath);
        }
        if (f.getBairro() == null){
            throw new ControladorException("Informe o Bairro do fornecedor", 
                    sPath);
        }
        if (f.getMunicipio() == null){
            throw new ControladorException(
                    "Informe o municipio do fornecedor", sPath);
        }
        if (f.getUf() == null){
            throw new ControladorException(
                    "Informe a sigla do estado do fornecedor", sPath);
        }
        if (f.getCep() == null){
            throw new ControladorException("Informe o CEP", sPath);
        }
        if (f.getFone() == null){
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
    
    public void verificarSePodeExcluir(Fornecedor f) throws ControladorException {
        try{
            Fornecedor forn= rpForn.pesqCodForn(f.getCodForn(), false);
            if (forn == null){
                throw new ControladorException(rb.getString("CtrlFornNaoExiste"),
                        "ControladorFornecedor.verificarSePodeExcluir()");
            }
            //Verificar se este fornecedor está em uso nos reposiórios e entrada ou saída
            //RepositorioEntrada rpEnt= new RepositorioEntrada();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " fornecedor.",
                    "ControladorFornecedor.verificarSePodeExcluir()");
        }
        catch(RepositorioPesquisarException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " fornecedor.",
                    "ControladorFornecedor.verificarSePodeExcluir()");
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
    
    public Fornecedor trazer(Integer cod) throws ControladorException{
        try{
            Fornecedor forn= rpForn.pesqCodForn(cod, false);
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