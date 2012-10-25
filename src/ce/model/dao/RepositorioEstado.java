/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Estado;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioEstado implements IRepositorioEstado{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioEstado(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
        public void inserir(Estado e) throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "insert into estados(uf, descricao) values(?, ?)";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, e.getUf());
            pstmt.setString(2, e.getDescricao());
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException ex){
            throw new RepositorioException(ex);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public void alterar(Estado e)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "update estados set descricao=? where uf=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, e.getUf());
            pstmt.setString(2, e.getDescricao());
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException ex){
            throw new RepositorioException(ex);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public void excluir(String uf)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "delete from estados where uf=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, uf);
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public List<Estado> listar() throws ConexaoException,
            RepositorioException{
        List<Estado> lista = new ArrayList<Estado>();
        Estado e;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from estados";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                e = new Estado(rs.getString("UF"), rs.getString("descricao")); 
                lista.add(e);
            }
            stmt.close();
            return lista;
        }
        catch(SQLException ex){
            throw new RepositorioException(ex);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public List<Estado> pesquisar(String descricao) throws ConexaoException,
            RepositorioException{
                List<Estado> lista = new ArrayList<Estado>();
        Estado e;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from estados where descricao like ?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, descricao);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                e = new Estado(rs.getString("UF"), rs.getString("descricao"));
                lista.add(e);
            }
            pstmt.close();
            return lista;
        }
        catch(SQLException ex){
            throw new RepositorioException(ex);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public Estado pesqPorUf(String uf) throws ConexaoException, 
            RepositorioException{
        Estado e= null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from estados where uf=?";        
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, uf);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                e = new Estado(rs.getString("UF"), rs.getString("descricao")); 
            }
            pstmt.close();
            return e;
        }
        catch(SQLException ex){
            throw new RepositorioException(ex);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
}
