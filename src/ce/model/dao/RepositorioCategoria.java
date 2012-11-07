package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
import ce.erro.RepositorioForeignKeyException;
import ce.erro.RepositorioListarException;
import ce.erro.RepositorioInserirException;
import ce.erro.RepositorioAlterarException;
import ce.erro.RepositorioExcluirException;
import ce.erro.RepositorioPesquisarException;
import ce.model.basica.Categoria;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author professor
 */
public class RepositorioCategoria implements IRepositorioCategoria{
    private IGerenciadorConexao gc;

    public RepositorioCategoria(){
         gc = GerenciadorConexao.getInstancia();
    }
    
    @Override
    public List<Categoria> listar()throws ConexaoException,
            RepositorioListarException{
        List<Categoria> lista = new ArrayList();
        Categoria cat = null;
        Connection c = gc.conectar();
        String sql = "SELECT codCateg, Descricao FROM categoria";
        try{
            Statement stm = c.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while( rs.next() ){
                cat = new Categoria();
                cat.setCodCateg( rs.getInt("codCateg") );
                cat.setDescricao( rs.getString("Descricao") );
                lista.add(cat);
            }
            rs.close();
            stm.close();
            return lista;
        }catch(SQLException e){
            throw new RepositorioListarException(e, "RepositorioCategoria");
        }finally{
            gc.desconectar(c);
        }
    }
    
    @Override
    public void incluir(Categoria obj)throws ConexaoException,
            RepositorioInserirException{
        Connection c = gc.conectar();
        String sql = "INSERT INTO categoria (Descricao) VALUES (?)";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString( 1, obj.getDescricao() );
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            throw new RepositorioInserirException(e, "RepositorioCategoria");
        }finally{
            gc.desconectar(c);
        }
    }
    
    @Override
    public void alterar(Categoria obj)throws ConexaoException,
            RepositorioAlterarException{
        Connection c = gc.conectar();
        String sql = "UPDATE categoria SET descricao=? WHERE codCateg=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString( 1, obj.getDescricao() );
            pstm.setInt(2, obj.getCodCateg() );
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            throw new RepositorioAlterarException(e, "RepositorioCategoria");
        }finally{
            gc.desconectar(c);
        }
    }
    
    @Override
    public void excluir(Integer codCateg)throws ConexaoException,
            RepositorioExcluirException, RepositorioForeignKeyException{
        Connection c = gc.conectar();
        String sql = "DELETE FROM categoria WHERE codCateg=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt( 1, codCateg);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            String msg= e.getMessage().toLowerCase();
            if (msg!=null && msg.contains("foreign key constraint fails")){
                throw new RepositorioForeignKeyException(e, "RepositorioCategoria.excluir()");
            }
            throw new RepositorioExcluirException(e, "RepositorioCategoria");
        }finally{
            gc.desconectar(c);
        }
    }

    @Override
    public Categoria pesquisar(String descricao)throws ConexaoException,
            RepositorioPesquisarException{
        Categoria cat = null;
        Connection c = gc.conectar();
        String sql = "SELECT codCateg, descricao FROM categoria WHERE descricao=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString(1, descricao);
            ResultSet rs = pstm.executeQuery();

            if( rs.next() ){
                cat = new Categoria();
                cat.setCodCateg( rs.getInt("codCateg") );
                cat.setDescricao( rs.getString("Descricao") );
            }
            rs.close();
            pstm.close();
            return cat;
        }catch(SQLException e){
            throw new RepositorioPesquisarException(e, "RepositorioCategoria");
        }finally{
            gc.desconectar(c);
        }
    }
    
    @Override
    public Categoria pesqPorCod(Integer codCateg)throws ConexaoException,
            RepositorioPesquisarException{
        Categoria categoria = null;
        Connection c = gc.conectar();
        String sql = "select codCateg, descricao from categoria where codCateg=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt(1, codCateg);
            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                categoria = new Categoria();
                categoria.setCodCateg( resultSet.getInt("codCateg") );
                categoria.setDescricao( resultSet.getString("Descricao") );
            }
            resultSet.close();
            pstm.close();
            return categoria;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e, "RepositorioCategoria");
        }
        finally{
            gc.desconectar(c);
        }
    }
}
