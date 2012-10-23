/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.basica;

/**
 *
 * @author Andre
 */
public class FornXProd {
    private Produto produto;
    private Fornecedor fornecedor;
    
    private FornXProd(){
        //fornecedor= new Fornecedor();
        //produto= new Produto();
    }
    public FornXProd(Fornecedor fornecedor, Produto produto){
        this.fornecedor=fornecedor;
        this.produto=produto;
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
