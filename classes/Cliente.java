/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.*;
import static projeto.Projeto.*;

/**
 *
 * @author PC
 */
public abstract class Cliente extends Utilizador implements java.io.Serializable{
    
    //attributes

    /**
     *
     */
        protected ArrayList <Reserva> reservas;
    
    //methods

    
    public Cliente(ArrayList<Reserva> reservas, String email, String morada, long nif, String nome, String password, String username, long telefone) {
        super(email, morada, nif, nome, password, username, telefone);
        this.reservas = reservas;
    }


    public abstract void cancela_reserva(Tempo dhoje);


    public abstract void reserva_viagem(ArrayList <Viagem> viagens,Tempo tempo);
   
    /**
     * Imprime se as reservas de um cliente
     */
    private void consulta_reserva(){
        if(this.reservas==null)
            print_string("nao exitem reservas\n");
        else{
        for(Reserva reserva:this.reservas)
            print_reserva(reserva);
        }
        
    }
    
    
    /**
     * Funcao que permite atribuir uma pontuacao e um comentario a uma viagem
     * inicialmente é pedido o codigo da viagem a comantar, depois de a procurar na lista de viagens, é pedido uma pontuacao e um comentario, estes sao inseridos num objeto do tipo Comentario, que é posteriormente adicionado ao ArrayList de comentarios da viagem
     * @param viagens lista com todas as viagens
     */
    private void comentar(ArrayList <Viagem> viagens){
        
        int codigo;
        Scanner sc=scanner();
        do{
            print_string("Indique o codigo da viagem a comentar:");
            while(!sc.hasNextInt()){
                sc.next();
            }
            codigo=sc.nextInt();sc.nextLine();
        }while(procura_viagem(viagens,codigo)==null);
        Viagem v=new Viagem();
        for(Reserva reserva:reservas){
            if(reserva.viagem.getCodigo()==codigo)
                v=reserva.viagem;
        }
        if(v!=null){
            print_string("Indique a pontuacao a dar:");
            int pontuacao=sc.nextInt();sc.nextLine();
            print_string("Indique o comentario a fazer:");
            String comentario=sc.nextLine();
            Comentario c=new Comentario(pontuacao,comentario);
            int i;
            for(i=0;i<reservas.size();i++){
                if(reservas.get(i).viagem.getCodigo()==codigo){
                    v=reservas.get(i).viagem;
                    v.getComentarios().add(c);
                }

            }
        }
        else
            print_string("Essa viagem nao foi feita pelo utilizador");
    }
    
    /**
     * funcao usada para que seja mostrada uma notificação ao cliente no caso de haver um cancelamento de reserva numa viagem em que o cliente estava na lista de espera
     * percorre as reservas do cliente e verifica se alguma é do tipo "Espera", nesse caso, se o cliente assim o desejar, o cliente ocupa esse lugar vago
     * @param lista listas com os utilizadores
     * @param tempo tempo inderido no login
     */
    public void identifica_espera(ArrayList <Utilizador> lista, Tempo tempo){
        Espera espera;
        int lugar;
        if(reservas!=null){
            for(Reserva reserva:reservas){
                if("Espera".equals(reserva.get_class())){
                    espera=(Espera) reserva;
                    lugar=espera.verifica_reservas(lista,tempo);
                    
                    if(lugar!=-1){
                        Valida valida;
                        reservas.remove(reserva);
                        valida=new Valida(reserva.autocarro,username,tempo,(lugar+1),reserva.valor,reserva.viagem);
                        reservas.add(valida);
                        valida.viagem.set1Lugar(lugar,1);
                        System.out.println(valida.viagem.getLugar());
                                
                    }
                }
            }
        }
        
    }
    
    /**
     * Função que mostra o menu do cliente, onde se encontram as ações que esse cliente pode tomar
     * @param lista lista com todos os utilizadores
     * @param bus lista com todos os autocarrros
     * @param viagem lista com todos as viagens
     * @param tempo data e hora de login
     * @return 1 no caso se ser feito Logout, 0 no caso de se ser feito Exit
     */
    public int show_menu(ArrayList <Utilizador> lista,ArrayList <Autocarro> bus,ArrayList <Viagem> viagem, Tempo tempo){
    int opcao;
    do{
    
        Scanner sc = scanner();
        
        print_string("1.Consulta reserva\n2.Cancelar reserva\n3.Consultar as viagens\n4.Reservar viagem\n5.Inserir Comentario/Pontuacao\n6.Ler Comentario/Pontuacao\n7.Logout\n8.Exit");
        while(!sc.hasNextInt()){
           sc.next();
        }
        opcao=sc.nextInt();sc.nextLine();//indica a ação a realizar sobre a reserva

    
        switch(opcao){
            case 1: this.consulta_reserva();break;
            case 2: this.cancela_reserva(tempo);break;
            case 3: this.consulta_destino(viagem,tempo);break;
            case 4: this.reserva_viagem(viagem,tempo);break;
            case 5: this.comentar(viagem);break;
            case 6: this.ver_comentarios(viagem);break;
            case 7: break;
            case 8: break;
            }
    }while(opcao!=7 && opcao!=8);//no caso de ser feito Logout ou Exit, saimos do ciclo
    if(opcao==7)
        return 1;
    return 0;

    }
    /**
     * funcao que imprime as viagens com um detrerminado destino
     * inicialmente é pedido o destino, e no caso do atributo Destino de cada viagem ser igual ao destino pedido e o tempo do Login ser maior do da viagem(ou seja a viagem ainda não aconteceu), imprime essa viagem
     * @param viagens
     * @param tempo 
     */
    private void consulta_destino(ArrayList <Viagem> viagens,Tempo tempo){
    print_string("Indique o destino que pretende");
    Scanner sc = new Scanner(System.in);
    String destino=sc.nextLine();
    Iterator <Viagem> it = viagens.iterator();
        Viagem viagem;
        while(it.hasNext()){
            viagem=it.next();
            if(viagem.getDestino().equals(destino) && tempo.dif_datas(viagem.getData())>0)    
                print_viagem(viagem);


}
    }

    
    /**
     * Funcao que imprime os comentarios de uma determinado viagem
     * incialmente é pedida o codigo da viagem, e apos procurar a respetiva viagem na lista de viagens, imprime o ArraList de comnetarios da viagem
     * @param viagens lista de viagens
     */
    private void ver_comentarios(ArrayList <Viagem> viagens){
        int codigo;
        Scanner sc=scanner();
        do{
            print_string("Indique o codigo da viagem da qual pretende ver os comentarios"); 
            while(!sc.hasNextInt()){
                sc.next();
            }
            codigo=sc.nextInt();sc.nextLine();
        }while(procura_viagem(viagens,codigo)==null);
        int i;
        for(i=0;i<viagens.size();i++){
            if(viagens.get(i).getCodigo()==codigo){
                ArrayList <Comentario> lista=viagens.get(i).getComentarios();
                for(Comentario c:lista)
                    print_comentario(c);
                
                break;
                
            }
                
        }
    }

    /**
     *funcao que retorna o tipo de utilizador
     * @return tipo de utilizador
     */
    @Override
    public String get_class() {
        return "Cliente";
    }    

}
