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
    
    public void validar(Fornecedor f) throws ControladorException{
        /*boolean contemErro= false;
        List fieldsName= new ArrayList();
        List fieldsMsg= new ArrayList();
        if (f.getNome() == null){
            fieldsName.add("Nome");
            fieldsMsg.add("O campo não pode estar em branco.");
            contemErro=true;
        }*/
        if (f.getNome() == null){
            throw new ControladorException("O campo nome deve ser preenchido.");
        }
        if (f.getCnpj() == null){
            throw new ControladorException("CNPJ inválido.");
        }
        if (f.getLogradouro() == null){
            throw new ControladorException("Informe o logradouro do fornecedor");
        }
        if (f.getBairro() == null){
            throw new ControladorException("Informe o Bairro do fornecedor");
        }
        if (f.getMunicipio() == null){
            throw new ControladorException("Informe o municipio do fornecedor");
        }
        if (f.getUf() == null){
            throw new ControladorException("Informe a sigla do estado do fornecedor");
        }
        if (f.getCep() == null){
            throw new ControladorException("Informe o CEP");
        }
        if (f.getFone() == null){
            throw new ControladorException("Informe o número do telefone do fornecedor");
        }
    }
    
    public void inserir(Fornecedor f) throws ControladorException{
        try{
            rpForn.inserir(f);
        }
        catch(RepositorioInserirException | ConexaoException e){
            throw new ControladorException(e, "ControladorFornecedor.inserir()");
        }
    }
    
    public void alterar(Fornecedor f) throws ControladorException{
        try{
            rpForn.alterar(f);
        }
        catch(RepositorioAlterarException | ConexaoException e){
            throw new ControladorException(e, "ControladorFornecedor.alterar()");
        }
    }
    
    public void excluir(Fornecedor f) throws ControladorException{
        try{
            rpForn.excluir(f.getCodForn());
        }
        catch(RepositorioException | ConexaoException e){
            throw new ControladorException(e, "ControladorFornecedor.excluir()");
        }
    }
}
