/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.basica;

/**
 *
 * @author Andre
 */
public class Funcionario {
    private String cpf;
    private String nome;
    private String dtNasc;
    private String logradouro;
    private int num;
    private String comp;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String fone;
    private String email;
    
    public Funcionario(){
        
    }
    public Funcionario(String cpf, String nome, String dtNasc, 
            String logradouro, int num, String comp, String bairro,
            String municipio, String uf, String cep, String fone, String email){
        this.cpf=cpf;
        this.nome=nome;
        this.dtNasc=dtNasc;
        this.logradouro=logradouro;
        this.num=num;
        this.comp=comp;
        this.bairro=bairro;
        this.municipio=municipio;
        this.uf=uf;
        this.cep=cep;
        this.fone=fone;
        this.email=email;
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @param cpf the cpf to set
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the dtNasc
     */
    public String getDtNasc() {
        return dtNasc;
    }

    /**
     * @param dtNasc the dtNasc to set
     */
    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    /**
     * @return the logradouro
     */
    public String getLogradouro() {
        return logradouro;
    }

    /**
     * @param logradouro the logradouro to set
     */
    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    /**
     * @return the num
     */
    public int getNum() {
        return num;
    }

    /**
     * @param num the num to set
     */
    public void setNum(int num) {
        this.num = num;
    }

    /**
     * @return the comp
     */
    public String getComp() {
        return comp;
    }

    /**
     * @param comp the comp to set
     */
    public void setComp(String comp) {
        this.comp = comp;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        this.uf = uf.toUpperCase();
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        this.cep = cep;
    }

    /**
     * @return the fone
     */
    public String getFone() {
        return fone;
    }

    /**
     * @param fone the fone to set
     */
    public void setFone(String fone) {
        this.fone = fone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email.toLowerCase();
    }
    
    @Override
    public String toString(){
        return cpf + " - " + nome + " - " + dtNasc + " - " + logradouro
                + " - " +num + " - " + comp + " - " + bairro+ " - " + municipio
                + " - " + uf + " - " + cep + " - " + fone + " - " + email;
    }
    
    
}
