package ce.model.basica;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class Produto {
    private Integer codProd;
    private String descProd;
    private Categoria categoria;
    private Double qtdeMin;
    private Double qtdeIdeal;
    private Double qtdeEstoq;
    private List<Fornecedor> fornecedores;
    //private List<Fornecedor> delForns;
    //private List<Fornecedor> insForns;

    public Produto(){
        this.categoria=new Categoria();
        this.fornecedores= new ArrayList<Fornecedor>();
    }
    
    public Produto(Integer codProd, String descProd, Double qtdeEstoq, 
            Double qtdeMin, Double qtdeIdeal, Categoria categoria){
            //List<Fornecedor> fornecedores){
        this();
        this.codProd= codProd;
        this.descProd= descProd;
        this.qtdeEstoq= qtdeEstoq;
        this.qtdeMin= qtdeMin;
        this.qtdeIdeal= qtdeIdeal;
        this.categoria= categoria;
        //this.fornecedores=fornecedores;
        //this.categoria.setCodCateg(categoria.getCodCateg());
        //this.categoria.setDescricao(categoria.getDescricao());
    }

    /**
     * @return the codProd
     */
    public Integer getCodProd() {
        return codProd;
    }

    /**
     * @param codProd the codProd to set
     */
    public void setCodProd(Integer codProd) {
        this.codProd = codProd;
    }

    /**
     * @return the descProd
     */
    public String getDescProd() {
        return descProd;
    }

    /**
     * @param descProd the descProd to set
     */
    public void setDescProd(String descProd) {
        this.descProd = descProd;
    }

    /**
     * @return the categoria
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * @param categoria the categoria to set
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * @return the qtdeMin
     */
    public Double getQtdeMin() {
        return qtdeMin;
    }

    /**
     * @param qtdeMin the qtdeMin to set
     */
    public void setQtdeMin(Double qtdeMin) {
        this.qtdeMin = qtdeMin;
    }

    /**
     * @return the qtdeIdeal
     */
    public Double getQtdeIdeal() {
        return qtdeIdeal;
    }

    /**
     * @param qtdeIdeal the qtdeIdeal to set
     */
    public void setQtdeIdeal(Double qtdeIdeal) {
        this.qtdeIdeal = qtdeIdeal;
    }

    /**
     * @return the qtdeEstoq
     */
    public Double getQtdeEstoq() {
        return qtdeEstoq;
    }

    /**
     * @param qtdeEstoq the qtdeEstoq to set
     */
    public void setQtdeEstoq(Double qtdeEstoq) {
        this.qtdeEstoq = qtdeEstoq;
    }

    /**
     * @return the fornecedores
     */
    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    /**
     * @param fornecedores the fornecedores to set
     */
    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }
    
}
