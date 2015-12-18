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

import java.math.*;
import java.util.*;
import static projeto.FicheiroDeObjeto.*;
import static projeto.FicheiroDeTexto.*;
import static projeto.Projeto.*;


public class Administrador extends Utilizador {
    
    //attributes
    

    //methods
    
    public Administrador(String email, String morada, long nif, String nome, String password, String username, long telefone) {
        super(email, morada, nif, nome, password, username, telefone);
    }
    /**
     * funcao que permite alterar os atributos do autocarro, é pedida a matricula do autocarro que queremos alterar, sendo depois imprimidos os atributos que podemos alterar, 
     * sendo estes alterardos atraves dos setters
     * @param lista lista com os autocarros existentes
     */
    private void altera_autocarro(ArrayList <Autocarro> lista) {
        print_string("Indique a matricula do autocarro a alterar");
        Scanner sc = scanner();
        while(!sc.hasNextInt()){
            sc.next();
        }
        String matricula=sc.nextLine();
        Autocarro bus=procura_autocarro(lista,matricula);
        print_string("1.Matricula\n2.Lotacao");
        while(!sc.hasNextInt()){
            sc.next();
        }
        int op=sc.nextInt();
        print_string("Indique o novo dado:");
        switch(op){
            case 1: String dado=sc.nextLine();
                    bus.setMatricula(dado);
            
            case 2: int dado1=sc.nextInt();
                    bus.setLotacao(dado1);
        }
        list2file_autocarro(lista);
    }
    /**
     * funcao que permite alterar os atributos de um utilizador, é pedido o username do utilizador que queremos alterar, sendo depois imprimidos os atributos que podemos alterar, 
     * sendo estes alterardos atraves dos setters
     * @param lista funcao com os utilizadores existentes
     */
    private void altera_utilizador(ArrayList <Utilizador> lista){
        print_string("Indique o username do utilizador a alterar");
        Scanner sc = scanner();
        String name=sc.nextLine();
        int opcao;
        Utilizador user=procura_utilizador(lista,name);
        if(user!=null){

            print_string("1.Nome\n2.Email\n3.Morada\n4.Nif\n5.telefone\n6.Username\n7.Password");
            Scanner op = scanner();
            while((opcao=verifica_int(op.nextLine()))==-1)
            print_string("Indique o novo dado:");
            switch(opcao){
                case 1: user.setNome(sc.nextLine());break;
                case 2: user.setEmail(sc.nextLine());break;
                case 3: user.setMorada(sc.nextLine());break;
                case 4: user.setNif(sc.nextLong());sc.nextLine();break;
                case 5: user.setTelefone(sc.nextLong());break;
                case 6: user.setUsername(sc.nextLine());break;
                case 7: user.setPassword(sc.nextLine());break;             
            }
         list2file_utilizador(lista);  

        }
        
        
            
    }
    /**
     * funcao que permite alterar da viagem caso esta nao possua reservas, é pedida o codigo da viagem que queremos alterar, sendo depois imprimidos os atributos que podemos alterar, 
     * sendo estes alterardos atraves dos setters
     * @param viagens 
     */
    private void altera_viagem(ArrayList <Viagem> viagens){
       print_string("Indique o codigo da viagem a alterar");
        Scanner sc = scanner();
        while(!sc.hasNextInt()){
            sc.next();
        }
        int codigo=sc.nextInt();
        Viagem viagem=procura_viagem(viagens,codigo);
        if(viagem!=null){
           print_string("1.Codigo\n2.Origem\n3.Destino\n4.Preco\n5.Data\n6.Duracao");
            Scanner op = scanner();
            while(!op.hasNextInt()){
                op.next();
            }
            int opcao=op.nextInt();
            print_string("Indique o novo dado:");
            String dado=op.nextLine();
            
            switch(opcao){
                case 1: viagem.setCodigo(op.nextInt());op.nextLine();break;
                case 2: viagem.setOrigem(op.nextLine());break;
                case 3: viagem.setDestino(op.nextLine());break;
                case 4: viagem.setPreco(op.nextInt());op.nextLine();break;
                case 5: print_string("1.Dia\n2.Mes\n3.Ano");
                        while(!op.hasNextInt()){
                            op.next();
                        }
                        int opcao2=op.nextInt();
                        switch(opcao2){
                            case 1: viagem.getData().setDia(opcao2);break;
                            case 2: viagem.getData().setMes(opcao2);break;
                            case 3: viagem.getData().setAno(opcao2);break;
                        }
                    
                        
                case 6: viagem.setDuracao(dado);break;
            
            
            }
            list2file_viagem(viagens);
           }
     }
    /**
     * funcao que criar um autocarro, é pedida a matricula e a lotacao do novo autocarro, os atributos novos sao sujeitos a uma verificacao, sendo apos a verificacao atributos validos
     * este novo autocarro é depois adicionado à lista de autocarros
     * @param lista lista de autocarros, onde vai ser adicionada o novo autocarro
     */
    private void cria_autocarro(ArrayList <Autocarro> lista){
        Autocarro autocarro;
        String matricula;
        String aux;
        int lotacao;
        Scanner sc = scanner();
        do{
            print_string("Introduza a lotacao do autocarro:");
            aux=sc.nextLine();
            lotacao=verifica_int(aux);
        }while(lotacao==-1);
        do{
            print_string("Introduza a matricula do autocarro:");
            matricula=sc.nextLine();    
        }while((matricula=verifica_matricula(matricula))==null);
        autocarro=new Autocarro(lotacao,matricula);
        lista.add(autocarro);
        //atualizamos o ficheiro dos autocarros
        list2file_autocarro(lista);
    }
        
    /**
     * funcao que permite a criação de um novo utilizador, são pedidos os atributos dos novos utilizadores, sendo em cada um verificado se é um atributo valido ou não,
     * apos isso é perguntado que tipo de utilizador pretende criar(administrador,cliente regular, ou cliente premium), sendo adicionado a respetiva lista consoante o tipo de utilizador criado
     * @param l_user lista com os utilizadores, inde vai ser inserido o novo utilizador
     * @param opcao numero que vai indicar que tipo de utilizador pretende-se criar(1:Administrador, 2:Cliente regular,3:Cliente premium)
     */
    private void cria_utilizador(ArrayList <Utilizador> l_user, int opcao){
       
        ArrayList <Reserva> lista=null;
        Utilizador user = null;
        String nome,nome_user,mail,pass,adress;
        long n,t;
        Scanner sc = scanner();
        
        
        do{
            print_string("Introduza o nome:");
            nome=sc.nextLine();
        }while(verifica_nome(nome)==0);
       
        do{
            print_string("Introduza o email:");   
            mail=sc.nextLine();
        }while(verifica_mail(mail)!=1);
        do{
            print_string("Introduza o numero de telefone:");
            
            while(!sc.hasNextLong()){
                sc.next();
            }
            t=sc.nextLong();sc.nextLine();
        }while(verifica_numero(t)==0 );
        do{
            print_string("Introduza o nif:");
            while(!sc.hasNextLong()){
                sc.next();
            }
            n=sc.nextLong();sc.nextLine();
        
        }while(verifica_numero(n)==0);
        do{
           print_string("Introduza a morada:");
            adress=sc.nextLine();
        }while(verifica_morada(adress)==0);
        do{
            print_string("Introduza o nome de utilizador");
            nome_user=sc.nextLine();
        
        }while(verifica_user(nome_user, l_user)==0);
        do{
           print_string("Introduza a password:");
            pass=sc.nextLine();
        }while(pass.indexOf(" ")!=-1);
            
        switch(opcao){
           case 1: user=new Administrador(mail,adress,n,nome,pass,nome_user,t);break;
           case 2: user=new Regular(null,mail,adress,n,nome,pass,nome_user,t);break;
           case 3: user=new Premium(null,mail,adress,n,nome,pass,nome_user,t);break;
          }
        l_user.add(user);
        //atualizamos o ficheiro com os utilizadores
        list2file_utilizador(l_user);
        
    }
    

    /**
     * funcao que permite criar uma nova viagem, primeiro é pedido cada atributo de uma viagem, sendo cada um deles verificados se são validos ou não,
     * @param viagens
     * @param bus 
     */
    private void cria_viagem(ArrayList <Viagem> viagens, ArrayList <Autocarro> bus){
        Viagem viagem;
        int[] lugares;
        int codigo,lotacao, v,n;
        String aux;
        float preco;
        Tempo data;
        ArrayList <Autocarro> autocarros=null;
        ArrayList < Comentario> comentarios=new ArrayList();
        Scanner sc=scanner();
        do{
            print_string("Indique o codigo da viagem"); 
            while(!sc.hasNextInt()){
            sc.next();
        }
            codigo=sc.nextInt();sc.nextLine();
        }while(procura_viagem(viagens,codigo)!=null);
        
        print_string("Indique a origem da viagem");
        String origem=sc.nextLine();
        print_string("Indique a destino da viagem");
        String destino=sc.nextLine();
        do{
            print_string("Indique a preco da viagem");
            aux=sc.nextLine();
            
        }while((preco=verifica_preco(aux))==-1);
        
        do{
            print_string("Indique a hora da viagem");
            while(!sc.hasNextInt()){
                sc.next();
            }
            int hora=sc.nextInt();sc.nextLine();
            print_string("Indique os minutos da viagem");
            while(!sc.hasNextInt()){
                sc.next();
            }
            int minuto=sc.nextInt();sc.nextLine();
            print_string("Indique a dia da viagem");
            while(!sc.hasNextInt()){
                sc.next();
            }
            
            int dia=sc.nextInt();sc.nextLine();
            print_string("Indique a mes da viagem");
            while(!sc.hasNextInt()){
                sc.next();
            }
            
            int mes=sc.nextInt();sc.nextLine();
            print_string("Indique a ano da viagem");
            while(!sc.hasNextInt()){
                sc.next();
            }
            
            int ano=sc.nextInt();sc.nextLine();

            print_string("Indique a duracao da viagem");
            data=new Tempo(ano,dia,hora,mes,minuto);
            v=verifica_data(data);
            if(v==0)
                print_string("A data ou hora inseridas sao invalidas");
        }while(v==0);
        String duracao=sc.nextLine();
        do{
            print_string("Indique o numero de autocarros");
            aux=sc.nextLine();
        }while((n=verifica_int(aux))==-1);
        do{
            print_string("Indique o numero de lugares para os autocarros");
            aux=sc.nextLine();
        }while((lotacao=verifica_int(aux))==-1);
        lugares=new int[lotacao];
        autocarros=new ArrayList <Autocarro>(n);

        for(int i=0;i<n;i++){
            if(bus.size()>=n){
                Autocarro autocarro=bus.get(i);
                //verificamos se existe ou não viagens(ou seja se existem autocarros ocupados, se nao houver podem ser logo associados a viagem, no caso de ainda nao estarem na viagem
                if(viagens.size()==0 && lotacao<=autocarro.getLotacao() && !autocarros.contains(autocarro))
                    autocarros.add(autocarro);
                //verificamos se podemos adicionar o autocarro a viagem
                else if(verifica_autocarro(autocarro,viagens,lotacao,data)==1 && !autocarros.contains(autocarro)){//verificamos se um autocarro ainda nao existe na viagem
                    autocarros.add(autocarro);
                }

            }
            else{
                print_string("Nao existem numeros de autocarro suficientes");
                break;
            }
        }
        //imprimimos o numero de autocarros que podem ser associados à viagem, no caso de nao terem havido autocarros suficientes para a viagem ser criada    
        if(autocarros.size()<n)
            print_string("So existe "+autocarros.size()+" autocarros disponiveis");
        //metemos os lugares a -1, que significa que estao livres(1 significa que estão ocupados, 0 significa que foram cancelados, -1 significa que estao livrres)
        else{
            for(int c=0;c<lotacao;c++){
                    lugares[c]=-1;
            }
            //inserimos a viagem na lista de viagens
            viagem=new Viagem(autocarros,codigo,comentarios,data,destino,duracao,lugares,preco,origem);
            viagens.add(viagem);
            print_string("Criacao da viagem bem sucedida");
            //atualizamos o ficheiro com as viagens
            list2file_viagem(viagens);
        }
    
    }
    
 
    

    /**
     * funcao que imprime os atributos do autocarro,inicialmente é pedida a matricula, sendo procurado a matricula na lista de autocarros para encontrar o autocarro respetivo
     * @param lista lista que contem os autocarros
     */
    private void consulta_autocarro(ArrayList <Autocarro> lista){
        print_string("Indique a matricula do autocarro a consultar");
        Scanner sc = scanner();
        String name=sc.nextLine();
        Autocarro bus=procura_autocarro(lista,name);
        if(bus!=null)
            System.out.println(bus);
    }
    /**
     * funcao que imprime os atributos do utilizador, inicalmente é pedido o username do utilizador, sendo este procurado na lista de utilizadores, de modo a encontrar o utilizador respetivo ao username
     * @param lista lista que contem os utilizadores
     */
    private void consulta_utilizador(ArrayList <Utilizador> lista){
        print_string("Indique o username do utilizador a consultar");
        Scanner sc = scanner();
        String name=sc.nextLine();
        Utilizador user=procura_utilizador(lista,name);
        if(user!=null)
            System.out.println(user);
    }
    

    /**
     * funcao que elimina um autocarro, é pedida a matricula, sendo atraves desta descoberto o autocarro que pretendemos eliminar, apos isso o autocarro é eliminado da lista dos autocarros
     * sendo depois atualizado o ficheiro com a lista de autocarros
     * @param lista lista de autocarros
     */
    private void elimina_autocarro(ArrayList <Autocarro> lista){
        print_string("Indique a matricula do autocarro a eliminar");
        Scanner sc = scanner();
        String matricula=sc.nextLine();
        Autocarro bus=procura_autocarro(lista,matricula);
        if(bus!=null){
            lista.remove(bus);
            list2file_autocarro(lista);
        }
    }
    /**
     * 
     * funcao que indica se um determinado autocarro existe, ou não na lista de autocarros, atraves da matricula procuramos na lista de autocarros para ver se o autocarro existe ou não
     * @param lista lista de autocarros
     * @param matricula matricula do autocarro a procurar
     * @return retorna o autocarro caso ele exista, se não retorna null
     */
     
    private Autocarro procura_autocarro(ArrayList <Autocarro> lista,String matricula){
       
        Iterator <Autocarro> it = lista.iterator();
        Autocarro bus;
        while(it.hasNext()){
            bus=it.next();
            if(bus.getMatricula().equals(matricula))
                return bus;
        }
        return null;
        
    }
    
    /**
     * funcao que elimina um determinado utilizador caso ele exista, é pedido o username do utilizador sendo este procurado na lista de utilizadores, se o encontrar este é removido da lista de utilizadores
     * o ficheiro com os utilizadores é atualizada
     * @param lista lista com os utilizadores
     */
    private void elimina_utilizador(ArrayList <Utilizador> lista){
        print_string("Indique o username do utilizador a eliminar");
        Scanner sc = scanner();
        String name=sc.nextLine();
        Utilizador user=procura_utilizador(lista,name);
        if(user!=null){
            lista.remove(user);
            
            list2file_utilizador(lista);
          
        }
        else
            print_string("Esse utilizador nao existe");
        
    }
    /**
     * funcao onde é pedido o username do utilizador sendo este procurado na lista de utiizadores, para ver se esse utilizador existe ou não
     * @param lista lista de utilizadores
     * @param username userame do utilizador a procurar
     * @return retorna o utilizador caso ele exista, se não retorna null
     */
    private Utilizador procura_utilizador(ArrayList <Utilizador> lista,String username){
       
        Iterator <Utilizador> it = lista.iterator();
        Utilizador user;
        while(it.hasNext()){
            user=it.next();
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
        
    } 
    /**
     *  funcao que elimina uma determinada viagem, inicialmente é pedido o codigo da viagem para procurar a viagem na lista, caso a encontre, remove-a da lista, no caso de nao encontrar a viagem nao é eliminada nenhuma viagem
     * @param viagens lista de viagens
     * @param users lista de utilizadores
     */
    private void elimina_viagem(ArrayList <Viagem> viagens, ArrayList <Utilizador> users){
        print_string("Indique o codigo da viagem a eliminar");
        Scanner sc = scanner();
        while(!sc.hasNextInt()){
            sc.next();
        }
        int name=sc.nextInt();
        boolean flag=false;
        Viagem viagem=procura_viagem(viagens,name);
        for(int i=0;i<viagem.getLugar().length;i++){
            if(viagem.getLugar()[i]==1){
                print_string("Nao e possivel eliminar esta viagem pois ja possui reservas");
                flag=true;
                break;
        }
        }
        if(viagem!=null && flag==false){
            viagens.remove(viagem);
            list2file_viagem(viagens);
            
        }
        else if(viagem==null)
            print_string("Essa viagem nao existe");
    }
    /**
     * funcao que indica o volume de vendas em cada mes e o dia do ano em que houve mais vendas,
     * inicialmente é pedido o ano em que queremos realizar as estatisticas, e é criado um array de arrays de inteiros([12][31]) em que cada posicao corresponde a um dia do ano, e é criado um interio max, que permiterá realizar as estatisticas
     * para obtemos as reserva todas usamos a funcao user2reserva(),onde obtemos um ArrayList com as reservas todas
     * no caso da reserva ter sido feita no ano pretendido e corresponder a uma reserva do tipo "Valida" é incrementado 1 à respetiva posicao da data no array, sendo que no caso de ser maior que o valor maximo(max) este é atualizado
     * 
     * @param viagens lista de viagens
     * @param lista lista de utilizadores
     */
 private void estatisticas_viagem( ArrayList <Viagem> viagens, ArrayList <Utilizador> lista){
        Scanner sc=scanner();
        print_string("Introduza o ano cujas estatísticas quer visualizar:");
        while(!sc.hasNextInt()){
            sc.next();
        }
        String stats="";
        int ano=sc.nextInt();sc.nextLine();
        Tempo data=new Tempo(0, 0,0,ano,0);
        int[][] dia_vendas=new int[12][31];
        ArrayList <Tempo> datas=new ArrayList();
        ArrayList <Reserva> reservas=user2reserva(lista);
        int[] vendas=new int[12];
        for(Reserva reserva:reservas){
            if("Valida".equals(reserva.get_class()) && reserva.getData().getAno()==ano){
                int mes=reserva.getData().getMes();
                int dia=reserva.getData().getDia();
                vendas[mes-1]++;
                dia_vendas[mes-1][dia-1]++;
            }
        }
        int max=0;//percorrer a matriz, para ver qual o dia com mais vendas
        for(int mes=0; mes<dia_vendas.length; mes++){
            for(int dia=0; dia<dia_vendas[mes].length; dia++){
                if(dia_vendas[mes][dia]==max){ 
                    data.setDia(dia+1);
                    data.setMes(mes+1);
                    datas.add(data);
                }
                else if(dia_vendas[mes][dia]>max){
                    data.setDia(dia+1);
                    data.setMes(mes+1);
                    datas.clear();
                    datas.add(data);
                    max=dia_vendas[mes][dia];
                }   
            }
        }
        if(datas.size()!=0){
            stats+="Dias com mais vendas: ";
            print_string("Dias do ano com mais vendas: ");
        }
        for(Tempo d:datas){
            stats+=datas;
            System.out.println(d);
        }
        stats+="Vendas mensais: ";
        for(int i=0;i<12;i++){
            System.out.println(vendas[i]);
        }
        stats2file(stats);
 }
 
    /**
     * funcao que permite listar todas as reservas de uma viagem 
     * inicialmente e pedido o codigo da viagem, posteriormente é percorrido a lista de utilizadores, no caso de ser do tipo "cliente", percorre-se o array de reservas desse cliente, no caso do codigo da viagem da reserva ser igual a codigo inicialmente pedido, a reserva que esta associada a essa viagem é adicionada ao ArrayList que irá ser retornado
     * @param users
     * @return ArrayList com todas as reservas duma viagem
     */
    
    private ArrayList <Reserva> listar_reservas(ArrayList <Utilizador> users){
        Scanner sc=scanner();
        ArrayList <Reserva> lista=new ArrayList();
        print_string("Introduza o código da viagem cujas reservas pretende visualizar");
        while(!sc.hasNextInt()){
            sc.next();
        }
        int cod=sc.nextInt();sc.nextLine();
        for(Utilizador utilizador:users){
            if("Cliente".equals(utilizador.get_class())){
                Cliente cliente=(Cliente)utilizador;
                for(Reserva reserva:cliente.reservas){
                    if(cod==reserva.viagem.getCodigo())
                        lista.add(reserva);
                }
            }
        }
        return lista;
    }
    /**
     *  funcao que imprime a(s) viagem(s) mais vendidas num determinado mes
     * para auxiliar é criado um array com o mesmo tamanho que a lista de viagens
     * inicialmente é pedido o mes em que se pretende ver a viagem mais vendida, para tal percorremos todo a lista de viagens e para cada uma contamos os lugares que estao a 1(ou seja, que estao ocupados), armazenamos o valor dado a respetiva posicao no array
     * depois percorremos o array gerado para encontrar o(s) valor(es) maximo(s), que sao imprimidos e escritos no ficheiro de estatisticas
     * @param viagens lista de viagens
     */
    private void mais_vendida(ArrayList <Viagem> viagens){
        print_string("Introduza o mes em que quer ver qual a viagem mais vendida: ");
        Scanner sc=scanner();
        
        while(!sc.hasNextInt()){
            sc.next();
        }
        int mes = sc.nextInt();sc.nextLine();
        String stats="A viagem mais vendida no mes "+mes+" e/sao a(s) viagem com o(s) codigo(s) ";
        int i=0,max=0;
        int []nViagens=new int [viagens.size()];
        int [] lugar;
        for(i=0; i<viagens.size(); i++){
            if(viagens.get(i).getData().getMes()==mes){
                lugar=viagens.get(i).getLugar();
                for(int c=0;c<lugar.length;c++){
                       if(lugar[c]==1)
                           nViagens[i]++;
                   }
                        
        }
            
            if(nViagens[i]>max)
                max=nViagens[i];
        }
        if(nViagens.length==0)
            stats="";
        for(int c=0;c<nViagens.length;c++){
            if(nViagens[c]==max){
                print_viagem(viagens.get(c));
                stats+=viagens.get(c).getCodigo()+" ";//adicionamos à string que vamos escrever no ficheiro o codigo da viagem
            }
        }
        //escrevemos a string no ficheiro de estatisticas
        stats2file(stats);
        
        
    }
    
    
    /**
     * funcao que retorna todas as reservas do tipo canceladas, é percorrida a lista de utilizadores e no caso de ser do tipo "Cancelad", adicionamos ao ArraList que vamos retornar
     * @param users lista de utilizadores
     * @return retorna uma ArrayList com as reservas canceladas
     */
    private ArrayList <Reserva> reservas_canceladas(ArrayList <Utilizador> users){//percorremos os utilizadores todos, e para cada um verificamos se tem alguma reserva do tipo "Cancelada" essa lista
        ArrayList <Reserva> reservas=listar_reservas(users);
        for(Reserva reserva: reservas){
            if(!"Cancelada".equals(reserva.getClass()))
                reservas.remove(reserva);
        }
        return reservas;  
    }
    /**
     *funcao que retorna todas as reservas do tipo em espera
     * @param users lista de utilizadores
     * @return retorna uma ArrayList com as reservas em espera
     */
    private ArrayList <Reserva> reserva_espera(ArrayList <Utilizador> users){//percorremos os utilizadores todos, e para cada um verificamos se tem alguma reserva do tipo "Espera" e devolvemos essa lista
         ArrayList <Reserva> reservas=listar_reservas(users);
         for(Reserva reserva: reservas){
            if("Espera".equals(reserva.getClass()))
               reservas.remove(reserva);
         }
         return reservas;
    }
    
    /**
     * é a funcao que mostra o menu pertencente ao administrador
     * @param lista lista com todos os utilizadores
     * @param bus lista com todos os autocarrros
     * @param viagem lista com todos as viagens
     * @param tempo data e hora de login
     * @return 
     */
    public int show_menu(ArrayList <Utilizador> lista,ArrayList <Autocarro> bus,ArrayList <Viagem> viagem,Tempo tempo){
        int opcao1,opcao2,opcao3,opcao4;
        do{
        print_string("\n1.Gerir Utilizadores\n2.Gerir Autocarros\n3.Gerir Viagens\n4.Outros\n5.Logout\n6.Exit");
        Scanner op=scanner();
        while(!op.hasNextInt()){
            op.next();
        }
        opcao1=op.nextInt();op.nextLine();//indica a opcao a realizar sobre o utilizador
        switch(opcao1){
            case 1: print_string("\n1.Criar Utilizador\n2.Eliminar Utilizador\n3.Alterar Utilizador\n4.Consultar Utilizador\n5.Listar Utilizadores\n6.Exit");
                    while(!op.hasNextInt()){
                        op.next();
                    }
                    opcao2=op.nextInt();op.nextLine();//indica a opcao a realizar sobre o administradot
                    switch(opcao2){
                        case 1: print_string("\n1.Criar Administrador\n2.Criar cliente Regular\n3.Criar cliente Premium\n4.Exit");
                                while(!op.hasNextInt()){
                                    op.next();
                                }
                                opcao3=op.nextInt();op.nextLine();
                                
                                if(opcao3!=4)
                                    this.cria_utilizador(lista,opcao3);
                                else
                                    break;
                                
                                break;
                        
                        case 2: print_string("\n1.Eliminar Administrador\n2.Eliminar cliente Regular\n3.Eliminar cliente Premium\n4.Exit");
                                while(!op.hasNextInt()){
                                    op.next();
                                }
                                opcao3=op.nextInt();op.nextLine();//indica o tipo de utilizador que pretendemos eliminar
                                switch(opcao3){
                                   case 1: this.elimina_utilizador(lista);break;
                                   case 2: this.elimina_utilizador(lista);break;
                                   case 3: this.elimina_utilizador(lista);break;
                                   case 4: break;
                                   default: print_string("A opcao nao e valida, tente outra vez");
                                }
                                break;
                        
                        case 3: print_string("\n1.Alterar Administrador\n2.Alterar cliente Regular\n3.Alterar cliente Premium\n4.Exit");
                                while(!op.hasNextInt()){
                                    op.next();
                                }
                                opcao3=op.nextInt();op.nextLine();//indica o tipo de utilizador que pretendemos alterar

                                switch(opcao3){
                                   case 1: this.altera_utilizador(lista);break;
                                   case 2: this.altera_utilizador(lista);break;
                                   case 3: this.altera_utilizador(lista);break;
                                   case 4: break;
                                   default: print_string("A opcao nao e valida, tente outra vez");
                                }
                                break;
                        
                        case 4: consulta_utilizador(lista);break;
                        
                        case 5: Listar_utilizadores(lista);break;
                          
                        case 6:break;
                    }
                    break;
                    

            case 2: print_string("\n1.Criar Autocarro\n2.Eliminar Autocarro\n3.Consultar Autocarro\n4.Listar Autocarros\n5.Alterar autocarro\n6.Exit");
                    while(!op.hasNextInt()){
                                    op.next();
                                }
                    opcao2=op.nextInt();op.nextLine();//indica o tipo de operacao que pretendemos realizar sobre um autocarro
                    switch(opcao2){
                       case 1: this.cria_autocarro(bus);break;
                       case 2: this.elimina_autocarro(bus);break;
                       case 3: this.consulta_autocarro(bus);break;
                       case 4: this.Listar_autocarros(bus);break;
                       case 5: this.altera_autocarro(bus);break;
            
                    
                    }
                    break;
            
            case 3: print_string("\n1.Criar Viagem\n2.Eliminar Viagem\n3.Alterar Viagem\n4.Consultar Viagem\n5.Listar Viagens\n6.Exit");
                    while(!op.hasNextInt()){
                                    op.next();
                                }
                    opcao2=op.nextInt();op.nextLine();//indica o tipo de operacao a realizar sobre uma viagem
                    switch(opcao2){
                       case 1: this.cria_viagem(viagem,bus);break;
                       case 2: this.elimina_viagem(viagem,lista);break;
                       case 3: this.altera_viagem(viagem);break;
                       case 4: this.consulta_viagem(viagem);break;
                       case 5: this.Listar_viagens(viagem);break;
                       case 6: break;
                    
                    }
                    break;
            
            case 4: print_string("1.Viagem mais vendida num determinado mês\n2.Cliente que mais viagens comprou num determinado mês\n3.Listar todas as viagens que não tiveram reservas num determinado mês\n"+
                   "4.Listar as reservas de uma viagem\n5.Listar as reservas canceladas de uma viagem\n6.Listar as reservas/clientes em espera\n7.Identificar a viagem com melhor pontuação num determinado mês\n8.Volume de vendas em cada mes e o dia do ano em que houve mais vendas\n9.Exit");
                     while(!op.hasNextInt()){
                                    op.next();
                                }
                     opcao4=op.nextInt();op.nextLine();
                     switch(opcao4){
                         case 1:mais_vendida(viagem);break;
                         case 2:traveller(lista);break;
                         case 3:viagem_sem_res(viagem);break;
                         case 4:printReserva(listar_reservas(lista));break;
                         case 5:printReserva(reservas_canceladas(lista));break;
                         case 6:printReserva(reserva_espera(lista));break;
                         case 7:viagem_pontuacao(viagem);break;
                         case 8:estatisticas_viagem(viagem,lista);break;
                         case 9:break;
                       
                         
                     }
                     break;
        
        
        
        
        
        }
        
        
        }while(opcao1!=6 && opcao1!=5);
        if(opcao1==5)
            return 1;
        return 0;
                 
  }
    /**
     * funcao que indica o o cliente que mais viagens comprou num determinado mes
     * @param l_users lista de users
     */
    
     private void traveller(ArrayList <Utilizador> l_users){
        print_string("Introduza o mes em que quer ver qual o cliente com mais viagens compradas: ");
        Scanner sc=scanner();
        while(!sc.hasNextInt()){
            sc.next();
        }
        String stats="O cliente(s) com mais viagens compradas e/sao ";//string que vai ser escrita no ficheiro de estatisticas
        int mes = sc.nextInt();sc.nextLine();
        int max=0, temp;
        ArrayList <Cliente> clientes=new ArrayList();
        for(Utilizador utilizador:l_users){
            if("Cliente".equals(utilizador.get_class())){
                Cliente client=(Cliente) utilizador;
                temp=0;
                for(int i=0;i<client.reservas.size();i++){
                    if(client.reservas.get(i).data.getMes()==mes)
                       temp++; 
                }
                System.out.println(client.username+" "+temp);
                if(temp==max)
                    clientes.add(client);
                else if(temp>max){
                    clientes.clear();
                    clientes.add(client);
                    max=temp;
                }
            }
        }
        if(clientes.size()==0)
            stats="";
        stats=printCliente(clientes,stats);
        stats2file(stats);
    }
     /**
     * funcao que retorna um ArrayList com todas as reservas, percorremos cada um dos utilizadores e adicionamos as asuas reservas ao ArrayList que vamos retornar
     * @param users lista com todos os utilizadores
     * @return ArrayList com todas as reservas
     */
     public ArrayList <Reserva> user2reserva(ArrayList <Utilizador> users){
        ArrayList <Reserva> reservas=new ArrayList();
        for(Utilizador user: users){
            if("Cliente".equals(user.getClass())){
                Cliente client=(Cliente) user;
                reservas.addAll(client.reservas);
            }
        }
        return reservas;
    }
    /**
     * funcao onde verificamos se um nome é valido
     * verificamos se o nome do utilizador é valido,ou seja, o primeiro e ultimo caracteres nao podem ser espaços, nao pode haver dois espaços seguidos, nem numeros no nome
     * @param nome nome a verificae
     * @return 1 no caso do nome se valido, 0 no caso do nome ser invalido
     */
    private int verifica_nome(String nome){
        int i;
        for(i=0; i<nome.length()-1; i++){
            char c=nome.charAt(i);
            if(Character.isLetter(c)==false)
                return 0;
            if(Character.isWhitespace(c) && Character.isWhitespace(nome.charAt(i+1))) // na última iteração i+1 não existe!!
                return 0;
        }
        if(Character.isWhitespace(nome.charAt(i)))
            return 0;
        if(Character.isWhitespace(nome.charAt(0)))
            return 0;
        return 1;
    }
        
    /**
     * funcao que verifica um username, verifica se o username nao tem numeros e se ainda nao foi registado por outro utilizador
     * @param nome username que queremos verificar
     * @param users lista com os utilizadores
     * @return 1 se o username for valido, 0 se for invalido
     */
    private int verifica_user(String nome, ArrayList <Utilizador> users){
        for(int i=0; i<nome.length(); i++){
            char c=nome.charAt(i);
            if((int) c <47 || ((int) c>57 && (int) c<65 )||(int) c>90 && (int) c<97 || (int) c>122  ){ 
                print_string("O username é inválido. Só pode conter letras e números");
                return 0;
            }
        }
        for(int j=0;j<users.size();j++){
            if(users.get(j).username.equals(nome)){
                print_string("O username já foi registado");
                return 0;
            }
        }
        return 1;
    }

/**
 * funcao que indica a(s) viagem(s) com melhor pontuacao num determinado mes
 * percorremos a lista de viagens, para cada uma descobrimos a media, e comparamos com o valor maximo, no caso ser maior atualiza esse valor, no caso de ser igual adiciona a viagem a um ArrayList com as viagens com pontuacao igual ao valor maximo
 * no final é imprimido o array com as viagens com pintuacao maxima, e essas viagens sao escritas no ficheiro de estatisticas
 * @param viagens lista de viagens
 */
 private void viagem_pontuacao(ArrayList <Viagem> viagens){
        Tempo d;
        float media=0, temp;
        print_string("Indique o mes:");
        Scanner sc=scanner();
        while(!sc.hasNextInt()){
            sc.next();
        }
        
        int mes=sc.nextInt();sc.nextLine();
        String stats="A(s) viagem com melhor pontuacao no mes"+mes+"tem o seguinte codigo: ";//string que vai ser escrita no ficheiro
        ArrayList <Viagem> melhor=new ArrayList();
        for(Viagem viagem:viagens){
            d=viagem.getData();
            if(mes==d.getMes()){
                temp=viagem.media();
                if(temp==media){
                    stats+=viagem.getCodigo();
                    melhor.add(viagem);
                }
                else if(temp>media){
                    stats="A(s) viagem com melhor pontuacao no mes"+mes+"tem o seguinte codigo: ";//inicializa a string
                    media=temp;
                    melhor.clear();
                    melhor.add(viagem);
                    stats+=viagem.getCodigo();
                }
            } 
        }
        if(melhor.size()==0)
            stats="";//inicializamos a string
        else if(melhor.size()==1){
            print_string("Esta foi a melhor viagem do mes com "+media+" pontos");
        }
        else
            print_string("Estas foram as melhores viagens do mes com "+media+" pontos");
        Listar_viagens(melhor);
        stats2file(stats);
        
        
    }
    /**
     * funcao que lista todas as viagens que não tiveram reservas num determinado mês
     * sao percorridas as viagens todas e no caso do primeiro lugar ser -1, significa que a viagem nao tem reservas sendo esta adicionado ao ArrayList que vai ser retornado
     * @param viagens lista de viagens
     */
    private void viagem_sem_res(ArrayList <Viagem> viagens){
            ArrayList <Viagem> vazias=new ArrayList();
            print_string("Indique o mes:");
            Scanner sc=scanner();
            while(!sc.hasNextInt()){
                sc.next();
            }
            int mes=sc.nextInt();sc.nextLine();
            for(Viagem viagem: viagens){
                if(viagem.getData().getMes()==mes){
                        int[] lugares=viagem.getLugar();
                            if(lugares[0]==-1)
                                vazias.add(viagem);
                }

            }        

            Listar_viagens(vazias);

        }
    /**
     * funcao que lista todos os utilizadores do ArrayList
     * @param lista lista com os utilizadores
     */
    private void Listar_utilizadores(ArrayList <Utilizador> lista){
            for(Utilizador user:lista){
                System.out.println(user);
            }

        } 
    /**
     *  funcao que lista todos os autocarros do ArrayList
     * @param lista lista de autocarros
     */
    private void Listar_autocarros(ArrayList <Autocarro> lista){//imprime todos os autocarros
        for(Autocarro bus:lista){
            System.out.println(bus);
        }
        
    }
    /**
     * funcao que lista todas as viagens do ArrayList
     * @param viagens 
     */
    private void Listar_viagens(ArrayList <Viagem> viagens){//imprime todas as viagens
        for(Viagem viagem:viagens){
            System.out.println(viagem);
        }
    }




    /**
     * funcao que imprime uma ArrayList de clientes e escreve o seu nome numa string
     * @param clientes lista de clientes
     * @param stats string onde vai ser escrito os nomes dos clientes
     * @return retorna uma string onde foram adicionados os nomes dos clientes do ArrayList
     */
    private String printCliente(ArrayList <Cliente> clientes,String stats){
        for(Cliente cliente:clientes){
            print_string(cliente+"\n");
            stats+=cliente.nome+" ";
            }
        print_string("");
        return stats;
    }
    /**
     * funcao que imprime todas as reservas do ArrayList
     * @param reservas lista com as reservas
     */
    private void printReserva(ArrayList <Reserva> reservas){
        for(Reserva reserva:reservas)
            print_string("\n\n start:\n"+reserva+"\n");
        print_string("");
    }

    @Override
    public String get_class() {
        return "Administrador";
    }




    }
