/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.FornXProd;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andre
 */
public class RepositorioFornXProd implements IRepositorioFornXProd{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioFornXProd(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
    public void inserir(FornXProd fxp) throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "insert into FornXProd(codForn, codProd) values(?, ?)";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, fxp.getFornecedor().getCodForn());
            pstmt.setInt(2, fxp.getProduto().getCodProd());
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
    
    public void alterar(FornXProd fxpAnt, FornXProd fxpNovo)
            throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "update FornXProd set codForn=?, codProd=?"
                + " where codForn=? and codProd=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, fxpNovo.getFornecedor().getCodForn());
            pstmt.setInt(2, fxpNovo.getProduto().getCodProd());
            pstmt.setInt(3, fxpAnt.getFornecedor().getCodForn());
            pstmt.setInt(4, fxpAnt.getProduto().getCodProd());
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
    
    public void excluir(FornXProd fxp)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "delete from FornXProd where codForn=? and codProd=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, fxp.getFornecedor().getCodForn());
            pstmt.setInt(2, fxp.getProduto().getCodProd());
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
    
    public List<FornXProd> listar() throws ConexaoException,
            RepositorioException{
        List<FornXProd> lista = new ArrayList<FornXProd>();
        IRepositorioFornecedor rpf= new RepositorioFornecedor();
        IRepositorioProduto rpp = new RepositorioProduto();
        FornXProd fxp;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Fornecedor";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                fxp = new FornXProd(rpf.pesqCodForn(rs.getInt("codForn")), 
                        rpp.pesqCodProd(rs.getInt("codProd")));
                lista.add(fxp);
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
    
    public List<FornXProd> pesqFornDoProd(Integer codProd) throws ConexaoException,
            RepositorioException{
                List<FornXProd> lista = new ArrayList<FornXProd>();
        IRepositorioFornecedor rpf= new RepositorioFornecedor();
        IRepositorioProduto rpp = new RepositorioProduto();
        FornXProd fxp;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from FornXProd where codProd=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codProd);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                fxp = new FornXProd(rpf.pesqCodForn(rs.getInt("codForn")), 
                        rpp.pesqCodProd(rs.getInt("codProd")));
                lista.add(fxp);
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
    
    public List<FornXProd> pesqProdDoForn(Integer codForn) throws ConexaoException,
            RepositorioException{
                List<FornXProd> lista = new ArrayList<FornXProd>();
        IRepositorioFornecedor rpf= new RepositorioFornecedor();
        IRepositorioProduto rpp = new RepositorioProduto();
        FornXProd fxp;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from FornXProd where codForn=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codForn);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                fxp = new FornXProd(rpf.pesqCodForn(rs.getInt("codForn")), 
                        rpp.pesqCodProd(rs.getInt("codProd")));
                lista.add(fxp);
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
}
