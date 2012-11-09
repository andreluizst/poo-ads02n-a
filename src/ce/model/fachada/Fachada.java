/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.fachada;

import ce.erro.ControladorException;
import ce.model.basica.*;
import ce.model.regra.*;
/**
 *
 * @author andreluiz
 */
public class Fachada {
    
    public Fachada(){
        
    }
    
    public void incluir(Categoria c){
        ControladorCategoria ctrlCateg= new ControladorCategoria();
        try{
            ctrlCateg.validarDados(c);
            ctrlCateg.verificarSePodeInserir(c);
            ctrlCateg.inserir(c);
        }
        catch(ControladorException ce){
            
        }
    }
    
    public void incluir(Produto p){
        ControladorProduto ctrl= new ControladorProduto();
        try{
            ctrl.validarDados(p);
            ctrl.verificarSePodeInserir(p);
            ctrl.inserir(p);
        }
        catch(ControladorException ce){
            
        }
    }
    
    public void incluir(Fornecedor f){
        ControladorFornecedor ctrl= new ControladorFornecedor();
        try{
            ctrl.validarDados(f);
            ctrl.verificarSePodeInserir(f);
            ctrl.inserir(f);
        }
        catch(ControladorException ce){
            
        }
    }
    
    public void incluir(Funcionario f){
        ControladorFuncionario ctrl= new ControladorFuncionario();
        try{
            ctrl.validarDados(f);
            ctrl.verificarSePodeInserir(f);
            ctrl.inserir(f);
        }
        catch(ControladorException ce){
            
        }
    }
    
    public void incluir(Perfil p){
        ControladorPerfil ctrl= new ControladorPerfil();
        try{
            ctrl.validarDados(p);
            ctrl.verificarSePodeInserir(p);
            ctrl.inserir(p);
        }
        catch(ControladorException ce){
            
        }
    }
    
    public void incluir(Usuario u){
        ControladorUsuario ctrl= new ControladorUsuario();
        try{
            ctrl.validarDados(u);
            ctrl.verificarSePodeInserir(u);
            ctrl.inserir(u);
        }
        catch(ControladorException ce){
            
        }
    }
    
    public void incluir(LocalEstoque le){
        ControladorLocalEstoque ctrl= new ControladorLocalEstoque();
        try{
            ctrl.validarDados(le);
            ctrl.verificarSePodeInserir(le);
            ctrl.inserir(le);
        }
        catch(ControladorException ce){
            
        }
    }
    /* FALTA implementar o ControladorEntrada
    public void incluir(Entrada e){
        ControladorEntrada ctrl= new ControladorEntrada();
        try{
            ctrl.validarDados(e);
            ctrl.verificarSePodeInserir(e);
            ctrl.inserir(e);
        }
        catch(ControladorException ce){
            
        }
    }*/
    
    /* FALTA implementar o ControladorSaida
    public void incluir(Saida s){
        ControladorSaida ctrl= new ControladorSaida();
        try{
            ctrl.validarDados(s);
            ctrl.verificarSePodeInserir(s);
            ctrl.inserir(s);
        }
        catch(ControladorException ce){
            
        }
    }
    */
}