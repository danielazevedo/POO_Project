/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.EOFException;
import static projeto.Projeto.*;


/**
 *
 * @author PC
 */
public abstract class Utilizador implements java.io.Serializable {
    
    //attributes
    protected String email;
    protected String morada;
    protected long nif;
    protected String nome;
    protected String password;
    protected String username;
    protected long telefone;
    
    
    //methods

    public Utilizador(String email, String morada, long nif, String nome, String password, String username, long telefone) {
        this.email = email;
        this.morada = morada;
        this.nif = nif;
        this.nome = nome;
        this.password = password;
        this.username = username;
        this.telefone = telefone;
    }

    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public void setNif(long nif) {
        this.nif = nif;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }
    /**
     * 
     * funcao que indica se uma determinada viagem existe ou não na lista de viagens, atraves do codigo procuramos na lista de viagens para ver se a viagem existe ou não
     * @param lista lista de autocarros
     * @param codigo codigo da viagem a procurar
     * @return retorna a viagem caso ela exista, se não retorna null
     */
     public Viagem procura_viagem(ArrayList <Viagem> lista,int codigo){
       
        Iterator <Viagem> it = lista.iterator();
        Viagem viagem;
        while(it.hasNext()){
            viagem=it.next();
            if(viagem.getCodigo()==codigo)
                return viagem;
        }
        return null;
        
    }
    
     public abstract String get_class();
     
     /**
     * funcao que imprime os atributos da viagem,inicialmente é pedido o codigo da viagem, sendo procurado a matricula na lista de autocarros para encontrar a viagem respetiva
     * @param viagens lista que contem as viagens
     */ 
    public void consulta_viagem(ArrayList <Viagem> viagens){
        print_string("Indique o codigo da viagem a consultar");
        Scanner sc = new Scanner(System.in);
        int codigo=sc.nextInt();
        Viagem viagem=procura_viagem(viagens,codigo);
        if(viagem!=null)
            System.out.println(viagem);
        
        
    }
    
    

     public abstract int show_menu(ArrayList <Utilizador> lista,ArrayList <Autocarro> bus,ArrayList <Viagem> viagem,Tempo tempo);
    
    
    

    @Override
    public String toString() {
        return "\nemail= " + email+ "\nmorada=" + morada + "\nnif=" + nif + "\nnome=" + nome + "\nusername=" + username + "\ntelefone=" + telefone;
    }

    
}
