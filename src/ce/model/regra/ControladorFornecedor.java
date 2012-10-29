/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.regra;

import ce.erro.ConexaoException;
import ce.erro.ControladorException;
import ce.erro.ControladorInserirException;
import ce.erro.ControladorAlterarException;
import ce.erro.ControladorExcluirException;
import ce.erro.ControladorValidarException;
import ce.erro.ControladorVerificarException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioInserirException;
import ce.model.basica.Fornecedor;
//import ce.model.dao.RepositorioProduto;
import ce.model.dao.RepositorioFornecedor;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author andreluiz
 */
public class ControladorFornecedor {
    private RepositorioFornecedor rpForn= new RepositorioFornecedor();
    
    public void validar(Fornecedor f) throws ControladorValidarException{
        /*boolean contemErro= false;
        List fieldsName= new ArrayList();
        List fieldsMsg= new ArrayList();
        if (f.getNome() == null){
            fieldsName.add("Nome");
            fieldsMsg.add("O campo não pode estar em branco.");
            contemErro=true;
        }*/
        if (f.getNome() == null){
            throw new ControladorValidarException("O campo nome deve ser preenchido.");
        }
        if (f.getCnpj() == null){
            throw new ControladorValidarException("CNPJ inválido.");
        }
        if (f.getLogradouro() == null){
            throw new ControladorValidarException("Informe o logradouro do fornecedor");
        }
        if (f.getBairro() == null){
            throw new ControladorValidarException("Informe o Bairro do fornecedor");
        }
        if (f.getMunicipio() == null){
            throw new ControladorValidarException("Informe o municipio do fornecedor");
        }
        if (f.getUf() == null){
            throw new ControladorValidarException("Informe a sigla do estado do fornecedor");
        }
        if (f.getCep() == null){
            throw new ControladorValidarException("Informe o CEP");
        }
        if (f.getFone() == null){
            throw new ControladorValidarException("Informe o número do telefone do fornecedor");
        }
    }
    
    public void inserir(Fornecedor f) throws ControladorInserirException{
        try{
            rpForn.inserir(f);
        }
        catch(RepositorioInserirException | ConexaoException e){
            throw new ControladorInserirException(e, "ControladorFornecedor.inserir()");
        }
    }
}
