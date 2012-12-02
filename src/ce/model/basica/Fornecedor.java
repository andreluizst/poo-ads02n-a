/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ce.model.basica;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Andre
 */
public class Fornecedor {
    private Integer codForn;
    private String nome;
    private String cnpj;
    private String logradouro;
    private int num;
    private String comp;
    private String bairro;
    private String municipio;
    private String uf;
    private String cep;
    private String fone;
    private String email;
    private List<Produto> produtos;
    
    public Fornecedor(){
        produtos= new ArrayList();
    }
    
    public Fornecedor(Integer codForn, String nome, String cnpj, 
            String logradouro, int num, String comp, String bairro,
            String municipio, String uf, String cep, String fone,
            String email){
        this();
        this.codForn= codForn;
        this.nome=nome;
        this.cnpj=cnpj;
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
    
    public Fornecedor(String nome, String cnpj, String logradouro, int num,
            String comp, String bairro, String municipio, String uf, String cep,
            String fone, String email){
        this();
        this.codForn= 0;
        this.nome=nome;
        this.cnpj=cnpj;
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
    
    public Fornecedor(Integer codForn, String nome){
        this();
        this.codForn= codForn;
        this.nome=nome;
        this.cnpj="";
        this.logradouro="";
        this.num=0;
        this.comp="";
        this.bairro="";
        this.municipio="";
        this.uf="";
        this.cep="";
        this.fone="";
        this.email="";
    }

    public Fornecedor(Integer codForn, String nome, String cnpj){
        this();
        this.codForn= codForn;
        this.nome=nome;
        this.cnpj=cnpj;
        this.logradouro="";
        this.num=0;
        this.comp="";
        this.bairro="";
        this.municipio="";
        this.uf="";
        this.cep="";
        this.fone="";
        this.email="";
    }
    
    /**
     * @return the codForn
     */
    public Integer getCodForn() {
        return codForn;
    }

    /**
     * @param codForn the codForn to set
     */
    public void setCodForn(Integer codForn) {
        this.codForn = codForn;
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
     * @return the cnpj
     */
    public String getCnpj() {
        return cnpj;
    }

    /**
     * @param cnpj the cnpj to set
     */
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

    /**
     * @return the produtos
     */
    public List<Produto> getProdutos() {
        return produtos;
    }

    /**
     * @param produtos the produtos to set
     */
    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
    
    /**
     * 
     * @return 
     * Texto contendo o nome e o CNPJ do fornecedor.
     */
    @Override
    public String toString(){
        return getNome() + ", CPF/CNPJ " + getCnpj();
    }
    
    /**
     * 
     * @return 
     * Texto contendo os valores de todos os atribudos do objeto.
     */
    public String toStringAll(){
        return codForn + " - " + cnpj + " - " + nome + " - " + logradouro
                + " - " + num + " - " + comp + " - " + bairro
                + " - " + municipio + " - " + cep + " - " + fone+ " - " + email;
    }
}
