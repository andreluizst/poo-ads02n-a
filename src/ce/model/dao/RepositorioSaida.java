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
import ce.model.basica.Saida;
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
public class RepositorioSaida implements IRepositorioSaida{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioSaida(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
    public void inserir(Saida s) throws ConexaoException, 
            RepositorioInserirException{
        Connection c= gerenciadorConexao.conectar();
        String sql="Insert into saida (codEnt, dtSaida, qtde) values(?,?,?)";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, s.getEntrada().getCodEntrada());
            pstmt.setString(2, s.getDataSaida());
            pstmt.setDouble(3, s.getQtde());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioInserirException(e, "RepositorioSaida.inserir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public void alterar(Saida s) throws ConexaoException, 
            RepositorioAlterarException{
        Connection c= gerenciadorConexao.conectar();
        String sql="Update saida set codEnt=?, dtSaida=?, qtde=? where codSaida=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, s.getEntrada().getCodEntrada());
            pstmt.setString(2, s.getDataSaida());
            pstmt.setDouble(3, s.getQtde());
            pstmt.setInt(4, s.getCodSaida());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioAlterarException(e, "RepositorioSaida.alterar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public void excluir(Saida s) throws ConexaoException, 
            RepositorioExcluirException{
        Connection c= gerenciadorConexao.conectar();
        String sql="Delete form saida where codSaida=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, s.getCodSaida());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioExcluirException(e, "RepositorioSaida.excluir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public List<Saida> listar() throws ConexaoException, 
            RepositorioListarException{
        List<Saida> lista= new ArrayList<Saida>();
        Saida s= null;
        Entrada e= null;
        Produto p= null;
        Fornecedor f= null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select s.codSaida, s.codEnt, s.dtSaida, s.qtde, e.codProd,"
                + " e.codForn, e.qtde as qtdeEnt, e.lote, p.descProd, f.nome, f.CNPJ"
                + " from Saida as s inner join Entrada as e where e.codEnt = s.codEnt"
                + " inner join Produto as p where p.codProd = e.codProd"
                + " inner join Fornecedor as f where f.codForn = e.codForn";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            while (rs.next()){
                s= new Saida(rs.getInt("codSaida"), rs.getDouble("qtde"), rs.getString("dtSaida"));
                e= new Entrada();
                e.setCodEntrada(rs.getInt("codEnt"));
                e.setLote(rs.getString("lote"));
                e.setQtde(rs.getDouble("qtdeEnt"));
                p= new Produto();
                p.setCodProd(rs.getInt("codProd"));
                p.setDescProd("descprod");
                f= new Fornecedor();
                f.setCodForn(rs.getInt("codForn"));
                f.setNome("nome");
                f.setCnpj(rs.getString("CNPJ"));
                e.setFornecedor(f);
                e.setProduto(p);
                s.setEntrada(e);
                lista.add(s);
                
            }
            return lista;
        }
        catch(SQLException ex){
            throw new RepositorioListarException(ex, "RepositorioSaida.listar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    public Saida pesqNum(Integer Num) 
            throws ConexaoException, RepositorioPesquisarException{
        Connection c= gerenciadorConexao.conectar();
        Saida s= null;
        Entrada e= null;
        Fornecedor f= null;
        Produto p= null;
        String sql= "select s.codSaida, s.codEnt, s.dtSaida, s.qtde, e.codProd,"
                + " e.codForn, e.qtde as qtdeEnt, e.lote, p.descProd, f.nome, f.CNPJ"
                + " from Saida as s inner join entrada as e where e.codEnt = s.codEnt"
                + " inner join produto as p where p.codProd = e.codProd"
                + " inner join Fornecedor as f where f.codForn = e.codForn";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            // implementar o restante do código...zzzzZZZZ
            
            return s;
        }
        catch(SQLException ex){
            throw new RepositorioPesquisarException(ex, "RepositorioSaida.pesNum()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
}
