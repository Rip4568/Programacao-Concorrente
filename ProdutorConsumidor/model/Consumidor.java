package model;

import controller.ControladorTelaPrincipal;

public class Consumidor extends Thread {
    private Buffer buffer;
    private ControladorTelaPrincipal controladorTelaPrincipal;
    public Consumidor(Buffer buffer, ControladorTelaPrincipal controladorTelaPrincipal){
        this.controladorTelaPrincipal = controladorTelaPrincipal;
        this.buffer = buffer;
    }

    
    public void run(){
        while(true){
            try {
                /*por padrao o consumidor tera um tempo de espera em 10 segundos*/
                sleep((int)(10000 / controladorTelaPrincipal.getVelocidadeConsumidor()));
                buffer.consumir();//apos os 10 segundos ele ira consumir
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
