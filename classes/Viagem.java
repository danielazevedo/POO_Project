/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import static projeto.Projeto.*;

/**
 *
 * @author PC
 */
public class Viagem implements Serializable {
    
    //attributes
    private ArrayList <Autocarro> autocarros;
    private int codigo;
    private ArrayList <Comentario> comentarios;
    private Tempo data;
    private String destino;
    private String duracao; 
    private int []lugar;
    private float preco;
    private String origem;
    
    
    //methods

    public Viagem(ArrayList<Autocarro> autocarros, int codigo, ArrayList<Comentario> comentarios, Tempo data, String destino, String duracao, int[] lugar, float preco, String origem) {
        this.autocarros = autocarros;
        this.codigo = codigo;
        this.comentarios = comentarios;
        this.data = data;
        this.destino = destino;
        this.duracao = duracao;
        this.lugar = lugar;
        this.preco = preco;
        this.origem = origem;
    }
    public Viagem() {
        this.autocarros = null;
        this.codigo = 0;
        this.comentarios = new ArrayList();
        this.data = null;
        this.destino = null;
        this.duracao = null;
        this.lugar = null;
        this.preco = 0;
        this.origem = null;
    }

    

    public ArrayList<Comentario> getComentarios() {
        return comentarios;
    }

    
        
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setAutocarros(ArrayList<Autocarro> autocarros) {
        this.autocarros = autocarros;
    }

    public void setComentarios(ArrayList<Comentario> comentarios) {
        this.comentarios = comentarios;
    }

    public void setData(Tempo data) {
        this.data = data;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public Tempo getData() {
        return data;
    }

    public ArrayList<Autocarro> getAutocarros() {
        return autocarros;
    }

    public String getDestino() {
        return destino;
    }

    public String getDuracao() {
        return duracao;
    }

    public int[] getLugar() {
        return lugar;
    }

    public float getPreco() {
        return preco;
    }

    public String getOrigem() {
        return origem;
    }
    /**
     * Funcao que calcula a media, percorre o ArrayList de comentarios, somando a pontuacao de cada um deles, dividindo depois pelo tamanho do ArrayList de comentarios
     * @return media calculada
     */
    public float media(){
        float media=0;
        for (Comentario comentario : comentarios) {
            media+=comentario.getPontuacao();
        }
        media/=comentarios.size();
        return media;
    }

    public void setLugar(int[] lugar) {
        this.lugar = lugar;
    }
    /**
     *Funcao que alterar apenas um lugar no array
     *@param a posicao do array que pretedemos alterar
     *@param novo valor que queremos por na array
     */
    public void set1Lugar(int a,int novo){
        this.lugar[a]=novo;
    }
    public void print_lugares(){
    for(int c=0;c<lugar.length;c++){
        System.out.print(lugar[c]);
    }
        print_string("");
    
    }

    @Override
    public String toString() {
        return "\nautocarros=" + autocarros + "\ncodigo=" + codigo + "\ndata=" + data + "\ndestino=" + destino + "\nduracao=" + duracao +" horas\nlugares=" + Arrays.toString(lugar) +"\npreco=" + preco + "\norigem=" + origem+"\n";
    }
    
    

    
    

    
    
}
