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
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Fornecedor;
import ce.model.basica.Produto;
import ce.model.basica.Entrada;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import ce.model.dao.IRepositorioFornecedor;
import ce.model.dao.RepositorioFornecedor;
import ce.model.dao.IRepositorioProduto;
import ce.model.dao.RepositorioProduto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioEntrada implements IRepositorioEntrada{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioEntrada(){
        gerenciadorConexao = GerenciadorConexao.getInstancia();
    }
    
    @Override
    public void inserir(Entrada e) throws ConexaoException, 
            RepositorioInserirException{
        Connection c= gerenciadorConexao.conectar();
        String sql="Insert into entrada (codProd, codForn, dtEnt, lote, qtde)"
                + " values(?,?,?,?,?)";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, e.getProduto().getCodProd());
            pstmt.setInt(2, e.getFornecedor().getCodForn());
            pstmt.setString(3, e.getDataEntrada());
            pstmt.setString(4, e.getLote());
            pstmt.setDouble(5, e.getQtde());
            pstmt.executeUpdate();
            pstmt.close();
            IRepositorioProduto rpProd= new RepositorioProduto();
            /*e.getProduto().setQtdeEstoq(e.getProduto().getQtdeEstoq()+e.getQtde());
            rpProd.alterar(e.getProduto());*/
            rpProd.atualizarQtde(e.getProduto());
        }
        catch(SQLException sqlE){
            throw new RepositorioInserirException(sqlE, "RepositorioEntrada.inserir()");
        }
        catch(RepositorioAlterarException ae){
            //if (reverterInserir(e))
                throw new RepositorioInserirException(
                        "Não foi possível atualizar a quantidade do protudo em estoque",
                        "RepositorioEntrada.inserir()");

        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void alterar(Entrada e) throws ConexaoException, 
            RepositorioAlterarException{
        Connection c= gerenciadorConexao.conectar();
        String sql="Update entrada set codProd=?, codForn=?, dtEnt=?, lote=?,"
                + " qtde=? where codEnt=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, e.getProduto().getCodProd());
            pstmt.setInt(2, e.getFornecedor().getCodForn());
            pstmt.setString(3, e.getDataEntrada());
            pstmt.setString(4, e.getLote());
            pstmt.setDouble(5, e.getQtde());
            pstmt.setInt(6, e.getCodEntrada());
            pstmt.executeUpdate();
            pstmt.close();
            IRepositorioProduto rpProd= new RepositorioProduto();
            rpProd.atualizarQtde(e.getProduto());
        }
        catch(SQLException sqlE){
            throw new RepositorioAlterarException(sqlE, "RepositorioEntrada.alterar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void excluir(Entrada e) throws ConexaoException, 
            RepositorioExcluirException{
        Connection c= gerenciadorConexao.conectar();
        String sql="delete from Entrada where codEnt=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, e.getCodEntrada());
            pstmt.executeUpdate();
            pstmt.close();
            IRepositorioProduto rpProd= new RepositorioProduto();
            rpProd.atualizarQtde(e.getProduto());
        }
        catch(SQLException sqlE){
            throw new RepositorioExcluirException(sqlE, "RepositorioEntrada.excluir()");
        }
        catch(RepositorioAlterarException ae){
            throw new RepositorioExcluirException(
                    "Não foi possível atualizar a quantidade do produto",
                    "RepositorioEntrada.excluir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public List<Entrada> listar() throws ConexaoException, 
            RepositorioListarException{
         List<Entrada> lista = new ArrayList<Entrada>();
        Entrada e=null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Entrada";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            IRepositorioProduto rpProd= new RepositorioProduto();
            IRepositorioFornecedor rpForn= new RepositorioFornecedor();
            while (rs.next()){
                e = new Entrada(rs.getInt("codEnt"),
                        rpProd.pesqCodProd(rs.getInt("codProd"), false),
                        rpForn.pesqCodForn(rs.getInt("codForn"), false),
                        rs.getString("dtEnt"), rs.getString("lote"),
                        rs.getDouble("qtde"));
                
                lista.add(e);
            }
            rs.close();
            stmt.close();
            return lista;
        }
        catch(SQLException ex){
            throw new RepositorioListarException(ex,
                    "RepositorioEntrada.listar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioListarException(ex, 
                    "RepositorioEntrada.listar()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public Entrada pesqNum(Integer num) throws ConexaoException, 
            RepositorioPesquisarException{
        Entrada e=null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Entrada where codEnt=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, num);
            ResultSet rs= pstmt.executeQuery(sql);
            IRepositorioProduto rpProd= new RepositorioProduto();
            IRepositorioFornecedor rpForn= new RepositorioFornecedor();
            while (rs.next()){
                e = new Entrada(rs.getInt("codEnt"),
                        rpProd.pesqCodProd(rs.getInt("codProd"), false),
                        rpForn.pesqCodForn(rs.getInt("codForn"), false),
                        rs.getString("dtEnt"), rs.getString("lote"),
                        rs.getDouble("qtde"));
            }
            rs.close();
            pstmt.close();
            return e;
        }
        catch(SQLException ex){
            throw new RepositorioPesquisarException(ex,
                    "RepositorioEntrada.pesqNum()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, 
                    "RepositorioEntrada.pesqNum()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    private boolean reverterInserir(Entrada e)throws ConexaoException, 
            RepositorioInserirException {
        Connection c= gerenciadorConexao.conectar();
        String sql= "Delete from Entrada where codEnt=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, e.getCodEntrada());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException sqlE){
            throw new RepositorioInserirException(sqlE,
                    "RepositorioEntrada.inserir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
        
        return true;
    }
    
    private boolean reverterAlterar(Entrada e) throws ConexaoException, 
            RepositorioAlterarException{
        Connection c= gerenciadorConexao.conectar();
        String sql="Update entrada set codProd=?, codForn=?, dtEnt=?, lote=?,"
                + " qtde=? where codEnt=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, e.getProduto().getCodProd());
            pstmt.setInt(2, e.getFornecedor().getCodForn());
            pstmt.setString(3, e.getDataEntrada());
            pstmt.setString(4, e.getLote());
            pstmt.setDouble(5, e.getQtde());
            pstmt.setInt(6, e.getCodEntrada());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException sqlE){
            throw new RepositorioAlterarException(sqlE, "RepositorioEntrada.alterar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
        return true;
    }
}
