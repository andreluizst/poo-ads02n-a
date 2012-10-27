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
public class RepositorioFornecedor implements IRepositorioFornecedor{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioFornecedor(){
        gerenciadorConexao = GerenciadorConexao.getInstancia();
    }
    
    public void inserir(Fornecedor f) throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "insert into Fornecedor(cnpj, nome, logradouro, num, comp,"
                + " bairro, municipio, uf, cep, fone, email)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, f.getCnpj());
            pstmt.setString(2, f.getNome());
            pstmt.setString(3, f.getLogradouro());
            pstmt.setInt(4, f.getNum());
            pstmt.setString(5, f.getComp());
            pstmt.setString(6, f.getBairro());
            pstmt.setString(7, f.getMunicipio());
            pstmt.setString(8, f.getUf());
            pstmt.setString(9, f.getCep());
            pstmt.setString(10, f.getFone());
            pstmt.setString(11, f.getEmail());
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositorioFornecedor.inserir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public void alterar(Fornecedor f)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "update Fornecedor set cnpj=?, nome=?, logradouro=?,"
                + "num=?,comp=?,bairro=?, municipio=?, uf=?, cep=?, fone=?,"
                + "email=? where codForn=?";
        boolean atualizaFornXProd= false;
        List<Produto> ProdsForn= new ArrayList<Produto>();
        Produto p= null;
        String sqlProds= "select distinct codProd, codForn from FornXProd"
                + " where codForn=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, f.getCnpj());
            pstmt.setString(2, f.getNome());
            pstmt.setString(3, f.getLogradouro());
            pstmt.setInt(4, f.getNum());
            pstmt.setString(5, f.getComp());
            pstmt.setString(6, f.getBairro());
            pstmt.setString(7, f.getMunicipio());
            pstmt.setString(8, f.getUf());
            pstmt.setString(9, f.getCep());
            pstmt.setString(10, f.getFone());
            pstmt.setString(11, f.getEmail());
            pstmt.setInt(12, f.getCodForn());
            pstmt.execute();
            pstmt.close();
            PreparedStatement pstmtProds= c.prepareStatement(sqlProds);
            pstmtProds.setInt(1, f.getCodForn());
            ResultSet rs= pstmtProds.executeQuery();
            IRepositorioProduto rpProd= new RepositorioProduto();
            while (rs.next()){
                p= rpProd.pesqCodProd(rs.getInt("codProd"));
                ProdsForn.add(p);
            }
            if (ProdsForn.size() != f.getProdutos().size()){
                atualizaFornXProd=true;
            }else{
                if (p.getFornecedores().size() > 0){
                    for (int i=0;i<p.getFornecedores().size();i++){
                        if (ProdsForn.get(i).getCodProd() != 
                                f.getProdutos().get(i).getCodProd()){
                            atualizaFornXProd= true;
                            break;
                        }
                    }
                }
            }
            rs.close();
            pstmtProds.close();
            if (atualizaFornXProd){
                sqlProds= "delete from FornXProd where codForn=?";
                pstmtProds= c.prepareStatement(sqlProds);
                pstmtProds.setInt(1, f.getCodForn());
                pstmtProds.execute();
                pstmtProds.close();
                if (f.getProdutos().size() > 0){
                    sqlProds= "insert into FornXProd (codForn, codProd)"
                        + " values(?, ?)";
                    for (int i=0;i<f.getProdutos().size();i++){
                        pstmtProds= c.prepareStatement(sqlProds);
                        pstmtProds.setInt(1, f.getCodForn());
                        pstmtProds.setInt(2, f.getProdutos().get(i).getCodProd());
                        pstmtProds.execute();
                        pstmtProds.close();
                    }
                }
            }
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositorioFornecedor.alterar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public void excluir(Integer codForn)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "delete from Fornecedor where codForn=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, codForn);
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositorioFornecedor.excluir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public List<Fornecedor> listar() throws ConexaoException,
            RepositorioException{
        List<Fornecedor> lista = new ArrayList<Fornecedor>();
        List<Produto> produtos= new ArrayList<Produto>();
        Fornecedor f;
        Produto p=null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Fornecedor";
        String sqlProds= "SELECT DISTINCT codProd, codForn from FornXProd"
                + " where codForn=?";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            PreparedStatement pstmtProds= c.prepareStatement(sqlProds);
            IRepositorioProduto rpProd= new RepositorioProduto();
            while (rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                pstmtProds.setInt(1, f.getCodForn());
                ResultSet rsProds= pstmtProds.executeQuery();
                while (rsProds.next()){
                    p= rpProd.pesqCodProd(rsProds.getInt("codProd"));
                    produtos.add(p);
                }
                rsProds.close();
                pstmtProds.close();
                f.setProdutos(produtos);
                lista.add(f);
            }
            rs.close();
            stmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositorioFornecedor.listar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioException(ex, "RepositorioFornecedor.listar()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public List<Fornecedor> pesquisar(String nome) throws ConexaoException,
            RepositorioException{
        List<Fornecedor> lista = new ArrayList<Fornecedor>();
        List<Produto> produtos= new ArrayList<Produto>();
        Fornecedor f=null;
        Produto p=null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Fornecedor where nome like ?";
        String sqlProds= "SELECT DISTINCT codProd, codForn"
                + " from FornXProd where codForn=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs= pstmt.executeQuery();
            PreparedStatement pstmtProds= c.prepareStatement(sqlProds);
            IRepositorioProduto rpProd= new RepositorioProduto();
            while (rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                pstmtProds.setInt(1, f.getCodForn());
                ResultSet rsProds= pstmt.executeQuery();
                while (rsProds.next()){
                    p= rpProd.pesqCodProd(rsProds.getInt("codProd"));
                    produtos.add(p);
                }
                rsProds.close();
                pstmtProds.close();
                f.setProdutos(produtos);
                lista.add(f);
            }
            rs.close();
            pstmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositorioFornecedor.pesquisar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioException(ex, "RepositorioFornecedor.pesquisar()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public Fornecedor pesqCodForn(Integer codForn) throws ConexaoException, 
            RepositorioException{
        Fornecedor f= null;
        Produto p= null;
        Connection c= gerenciadorConexao.conectar();
        List<Produto> produtos= new ArrayList<Produto>();
        String sql= "select * from Fornecedor where codForn=?";        
        String sqlProds = "SELECT DISTINCT codProd, codForn"
                + " from fornxprod where codForn=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codForn);
            ResultSet rs= pstmt.executeQuery();
            PreparedStatement pstmtProds= c.prepareStatement(sqlProds);
            IRepositorioProduto rpProd= new RepositorioProduto();
            if(rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                pstmtProds.setInt(1, codForn);
                ResultSet rsProds= pstmtProds.executeQuery();
                while (rsProds.next()){
                    p= rpProd.pesqCodProd(rsProds.getInt("codProd"));
                    produtos.add(p);
                }
                f.setProdutos(produtos);
                rsProds.close();
            }
            rs.close();
            pstmt.close();
            pstmtProds.close();
            return f;
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositoroiFornecedor.pesqCodForn()");
        }
        catch(RepositorioException ex){
            throw new RepositorioException(ex, "RepositorioFornecedor.pesqCodForn()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
}
