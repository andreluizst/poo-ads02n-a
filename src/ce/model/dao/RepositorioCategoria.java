package ce.model.dao;

import ce.erro.ConexaoException;
import ce.erro.RepositorioException;
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

    public List<Categoria> listar()throws ConexaoException,RepositorioException{
        List<Categoria> lista = new ArrayList<Categoria>();
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

            stm.close();

            return lista;
        }catch(SQLException e){
            throw new RepositorioException(e);
        }finally{
            gc.desconectar(c);
        }
    }

    public void incluir(Categoria obj)throws ConexaoException,RepositorioException{
        Connection c = gc.conectar();
        String sql = "INSERT INTO categoria (Descricao) VALUES (?)";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString( 1, obj.getDescricao() );
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            throw new RepositorioException(e);
        }finally{
            gc.desconectar(c);
        }
    }

    public void alterar(Categoria obj)throws ConexaoException,RepositorioException{
        Connection c = gc.conectar();
        String sql = "UPDATE categoria SET descricao=? WHERE codCateg=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setString( 1, obj.getDescricao() );
            pstm.setInt(2, obj.getCodCateg() );
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            throw new RepositorioException(e);
        }finally{
            gc.desconectar(c);
        }
    }

    public void excluir(Integer codCateg)throws ConexaoException,RepositorioException{
        Connection c = gc.conectar();
        String sql = "DELETE FROM categoria WHERE codCateg=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            pstm.setInt( 1, codCateg);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            throw new RepositorioException(e);
        }finally{
            gc.desconectar(c);
        }
    }

    public Categoria pesquisar(String descricao)throws ConexaoException,RepositorioException{
        Categoria cat = null;
        Connection c = gc.conectar();
        String sql = "SELECT codCateg, descricao FROM categoria WHERE descricao=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            if( rs.next() ){
                cat = new Categoria();
                cat.setCodCateg( rs.getInt("codCateg") );
                cat.setDescricao( rs.getString("Descricao") );
            }

            pstm.close();

            return cat;
        }catch(SQLException e){
            throw new RepositorioException(e);
        }finally{
            gc.desconectar(c);
        }
    }
    
    public Categoria pesqPorCod(Integer codCateg)throws ConexaoException,RepositorioException{
        Categoria categoria = null;
        Connection c = gc.conectar();
        String sql = "select codCateg, descricao from categoria where codCateg=?";
        try{
            PreparedStatement pstm = c.prepareStatement(sql);
            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()){
                categoria = new Categoria();
                categoria.setCodCateg( resultSet.getInt("codCateg") );
                categoria.setDescricao( resultSet.getString("Descricao") );
            }
            pstm.close();
            return categoria;
        }
        catch(SQLException e){
            throw new RepositorioException(e);
        }
        finally{
            gc.desconectar(c);
        }
    }
}
