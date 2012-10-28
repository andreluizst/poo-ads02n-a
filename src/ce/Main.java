package ce;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Categoria;
import ce.model.basica.Unidade;
import ce.model.basica.Produto;
import ce.model.basica.Fornecedor;
import ce.model.basica.LocalEstoque;
import ce.model.basica.Funcionario;
import ce.model.dao.IRepositorioCategoria;
import ce.model.dao.RepositorioCategoria;
import ce.model.dao.IRepositorioUnidade;
import ce.model.dao.RepositorioUnidade;
import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioProduto;
import ce.model.dao.IRepositorioFornecedor;
import ce.model.dao.RepositorioFornecedor;
import ce.model.dao.IRepositorioLocalEstoque;
import ce.model.dao.RepositorioLocalEstoque;
import ce.model.dao.IRepositorioFuncionario;
import ce.model.dao.RepositorioFuncionario;
import java.util.ArrayList;

/**
 *
 * @author professor
 */
public class Main {
    private static IRepositorioCategoria rpCateg;
    private static IRepositorioUnidade rpUnid;
    private static IRepositorioProduto rpProd;
    private static IRepositorioFornecedor rpForn;
    private static IRepositorioLocalEstoque rpLclEstoque;
    private static IRepositorioFuncionario rpFun;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        rpCateg = new RepositorioCategoria();
        rpProd= new RepositorioProduto();
        rpUnid=new RepositorioUnidade();
        rpForn= new RepositorioFornecedor();
        rpLclEstoque= new RepositorioLocalEstoque();
        rpFun= new RepositorioFuncionario();
        testaInserir(); testaListar();
        testaAlterar(); testaListar();
        //testaExcluir(); testaListar();
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
            /*LocalEstoque- TESTADA e OK
            rpLclEstoque.inserir(new LocalEstoque("SA-C04P03V01"));
            rpLclEstoque.inserir(new LocalEstoque("SB-C02P01V02"));
            */
            /*Fornecedor - TESTADA E OK.
            rpForn.inserir(new Fornecedor("Infobox", "10345839000209",
                    "Rua Padre Carapuceiro", 345, "loja 02", "Boa Viagem",
                    "Recife", "PE", "51040100", "8134768899", "vendas@infobox.com.br"));
            rpForn.inserir(new Fornecedor("Nagem", "11435908001203",
                    "Av Cons. Aguiar", 1456, "", "Boa Viagem",
                    "Recife", "PE", "51055060", "8122212325", "vendas@nagem.com.br"));
            rpForn.inserir(new Fornecedor("Infohouse", "35456123000102",
                    "Av Cons. Aguiar", 1002, "", "Boa Viagem",
                    "Recife", "PE", "51055060", "8134622233", "vendas@infohouse.com.br"));
            */
            //Funcionario.inserir() - TESTADO E OK
            /*rpFun.inserir(new Funcionario("98512536598", "Maria da Silva", "15/05/1965", 
                    "Rua Frei Damião", 20, "", "Afogados", "Recife", "PE", 
                    "50030450", "8133442560", "maria.silva@gmail.com"));*
            rpFun.inserir(new Funcionario("25648712341", "João Souza de Andrade",
                    "15/05/1962", "Av Recife", 1520, "", "Ipsepe",
                    "Recife", "PE", "51020310", "8132456655", 
                    "joao.andrade@gmail.com"));
            rpFun.inserir(new Funcionario("00011122200", "Milena Tavares",
                    "13/09/1980", "Av Brasil", 840, "", "Prazeres",
                    "Jaboarão dos Guararapes", "PE", "52032024", "8134656987", 
                    "milena.tavares@hotmail.com"));*/
            System.out.println("Inserido com sucesso!");
        } catch (ConexaoException ex) {
            System.out.println("ERRO de conexão: " + ex.getMessage());
        }
        catch(RepositorioInserirException e){
            System.out.println("ERRO de inclusao " + e.getPathClassCall() + ": " + e.getMessage());
        }
        catch (RepositorioException ex) {
            System.out.println("ERRO " + ex.getPathClassCall() + ": " + ex.getMessage());
        }
    }
    
    private static void testaAlterar(){
        //Produto p1;
        Fornecedor f=null;
        Funcionario fun=null;
        try{
            /*
            //Produto.alterar() - TESTADO E OK.
            //p1= rpProd.pesqCodProd(1, true);
            //p1.getFornecedores().add(rpForn.pesqCodForn(1));
            //p1.getFornecedores().add(rpForn.pesqCodForn(3));
            //p1.getFornecedores().add(rpForn.pesqCodForn(2));
            //p1.getFornecedores().remove(1);
            //p1.getFornecedores().clear();
            System.out.println("rpProd.alterar(p1);...");
            rpProd.alterar(p1);*/
            //Fornecedor.alterar() - TESTADO E OK
            /*
            f= rpForn.pesqCodForn(1, true);
            //f.getProdutos().add(rpProd.pesqCodProd(1, false));
            //f.getProdutos().add(rpProd.pesqCodProd(2, false));
            //f.getProdutos().remove(1);
            //f.getProdutos().clear();
            rpForn.alterar(f);
            */
            //Funcionario.alterar() - TESTADO E OK
            fun= rpFun.pesqCpf("00011122200");
            /*fun.setNome("Milena Tavares");
            fun.setNome("fui alterado");
            */
            rpFun.alterar(fun);
            
            System.out.println("Alterado com sucesso!");
        }
        catch(ConexaoException ec){
            System.out.println("ERRO conexão: " + ec.getMessage());
        }
        /*catch(RepositorioPesquisarException epr){
            System.out.println("ERRO de pesquisa " + epr.getPathClassCall() + ": " + epr.getMessage());
        }*/
        catch(RepositorioAlterarException ear){
            System.out.println("ERRO de alteração " + ear.getPathClassCall() + ": " + ear.getMessage());
        }
        catch(RepositorioException re){
            System.out.println("testaAlterar() - ERRO " + re.getPathClassCall() + ": " + re.getMessage());
        }
    }

    private static void testaListar() {
        System.out.println("LISTANDO...");
        try {
            
            //*Categoria - testada e ok
             for (Categoria item : (ArrayList<Categoria>) rpCateg.listar()) {
                System.out.println(item.getCodCateg() + " - " + item.getDescricao());
             }//*/
            //*Unidade - TESTADA E OK
            for (Unidade item : (ArrayList<Unidade>) rpUnid.listar()) {
                System.out.println(item.getCodUnid() + " - " + item.getDescricao());
            }//*/
            
            //Produto - parcialmente ok. Falta testar a lista de fornecedores
            //dessa classe
            for (Produto item : (ArrayList<Produto>) rpProd.listar()) {
                System.out.println(item.getCategoria().getDescricao() + " - "
                        + item.getCodProd() + " - " + item.getDescProd());
            }
            //LocalEstoque - TESTADA E OK
            //Fornecedor - TESTADA E OK
            System.out.println("Listando LocalEstoque...");
            for (LocalEstoque item : (ArrayList<LocalEstoque>) rpLclEstoque.listar()) {
                System.out.println(item.getCodLocal() + " - " + item.getDescricao());
            }
            /*System.out.println("Listando Fornecedor...");
            for (Fornecedor item : (ArrayList<Fornecedor>) rpForn.listar()) {
                System.out.println(item.getCodForn() + " - " + item.getNome()
                        + " - " + item.getCnpj() + " - " + item.getFone());
                System.out.println(item.getLogradouro() + " " + item.getComp()
                        + " - " + item.getNum() + " - " + item.getBairro()
                        + " - " + item.getMunicipio() + " - " + item.getUf());
            }*/
            //Funcionario.listar() - TESTADO E OK
            System.out.println("Listando Funcionario...");
            for (Funcionario fun: rpFun.listar()){
                System.out.println(fun.toString());
            }
        }
        catch (ConexaoException ex) {
            System.out.println("ERRO conexão: " + ex.getMessage());
        } 
        catch(RepositorioListarException e){
            System.out.println("ERRO ao listar " + e.getPathClassCall()+ ": " + e.getMessage());
        }
        catch (RepositorioException ex) {
            System.out.println("ERRO repositório: " + ex.getPathClassCall() + ex.getMessage());
        }
    }
    
    public static void testaExcluir(){
        
        try{
            rpFun.excluir("25648712341");
        }
        catch (ConexaoException ex) {
            System.out.println("ERRO conexão: " + ex.getMessage());
        } 
        catch(RepositorioExcluirException e){
            System.out.println("ERRO de exclusão em " + e.getPathClassCall()+ ": " + e.getMessage());
        }
        catch (RepositorioException ex) {
            System.out.println("ERRO repositório: " + ex.getPathClassCall() + ex.getMessage());
        }
    }
    
}
