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
import ce.model.basica.Unidade;
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
    
    @Override
    public void inserir(Produto p) throws ConexaoException, RepositorioInserirException{
        Connection conexao = gc.conectar();
        String sql= "Insert into Produto(descProd, qtdeEstoq, qtdeMin,"
            + " qtdeIdeal, codCateg, statusProd, codUnid)"
            + " values(?, ?, ?, ?, ?, ?, ?)";
        int codForn=0;
        String sqlForns= "insert into FornXProd(codProd, codForn) values(?,?)";
        try{
            PreparedStatement pstmt= conexao.prepareStatement(sql);
            pstmt.setString(1, p.getDescProd());
            pstmt.setDouble(2, p.getQtdeEstoq());
            pstmt.setDouble(3, p.getQtdeMin());
            pstmt.setDouble(4, p.getQtdeIdeal());
            pstmt.setInt(5, p.getCategoria().getCodCateg());
            pstmt.setInt(6, p.getStatusProd());
            pstmt.setInt(7, p.getUnidade().getCodUnid());
            pstmt.execute();
            if (p.getFornecedores().size() >= 1){
                PreparedStatement pstmtFornProd= conexao.prepareStatement(sqlForns);
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
            throw new RepositorioInserirException(e, "RepositorioProduto.inserir()");
        }
        finally{
            gc.desconectar(conexao);
        }
    }

    @Override
    public void alterar(Produto p) throws ConexaoException, RepositorioAlterarException{
        Connection conexao = gc.conectar();
        boolean atualizaFornXProd= false;
        List<Fornecedor> fornsDB= new ArrayList<Fornecedor>();
        Fornecedor f= null;
        String sql= "update Produto set descProd=?, qtdeEstoq=?, qtdeMin=?,"
                + " qtdeIdeal=?, codCateg=?, statusProd=?, codUnid=?"
                + " where codProd=?";
        String sqlFornsDB= "select distinct codProd, codForn from FornXProd"
                + " where codProd=?";
        try{
            PreparedStatement pstmt = conexao.prepareStatement(sql);
            pstmt.setString(1, p.getDescProd());
            pstmt.setDouble(2, p.getQtdeEstoq());
            pstmt.setDouble(3, p.getQtdeMin());
            pstmt.setDouble(4, p.getQtdeIdeal());
            pstmt.setDouble(5, p.getCategoria().getCodCateg());
            pstmt.setDouble(6, p.getStatusProd());
            pstmt.setInt(7, p.getUnidade().getCodUnid());
            pstmt.setInt(8, p.getCodProd());
            pstmt.execute();
            pstmt.close();
            PreparedStatement pstmtFornsDB= conexao.prepareStatement(sqlFornsDB);
            pstmtFornsDB.setInt(1, p.getCodProd());
            ResultSet rsFornsDB= pstmtFornsDB.executeQuery();
            IRepositorioFornecedor rpForn= new RepositorioFornecedor();
            while (rsFornsDB.next()){
                f= rpForn.pesqCodForn(rsFornsDB.getInt("codForn"), false);
                fornsDB.add(f);
            }
            if (fornsDB.size() != p.getFornecedores().size()){
                atualizaFornXProd=true;
            }else{
                if (p.getFornecedores().size() > 0){
                    for (int i=0;i<p.getFornecedores().size();i++){
                        if (fornsDB.get(i).getCodForn() != 
                                p.getFornecedores().get(i).getCodForn()){
                            atualizaFornXProd= true;
                            break;
                        }
                    }
                }
            }
            rsFornsDB.close();
            pstmtFornsDB.close();
            if (atualizaFornXProd){
                sqlFornsDB= "delete from FornXProd where codProd=?";
                pstmtFornsDB= conexao.prepareStatement(sqlFornsDB);
                pstmtFornsDB.setInt(1, p.getCodProd());
                pstmtFornsDB.execute();
                pstmtFornsDB.close();
                if (p.getFornecedores().size() > 0){
                    sqlFornsDB= "insert into FornXProd (codProd, codForn)"
                        + " values(?, ?)";
                    //pstmtFornsDB= conexao.prepareStatement(sqlFornsDB);
                    //pstmtFornsDB.setInt(1, p.getCodProd());
                    for (int i=0;i<p.getFornecedores().size();i++){
                        pstmtFornsDB= conexao.prepareStatement(sqlFornsDB);
                        pstmtFornsDB.setInt(1, p.getCodProd());
                        pstmtFornsDB.setInt(2, p.getFornecedores().get(i).getCodForn());
                        pstmtFornsDB.execute();
                        pstmtFornsDB.close();
                    }
                }
            }
            
        }
        catch(SQLException e){
            throw new RepositorioAlterarException(e, "RepositorioProduto.alterar()");
        }
         catch(RepositorioException ex){
            throw new RepositorioAlterarException(ex, 
                    "RepositorioProduto.alterar()."+ex.getPathClassCall());
        }
        finally{
            gc.desconectar(conexao);
        }
    }
    
    @Override
    public void excluir(Integer codProd)throws ConexaoException, RepositorioExcluirException{
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
            throw new RepositorioExcluirException(e, "RepositorioProduto.excluir()");
        }
        finally{
            gc.desconectar(conexao);
        }
    }
    
    @Override
    public List<Produto> listar()throws ConexaoException, RepositorioListarException{
        List<Produto> lista = new ArrayList<Produto>();
        List<Fornecedor> fornecedores= new ArrayList<Fornecedor>();
        Produto p= null;
        Fornecedor f= null;
        //Unidade u= null;
        Connection c = gc.conectar();
        String sql= "Select * from Produto";
        String sqlForns= "SELECT DISTINCT codProd, codForn from FornXProd"
                + " where codProd=?";
        try{
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            
            IRepositorioCategoria rpCateg = new RepositorioCategoria();
            IRepositorioUnidade rpUnid= new RepositorioUnidade();
            IRepositorioFornecedor rpForn= new RepositorioFornecedor();
            while (rs.next()){
                p= new Produto(rs.getInt("codProd"), rs.getString("descProd"),
                        rs.getDouble("qtdeEstoq"), rs.getDouble("qtdeMin"),
                        rs.getDouble("qtdeIdeal"), rs.getInt("statusProd"),
                        rpCateg.pesqPorCod(rs.getInt("codCateg")),
                        rpUnid.pesqCodUnid(rs.getInt("codUnid")));
                //PreparedStatement pstmtForns= c.prepareStatement(sqlForns);
                PreparedStatement pstmtForns= c.prepareStatement(sqlForns);
                pstmtForns.setInt(1, p.getCodProd());
                ResultSet rsForns= pstmtForns.executeQuery();
                while (rsForns.next()){
                    f= rpForn.pesqCodForn(rsForns.getInt("codForn"), false);
                    fornecedores.add(f);
                }
                rsForns.close();
                pstmtForns.close();
                p.setFornecedores(fornecedores);
                lista.add(p);
            }
            rs.close();
            statement.close();
            //pstmtForns.close();
            return lista;
        }
        catch(SQLException e){
            throw new RepositorioListarException(e, "RepositorioProduto.listar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioListarException(ex,
                    "RepositorioProduto.listar()."+ex.getPathClassCall());
        }
        finally{
            gc.desconectar(c);
        }
    }
    
    @Override
    public List<Produto> pesquisar(String descProd) throws ConexaoException, 
            RepositorioPesquisarException{
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
            
            IRepositorioCategoria rpCateg = new RepositorioCategoria();
            IRepositorioFornecedor rpForn= new RepositorioFornecedor();
            IRepositorioUnidade rpUnid= new RepositorioUnidade();
            while (rs.next()){
                p= new Produto(rs.getInt("codprod"), rs.getString("descProd"),
                        rs.getDouble("qtdeEstoq"), rs.getDouble("qtdeMin"),
                        rs.getDouble("qtdeIdeal"), rs.getInt("statusProd"),
                        rpCateg.pesqPorCod(rs.getInt("codCateg")),
                        rpUnid.pesqCodUnid(rs.getInt("codUnid")));
                pstmtForns.setInt(1, p.getCodProd());
                ResultSet rsForns= pstmtForns.executeQuery();
                while (rsForns.next()){
                   f= rpForn.pesqCodForn(rsForns.getInt("codForn"), false);
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
            throw new RepositorioPesquisarException(e, "RepositorioProduto.pesquisar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, "RepositorioProduto.pesquisar()."+ex.getPathClassCall());
        }
        finally{
            gc.desconectar(c);
        }
    }
    /**
     * 
     * @param codProd
     * Código do Produto desejado
     * @param comForns
     * Se false a lista de fornecedores do Produto não será preenchida. Este
     * parametro deve ser false se o método estiver sendo chamado de dentro
     * do repositório de fornecedores, pois gerará erro de conexão:
     *      com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: 
     *              Data source rejected establishment of connection,  message from server: "Too many connections"
     * @return
     * Retorna um Produto com todos os seus atributos preenchidos
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    @Override
    public Produto pesqCodProd(Integer codProd, boolean comForns) throws ConexaoException, 
            RepositorioPesquisarException{
        Produto p= null;
        Fornecedor f= null;
        List<Fornecedor> fornecedores= new ArrayList<Fornecedor>();
        Connection c = gc.conectar();
        String sql= "Select * from Produto where codProd=?";
        String sqlForns = "SELECT DISTINCT codProd, codForn"
                + " from fornxprod where codProd=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, codProd);
            ResultSet rs = pstmt.executeQuery();
            IRepositorioCategoria rpCateg = new RepositorioCategoria();
            IRepositorioUnidade rpUnid= new RepositorioUnidade();
            IRepositorioFornecedor rpForn= new RepositorioFornecedor();
            if (rs.next()){
                p= new Produto(rs.getInt("codprod"), rs.getString("descProd"),
                        rs.getDouble("qtdeEstoq"), rs.getDouble("qtdeMin"),
                        rs.getDouble("qtdeIdeal"), rs.getInt("statusProd"),
                        rpCateg.pesqPorCod(rs.getInt("codCateg")),
                        rpUnid.pesqCodUnid(rs.getInt("codUnid")));
                if (comForns){
                    PreparedStatement pstmtForns= c.prepareStatement(sqlForns);
                    pstmtForns.setInt(1, codProd);
                    ResultSet rsForns= pstmtForns.executeQuery();
                    while (rsForns.next()){
                        f= rpForn.pesqCodForn(rsForns.getInt("codForn"), false);
                        fornecedores.add(f);
                    }
                    p.setFornecedores(fornecedores);
                    rsForns.close();
                    pstmtForns.close();
                }
            }
            rs.close();
            pstmt.close();
            if (f==null){
                throw new RepositorioPesquisarException("Produto."+codProd+" não encontrado!",
                        "RepositoroiFornecedor.pesqCodProd()");
            }
            return p;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e, "RepositorioProduto.pesqCodProd()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, "RepositorioProduto.pesqCodProd()."+ex.getPathClassCall());
        }
        finally{
            gc.desconectar(c);
        }
    }
  
}
