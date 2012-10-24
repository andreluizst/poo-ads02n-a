/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ce.model.basica;

/**
 *
 * @author aluno
 */
public class Saida {

    private Integer codSaida;
    private Double qtde;
    private String dataSaida;

    public Saida(){

    }
    public Saida(Integer codSaida, Double qtde, String dataSaida){
        this.codSaida = codSaida;
        this.qtde = qtde;
        this.dataSaida = dataSaida;
    }

    /**
     * @return the codSaida
     */
    public Integer getCodSaida() {
        return codSaida;
    }

    /**
     * @param codSaida the codSaida to set
     */
    public void setCodSaida(Integer codSaida) {
        this.codSaida = codSaida;
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
     * @return the dataSaida
     */
    public String getDataSaida() {
        return dataSaida;
    }

    /**
     * @param dataSaida the dataSaida to set
     */
    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    
}
