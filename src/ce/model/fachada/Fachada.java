/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.fachada;

import ce.erro.ControladorException;
import ce.erro.GeralException;
import ce.model.basica.*;
import ce.model.regra.*;
/**
 *
 * @author andreluiz
 */
public class Fachada {
    private ControladorCategoria ctrlCateg= new ControladorCategoria();
    private ControladorProduto ctrlProd= new ControladorProduto();
    private ControladorFornecedor ctrlForn= new ControladorFornecedor();
    private ControladorFuncionario ctrlFun= new ControladorFuncionario();
    private ControladorPerfil ctrlPer= new ControladorPerfil();
    private ControladorUsuario ctrlUsu= new ControladorUsuario();
    private ControladorLocalEstoque ctrlLocalE= new ControladorLocalEstoque();
    private ControladorEntrada ctrlE= new ControladorEntrada();
    //private ControladorSaida ctrlS= new ControladorSaida();
    
    public Fachada(){
        
    }
    
    public void incluir(Categoria c) throws GeralException{
        ctrlCateg.validarDados(c);
        ctrlCateg.verificarSePodeInserir(c);
        ctrlCateg.inserir(c);
    }
    
    public void incluir(Produto p) throws GeralException{
        ctrlProd.validarDados(p);
        ctrlProd.verificarSePodeInserir(p);
        ctrlProd.inserir(p);
    }
    
    public void incluir(Fornecedor f) throws GeralException{
        ctrlForn.validarDados(f);
        ctrlForn.verificarSePodeInserir(f);
        ctrlForn.inserir(f);
    }
    
    public void incluir(Funcionario f) throws GeralException{
        ctrlFun.validarDados(f);
        ctrlFun.verificarSePodeInserir(f);
        ctrlFun.inserir(f);
    }
    
    public void incluir(Perfil p) throws GeralException{
        ctrlPer.validarDados(p);
        ctrlPer.verificarSePodeInserir(p);
        ctrlPer.inserir(p);
    }
    
    public void incluir(Usuario u) throws GeralException{
        ctrlUsu.validarDados(u);
        ctrlUsu.verificarSePodeInserir(u);
        ctrlUsu.inserir(u);
    }
    
    public void incluir(LocalEstoque le) throws GeralException{
        ctrlLocalE.validarDados(le);
        ctrlLocalE.verificarSePodeInserir(le);
        ctrlLocalE.inserir(le);
    }
    /* FALTA implementar o ControladorEntrada
    public void incluir(Entrada e) throws GeralException{
        ctrlE.validarDados(e);
        ctrlE.verificarSePodeInserir(e);
        ctrlE.inserir(e);
    }*/
    
    /* FALTA implementar o ControladorSaida
    public void incluir(Saida s) throws GeralException{
        ctrlS.validarDados(s);
        ctrlS.verificarSePodeInserir(s);
        ctrlS.inserir(s);
    }
    */
    
    public void alterar(Categoria c) throws GeralException{
        ctrlCateg.verificarSeExiste(c);
        ctrlCateg.alterar(c);
    }
    
    public void alterar(Produto p) throws GeralException{
        ctrlProd.verificarSeExiste(p);
        ctrlProd.alterar(p);
    }
    
    public void alterar(Fornecedor f) throws GeralException{
        ctrlForn.verificarSeExiste(f);
        ctrlForn.alterar(f);
    }
    
    public void alterar(Funcionario f) throws GeralException{
        ctrlFun.verificarSeExiste(f);
        ctrlFun.alterar(f);
    }
}