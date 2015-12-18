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
public class Cancelada extends Reserva{

    public Cancelada(Autocarro autocarro, String cliente, Tempo data, float valor, Viagem viagem) {
        super(autocarro, cliente, data, valor, viagem);
    }
    
    //attributes
    
    //methods

    @Override
    public String get_class() {
        return "Cancelada";
    }
}
