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
public class Valida extends Reserva{
    private int lugar;
    public Valida(Autocarro autocarro, String username, Tempo data,int lugar, float valor, Viagem viagem) {
        super(autocarro, username, data, valor, viagem);
        this.lugar=lugar;
    }


    
    
    //attributes
    
    //methods
    
    
    public int getLugar() {
        return lugar;
    }

    @Override
    /**
     * funcao que uam string com o tipo de classe
     */
    public String get_class() {
        return "Valida";
    }

    

    
}
