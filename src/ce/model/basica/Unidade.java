/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.basica;

/**
 *
 * @author Andre
 */
public class Unidade {
    private int codUnid;
    private String descricao;
    
    public Unidade(){
        
    }
    
    public Unidade(int codUnid, String descricao){
        this.codUnid=codUnid;
        this.descricao=descricao;
    }

    /**
     * @return the codUnid
     */
    public int getCodUnid() {
        return codUnid;
    }

    /**
     * @param codUnid the codUnid to set
     */
    public void setCodUnid(int codUnid) {
        this.codUnid = codUnid;
    }

    /**
     * @return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    
}