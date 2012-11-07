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
import ce.model.basica.Perfil;
import ce.model.basica.Usuario;
import ce.model.basica.Funcionario;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import ce.model.dao.IRepositorioPerfil;
import ce.model.dao.RepositorioPerfil;
import ce.model.dao.IRepositorioFuncionario;
import ce.model.dao.RepositorioFuncionario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class RepositorioUsuario implements IRepositorioUsuario{
    private IGerenciadorConexao gerenciadorConexao;
    
    public RepositorioUsuario(){
        gerenciadorConexao= GerenciadorConexao.getInstancia();
    }
    
    @Override
    public void inserir(Usuario u) throws ConexaoException, 
            RepositorioInserirException{
        Connection c= gerenciadorConexao.conectar();
        String sql= "Insert into Usuario(nome, codPerfil, cpf, senha)"
                + " values(?,?,?, ?)";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, u.getNome());
            pstmt.setInt(2, u.getPerfil().getCodPerfil());
            pstmt.setString(3, u.getFuncionario().getCpf());
            pstmt.setString(4, u.getSenha());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioInserirException(e, "RepositorioUsuario.inserir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void alterar(Usuario u) throws ConexaoException, 
            RepositorioAlterarException{
        Connection c= gerenciadorConexao.conectar();
        String sql= "Update Usuario set nome=?, codPerfil=?, senha=?, cpf=?"
                + " where codUsr=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, u.getNome());
            pstmt.setInt(2, u.getPerfil().getCodPerfil());
            pstmt.setString(3, u.getSenha());
            pstmt.setString(4, u.getFuncionario().getCpf());
            pstmt.setInt(5, u.getCodUsuario());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            throw new RepositorioAlterarException(e, "RepositorioUsuario.alterar()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void excluir(Usuario u) throws ConexaoException, 
            RepositorioForeignKeyException, RepositorioExcluirException{
        Connection c= gerenciadorConexao.conectar();
        String sql= "delete from usuario where codUsr=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, u.getCodUsuario());
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch(SQLException e){
            String msg= e.getMessage().toLowerCase();
            if (msg!=null && msg.contains("foreign key constraint fails")){
                throw new RepositorioForeignKeyException(e,
                        "RepositorioUsuario.excluir()");
            }
            throw new RepositorioExcluirException(e, "RepositorioUsuario.excluir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public List<Usuario> listar() throws ConexaoException, 
            RepositorioListarException{
        List<Usuario> lista= new ArrayList<Usuario>();
        Usuario u= null;
        String sql= "Select * from Usuario";
        Connection c= gerenciadorConexao.conectar();
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            IRepositorioPerfil rpPerfil= new RepositorioPerfil();
            IRepositorioFuncionario rpFun= new RepositorioFuncionario();
            while (rs.next()){
                u= new Usuario(rs.getInt("codUsr"), rs.getString("nome"),
                        rpPerfil.pesqCod(rs.getInt("codPerfil")),
                        rpFun.pesqCpf(rs.getString("cpf")),
                        rs.getString("senha"));
                
                lista.add(u);
            }
            rs.close();
            stmt.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioListarException(e, "RepositorioUsuario.listar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioListarException(ex, 
                    "RepositorioUsuario.listar()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public Usuario pesqCod(int cod)throws ConexaoException, 
            RepositorioPesquisarException{
        Usuario u= null;
        String sql= "Select * from Usuario where codUsr=?";
        Connection c= gerenciadorConexao.conectar();
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, cod);
            ResultSet rs= pstmt.executeQuery();
            IRepositorioPerfil rpPerfil= new RepositorioPerfil();
            IRepositorioFuncionario rpFun= new RepositorioFuncionario();
            while (rs.next()){
                u= new Usuario(rs.getInt("codUsr"), rs.getString("nome"),
                        rpPerfil.pesqCod(rs.getInt("codPerfil")),
                        rpFun.pesqCpf(rs.getString("cpf")),
                        rs.getString("senha"));
            }
            rs.close();
            pstmt.close();
            if (u==null){
                throw new RepositorioPesquisarException("Usuario."+cod+" não encontrado!",
                        "RepositorioUsuario.pesqCod()");
            }
            return u;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e, "Repositoriousuario.pesqCod()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, 
                    "RepositorioUsuario.pesqCod()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public Usuario pesqCpf(String cpf)throws ConexaoException, 
            RepositorioPesquisarException{
        Usuario u= null;
        String sql= "Select * from Usuario where cpf=?";
        Connection c= gerenciadorConexao.conectar();
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, cpf);
            ResultSet rs= pstmt.executeQuery();
            IRepositorioPerfil rpPerfil= new RepositorioPerfil();
            IRepositorioFuncionario rpFun= new RepositorioFuncionario();
            while (rs.next()){
                u= new Usuario(rs.getInt("codUsr"), rs.getString("nome"),
                        rpPerfil.pesqCod(rs.getInt("codPerfil")),
                        rpFun.pesqCpf(rs.getString("cpf")),
                        rs.getString("senha"));
            }
            rs.close();
            pstmt.close();
            if (u==null){
                throw new RepositorioPesquisarException("Usuario."+cpf+" não encontrado!",
                        "RepositorioUsuario.pesqCod()");
            }
            return u;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e, "Repositoriousuario.pesqCod()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, 
                    "RepositorioUsuario.pesqCod()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
}
