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
import ce.model.basica.Entrada;
import ce.model.dao.RepositorioEntrada;
import ce.model.dao.IRepositorioEntrada;
import ce.model.dao.RepositorioProduto;
import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioFornecedor;
import ce.model.dao.IRepositorioFornecedor;
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
        //entrar código
    }
}
