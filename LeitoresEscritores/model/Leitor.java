package model;

import java.util.concurrent.Semaphore;

import controller.BancoDeDados;
import controller.ControladorTelaPrincipal;

public class Leitor extends Thread {
    private Semaphore mutex;
    int id;
    BancoDeDados bancoDeDados;
    ControladorTelaPrincipal controladorTelaPrincipal;
    public Leitor(int id,BancoDeDados bancoDeDados,ControladorTelaPrincipal controladorTelaPrincipal){
        this.mutex = new Semaphore(1);
        this.id = id;
        this.bancoDeDados = bancoDeDados;
        this.controladorTelaPrincipal = controladorTelaPrincipal;
    }
    /**
     * eh essencialmente importante que o db fique no controlador
     * para que ambos (leitor e escritor) acessem a variavel
     * caso algum deles tenha acesasdo o outro não devera intervir
     * 
     * uma solução boa seria um db para cada um
     * tanto para escritor quanto apra leitor assim poderia ter um controle melhor
     * acredito que desta foma fique inviavel porem nao custa nada tenta rsrs
     * 
     * 
     */

    public void run(){
        while(true){
            try {
                mutex.acquire();
                /**
                 * bancoDeDados.IncrementarQuantidadeLeitores();
                 * aumenta a quantidade de leitores querendo acessar a regiao critica
                 */
                bancoDeDados.incrementarQuantidadeLeitores();
                if(bancoDeDados.getQuantidadeLeitores() == 1){
                    /**
                     * o primeiro que conseguir entrar ira travar o banco de dados acessando 
                     */
                    bancoDeDados.acessarBancoDeDados("Leitor",id);
                    //db.acquire();
                    //boolean TemLeitorLendo = True;
                    //bancoDeDados.temLeitorLendo();
                }
                
                mutex.release();
                
                
                /**
                 * neste metodo inicia a animacao de ir ate a cadeira
                 * sentar
                 * tempo assistindo (5000)
                 * levantar
                 * desocupar a cadeira
                 * "sair da sala"
                 */
                //if(bancoDeDados.temEscritor() == false)
                ler(id);
                //
                
                
                mutex.acquire();
                bancoDeDados.decrementarQuantidadeLeitores();
                if(bancoDeDados.getQuantidadeLeitores() == 0){

                    bancoDeDados.liberarBancoDeDados("Leitor",id);
                    //db.release();
                    //bancoDeDados.naoTemLeitor();
                }
                //esperar(x);//esperar antes de começar o ciclo novamente
                //OBS. o tempo de espera tem que ser maior que o tempo do escritor querendo acessar a rc
                
                esperar(id);//espera antes de começar o loop novamente
                mutex.release();//sai da RC
                
                

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void ler(int id){
        int tempo = controladorTelaPrincipal.getVelocidadeSliderLeitor() * 1000;
        System.out.println("VelocidadeSliderLeitor : " + controladorTelaPrincipal.getVelocidadeSliderLeitor());
        try {
            //System.out.println("Leitor id:" + id + " esta lendo, vai demorar " + (tempo/1000) + " segundos");
            controladorTelaPrincipal.ocuparCadeira_e_Assistir(id);
            sleep(tempo);
            controladorTelaPrincipal.descouparCadeira_e_Sair(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void esperar(int id){
        //System.out.println("Terminei de ler e vou esperar " + (tempo/1000) + " segundos...");
        int tempo = (controladorTelaPrincipal.getVelocidadeSliderLeitor() * 1000) * 2;
        try {
            sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}