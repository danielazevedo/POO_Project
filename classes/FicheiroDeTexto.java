/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static projeto.Projeto.*;

/**
 *
 * @author daniel
 */
public class FicheiroDeTexto {
    
    private BufferedReader fR;
    private BufferedWriter fW;

    
public void abreLeitura(String nomeDoFicheiro) throws IOException{
    
    fR = new BufferedReader(new
    FileReader(nomeDoFicheiro));
}


public void abreEscrita(String nomeDoFicheiro) throws IOException{
    fW = new BufferedWriter(new FileWriter(nomeDoFicheiro,true));

}

public String leLinha() throws IOException{
    return fR.readLine();
}


public void escreveLinha(String linha) throws IOException {
    fW.write(linha,0,linha.length());
    fW.newLine();

}

public void fechaLeitura() throws IOException {
    fR.close();
}
public void fechaEscrita() throws IOException {
    fW.close();
}

    /**
     *Funcao que escreve uma string no ficheiro estatisticas.txt
     * @param string que e escrita no ficheiro
     */
    public static void stats2file(String string){
    FicheiroDeTexto file= new FicheiroDeTexto();
        try {
            file.abreEscrita("estatisticas.txt");
        } catch (IOException ex) {
            Logger.getLogger(FicheiroDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            file.escreveLinha(string);
        } catch (IOException ex) {
            Logger.getLogger(FicheiroDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            file.fechaEscrita();
        } catch (IOException ex) {
            Logger.getLogger(FicheiroDeTexto.class.getName()).log(Level.SEVERE, null, ex);
        }
}



}