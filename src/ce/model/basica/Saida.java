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
    private Entrada entrada;

    public Saida(){
        entrada= new Entrada();
    }
    
    public Saida(Integer codSaida, Double qtde, String dataSaida, Entrada entrada){
        this.codSaida = codSaida;
        this.qtde = qtde;
        this.dataSaida = dataSaida;
        this.entrada=entrada;
    }
    
    public Saida(Integer codSaida, Double qtde, String dataSaida){
        this();
        this.codSaida = codSaida;
        this.qtde = qtde;
        this.dataSaida = dataSaida;
    }
    
    public Saida(Double qtde, String dataSaida, Entrada entrada){
        this.codSaida = 0;
        this.qtde = qtde;
        this.dataSaida = dataSaida;
        this.entrada=entrada;
    }
    
    public Saida(Double qtde, String dataSaida){
        this();
        this.codSaida = 0;
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

    /**
     * @return the entrada
     */
    public Entrada getEntrada() {
        return entrada;
    }

    /**
     * @param entrada the entrada to set
     */
    public void setEntrada(Entrada entrada) {
        this.entrada = entrada;
    }
    
    /**
     * 
     * @return 
     * Texto contendo o código e a data de saída
     */
    @Override
    public String toString(){
        return codSaida + " - " + dataSaida;
    }
    
    /**
     * 
     * @return 
     * Texto do conteúdo de todos os atribudos do objeto
     */
    public String toStringAll(){
        return codSaida + " - " + dataSaida + " - " + entrada.getNumero()
                + " - " + entrada.getProduto().getDescProd() 
                + " - " + qtde;
    }
    
}
