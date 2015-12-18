package projeto;

import java.util.ArrayList;
import java.util.Scanner;
import static projeto.Projeto.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC
 */
public class Espera extends Reserva{

    public Espera(Autocarro autocarro, String username, Tempo data, float valor, Viagem viagem) {
        super(autocarro, username, data, valor, viagem);
    }
    
    //attributes
    
    //methods
    /**
     * funcao que permite ao cliente do tipo Espera reservar uma viagem que foi cancelada por outro utilizador
     * nesta funçºao é procurada a viagem que esta em espera e apoa a encontrar altera a reserva do tipo Espera para o tipo Valida
     * @param pos lugar da viagem em espera
     * @param users lista de utilizadores
     * @param tempo data de login
     */
    private void autoriza_pagamento(int pos, ArrayList <Utilizador> users,Tempo tempo){
        Reserva valida;
        for(int i=0;i<users.size();i++){
            Cliente cliente=(Cliente) users.get(i);
            if(username.equals(cliente.getUsername())){
                for( int j=0; j<cliente.reservas.size();j++){
                    Reserva reserva=cliente.reservas.get(j);
                    if(reserva.viagem==viagem){
                        cliente.reservas.remove(reserva); //mudança de class
                        valida=new Valida(viagem.getAutocarros().get(j),username,tempo,pos+1,viagem.getPreco(),viagem);
                        cliente.reservas.add(valida);
                        reserva.viagem.set1Lugar(pos,1);//alteramos o lugar de 0 para 1(fica ocupado)
                    } 
                } 
            }
        }
            
    }
    /**
     * funcao que indica a posicao do lugar que foi cancelado por outro utilizador, e pergunta ao cliente, quando faz Loogin, se pretende ocupar essa vaga
     * @param lista
     * @param tempo
     * @return 
     */
    public int verifica_reservas(ArrayList <Utilizador> lista, Tempo tempo){
        int opcao;
        for(int i=0;i<viagem.getLugar().length;i++){
                    if(viagem.getLugar()[i]==0){
                        print_string("Existe uma vaga no lugar "+(i+1)+" na viagem com o codigo "+viagem.getCodigo()+"\n");
                        print_string("Pretende preencher a vaga?\n1.Sim\n2.Nao");
                        Scanner sc = scanner();
                        while(!sc.hasNextInt()){
                            sc.next();
                        }
                        opcao= sc.nextInt();sc.nextLine();
                        switch(opcao){
                            case 1: return i;
                            case 2: return -1;
                        }
                      
            }
           
        }
        return -1;
    }
    /**
     * funcao que retorna o tipo de utilizador
     * @return 
     */
    @Override
    public String get_class() {
        return "Espera";
    }
    }


