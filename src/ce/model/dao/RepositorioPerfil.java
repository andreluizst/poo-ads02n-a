/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Perfil;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioPerfil implements IRepositorioPerfil {
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioPerfil(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
    @Override
    public void inserir(Perfil p) throws ConexaoException, 
            RepositorioInserirException{
        Connection c= gerenciadorConexao.conectar();
        String sql= "Insert into Perfil (nome) values(?) ";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, p.getNome());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioInserirException(e, "RepositorioPerfil.inserir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
        
    }
    
    @Override
    public void alterar(Perfil p)throws ConexaoException, 
            RepositorioAlterarException{
        Connection c= gerenciadorConexao.conectar();
        String sql= "Update Perfil set nome=? where codPerfil=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, p.getNome());
            pstmt.setInt(2, p.getCodPerfil());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioAlterarException(e, "RepositorioPerfil.alterar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void excluir(Perfil p)throws ConexaoException, 
            RepositorioExcluirException{
        Connection c= gerenciadorConexao.conectar();
        String sql= "Delete from Perfil where codPerfil=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, p.getCodPerfil());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioExcluirException(e, "RepositorioPerfil.excluir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public List<Perfil> listar()throws ConexaoException, 
            RepositorioListarException{
        List<Perfil> lista= new ArrayList<Perfil>();
        Perfil p= null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "Select * from Perfil";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                p= new Perfil(rs.getInt("codPerfil"), rs.getString("nome"));
                if (p != null){
                    lista.add(p);
                }
            }
            stmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioListarException(e, "RepositorioPerfil.listar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
        
    }
    
    @Override
    public Perfil pesqCod(int codPerfil)throws ConexaoException, 
            RepositorioPesquisarException{
        Connection c= gerenciadorConexao.conectar();
        Perfil p=null;
        String sql= "Select * from Perfil where codPerfil=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codPerfil);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                p= new Perfil(rs.getInt("codPerfil"), rs.getString("nome"));
            }
            rs.close();
            pstmt.close();
            if (p==null){
                throw new RepositorioPesquisarException("Perfl."+codPerfil+" não encontrado!",
                        "RepositorioPerfil.pesqCod()");
            }
            return p;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e, "RepositorioPerfil.pesqCod()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
}
