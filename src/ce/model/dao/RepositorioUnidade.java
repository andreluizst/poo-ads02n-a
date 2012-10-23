/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Unidade;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioUnidade implements IRepositorioUnidade{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioUnidade(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
    public void inserir(Unidade u) throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "insert into unidade(descricao) values(?)";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, u.getDescricao());
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
    
    public void alterar(Unidade u)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "update unidade set descricao=? where codUnid=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, u.getDescricao());
            pstmt.setInt(2, u.getCodUnid());
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
    
    public void excluir(Integer codUnid)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "delete from unidade where codUnid=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, codUnid);
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
    
    public List<Unidade> listar() throws ConexaoException,
            RepositorioException{
        List<Unidade> lista = new ArrayList<Unidade>();
        Unidade u;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from unidade";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                u = new Unidade(rs.getInt("codUnid"), rs.getString("descricao")); 
                lista.add(u);
            }
            stmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public List<Unidade> pesquisar(String descricao) throws ConexaoException,
            RepositorioException{
                List<Unidade> lista = new ArrayList<Unidade>();
        Unidade u;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from unidade where descricao like ?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, descricao);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                u = new Unidade(rs.getInt("codUnid"), rs.getString("descricao")); 
                lista.add(u);
            }
            pstmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public Unidade pesqCodForn(Integer codUnid) throws ConexaoException, 
            RepositorioException{
        Unidade u= null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from unidade where codUnid=?";        
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codUnid);
            ResultSet rs= pstmt.executeQuery();
            if(rs != null){
                u = new Unidade(rs.getInt("codUnid"), rs.getString("descricao"));
            }
            pstmt.close();
            return u;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
}