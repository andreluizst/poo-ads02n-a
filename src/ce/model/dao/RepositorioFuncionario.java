/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.model.basica.Funcionario;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioFuncionario implements IRepositorioFuncionario{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioFuncionario(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
    public void inserir(Funcionario f) throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "insert into funcionario(cpf, nome, logradouro,"
                + "num, comp, bairro, municipio, uf, cep, fone, email, dtNasc)"
                + " values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, f.getCpf());
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
            pstmt.setString(12, f.getDtNasc());
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
    
    public void alterar(Funcionario f)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "update Fornecedor set DtNasc=?, nome=?, logradouro=?,"
                + "num=?,comp=?,bairro=?, municipio=?, uf=?, cep=?, fone=?,"
                + "email=? where cpf=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, f.getDtNasc());
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
            pstmt.setString(12, f.getCpf());
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
    
    public void excluir(String cpf)throws ConexaoException, RepositorioException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "delete from funcionario where cpf=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setString(1, cpf);
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
    
    public List<Funcionario> listar() throws ConexaoException,
            RepositorioException{
        List<Funcionario> lista = new ArrayList<Funcionario>();
        Funcionario f;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from funcionario";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                f = new Funcionario(rs.getString("CPF"), rs.getString("nome"), 
                        rs.getString("DtNasc"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                lista.add(f);
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
    
    public List<Funcionario> pesquisar(String nome) throws ConexaoException,
            RepositorioException{
                List<Funcionario> lista = new ArrayList<Funcionario>();
        Funcionario f;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from funcionario where nome like ?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, nome);
            ResultSet rs= pstmt.executeQuery();
            while (rs.next()){
                f = new Funcionario(rs.getString("CPF"), rs.getString("nome"), 
                        rs.getString("DtNasc"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                lista.add(f);
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
    
    public Funcionario pesqCpf(String cpf) throws ConexaoException, 
            RepositorioException{
        Funcionario f= null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from funcionario where cpf=?";        
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs= pstmt.executeQuery();
            if(rs != null){
                f = new Funcionario(rs.getString("CPF"), rs.getString("nome"), 
                        rs.getString("DtNasc"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
            }
            pstmt.close();
            return f;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
}
