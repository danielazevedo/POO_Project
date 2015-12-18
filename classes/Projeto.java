/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;


import java.util.*;
import java.io.Console;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.lang.Object.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.NullPointerException;
import static projeto.FicheiroDeObjeto.*;
import java.lang.Character;


/**
 *
 * @author PC
 */
public class Projeto implements java.io.Serializable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        ArrayList <Utilizador> l_user=new ArrayList();
        ArrayList <Autocarro> autocarro=new ArrayList();
        ArrayList <Viagem> viagens = new ArrayList();
        Utilizador user;
         
        //Utilizador admin=new Administrador("daniel","d",111,"dsc","1111","d",2222);
        //l_user.add(admin);
        //list2file_utilizador(l_user);
         
       
      
        try {
            
            verifica_ficheiros("User.txt");
        } catch (IOException ex) {
        }
        try {
            verifica_ficheiros("Viagens.txt");
        } catch (IOException ex) {
        }
        try {
            verifica_ficheiros("Autocarros.txt");
        } catch (IOException ex) {
        }
       
        file2list_utilizador(l_user);
        file2list_autocarro(autocarro);
        file2list_viagem(viagens);


        
        
        Utilizador l;
        int aux;
        
        do{ 
            System.out.println("\n-------Login--------\n");
            Tempo tempo=pede_data();
            
            l=login(l_user);
            if("Cliente".equals(l.get_class())){
                Cliente cliente=(Cliente) l;
                cliente.identifica_espera(l_user,tempo);
            }
                
            aux=l.show_menu(l_user,autocarro,viagens,tempo);
            list2file_utilizador(l_user);
            
        }while(aux!=0);

    }
    
    /**
     * Funcao que imprime um objeto do tipo Comentario
     * @param comentario 
     */
    public static void print_comentario(Comentario comentario){
        System.out.println(comentario);
    }
    /**
     * Funcao que imprime um objeto do tipo reserva
     * @param reserva 
     */
    public static void print_reserva(Reserva reserva){
        System.out.println(reserva);
    }
    /**
     * Funcao que imprime um objeto do tipo viagem
     * @param viagem 
     */
    public static void print_viagem(Viagem viagem){
        System.out.println(viagem);
    }
    /**
     * Funcao que imprime uma String
     * @param string 
     */
    public static void print_string(String string){
        System.out.println(string);
    }
   /**
    * Funcao que faz um Scanner
    * @return objeto do tipo Scanner
    */
    public static Scanner scanner(){
        Scanner sc=new Scanner(System.in);
        return sc;
    }
        
/**
 * Funcao que pede o nome e a password e verifica se estes existem na lista de utilizadores
 * @param l_user lista que contem os utilizadores
 * @return retorna o utilizador caso existe, se nao existir retorna
 */            
    public static Utilizador login(ArrayList <Utilizador> l_user){
        Scanner sc= scanner();
        String name, keyword;
        System.out.println("Insira o nome do Utilizador:");
        name=sc.nextLine();
        System.out.println("Introduza a sua password:");
        keyword=sc.nextLine();
        Utilizador user;
        if(name.equals("0")==false){
            for(int i=0;i<l_user.size();i++){
                user=l_user.get(i);
                if(user.username.equals(name)){
                    if(user.password.equals(keyword)==false){
                        print_string("O Utilizador ou password inseridos são inválidos");
                        user=login(l_user);//recursividade para encontrar o utilizador

                    }
                    return user;//quando o utilizador for válido, é retornado terminando as outras funções
                }
            }
            System.out.println("Username inserido nao existe");
            user=login(l_user);//recursividade para encontrar o utilizador
            return user;

        }
       return null;
        
    }
/**
 * Funcao que pede a data e as horas e verifica se sao datas e horas validas
 * @return 
 */        
    public static Tempo pede_data(){
        Tempo data=null;
        int v;
        int dia,mes,ano,hora,minuto;
        do{
            System.out.println("Indique as horas ");
            Scanner sc=new Scanner(System.in);
            while(!sc.hasNextInt()){
                sc.next();
            }
            hora=sc.nextInt();sc.nextLine();
            System.out.println("Indique os minutos");
            while(!sc.hasNextInt()){
                sc.next();
            }
            minuto=sc.nextInt();sc.nextLine();
            System.out.println("Indique o dia");
            while(!sc.hasNextInt()){
                sc.next();
            }
            dia=sc.nextInt();sc.nextLine();
            System.out.println("Indique o mes");
            while(!sc.hasNextInt()){
                sc.next();
            }
            mes=sc.nextInt();sc.nextLine();
            System.out.println("Indique o ano");
            while(!sc.hasNextInt()){
                sc.next();
            }
            ano=sc.nextInt();sc.nextLine();
            data=new Tempo(ano,dia,hora,mes,minuto);
            v=verifica_data(data);
            if(v==0)
                System.out.println("Data ou hora invalida");
        }while(v==0);
        
        return data;
    }
        /**
         * Funcao que verifica se uma data e uma hora sao validas
         * horas tem de estar entre 0 e 24, minutos tem de estar entre 0 e 60, consoante o mes do ano o dia vai estar entre 28 e 31, sendo tambem verificado se um ano é bissexto, ou seja, se é multiplo de 4,nao multiplo de 100 e se for de multiplo de 100 tem ser multiplo de 400
         * @param data
         * @return 
         */
public static int verifica_data(Tempo data){
    
    
    if(data.getAno()<0 || data.getMes()<0 || data.getDia()<0 || data.getMes()>12 || data.getHora()<0 || data.getMinuto()<0 || data.getHora()>24 || data.getMinuto()>60)
        return 0;
    
    
    
    if(data.bissexto()){
        if(data.getMes()==2){
            if(data.getDia()>29)
                return 0;
        }
    }
    
    
    switch(data.getMes()){
        case 1: if(data.getDia()>31)
                    return 0;
       
        case 2: if(data.getDia()>28)
                    return 0;
        case 3:if(data.getDia()>31)
                    return 0;
        case 4:if(data.getDia()>30)
                    return 0;
        case 5:if(data.getDia()>31)
                    return 0;
        case 6:if(data.getDia()>30)
                    return 0;
        case 7:if(data.getDia()>31)
                    return 0;
        case 8:if(data.getDia()>31)
                    return 0;
        case 9:if(data.getDia()>30)
                    return 0;
        case 10:if(data.getDia()>31)
                    return 0;
        case 11:if(data.getDia()>30)
                    return 0;
        case 12:if(data.getDia()>31)
                    return 0;
    
    }
    return 1;
    
}
/**
 * Funcao que indica se um autocarro pode ou não ser associado a uma viagem
 * Para ser associado a uma viagem a lotacao do autocarro tem de ser maior que aquela requerida pela viagem e o autocarro nao pode estar a ser usado na altura da data da viagem
 * Para verificarmos se o autocarro esta a ser usado, percorremos a lista de viagens
 * Para cada viagem vemos os autocarros associados e comparamos a data da viagem do autocarro com a data da viagem a que queremos associar o autocarro de modo verificar que estes nao estao a ser usados na altura da viagem pedida.
 * @param bus auocarro que queremos verificar
 * @param viagens lista das viagens
 * @param l lotacao da viagem
 * @param data data da reserva
 * @return 
 */
public static int verifica_autocarro(Autocarro bus,ArrayList <Viagem> viagens, int l,Tempo data){
    int i;
    ArrayList <Autocarro> autocarros=new ArrayList();
    for(i=0;i<viagens.size();i++){
        autocarros=viagens.get(i).getAutocarros();
        for(int k=0;k<autocarros.size();k++){
            if(l>autocarros.get(k).getLotacao() || (viagens.get(i).getData().maiorData(data)==2  )){
                return 0;

            }

        }

    }
    return 1;
}
/**
 * funcao que verifica se um determinado numero tem 9 digitos ou nao
 * @param numero a verificar
 * @return 1 se tiver 9 digitos, 0 se nao tiver 9 digitos
 */
    public static int verifica_numero(long numero){
        if(Math.pow(10,8) < numero && numero<Math.pow(10,9))
            return 1;
        return 0;
    }
   /**
    * verificamos se uma string contem ou nao numeros
    * @param n
    * @return -1 se a string nao tiver so numeros, float correspondente a string no caso de ter so numeros
    */
    public static float verifica_preco(String n){
        for(char c: n.toCharArray()){
            if(!Character.isDigit(c))
                return -1;
        }
        
        return Float.parseFloat(n);
    }
    
    /**
    * verificamos se uma string contem ou nao numeros
    * @param n
    * @return -1 se a string nao tiver so numeros, int correspondente a string no caso de ter so numeros
    */
    public static int verifica_int(String n){
        for(char c: n.toCharArray()){
            if(!Character.isDigit(c))
                return -1;
        }
        
        return Integer.parseInt(n);
    }
        
    /**
     * funcao que verifica se uma matricula é valida ou nao
     * uma matricula é valida no caso de ter 2 '-' conter 2 grupos de 2 numeros, e 1 grupo de 2 letras entre cada '-'
     * @param matricula
     * @return 
     */
    public static String verifica_matricula(String matricula){
        boolean bool=false;
        if( matricula.length()!=8)
            return null;
        if(matricula.charAt(2)!='-' || matricula.charAt(5)!='-')
            return null;
        else{
            for(int i=0; i<=6; i=i+3){
                if(Character.isLetter(matricula.charAt(i))){
                    bool=true;
                    if(!Character.isLetter(matricula.charAt(i+1)))
                        return null;
                    for(int j=i+3; j<=6; j=j+3){
                        if(!Character.isDigit(matricula.charAt(j)) || !Character.isDigit(matricula.charAt(j+1)))
                            return null;
                    }
                }
            }
        }
        if(bool==false)
            return null;
        return matricula;
        
    } /**
     * Funcao que verifica se uma string contem dois espaços seguidos ou nao
     * @param morada string que queremos verificar
     * @return 1 no caso de a string ser valida, 0 no caso de nao ser valida
     */
    public static int verifica_morada(String morada){
        if(morada.indexOf("  ")!=-1)
            return 0;
        if(morada.charAt(morada.length()-1)==' ' || morada.charAt(0)==' ')
            return 0;
        
        return 1;
        
    }
    /**
     * funcao que verifica se uma string(mail) e valida
     * o mail e valido se bao conter espaços e conter um '@' que nao estaje nem na primeira nem na ultima posições
     * @param mail
     * @return 
     */
    public static int verifica_mail(String mail){
        if(mail.charAt(0)=='@')
            return 0;
        for(int i=0;i<mail.length();i++){
            char c=mail.charAt(i);
            if(Character.isWhitespace(c))
                return 0;
            if(mail.charAt(i)=='@' && (i==mail.length()-1 || i==mail.indexOf(0)))
                return 0;
            else if(mail.charAt(i)=='@'){
                String temp=mail.substring(i);
                if(temp.indexOf('@')==-1)
                    return 1;
                
                
            }
        }
        if(mail.indexOf('@')==-1)
            return 0;
            
        
        return 1;
    }
    
}
    
    
    