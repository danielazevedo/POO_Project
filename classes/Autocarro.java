/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Autocarro implements Serializable {
    
    //attributes
    private int lotacao;
    private String matricula;
    
    
    
    //methods

    public Autocarro(int lotacao, String matricula) {
        this.lotacao = lotacao;
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public int getLotacao() {
        return lotacao;
    }

    public void setLotacao(int lotacao) {
        this.lotacao = lotacao;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public String toString() {
        return "\nlotacao=" + lotacao + ", matricula=" + matricula;
    }

    
    
    
}
