/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Fornecedor;
import ce.model.basica.Produto;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioProduto implements IRepositorioProduto{
    private IGerenciadorConexao gc;
    
    public RepositorioProduto(){
        gc= GerenciadorConexao.getInstancia();
    }
    
    public void inserir(Produto p) throws ConexaoException, RepositorioException{
        Connection conexao = gc.conectar();
        String sql= "Insert into Produto(descProd, qtdeEstoq, qtdeMin,"
            + "qtdeIdeal, codCateg) values(?, ?, ?, ?, ?)";
        int codForn=0;
        String sqlForns= "insert into FornXProd(codProd, codForn) values(?,?)";
        try{
            PreparedStatement pstmt= conexao.prepareStatement(sql);
            PreparedStatement pstmtFornProd= conexao.prepareStatement(sqlForns);
            pstmt.setString(1, p.getDescProd());
            pstmt.setDouble(2, p.getQtdeEstoq());
            pstmt.setDouble(3, p.getQtdeMin());
            pstmt.setDouble(4, p.getQtdeIdeal());
            pstmt.setInt(5, p.getCategoria().getCodCateg());
            pstmt.execute();
            if (p.getFornecedores().size() >= 1){
                for(int i=0;i<p.getFornecedores().size();i++){
                    pstmtFornProd.setInt(1, p.getCodProd());
                    pstmtFornProd.setInt(2, p.getFornecedores().get(i).getCodForn());
                    pstmtFornProd.execute();
                    pstmtFornProd.close();
                }
            }
            pstmt.close();
        }
        catch (SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gc.desconectar(conexao);
        }
    }
    // F A L T A:  implementar a inclusão os fornecedores do poduto  da
    //propriedade fornecedores (ArrayList)
    public void alterar(Produto p) throws ConexaoException, RepositorioException{
        Connection conexao = gc.conectar();
        boolean atualizaFornXProd= false;
        List<Fornecedor> fornsDB= new ArrayList<Fornecedor>();
        Fornecedor f= null;
        String sql= "update Produdo set descProd=?, qtdeEstoq=?, qtdeMin=?,"
                + " qtdeIdeal=?, codCateg=? where codProd=?";
        String sqlFornsDB= "select distinct codProd, codForn from FornXProd"
                + " where codProd=?";
        try{
            PreparedStatement pstmtFornsDB= conexao.prepareStatement(sqlFornsDB);
            pstmtFornsDB.setInt(1, p.getCodProd());
            ResultSet rsFornsDB= pstmtFornsDB.executeQuery();
            while (rsFornsDB.next()){
                f= new Fornecedor(rsFornsDB.getInt("codForn"), null, null, null,
                        0, null, null, null, null, null, null, null);
                fornsDB.add(f);
            }
            if (fornsDB.size() != p.getFornecedores().size()){
                atualizaFornXProd= true;
            }else{
                for (int i=0;i<fornsDB.size();i++){
                    atualizaFornXProd= fornsDB.get(i).getCodForn() != p.getFornecedores().get(i).getCodForn()?true:false;
                }
                pstmtFornsDB.close();
                if (atualizaFornXProd){
                    sqlFornsDB= "delete from FornXProd where codProd=?";
                    pstmtFornsDB= conexao.prepareStatement(sqlFornsDB);
                    pstmtFornsDB.setInt(1, p.getCodProd());
                    pstmtFornsDB.execute();
                    pstmtFornsDB.close();
                    sqlFornsDB= "insert into FornXProd (codProd, codForn)"
                            + " values(?, ?)";
                    pstmtFornsDB= conexao.prepareStatement(sqlFornsDB);
                    //pstmtFornsDB.setInt(1, p.getCodProd());
                    for (int i=0;i<p.getFornecedores().size();i++){
                        pstmtFornsDB.setInt(1, p.getCodProd());
                        pstmtFornsDB.setInt(2, p.getFornecedores().get(i).getCodForn());
                    }
                }
            }
            
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, p.getDescProd());
            pstmt.setDouble(2, p.getQtdeEstoq());
            pstmt.setDouble(3, p.getQtdeMin());
            pstmt.setDouble(4, p.getQtdeIdeal());
            pstmt.setDouble(5, p.getCategoria().getCodCateg());
            pstmt.setInt(6, p.getCodProd());
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gc.desconectar(conexao);
        }
    }
    
    public void excluir(Integer codProd)throws ConexaoException, RepositorioException{
        Connection conexao = gc.conectar();
        String sql= "delete from Produto where codProd=?";
        String sqlFrons= "delete from FornXProd where codProd=?";
        try{
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setInt(1, codProd);
            PreparedStatement pstmtFp= conexao.prepareStatement(sqlFrons);
            pstmtFp.execute();
            pstmt.execute();
            pstmt.close();
            pstmtFp.close();
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gc.desconectar(conexao);
        }
    }
    
    public List<Produto> listar()throws ConexaoException, RepositorioException{
        List<Produto> lista = new ArrayList<Produto>();
        List<Fornecedor> fornecedores= new ArrayList<Fornecedor>();
        Produto p= null;
        Fornecedor f= null;
        Connection c = gc.conectar();
        String sql= "Select * from Produto";
        String sqlForns= "SELECT DISTINCT codProd, codForn from FornXProd"
                + " where codProd=?";
        try{
            Statement statement = c.createStatement();
            PreparedStatement pstmtForns= c.prepareStatement(sqlForns);
            ResultSet rs = statement.executeQuery(sql);
            ResultSet rsForns= pstmtForns.executeQuery();
            RepositorioCategoria rpCateg = new RepositorioCategoria();
            while (rs.next()){
                p= new Produto(rs.getInt("codprod"), rs.getString("descProd"),
                        rs.getDouble("qtdeEstoq"), rs.getDouble("qtdeMin"),
                        rs.getDouble("qtdeIdeal"), 
                        rpCateg.pesqPorCod(rs.getInt("codCateg")));
                while (rsForns.next()){
                    f= new Fornecedor(rsForns.getInt("codForn"), null, 
                                null, null, 0, null, null, null, null, null,
                                null, null);
                    fornecedores.add(f);
                }
                p.setFornecedores(fornecedores);
                lista.add(p);
            }
            statement.close();
            pstmtForns.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gc.desconectar(c);
        }
    }
    
    public List<Produto> pesquisar(String descProd) throws ConexaoException, 
            RepositorioException{
        List<Produto> lista = new ArrayList<Produto>();
        List<Fornecedor> fornecedores= new ArrayList<Fornecedor>();
        Produto p = null;
        Fornecedor f= null;
        Connection c = gc.conectar();
        String sql= "Select * from Produto where descprod like ?";
        String sqlForns= "SELECT DISTINCT codProd, codForn"
                + " from FornXProd where codProd=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            PreparedStatement pstmtForns= c.prepareStatement(sqlForns);
            pstmt.setString(1, descProd);
            ResultSet rs = pstmt.executeQuery();
            ResultSet rsForns= pstmtForns.executeQuery();
            RepositorioCategoria rpCateg = new RepositorioCategoria();
            //IRepositorioFornecedor rpf= new RepositorioFornecedor();
            while (rs.next()){
                p= new Produto(rs.getInt("codprod"), rs.getString("descProd"),
                        rs.getDouble("qtdeEstoq"), rs.getDouble("qtdeMin"),
                        rs.getDouble("qtdeIdeal"), 
                        rpCateg.pesqPorCod(rs.getInt("codCateg")));
                while (rsForns.next()){
                   f= new Fornecedor(rsForns.getInt("codForn"), null, 
                                null, null, 0, null, null, null, null, null,
                                null, null);
                   fornecedores.add(f);
                }
                p.setFornecedores(fornecedores);
                lista.add(p);
            }
            pstmt.close();
            pstmtForns.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gc.desconectar(c);
        }
    }
    
    public Produto pesqCodProd(Integer codProd) throws ConexaoException, 
            RepositorioException{
        Produto p= null;
        Fornecedor f= null;
        List<Fornecedor> fornecedores= new ArrayList<Fornecedor>();
        Connection c = gc.conectar();
        String sql= "Select * from Produto where codProd=?";
        String sqlForns = "SELECT DISTINCT codProd, codForn"
                + " from fornxprod where codProd=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            PreparedStatement pstmtForns= c.prepareStatement(sqlForns);
            pstmt.setInt(1, codProd);
            pstmtForns.setInt(1, codProd);
            ResultSet rs = pstmt.executeQuery();
            ResultSet rsForns= pstmt.executeQuery();
            RepositorioCategoria rpCateg = new RepositorioCategoria();
            if (rs != null){
                p= new Produto(rs.getInt("codprod"), rs.getString("descProd"),
                        rs.getDouble("qtdeEstoq"), rs.getDouble("qtdeMin"),
                        rs.getDouble("qtdeIdeal"), 
                        rpCateg.pesqPorCod(rs.getInt("codCateg")));
                if (rsForns != null){
                    while (rsForns.next()){
                        f= new Fornecedor(rsForns.getInt("codForn"), null, 
                                null, null, 0, null, null, null, null, null,
                                null, null);
                        fornecedores.add(f);
                    }
                }
                p.setFornecedores(fornecedores);
            }
            pstmt.close();
            pstmtForns.close();
            return p;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gc.desconectar(c);
        }
    }
  
}
