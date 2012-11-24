/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.basica;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

/**
 *
 * @author andreluiz
 */
public class CategTeste {
    
    private String descricao;
    public static final String PROP_DESCRICAO = "descricao";
    private Integer codCateg;
    public static final String PROP_CODCATEG = "codCateg";

    /**
     * Get the value of codCateg
     *
     * @return the value of codCateg
     */
    public Integer getCodCateg() {
        return codCateg;
    }

    /**
     * Set the value of codCateg
     *
     * @param codCateg new value of codCateg
     */
    public void setCodCateg(Integer codCateg) {
        Integer oldCodCateg = this.codCateg;
        this.codCateg = codCateg;
        propertyChangeSupport.firePropertyChange(PROP_CODCATEG, oldCodCateg, codCateg);
    }

    /**
     * Get the value of Descricao
     *
     * @return the value of Descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Set the value of Descricao
     *
     * @param descricao new value of Descricao
     */
    public void setDescricao(String descricao) {
        String oldDescricao = this.descricao;
        this.descricao = descricao;
        propertyChangeSupport.firePropertyChange(PROP_DESCRICAO, oldDescricao, descricao);
    }
    private transient final PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

    /**
     * Add PropertyChangeListener.
     *
     * @param listener
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    /**
     * Remove PropertyChangeListener.
     *
     * @param listener
     */
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

}
