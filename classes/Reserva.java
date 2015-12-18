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
public abstract class Reserva implements java.io.Serializable{
    
    //attributes
    protected Autocarro autocarro;
    protected String username;
    protected Tempo data;
    protected float valor;
    protected Viagem viagem;

    public Reserva(Autocarro autocarro, String cliente, Tempo data,float valor, Viagem viagem) {
        this.autocarro = autocarro;
        this.username = cliente;
        this.data = data;
        this.valor = valor;
        this.viagem = viagem;
    }

    public float getValor() {
        return valor;
    }

    
    
    //methods
    public abstract String get_class();

    public Tempo getData() {
        return data;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
    
    
    @Override
    public String toString() {
        return "\nReserva:\nautocarro=" + autocarro + "\nusername do cliente=" + username  + "\nvalor=" + valor + "\nViagem:" + viagem + "\ndata=" + data + "\n";
    }

    
    
    

}
