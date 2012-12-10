/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ce.model.basica;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author aluno
 */
public class Entrada {

    private Integer numero;
    private Double qtde;
    private String dataEntrada;
    private String lote;
    private Produto produto;
    private Fornecedor fornecedor;
    /**
     * Construtor pardão
     */
    public Entrada(){
        fornecedor=new Fornecedor();
        produto=new Produto();
    }
    
    /**
     * 
     * @param numero
     * @param qtde
     * @param dataEntrada
     * @param lote 
     */
    public Entrada(Integer numero, Double qtde, Object dataEntrada, String lote){
        this();
        java.util.Date dt;
        this.numero = numero;
        this.qtde = qtde;
        this.qtde = qtde;
        if (dataEntrada instanceof String){
            try{
                dt= new SimpleDateFormat("dd/MM/yyy").parse((String)dataEntrada);
                this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
            }catch(Exception e){
                this.dataEntrada= "";
            }
        }
        if (dataEntrada instanceof java.sql.Date){
            dt= new java.util.Date(((java.sql.Date)dataEntrada).getTime());
            this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
        }
        this.lote = lote;
    }
    
    /**
     * 
     * @param numero
     * Número da entrada
     * @param qtde
     * Quantidade do produto
     * @param dataEntrada
     * Data no formato de String ou java.sql.Date.
     * @param lote
     * Número de lote
     * @param produto
     * Objeto do tipo Produto
     * @param fornecedor 
     * Objeto do tipo Fornecedor
     */
    public Entrada(Integer numero, Produto produto, Fornecedor fornecedor, 
            Object dataEntrada, String lote, Double qtde){
        java.util.Date dt;
        this.numero = numero;
        this.qtde = qtde;
        if (dataEntrada instanceof String){
            try{
                dt= new SimpleDateFormat("dd/MM/yyy").parse((String)dataEntrada);
                this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
            }catch(Exception e){
                this.dataEntrada= "";
            }
        }
        if (dataEntrada instanceof java.sql.Date){
            dt= new java.util.Date(((java.sql.Date)dataEntrada).getTime());
            this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
        }
        this.lote = lote;
        this.produto=produto;
        this.fornecedor=fornecedor;
    }
    
    /**
     * 
     * @param numero
     * @param dataEntrada
     * @param lote
     * @param qtde 
     */
    public Entrada(Integer numero, Object dataEntrada, String lote, 
            Double qtde){
        this();
        java.util.Date dt;
        this.numero = numero;
        this.qtde = qtde;
        if (dataEntrada instanceof String){
            try{
                dt= new SimpleDateFormat("dd/MM/yyy").parse((String)dataEntrada);
                this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
            }catch(Exception e){
                this.dataEntrada= "";
            }
        }
        if (dataEntrada instanceof java.sql.Date){
            dt= new java.util.Date(((java.sql.Date)dataEntrada).getTime());
            this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
        }
        this.lote = lote;
    }
    
    /**
     * 
     * @param qtde
     * @param dataEntrada
     * @param lote
     * @param produto
     * @param fornecedor 
     */
    public Entrada(Produto produto, Fornecedor fornecedor, 
            Object dataEntrada, String lote, Double qtde){
        java.util.Date dt;
        this.numero = 0;
        this.qtde = qtde;
        if (dataEntrada instanceof String){
            try{
                dt= new SimpleDateFormat("dd/MM/yyy").parse((String)dataEntrada);
                this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
            }catch(Exception e){
                this.dataEntrada= "";
            }
        }
        if (dataEntrada instanceof java.sql.Date){
            dt= new java.util.Date(((java.sql.Date)dataEntrada).getTime());
            this.dataEntrada= new SimpleDateFormat("dd/MM/yyyy").format(dt);
        }
        this.lote = lote;
        this.produto=produto;
        this.fornecedor=fornecedor;
    }

    /**
     * @return the numero
     */
    public Integer getNumero() {
        return numero;
    }

    /**
     * @param numero the numero to set
     */
    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    /**
     * @return the qtde
     */
    public Double getQtde() {
        return qtde;
    }

    /**
     * @param qtde the qtde to set
     */
    public void setQtde(Double qtde) {
        this.qtde = qtde;
    }
    
    /**
     * Retorn a data invertida (yyyy/MM/dd) no formado texto para manter a
     * compatibilidade com o MySQL
     * @return 
     */
    public String getStrDataInvertida(){
        String dt="";
        dt= getDataToMySqlDate().toString();
        return dt.replaceAll("[-]", "/");
    }
    
    /**
     * Retorn a data invertida (yyyy-MM-dd) no formato suportado pelo MySQL.
     * @return 
     * java.sql.Date
     */
    public java.sql.Date getDataToMySqlDate(){
        java.sql.Date data;
        try {
            data= new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse(dataEntrada).getTime());
        } catch (ParseException ex) {
            data= null;
        }
        return data;
    }

    /**
     * @return the dataEntrada
     */
    public String getDataEntrada() {
        return dataEntrada;
    }

    /**
     * @param dataEntrada the dataEntrada to set
     */
    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    /**
     * @return the lote
     */
    public String getLote() {
        return lote;
    }

    /**
     * @param lote the lote to set
     */
    public void setLote(String lote) {
        this.lote = lote;
    }

    /**
     * @return the produto
     */
    public Produto getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    /**
     * @return the fornecedor
     */
    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    /**
     * @param fornecedor the fornecedor to set
     */
    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    /**
     * 
     * @return 
     * Texto contendo o número e a data da entrada.
     */
    @Override
    public String toString(){
        return numero + " de " + dataEntrada;
    }
    
    /**
     * 
     * @return 
     * Texto contendo os valores de todos os atribudos do objeto.
     */
    public String toStringAll(){
        return numero + " - " + dataEntrada + " - " + fornecedor.toString()
                + " - " + produto.toString() +  " - " + qtde +  " - " + lote;
    }
    
}
