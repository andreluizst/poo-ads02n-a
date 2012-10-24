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

    public Entrada(){

    }
    public Entrada(Integer codEntrada, Double qtde, String dataEntrada, String lote){
        this.codEntrada = codEntrada;
        this.qtde = qtde;
        this.dataEntrada = dataEntrada;
        this.lote = lote;
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

    
}
