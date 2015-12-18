/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;
import java.util.Scanner;
import static projeto.FicheiroDeObjeto.list2file_utilizador;
import static projeto.FicheiroDeObjeto.list2file_viagem;
import static projeto.Projeto.*;

/**
 *
 * @author PC
 */
public class Premium extends Cliente implements java.io.Serializable{
    
    //attributes
    
    //methods
    
    public Premium(ArrayList<Reserva> reservas, String email, String morada, long nif, String nome, String password, String username, long telefone) {
        super(reservas, email, morada, nif, nome, password, username, telefone);
    } 

    @Override
    /**
     * Funcao que permite cancelar uma reserva
     * inicialmente e dado o codigo da viagem a eliminar e elimina a reserva associada a essa viagem, passando do tipo Valida para o tipo Cancelada
     */
    public void cancela_reserva(Tempo dhoje) {
        Tempo dviagem = null;
        int dif, codigo,j;
        Reserva reserva=null;
        Cancelada cancelada=null;
        Valida valida=null;
        Scanner sc=new Scanner(System.in);
        print_string("Introduza o código da viagem cuja reserva pretende eliminar:");
        codigo=sc.nextInt();
        for(int i=0; i<reservas.size(); i++){
            reserva=reservas.get(i);
            if((reserva.viagem.getCodigo())==codigo){
                valida=(Valida)reserva;
                dif=dhoje.dif_datas(reserva.viagem.getData());//indica a diferenca em dias entre as datas
                if(dif>=2){//no caso de cancelar a reserva ate dois dias antes da viagem, so recebe metade do valor pago
                    print_string("A viagem foi eliminada com sucesso e metade do valor da viagem ser-lhe-à devolvido");
                    reserva.setValor((float) (reserva.getValor()*0.5));//recebe apenas metade do valor
                }
                else{
                    print_string("A viagem foi eliminada com sucesso, mas por faltar "+dif+"dias para a viagem, não vai ser reembolsado");    
                     reserva.setValor(0);
                }
                break;
            }                
        }
       
        reserva.viagem.print_lugares();
        reserva.viagem.set1Lugar(valida.getLugar()-1,0);//coloca o lugar na viagem a 0
        reserva.viagem.print_lugares();
        reservas.remove(reserva);
        cancelada=new Cancelada(reserva.autocarro,reserva.username,reserva.data,reserva.valor,reserva.viagem);//e criada uma reserva do tipo cancelada
        reservas.add(cancelada);//adiciona a reserva Cancelada ás reservas do cliente
    }
    
   @Override
   /**
    * Funcao que permite reservar uma viagem
    * inicialmente é pedido o codigo da viagem, e apos a procurar na lista de viagens, no caso de a viagem ainda nao tiver acontecido, e o cliente ainda nao ter uma reserva nessa viagem, reserva a viagem
    * 
    */
    public void reserva_viagem(ArrayList<Viagem> viagens,Tempo tempo) {//tempo = dia de hoje
        print_string("Indique o codigo da viagem que pretende reservar");
        int codigo;
        Scanner sc=scanner();
        do{
            print_string("Indique o codigo da viagem"); 
            while(!sc.hasNextInt()){
                sc.next();
            }
            codigo=sc.nextInt();sc.nextLine();
        }while(procura_viagem(viagens,codigo)==null);
        Viagem viagem=null;
        boolean flag=false;
        if(reservas==null)
            reservas=new ArrayList <Reserva>();
        Reserva reserva=null;
        
        viagem=procura_viagem(viagens,codigo);//procura a viagem
        for(Reserva r:reservas){
            if(r.viagem==viagem && r.get_class()!="Cancelada")//no caso de a viagem nao estar nas reservas do cliente ou no caso em que nao e do tipo cancelada, a reserva nao pode acontecer
                flag=true;
        }
        double novo_preco=viagem.getPreco()-viagem.getPreco()*0.1;//desconta os 10% por ser tipo Premium
        if(flag==false && viagem!=null && tempo.maiorData(viagem.getData())==0){//verificamos se esta cliente ja fez uma reserva, e se essa viagem ja aconteceu ou nao
            int c;
            viagem.print_lugares();
            for(int j=0;j<viagem.getLugar().length;j++){
                c=0;
                while(c!=viagem.getLugar().length && viagem.getLugar()[c]==1) //verificar se existe lugares livres no autocarro
                   c++;
                if(c!=viagem.getLugar().length){//verificamos se existe lugares livres
                    viagem.set1Lugar(c,1);//mudamos o lugar para 1
                    print_string("A sua reserva foi feita com sucesso.Ficara no lugar "+(c+1)+" o preco a pagar sera "+viagem.getPreco());
                    viagem.print_lugares();
                    reserva = new Valida(viagem.getAutocarros().get(j),username,tempo,(c+1),(float)novo_preco,viagem);//cria uma nova reserva do tipo Valida



                }
                else{//no caso de estar os autocarros cheios é criada a reserva do tipo Espera
                    print_string("Os autocarros ja se encontram cheios.Foi colocado na fila de espera");
                    reserva = new Espera(viagem.getAutocarros().get(j),username,tempo,(float)novo_preco,viagem);//cria uma npva viagem do tipo Espera
                }
                this.reservas.add(reserva);//adicionamos a reserva a respetiva lista
                list2file_viagem(viagens);//atualizamos o ficheiro de viagens
                break;


            }


        }
        else if(viagem==null)//no caso do codigo da viagem nao existir, ou seja a viagem tambem nao existe
            print_string("Essa viagem nao existe");
        else if(tempo.maiorData(viagem.getData())==1)//no caso da data de login ser superior a data da viagem pedida
            print_string("Essa viagem ja passou");
        else    print_string("Este cliente ja possui/fez uma reserva nesta viagem");

    }    

    

}