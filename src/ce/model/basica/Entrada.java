/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ce.model.basica;

/**
 *
 * @author aluno
 */
public class Entrada {

    private Integer codEntrada;
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
     * @param codEntrada
     * @param qtde
     * @param dataEntrada
     * @param lote 
     */
    public Entrada(Integer codEntrada, Double qtde, String dataEntrada, String lote){
        this();
        this.codEntrada = codEntrada;
        this.qtde = qtde;
        this.dataEntrada = dataEntrada;
        this.lote = lote;
    }
    
    /**
     * 
     * @param codEntrada
     * @param qtde
     * @param dataEntrada
     * @param lote
     * @param produto
     * @param fornecedor 
     */
    public Entrada(Integer codEntrada, Produto produto, Fornecedor fornecedor, 
            String dataEntrada, String lote, Double qtde){
        this.codEntrada = codEntrada;
        this.qtde = qtde;
        this.dataEntrada = dataEntrada;
        this.lote = lote;
        this.produto=produto;
        this.fornecedor=fornecedor;
    }
    
    /**
     * 
     * @param codEntrada
     * @param dataEntrada
     * @param lote
     * @param qtde 
     */
    public Entrada(Integer codEntrada, String dataEntrada, String lote, 
            Double qtde){
        this();
        this.codEntrada = codEntrada;
        this.qtde = qtde;
        this.dataEntrada = dataEntrada;
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
            String dataEntrada, String lote, Double qtde){
        this.codEntrada = 0;
        this.qtde = qtde;
        this.dataEntrada = dataEntrada;
        this.lote = lote;
        this.produto=produto;
        this.fornecedor=fornecedor;
    }

    /**
     * @return the codEntrada
     */
    public Integer getCodEntrada() {
        return codEntrada;
    }

    /**
     * @param codEntrada the codEntrada to set
     */
    public void setCodEntrada(Integer codEntrada) {
        this.codEntrada = codEntrada;
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

    
}
