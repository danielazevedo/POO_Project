/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projeto.Projeto.*;


/**
 *
 * @author daniel
 */
public class FicheiroDeObjeto implements Serializable  {

    private ObjectInputStream iS;
    private ObjectOutputStream oS;
    
    
public boolean abreLeitura(String nomeDoFicheiro) {
    try{
    iS = new ObjectInputStream(new FileInputStream(nomeDoFicheiro));
    return true;
    } catch (IOException e){
    return false;
    }
}
    


public void abreEscrita(String nomeDoFicheiro) throws IOException{
    
    oS = new ObjectOutputStream(new FileOutputStream(nomeDoFicheiro,false));
    }
    
public Object leObjecto() throws IOException, ClassNotFoundException{
    return iS.readObject();
}


public void escreveObjecto(Object o) throws IOException{
    oS.writeObject(o);
}

public void fechaLeitura() throws IOException{
    iS.close();
}

public void fechaEscrita() throws IOException{
    oS.close();
}

    /**
     * Funcao que permite escrever a lista de utilizadores num ficheiro
     * @param lista lista de utilizadores
     */
    public static void list2file_utilizador(ArrayList <Utilizador> lista){
        
        FicheiroDeObjeto file = new FicheiroDeObjeto();
        try {
            file.abreEscrita("User.txt");
        } catch (IOException ex) {
        }
        for(Utilizador no:lista){
            try {
                file.escreveObjecto(no);
            } catch (IOException ex) {
                print_string("nao passa");
            }
            
    }
        try {
            file.fechaEscrita();
        } catch (IOException ex) {
            print_string("nao passa1");
        }
        
        
    }
    
    /**
     *Funcao que permite escrever a lista de autocarros num ficheiro
     * @param lista lista de autocarros
     */
    public static void list2file_autocarro(ArrayList <Autocarro> lista){
        
        FicheiroDeObjeto file = new FicheiroDeObjeto();
        try {
            file.abreEscrita("Autocarros.txt");
        } catch (IOException ex) {
        }
        for(Autocarro no:lista){
            try {
                file.escreveObjecto(no);
            } catch (IOException ex) {
                print_string("bus_passaBem");
            }
            
    }
        try {
            file.fechaEscrita();
        } catch (IOException ex) {
        }
        
    }
    
    /**
     *Funcao que permite escrever a lista de viagens num ficheiro
     * @param lista lista com as viagens
     */
    public static void list2file_viagem(ArrayList <Viagem> lista){
        
        FicheiroDeObjeto file = new FicheiroDeObjeto();
        try {
            file.abreEscrita("Viagens.txt");
        } catch (IOException ex) {
            print_string("nao abre bem");
        }
        for(Viagem no:lista){
            try {
                file.escreveObjecto(no);
            } catch (IOException ex) {
                print_string("nao passa bem");
            }
            
    }
        try {
            file.fechaEscrita();
        } catch (IOException ex) {
        }
        
    }
    
    /**
     * Funcao que carrega o ficheiro de utilizadores para a lista de utilizadores, adiciona cada objeto à lista dada
     * @param user lista de utilizadores
     */
    public static void file2list_utilizador(ArrayList <Utilizador> user){
        FicheiroDeObjeto fuser= new FicheiroDeObjeto();
        fuser.abreLeitura("User.txt");
        Utilizador aux=null;
        while(true){
            try {
                    aux=(Utilizador)fuser.leObjecto();
                    user.add((Utilizador) aux);
                    
                } catch (EOFException ex) {
                    break;    
                
                } catch (IOException | ClassNotFoundException ex) {
                   break;
                
                

        }
        
        }

        try {
            fuser.fechaLeitura();
        } catch (IOException ex) {
            Logger.getLogger(Projeto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        

        
        
    }
    
    /**
     *Funcao que carrega o ficheiro de autocarros para a lista de autocarros,adiciona cada objeto à lista dada
     * @param lista lista de autocarros
     */
    public static void file2list_autocarro(ArrayList <Autocarro> lista){
        FicheiroDeObjeto fbus= new FicheiroDeObjeto();
        fbus.abreLeitura("Autocarros.txt");
        Autocarro aux=null;
        while(true){
            try {
                    aux=(Autocarro)fbus.leObjecto();
                    lista.add((Autocarro) aux);
                    
                } catch (EOFException ex) {
                    print_string("eof exception");
                    break;    
                
                } catch (IOException | ClassNotFoundException ex) {
                    print_string("io exception");
                   break;
                
                

        }
        
        }

        try {
            fbus.fechaLeitura();
        } catch (IOException ex) {
            Logger.getLogger(Projeto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        

        
        
    }
    
    /**
     *Funcao que carrega o ficheiro de viagens para a lista de viagens,adiciona cada objeto à lista dada
     * @param lista lista de viagens
     */
    public static void file2list_viagem(ArrayList <Viagem> lista){
        FicheiroDeObjeto fviagem= new FicheiroDeObjeto();
        fviagem.abreLeitura("Viagens.txt");
        Viagem aux=null;
        while(true){
            try {
                    aux=(Viagem)fviagem.leObjecto();
                    lista.add((Viagem) aux);
                    
                } catch (EOFException ex) {
                    print_string("eof exception");
                    break;    
                
                } catch (IOException | ClassNotFoundException ex) {
                    print_string("io exception");
                   break;
                
                

        }
        
        }

        try {
            fviagem.fechaLeitura();
        } catch (IOException ex) {
            Logger.getLogger(Projeto.class.getName()).log(Level.SEVERE, null, ex);
        } 
        

        
        
    }
    
    /**
     * funcao que verifica se um determinado ficheiro existe, ou se precisa de ser criado
     * @param nome nome do ficheiro cuja existencia queremos verificar
     * @throws IOException
     */
    public static void verifica_ficheiros(String nome) throws IOException{
        
        FicheiroDeObjeto file= new FicheiroDeObjeto();
        if(file.abreLeitura(nome)){
            print_string("Ficheiro "+nome+" ja existe");
            file.fechaLeitura();
        }
        else{
            file.abreEscrita(nome);
            print_string("O Ficheiro "+nome+" foi criado");
            file.fechaEscrita();
        }
        
    }


}
