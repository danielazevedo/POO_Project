/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

/**
 *
 * @author PC
 */
public class Comentario implements java.io.Serializable{
    //attributes
    private int pontuacao;
    private String comentario;
    
    //methods

    public Comentario(int pontuacao, String comentario) {
        this.pontuacao = pontuacao;
        this.comentario = comentario;
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public String getComentario() {
        return comentario;
    }

    
    
    @Override
    public String toString() {
        return  "Pontuacao: " + pontuacao + "\nComentario:" + comentario+"\n";
    }
    
    
}
