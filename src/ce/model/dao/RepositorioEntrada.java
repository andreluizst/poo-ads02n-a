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
import ce.model.basica.Fornecedor;
import ce.model.basica.Produto;
import ce.model.basica.Entrada;
import ce.util.GerenciadorConexao;
import ce.util.IGerenciadorConexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
/**
 *
 * @author Andre
 */
public class RepositorioEntrada implements IRepositorioEntrada{
    private IGerenciadorConexao gerenciadorConexao;
    private ResourceBundle rb= ResourceBundle.getBundle("ce.erro.Erro");
    /**
     * Construtor padrão
     */
    public RepositorioEntrada(){
        gerenciadorConexao = GerenciadorConexao.getInstancia();
    }
    /**
     * Inclui uma nova entrada.
     * @param e
     * Objeto da classe Entrada que deseja incluir
     * @throws ConexaoException
     * Se houver algum problema com a conexão será lançada uma ConexaoException 
     * @throws RepositorioInserirException 
     * Se houver algum erro na execução do SQL será lançada uma exceção.
     */
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
            throw new RepositorioInserirException(sqlE, 
                    RepositorioEntrada.class.getName()+".inserir()");
        }
        catch(RepositorioAlterarException ae){
            //if (reverterInserir(e))
                throw new RepositorioInserirException(
                        rb.getString("CtrlErroAtlzQtde"),
                        RepositorioEntrada.class.getName()+".inserir()");

        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    /**
     * Altera uma entrada.
     * @param e
     * Objeto da classe Entrada com as alterações desejadas.
     * O número da entrada constante neste objeto deve ser o número da entrada
     * que sofrerá as alterações e os demais atribudos devem conter os valores
     * que foram modificados.
     * @throws ConexaoException
     * Se houver algum problema com a conexão será lançada uma ConexaoException 
     * @throws RepositorioAlterarException 
     * Se houver algum erro na execução do SQL será lançada uma exceção.
     */
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
            throw new RepositorioAlterarException(sqlE, 
                    RepositorioEntrada.class.getName()+".alterar()");
        }
        catch(RepositorioAlterarException ae){
                throw new RepositorioAlterarException(
                        rb.getString("CtrlErroAtlzQtde"),
                        RepositorioEntrada.class.getName()+".alterar()");

        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    /**
     * Exclui uma entrada.
     * @param e
     * Entrada que deseja exclluir
     * @throws ConexaoException
     * Se houver algum problema com a conexão será lançada uma ConexaoException 
     * @throws RepositorioForeignKeyException
     * Se houver algum erro de chave estrangeira como por exemplo, ao tentar excluir
     * uma entrada que está sendo referenciado por uma outra tabela do banco 
     * como a tabela Saida, será lançada uma exceção.
     * @throws RepositorioExcluirException 
     * Se houver algum erro na execução do SQL será lançada uma exceção.
     */
    @Override
    public void excluir(Entrada e) throws ConexaoException, 
            RepositorioForeignKeyException, RepositorioExcluirException{
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
            String msg= sqlE.getMessage().toLowerCase();
            if (msg!=null && msg.contains("foreign key constraint fails")){
                throw new RepositorioForeignKeyException(sqlE,
                        RepositorioEntrada.class.getName()+".excluir()");
            }
            throw new RepositorioExcluirException(sqlE, 
                    RepositorioEntrada.class.getName()+".excluir()");
        }
        catch(RepositorioAlterarException ae){
            throw new RepositorioExcluirException(
                    rb.getString("CtrlErroAtlzQtde"),
                    RepositorioEntrada.class.getName()+".excluir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    /**
     * Lista todas as entradas existentes.
     * @return
     * Retorna uma lista com as entradas.
     * @throws ConexaoException
     * Se houver algum problema com a conexão será lançada uma ConexaoException
     * @throws RepositorioListarException 
     * Se houver algum erro na execução do SQL será lançada uma exceção.
     */
    @Override
    public List<Entrada> listar() throws ConexaoException, 
            RepositorioListarException{
         List<Entrada> lista = new ArrayList();
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
                    RepositorioEntrada.class.getName()+".listar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioListarException(ex, 
                    RepositorioEntrada.class.getName()+".listar()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    /**
     * Pesquisa entrada pelo número.
     * @param num
     * Número da entrada.
     * @return
     * Retorna um objeto Entrada.
     * @throws ConexaoException
     * Se houver algum problema com a conexão será lançada uma ConexaoException
     * @throws RepositorioPesquisarException 
     * Se houver algum erro na execução do SQL será lançada uma exceção.
     */
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
                    RepositorioEntrada.class.getName()+".pesqNum()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, 
                    RepositorioEntrada.class.getName()+".pesqNum()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    /*private boolean reverterInserir(Entrada e)throws ConexaoException, 
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
    }*/
    
    /*private boolean reverterAlterar(Entrada e) throws ConexaoException, 
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
    }*/
}
