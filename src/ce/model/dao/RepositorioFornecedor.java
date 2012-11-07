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
    
    @Override
    public void inserir(Fornecedor f) throws ConexaoException, RepositorioInserirException{
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
            throw new RepositorioInserirException(e, "RepositorioFornecedor.inserir()");
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void alterar(Fornecedor f)throws ConexaoException, RepositorioAlterarException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "update Fornecedor set cnpj=?, nome=?, logradouro=?,"
                + "num=?,comp=?,bairro=?, municipio=?, uf=?, cep=?, fone=?,"
                + "email=? where codForn=?";
        boolean atualizaFornXProd= false;
        List<Produto> ProdsForn= new ArrayList();
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
                p= rpProd.pesqCodProd(rs.getInt("codProd"), false);
                ProdsForn.add(p);
            }
            if (ProdsForn.size() != f.getProdutos().size()){
                atualizaFornXProd=true;
            }else{
                if (f.getProdutos().size() > 0){
                    for (int i=0;i<f.getProdutos().size();i++){
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
                pstmtProds.executeUpdate();
                pstmtProds.close();
                if (f.getProdutos().size() > 0){
                    sqlProds= "insert into FornXProd (codForn, codProd)"
                        + " values(?, ?)";
                    for (int i=0;i<f.getProdutos().size();i++){
                        pstmtProds= c.prepareStatement(sqlProds);
                        pstmtProds.setInt(1, f.getCodForn());
                        pstmtProds.setInt(2, f.getProdutos().get(i).getCodProd());
                        pstmtProds.executeUpdate();
                        pstmtProds.close();
                    }
                }
            }
        }
        catch(SQLException e){
            throw new RepositorioAlterarException(e, "RepositorioFornecedor.alterar()");
        }
        catch(RepositorioException e){
            throw new RepositorioAlterarException(e, 
                    "RepositorioFornecedor.alterar()."+e.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public void excluir(Integer codForn)throws ConexaoException, 
            RepositorioForeignKeyException, RepositorioExcluirException{
        Connection c= gerenciadorConexao.conectar();
        String sql = "delete from Fornecedor where codForn=?";
        try{
            PreparedStatement pstmt = c.prepareStatement(sql);
            pstmt.setInt(1, codForn);
            pstmt.execute();
            pstmt.close();
        }
        catch(SQLException e){
            String msg= e.getMessage().toLowerCase();
            if (msg!=null && msg.contains("foreign key constraint fails")){
                throw new RepositorioForeignKeyException(e, "RepositorioFornecedor.excluir()");
            }
            throw new RepositorioExcluirException(e, "RepositorioFornecedor.excluir()");
        }
        finally
        {
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public List<Fornecedor> listar() throws ConexaoException,
            RepositorioListarException{
        List<Fornecedor> lista = new ArrayList();
        List<Produto> produtos= new ArrayList();
        Fornecedor f;
        Produto p=null;
        Connection c= gerenciadorConexao.conectar();
        String sql= "select * from Fornecedor";
        String sqlProds= "SELECT DISTINCT codProd, codForn from FornXProd"
                + " where codForn=?";
        try{
            Statement stmt= c.createStatement();
            ResultSet rs= stmt.executeQuery(sql);
            IRepositorioProduto rpProd= new RepositorioProduto();
            while (rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                PreparedStatement pstmtProds= c.prepareStatement(sqlProds);
                pstmtProds.setInt(1, f.getCodForn());
                ResultSet rsProds= pstmtProds.executeQuery();
                while (rsProds.next()){
                    p= rpProd.pesqCodProd(rsProds.getInt("codProd"), false);
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
            throw new RepositorioListarException(e,
                    "RepositorioFornecedor.listar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioListarException(ex, 
                    "RepositorioFornecedor.listar()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public List<Fornecedor> pesquisar(String nome) throws ConexaoException,
            RepositorioPesquisarException{
        List<Fornecedor> lista = new ArrayList();
        List<Produto> produtos= new ArrayList();
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
                    p= rpProd.pesqCodProd(rsProds.getInt("codProd"), false);
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
            throw new RepositorioPesquisarException(e, 
                    "RepositorioFornecedor.pesquisar()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, 
                    "RepositorioFornecedor.pesquisar()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    /*
     * As Interfaces IRepositorioProduto e IRepositorioFornecedor tiveram seus
     * métodos pesCodxxx atualizados com +1 paramentro para melhorar a 
     * usabilidade e resolver o problema de conexões pois o método 
     * RepositorioFornecedor.pesqCodForn faz chama o método 
     * RepositorioProduto.pesqCodProd que chama o pesCodForn o fornecedor 
     * gerando conexão extra caso não informe ao método para mão retornar os 
     * protudos do fornecedor dentro do fornecedor por exemplo.
     */
    /**
     * 
     * @param codForn
     * Código do Fornecedor desejado
     * @param comProds
     * Se false a lista de produtos do Fornecedor não será preenchida. Este
     * parametro deve ser false se o método estiver sendo chamado de dentro
     * do repositório de produtos, pois gerará erro de conexão:
     *      com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: 
     *              Data source rejected establishment of connection,  message from server: "Too many connections"
     * @return
     * Retorna um Fornecedor com todos os seus atributos preenchidos
     * @throws ConexaoException
     * @throws RepositorioException 
     */
    @Override
    public Fornecedor pesqCodForn(Integer codForn, boolean comProds) throws ConexaoException, 
            RepositorioPesquisarException{
        Fornecedor f= null;
        Produto p= null;
        Connection c= gerenciadorConexao.conectar();
        List<Produto> produtos= new ArrayList();
        String sql= "select * from Fornecedor where codForn=?";        
        String sqlProds = "SELECT DISTINCT codProd, codForn"
                + " from fornxprod where codForn=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setInt(1, codForn);
            ResultSet rs= pstmt.executeQuery();
            IRepositorioProduto rpProd= new RepositorioProduto();
            if(rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                if (comProds){
                    PreparedStatement pstmtProds= c.prepareStatement(sqlProds);
                    pstmtProds.setInt(1, codForn);
                    ResultSet rsProds= pstmtProds.executeQuery();
                    while (rsProds.next()){
                        p= rpProd.pesqCodProd(rsProds.getInt("codProd"), false);
                        produtos.add(p);
                    }
                    f.setProdutos(produtos);
                    rsProds.close();
                    pstmtProds.close();
                }
            }
            rs.close();
            pstmt.close();
            /*if (f==null){
                throw new RepositorioPesquisarException("Fornecedor."+codForn+" não encontrado!",
                        "RepositoroiFornecedor.pesqCodForn()");
            }*/
            return f;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e, "RepositoroiFornecedor.pesqCodForn()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, 
                    "RepositorioFornecedor.pesqCodForn()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
    
    @Override
    public Fornecedor pesqCnpj(String cnpj, boolean comProds) 
            throws ConexaoException, RepositorioPesquisarException{
        Fornecedor f= null;
        Produto p= null;
        Connection c= gerenciadorConexao.conectar();
        List<Produto> produtos= new ArrayList<>();
        String sql= "select * from Fornecedor where cnpj=?";        
        String sqlProds = "SELECT DISTINCT codProd, codForn"
                + " from fornxprod where codForn=?";
        try{
            PreparedStatement pstmt= c.prepareStatement(sql);
            pstmt.setString(1, cnpj);
            ResultSet rs= pstmt.executeQuery();
            IRepositorioProduto rpProd= new RepositorioProduto();
            if(rs.next()){
                f = new Fornecedor(rs.getInt("codForn"), rs.getString("nome"), 
                        rs.getString("CNPJ"), rs.getString("logradouro"),
                        rs.getInt("Num"), rs.getString("Comp"), 
                        rs.getString("Bairro"), rs.getString("Municipio"),
                        rs.getString("UF"), rs.getString("CEP"), 
                        rs.getString("Fone"), rs.getString("Email"));
                if (comProds){
                    PreparedStatement pstmtProds= c.prepareStatement(sqlProds);
                    pstmtProds.setInt(1, f.getCodForn());
                    ResultSet rsProds= pstmtProds.executeQuery();
                    while (rsProds.next()){
                        p= rpProd.pesqCodProd(rsProds.getInt("codProd"), false);
                        produtos.add(p);
                    }
                    f.setProdutos(produtos);
                    rsProds.close();
                    pstmtProds.close();
                }
            }
            rs.close();
            pstmt.close();
            /*if (f==null){
                throw new RepositorioPesquisarException("Fornecedor."+cnpj+" não encontrado!",
                        "RepositoroiFornecedor.pesqCodForn()");
            }*/
            return f;
        }
        catch(SQLException e){
            throw new RepositorioPesquisarException(e, "RepositoroiFornecedor.pesqCodForn()");
        }
        catch(RepositorioException ex){
            throw new RepositorioPesquisarException(ex, 
                    "RepositorioFornecedor.pesqCodForn()."+ex.getPathClassCall());
        }
        finally{
            gerenciadorConexao.desconectar(c);
        }
    }
}
