/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ce.model.basica;

/**
 *
 * @author aluno
 */
public class Perfil {

    private String nome;
    private int codPerfil;

    public Perfil(){

    }

    public Perfil(String nome, int codPerfil){
        this.nome = nome;
        this.codPerfil = codPerfil;
    }
    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }
    public int getCodPerfil(){
        return this.codPerfil;
    }
    public void setCodPerfil(int codPerfil){
        this.codPerfil = codPerfil;
    }
}
