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
import ce.model.basica.Categoria;
import ce.model.dao.RepositorioCategoria;
/**
 *
 * @author Andre
 */
public class ControladorCategoria {
    private RepositorioCategoria rpCateg = new RepositorioCategoria();
    
    public void validar(Categoria c) throws ControladorValidarException{
        if(c.getDescricao()==null){
            throw new ControladorValidarException("Campo com valor invalido.");
        }
    }

    public void verificar(Categoria c) throws ControladorVerificarException{
        try {
            Categoria aux = rpCateg.pesquisar(c.getDescricao());
            if(aux!=null){
                throw new ControladorVerificarException("Registro já existe.");
            }
        } catch (ConexaoException ex) {
            throw new ControladorVerificarException("VERIF Estou indisponível.");
        } catch (RepositorioException ex) {
            throw new ControladorVerificarException("VERIF Fiz bestera.");
        }
    }

    public void inserir(Categoria c) throws ControladorInserirException {
        try {
            rpCateg.incluir(c);
        } catch (ConexaoException ex) {
            throw new ControladorInserirException("Estou indisponível.");
        } catch (RepositorioException ex) {
            throw new ControladorInserirException("Fiz bestera.");
        }
    }
}
