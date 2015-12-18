/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projeto;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class Tempo implements Serializable{
    
    //attributes
    private int ano;
    private int dia;
    private int hora;
    private int mes;
    private int minuto;
        
    //methods

    public Tempo(int ano, int dia, int hora, int mes, int minuto) {
        this.ano = ano;
        this.dia = dia;
        this.hora = hora;
        this.mes = mes;
        this.minuto = minuto;
    }
    
    



    public int getAno() {
        return ano;
    }

    public int getDia() {
        return dia;
    }

    public int getMes() {
        return mes;
    }

    public int getHora() {
        return hora;
    }

    public int getMinuto() {
        return minuto;
    }

    
    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }
    
    public boolean bissexto(){
        if(ano%4==0 && ano%100!=0 || ano%400==0){
            return true;
        }
        return  false;
    }/**
     * Funcao que compara duas datas para ver qual a maior
     * A viagem mais recente é aquela que possui ou maior Ano, ou maior mes(no caso dos anos serem iguais), ou maior dia(no caso do ano e do mes serem iguais)
     * @param dviagem uma das viagens a comparar
     * @return 1 se a data dada como argumento for menor, 0 caso contrario
     */
    public int maiorData(Tempo dviagem){
        if(this.getAno()>dviagem.getAno())
            return 1;
        else if(this.getAno()==dviagem.getAno()){
            if(this.getMes()>dviagem.getAno())
                return 1;
            else if(this.getMes()==dviagem.getMes()){
                if(this.getDia()>dviagem.getDia())
                    return 1;
                else if(this.getDia()==dviagem.getDia())
                    return 2;
                else
                    return 0;
            }
            else
                return 0;
            
        }
        
        return 0;
    }
    /**
     * Função que retorna o numero de dias de diferença entre duas datas, para realizar a funcao aproveitamos o facto de até Agosto(exclusive) os meses ímpares terem 31 dias e os restantes 30 dias, excluindo Fevereiro. A partir de Agosto, os meses pares tem 31 e os restantes 30
     * Até que as datas coincidam, é incrementado 1 à variavel 'dif' a cada dia que passa, tendo em conta que o numero de dias de cada mes segue as condições acima referidas
     * @param dviagem
     * @return numero de dias de diferenca assumindo que a data dada como argumento é maior
     */
    public int dif_datas(Tempo dviagem){
        int dif=0;
        Tempo dhoje = new Tempo(ano, dia,hora, mes,minuto); //esta var é uma cópia da data do dia de hoje para que esta última não seja alterada
        while( dhoje.ano<dviagem.ano || dhoje.dia<dviagem.dia || dhoje.mes<dviagem.mes){
            boolean bool=dhoje.bissexto();
                 if(dhoje.ano==dviagem.ano && dhoje.dia==dviagem.dia && dhoje.mes==dviagem.mes){
                        return dif;}
                    //System.out.println(dhoje.dia+"/"+dhoje.mes+"/"+dhoje.ano);
                    if(dhoje.mes<8){
                        if(dhoje.mes%2==1){
                            if(dhoje.dia!=31)
                                dhoje.dia++;
                            else{
                                dhoje.dia=1;
                                dhoje.mes++;
                            }
                        }    
                        else if(dhoje.mes==2){
                            if(dhoje.dia<28)
                                dhoje.dia++;
                            else{
                                if(dhoje.dia==28 && bool==true)
                                    dhoje.dia++;
                                else{
                                    dhoje.dia=1;
                                    dhoje.mes++;
                                }
                            }
                        }
                        else{
                            if(dhoje.dia!=30)
                                dhoje.dia++;
                            else{
                                dhoje.dia=1;
                                dhoje.mes++;
                            }
                        }
                    }
                    else{
                        if(dhoje.mes%2==0){
                            if(dhoje.dia!=31)
                                dhoje.dia++;
                            else{
                                dhoje.dia=1;
                                if(dhoje.mes==12){
                                    dhoje.mes=1;
                                    dhoje.ano++;
                                }
                                else
                                    dhoje.mes++;
                            }
                        }    
                        else{
                            if(dhoje.dia!=30)
                                dhoje.dia++;
                            else{
                                dhoje.dia=1;
                                dhoje.mes++;
                            }
                        } 
                    }
                    dif++;
                }
            /*}
            dhoje.ano++;
        }*/
        return dif;
    }
    
    @Override
    public String toString() {
        return "dia:\n" + dia + "\nmes=" + mes + "\nano=" + ano;
    }
    
    
}
