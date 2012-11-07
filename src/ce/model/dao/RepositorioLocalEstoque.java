/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioForeignKeyException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.LocalEstoque;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre
 */
public class RepositorioLocalEstoque implements IRepositorioLocalEstoque{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioLocalEstoque(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
    @Override
    public void inserir(LocalEstoque le) throws ConexaoException, 
            RepositorioInserirException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "insert into LocalEstoque(descricao) values(?)";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, le.getDescricao());
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioInserirException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void alterar(LocalEstoque le)throws ConexaoException, 
            RepositorioAlterarException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "update LocalEstoque set descricao=? where codUnid=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, le.getDescricao());
            pstmt.setInt(2, le.getCodLocal());
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioAlterarException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void excluir(int codLocal)throws ConexaoException, 
            RepositorioForeignKeyException, RepositorioExcluirException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "delete from LocalEstoque where codLocal=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, codLocal);
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            String msg= e.getMessage().toLowerCase();
            if (msg!=null && msg.contains("foreign key constraint fails")){
                throw new RepositorioForeignKeyException(e,
                        "RepositorioLocalEstoque.excluir()");
            }
            throw new RepositorioExcluirException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public List<LocalEstoque> listar() throws ConexaoException,
            RepositorioListarException{
        List<LocalEstoque> lista = new ArrayList<LocalEstoque>();
        LocalEstoque le;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from LocalEstoque";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                le = new LocalEstoque(rs.getInt("codLocal"),
                        rs.getString("descricao")); 
                lista.add(le);
            }
            stmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioListarException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public List<LocalEstoque> pesquisar(String descricao) throws ConexaoException,
            RepositorioPesquisarException{
                List<LocalEstoque> lista = new ArrayList<LocalEstoque>();
        LocalEstoque le;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from LocalEstoque where descricao like ?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, descricao);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                le = new LocalEstoque(rs.getInt("codLocal"), rs.getString("descricao")); 
                lista.add(le);
            }
            pstmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public LocalEstoque pesqCod(int codLocal) throws ConexaoException, 
            RepositorioPesquisarException{
        LocalEstoque le= null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from LocalEstoque where codLocal=?";        
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codLocal);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                le = new LocalEstoque(rs.getInt("codLocal"), 
                        rs.getString("descricao"));
            }
            pstmt.close();
            return le;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
}
