package controller;

import java.util.concurrent.Semaphore;

import model.Garfo;

public class ControladorFilosofos {
    private int N,ESQ,DIR;
    final int PENSANDO = 0;
    final int FOME = 1;
    final int COMENDO = 2;
    int estado[];
    Semaphore arraySemaforos[];
    Garfo garfos[];
    Semaphore mutex;
    ControladorTelaPrincipal controladorTelaPrincipal;//passando o controlador da tela para obter alguns recursos
    /*******************************************************************
    * nome........:
    * funcao......:
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/
    /*******************************************************************
    * nome........:ControladorFilosofos
    * funcao......:Contrutor da classe
    * Parametros..:quantidadeFilosofos, e o controlador da tela do JavaFX
    * retorno.....:nao ha retorno
    ******************************************************************/
    public ControladorFilosofos(int quantidadeFilosofos,ControladorTelaPrincipal controladorTelaPrincipal){
        this.N = quantidadeFilosofos;
        arraySemaforos = new Semaphore[N];
        this.controladorTelaPrincipal = controladorTelaPrincipal;//instanciando o controlador da tela
        for(int i = 0; i < N; i++){
            arraySemaforos[i] = new Semaphore(0);//inicando todos os semaforos com 0
        }
        estado = new int[N];
        mutex = new Semaphore(1);// 1 pode entrar na RC,0 o não pode
        
    }//fim do metodo Contrutor ControladorFilosofos



    /*******************************************************************
    * nome........:pegarGarfos
    * funcao......:tenta adquirir (garfos) ou seja tenta entrar em estado de comer
    se os dois filosofos da esquerda e direita nao estiver comendo
    * Parametros..:int indici (iD) dos filosofos que chamaram
    * retorno.....:nao ha retorno
    ******************************************************************/
    public void pegarGarfos(int indici){
        try {
            mutex.acquire();//Tenta entrar na Regiao critica, se conseguir...
            estado[indici] = FOME;//altera o estado para FOME
            controladorTelaPrincipal.alterarImagensEsfomeando(indici);
            /** ^^^^^^^^^^^^^^^^^^^^
             * esse e o unico motivo de ter feito o controlador ser construido aqui
             * pois esse foi a unica forma de ter encontrado para alterar as imagens para o estado de FOME ou ESFOMENADO
             */
            System.out.println("Estou com fome i: " + indici + " vou tentar pegar os garfos");
            testarGarfos(indici);//tenta adquirir os dois garfos
            mutex.release();//Sai da RC
            arraySemaforos[indici].acquire();//bloqueia se nao pegar os dois garfos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /*******************************************************************
    * nome........:devolverGarfos
    * funcao......:devolve os garfos, ou seja ele entra em estado pensando
    e avisa para os dois vizinhos que ja pode comer
    * Parametros..:int indici(ID) para saber qual filosofo esta chamando
    * retorno.....:nao ha retorno
    ******************************************************************/
    public void devolverGarfos(int indici){
        ESQ = (indici + (N - 1)) % N;
        DIR = (indici + 1) % N;
        try {
            mutex.acquire();//tenta entrar na RC alterando o valor para 0
            estado[indici] = PENSANDO;//alterar o valor para PENSANDO(2)
            System.out.println("Devolvi os garfos e estou pensando i : " + indici);
            testarGarfos(ESQ);//olha se o vizinho da ESQUERDA pode comer agora
            testarGarfos(DIR);//olha se o vizinho da  DIREITA pode comer agora
            mutex.release();//sai da RC alterando o valor para 1
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*******************************************************************
    * nome........:testarGarfos
    * funcao......:verfica se a esquerda e a direita esta liberado para entrar em estado de comer
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/

    public void testarGarfos(int indici) {
        ESQ = (indici + (N - 1)) % N;
        DIR = (indici + 1) % N;
        //System.out.println("inidici do Filosofo : " + indici + " esquerda : " + ESQ + " Direita : " + DIR);
        if(estado[indici] == FOME && estado[ESQ] != COMENDO && estado[DIR] != COMENDO){
            estado[indici] = COMENDO;
            System.out.println("Estou comendo indici : " + indici);
            try {
                arraySemaforos[indici].release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//fim do metodo testarGarfos 
    
    public int getQuantidadeFilosofos(){
        return this.N;
    }//metodo nao utilizado, achei que seria util durante o desenvolvimento, pelo viso me enganei rsrs
}