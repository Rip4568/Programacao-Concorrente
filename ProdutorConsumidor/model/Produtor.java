package model;

import controller.ControladorTelaPrincipal;
import controller.FrutasProduzidas;

public class Produtor extends Thread {
    ControladorTelaPrincipal controladorTelaPrincipal;
    private int numeroAleatorio;
    private Buffer buffer;
    private Fruta fruta;
    FrutasProduzidas frutasProduzidas;

    public Produtor (Buffer buffer,ControladorTelaPrincipal controladorTelaPrincipal){
        this.controladorTelaPrincipal = controladorTelaPrincipal;
        this.buffer = buffer;
    }
    public void run(){
        while(true){
            try {
                numeroAleatorio = (int) (Math.random() * 3);//numero gerado sera [0,1,2] assim poderei colocar itens aleatorios na TableView
                switch(numeroAleatorio){
                    case 0:{
                        fruta = new Fruta("Beringela");
                        break;
                    }
                    case 1:{
                        fruta = new Fruta("Trigo");
                        break;
                    }
                    case 2:{
                        fruta = new Fruta("Banana");
                        break;
                    }
                    default:{
                        System.out.println("Entrei no default Produtor");
                        //SE POR ACASO vir parar aqui, porem acho que isso eh impossivel :v
                    }
                }
                /*por padrão o produtor ira produzir item começando em 10 segundos, a medida que o slider
                for alterando sera divido pelo valor, ou seja
                caso o usuario altere o valor do slider para 2, entao sera 10.0000 / 2 = 5000 ou 5 segundos abreviando */
                sleep((int)(10000 / controladorTelaPrincipal.getVelocidadeProdutor()));
                buffer.produzir(fruta);//jogando para o buffer
            } catch (InterruptedException e) {
                e.printStackTrace();
            }            
        }   
    }
}