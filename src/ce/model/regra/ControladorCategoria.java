/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.regra;

import ce.erro.ConexaoException;
import ce.erro.ControladorException;
import ce.erro.RepositorioException;
import ce.model.basica.Categoria;
import ce.model.dao.RepositorioCategoria;
/**
 *
 * @author Andre
 */
public class ControladorCategoria {
    private RepositorioCategoria rpCateg = new RepositorioCategoria();
    
    public void validar(Categoria c) throws ControladorException{
        if(c.getDescricao()==null){
            throw new ControladorException("Campo com valor invalido.");
        }
    }

    public void verificar(Categoria c) throws ControladorException{
        try {
            Categoria aux = rpCateg.pesquisar(c.getDescricao());
            if(aux!=null){
                throw new ControladorException("Registro já existe.");
            }
        } catch (ConexaoException ex) {
            throw new ControladorException("VERIF Estou indisponível.");
        } catch (RepositorioException ex) {
            throw new ControladorException("VERIF Fiz bestera.");
        }
    }

    public void inserir(Categoria c) throws ControladorException {
        try {
            rpCateg.incluir(c);
        } catch (ConexaoException ex) {
            throw new ControladorException("Estou indisponível.");
        } catch (RepositorioException ex) {
            throw new ControladorException("Fiz bestera.");
        }
    }
}
