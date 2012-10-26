/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Fornecedor;
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
            throw new RepositorioException(e, "RepositorioFornecedor");
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
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositorioFornecedor");
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
            throw new RepositorioException(e, "RepositorioFornecedor");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public List<Fornecedor> listar() throws ConexaoException,
            RepositorioException{
        List<Fornecedor> lista = new ArrayList<Fornecedor>();
        Fornecedor f;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Fornecedor";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                lista.add(f);
            }
            rs.close();
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
    
    public List<Fornecedor> pesquisar(String nome) throws ConexaoException,
            RepositorioException{
                List<Fornecedor> lista = new ArrayList<Fornecedor>();
        Fornecedor f;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Fornecedor where nome like ?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                lista.add(f);
            }
            rs.close();
            pstmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositorioFornecedor");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public Fornecedor pesqCodForn(Integer codForn) throws ConexaoException, 
            RepositorioException{
        Fornecedor f= null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Fornecedor where codForn=?";        
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codForn);
            ResultSet rs= pstmt.executeQuery();
            if(rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
            }
            rs.close();
            pstmt.close();
            return f;
        }
        catch(SQLException e){
            throw new RepositorioException(e, "RepositoroiFornecedor");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
}
