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
import ce.model.basica.Funcionario;
import ce.model.dao.RepositorioFuncionario;
import ce.model.dao.IRepositorioFuncionario;
import java.util.List;
import java.util.ResourceBundle;
import ce.util.ValidarStringData;
import ce.util.VerificarCpfCnpj;

/**
 *
 * @author Andre
 */
public class ControladorFuncionario {
    private IRepositorioFuncionario rpFun= new RepositorioFuncionario();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.util.Erro");
    
    
    public void validarDados(Funcionario f) throws ControladorException{
        //String sPath="ControladorFuncionario.validarDados()";
        if ((f.getNome() == null) || (f.getNome().compareTo("")==0)){
            throw new ControladorException("O campo nome deve ser preenchido.");
                    //sPath);
        }
        if ((f.getCpf() == null) || (f.getCpf().compareTo("")==0)){
            throw new ControladorException("O campo CPF deve ser preenchido.");
                    //sPath);
        }
        if (!VerificarCpfCnpj.executar(f.getCpf())){
            throw new ControladorException("CPF inválido!");
        }
        if (!ValidarStringData.execute(f.getDtNasc())){
           throw new ControladorException("O campo data deve ser preenchido com uma data válida.");
        }
        if ((f.getLogradouro() == null) || (f.getLogradouro().compareTo("")==0)){
            throw new ControladorException("O campo logradouro deve ser preenchido.");
                    //sPath);
        }
        if ((f.getBairro() == null) || (f.getBairro().compareTo("")==0)){
            throw new ControladorException("O campo bairro deve ser preenchido.");
        }
        if ((f.getMunicipio() == null) || (f.getMunicipio().compareTo("")==0)){
            throw new ControladorException("O campo municipio deve ser preenchido.");
        }
        if ((f.getUf() == null) || (f.getUf().compareTo("")==0)){
            throw new ControladorException("O campo UF deve ser preenchido.");
        }
        if ((f.getCep() == null) || (f.getCep().compareTo("")==0)){
            throw new ControladorException("O campo CEP deve ser preenchido.");
        }
        if ((f.getFone() == null) || (f.getFone().compareTo("")==0)){
            throw new ControladorException("O campo fone deve ser preenchido.");
        }
    }
    
    public void verificarSePodeInserir(Funcionario f) throws ControladorException{
        try{
            Funcionario fun= rpFun.pesqCpf(f.getCpf());
            if (fun != null){
                throw new ControladorException(rb.getString("CtrlFunExiste"),
                        "ControladorFuncionario.verificarSePodeInserir()");
            }
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " funcionário.",
                    "ControladorFuncionario.verificarSePodeInserir()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " funcionário.",
                    "ControladorFuncionario.verificarSePodeInserir()");
        }
    }
    
    public void inserir(Funcionario f) throws ControladorException{
        try{
            rpFun.inserir(f);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroInsIndisp") + " funcionário.",
                    "ControladorFuncionario.inserir()");
        }
        catch(RepositorioInserirException e){
            throw new ControladorException(
                    rb.getString("CtrlErroInserir") + " funcionário.",
                    "ControladorFuncionario.inserir()");
        }
    }
    
    public void alterar(Funcionario f) throws ControladorException{
        try{
            rpFun.alterar(f);
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroAltIndisp") + " funcionário.",
                    "ControladorFuncionario.alterar()");
        }
        catch(RepositorioAlterarException e){
            throw new ControladorException(
                    rb.getString("CtrlErroAlterar") + " funcionário.",
                    "ControladorFuncionario.alterar()");
        }
    }
    
    public void verificarSePodeExcluir(Funcionario f) throws ControladorException {
        try{
            Funcionario fun= rpFun.pesqCpf(f.getCpf());
            if (fun == null){
                throw new ControladorException(rb.getString("CtrlFunNaoExiste"),
                        "ControladorFuncionario.verificarSePodeExcluir()");
            }
            //Verificar se este funcionário está em uso nos reposiórios de usuário, entrada ou saída
            //RepositorioEntrada rpEnt= new RepositorioEntrada();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroVerifIndisp") + " funcionário.",
                    "ControladorFuncionario.verificarSePodeExcluir()");
        }
        catch(RepositorioPesquisarException ie){
            throw new ControladorException(
                    rb.getString("CtrlErroVerificar") + " funcionário.",
                    "ControladorFuncionario.verificarSePodeExcluir()");
        }
    }
    
    public void excluir(Funcionario f) throws ControladorException{
        try{
            rpFun.excluir(f.getCpf());
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroDelIndisp") + " funcionário.",
                    "ControladorFuncionario.excluir()");
        }
        catch(RepositorioExcluirException re){
            throw new ControladorException(
                    rb.getString("CtrlErroExcluir") + " funcionário.",
                    "ControladorFuncionario.excluir()");
        }
    }
    
    public List<Funcionario> listar() throws ControladorException{
        try{
            return rpFun.listar();
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroListIndisp") + " funcionários.",
                    "ControladorFuncionario.listar()");
        }
        catch(RepositorioListarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroListar") + " funcionários.",
                    "ControladorFuncionario.listar()");
        }
    }
    
    public Funcionario trazer(String cpf) throws ControladorException{
        try{
            Funcionario fun= rpFun.pesqCpf(cpf);
            if (fun == null){
                throw new ControladorException(rb.getString("CtrlFunNaoExiste"),
                        "ControladorFuncionario.trazer()");
            }
            return fun;
        }
        catch(ConexaoException ce){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazerIndisp") + " funcionário.",
                    "ControladorFuncionario.trazer()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroTrazer") + " funcionario.",
                    "ControladorFuncionario.trazer()");
        }
    }
    
    public List<Funcionario> pesquisar(String nome) throws ControladorException{
        try{
            return rpFun.pesquisar(nome);
        }
        catch(ConexaoException e){
            throw new ControladorException(
                    rb.getString("CtrlErroPesqIndisp") + " funcionário.",
                    "ControladorFuncionario.pesquisar()");
        }
        catch(RepositorioPesquisarException re){
            throw new ControladorException(
                    rb.getString("CtrlErroPesquisar") + " funcionário.",
                    "ControladorFuncionario.pesquisar()");
        }
    }
    
}
