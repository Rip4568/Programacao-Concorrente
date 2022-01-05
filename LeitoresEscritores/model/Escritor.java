package model;

import controller.BancoDeDados;
import controller.ControladorTelaPrincipal;


public class Escritor extends Thread{
    private int id;
    BancoDeDados bancoDeDados;
    ControladorTelaPrincipal controladorTelaPrincipal;
    public Escritor(int id, BancoDeDados bancoDeDados, ControladorTelaPrincipal controladorTelaPrincipal){
        this.id = id;
        this.bancoDeDados = bancoDeDados;
        this.controladorTelaPrincipal = controladorTelaPrincipal;
    }

    public void run(){
        while(true){
            obterDados(id);//nao sei se tem alguma utilidade este metodo.... depois eu vejo
            //if(bancoDeDados.temLeitor() == false)
            
            bancoDeDados.acessarBancoDeDados("Escritor", id);
            //bancoDeDados.temEscritorEscrevendo();
            //db.acquire();
            escrever(id);
            bancoDeDados.liberarBancoDeDados("Escritor", id);
            //bancoDeDados.naoTemEscritorEscrevendo();
            //db.release();
            esperar(id);
        }
    }

    public void obterDados(int id){
        System.out.println("Escritor id: " + id + " quer obter acesso ao banco de dados");
    }

    public void escrever(int id){
        int tempo = controladorTelaPrincipal.getVelocidadeSliderEscritor() * 1000;
        System.out.println("VelocidadeSliderEscritor : " + controladorTelaPrincipal.getVelocidadeSliderEscritor());
        try {
            controladorTelaPrincipal.irAte_a_Camera(id);
            System.out.println("Escritor id:" + id + " esta escrevendo.... vou demorar " + (tempo/1000) + " seg");
            sleep(tempo);
            controladorTelaPrincipal.alterarNomeDoFilme(nomeFilmeAleatorio());
            controladorTelaPrincipal.seAfastarDaCamera(id);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String nomeFilmeAleatorio(){
        int numeroAleatorio = (int)Math.random() * 5;//numero aleatorio de 0 a 4
        
        switch(numeroAleatorio){
            case 0:{
                return "O caçador de Dragao";
            }
            case 1:{
                return "O mestre do magos";
            }
            case 2:{
                return "Sem ideia para nome de filme";
            }
            case 3:{
                return "Sempre ao seu lado";//lindo esse filme ;-;
            }
            case 4:{
                return "Uma comedia nada Romantica";
            }
        }
        return "oxi";
    }

    public void esperar(int id){
        int tempo = (controladorTelaPrincipal.getVelocidadeSliderEscritor() * 1000) * 3;
        try {
            System.out.println("Escritor ID:" + id + " terminei de escrever vou esperar" + (tempo/1000) + " segundos");
            sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}