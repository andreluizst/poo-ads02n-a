package ce;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Categoria;
import ce.model.basica.Unidade;
import ce.model.basica.Produto;
import ce.model.dao.IRepositorioCategoria;
import ce.model.dao.RepositorioCategoria;
import ce.model.dao.IRepositorioUnidade;
import ce.model.dao.RepositorioUnidade;
import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioProduto;
import java.util.ArrayList;

/**
 *
 * @author professor
 */
public class Main {
    private static IRepositorioCategoria rpCateg;
    private static IRepositorioUnidade rpUnid;
    private static IRepositorioProduto rpProd;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        rpCateg = new RepositorioCategoria();
        rpProd= new RepositorioProduto();
        rpUnid=new RepositorioUnidade();
        //testaInserir();
        testaListar();
    }

    private static void testaInserir() {
        //Categoria c = new Categoria("Informática");//testado e ok
        Produto p;
        try {
            /*Categoria - TESTADA E OK
            rpCateg.incluir(c);
            rpCateg.incluir(new Categoria("Papelaria"));//testado e ok
            /*Unidade - TESTADA E OK*/
            /*rpUnid.inserir(new Unidade("Peça"));
            rpUnid.inserir(new Unidade("Caixa"));*/
            
            //Produto - TESTADO E OK
            
            p= new Produto("Monitor LCD 22", 100.00, 
                    50.00, 100.00, 0,
                    rpCateg.pesquisar("Informática"), 
                    rpUnid.pesqCodUnid(1));
            /*rpProd.inserir(p);
            rpProd.inserir(new Produto("Notebook DELL Inspiron 14r", 200.00,
                    40.00, 80.00, 0, rpCateg.pesquisar("Informática"),
                    rpUnid.pesqCodUnid(1)));*/
            System.out.println("Inserido com sucesso!");
        } catch (ConexaoException ex) {
            System.out.println("ERRO conexão: " + ex.getMessage());
        } catch (RepositorioException ex) {
            System.out.println("ERRO repositório: " + ex.getMessage());
        }
    }

    private static void testaListar() {
        System.out.println("LISTANDO...");
        try {
            
            /*Categoria - testada e ok
             for (Categoria item : (ArrayList<Categoria>) rpCateg.listar()) {
                System.out.println(item.getCodCateg() + " - " + item.getDescricao());
             }*/
            /*Unidade - TESTADA E OK
            for (Unidade item : (ArrayList<Unidade>) rpUnid.listar()) {
                System.out.println(item.getCodUnid() + " - " + item.getDescricao());
            }*/
            
            //Produto - dando erro. Possivelmente pq o campo codUnid da tabela
            //é NOT NULL e a classe Produto não está tratando esse campo.
            // INCLUIR ESTE ATRIBUTO, mas antes testar o RepositorioUnidade
            
            for (Produto item : (ArrayList<Produto>) rpProd.listar()) {
                System.out.println(item.getCategoria().getDescricao() + " - "
                        + item.getCodProd() + " - " + item.getDescProd());
            }
        } catch (ConexaoException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        } catch (RepositorioException ex) {
            System.out.println("ERRO: " + ex.getMessage());
        }
    }
//1º TESTE SUBVERSION
    
}
