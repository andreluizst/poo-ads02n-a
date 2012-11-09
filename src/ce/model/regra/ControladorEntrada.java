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
import java.util.List;
import java.util.ResourceBundle;
/**
 *
 * @author Andre
 */
public class ControladorEntrada {
    private IRepositorioEntrada rpEnt= new RepositorioEntrada();
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
}
