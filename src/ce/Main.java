package ce;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Categoria;
import ce.model.dao.IRepositorioCategoria;
import ce.model.dao.RepositorioCategoria;
import java.util.ArrayList;

/**
 *
 * @author professor
 */
public class Main {
    private static IRepositorioCategoria rep;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        rep = new RepositorioCategoria();
        //testaInserir();
        testaListar();
    }

    private static void testaInserir() {
        Categoria c = new Categoria("Roupa");
        try {
            rep.incluir(c);
            System.out.println("Inserido com sucesso!");
        } catch (ConexaoException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } catch (RepositorioException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }

    private static void testaListar() {
        System.out.println("LISTANDO CATEGORIA...");
        try {
            for (Categoria item : (ArrayList<Categoria>) rep.listar()) {
                System.out.println(item.getCodCateg() + " - " + item.getDescricao());
            }
        } catch (ConexaoException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } catch (RepositorioException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }
//1º TESTE SUBVERSION
    
}
