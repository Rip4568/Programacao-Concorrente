package model;

import controller.ControladorFilosofos;
import controller.ControladorTelaPrincipal;

public class Filosofo extends Thread {
    private int id;
    ControladorFilosofos controladorFilosofos;
    ControladorTelaPrincipal controladorTelaPrincipal;

    public Filosofo(int id,ControladorFilosofos controladorFilosofos,ControladorTelaPrincipal controladorTelaPrincipal){
        this.controladorTelaPrincipal = controladorTelaPrincipal;
        this.controladorFilosofos = controladorFilosofos;
        this.id = id;
    }//fim do construtor Filosofo

    public void run(){
        while(true){
            try {
                pensar();//animacao de pensar
                controladorFilosofos.pegarGarfos(id);//filosofo tenta pegar garfos, se conseguir executa a animacao abaixo
                comer();//Consegui pegar os garfos incicei a animacao de comer
                controladorFilosofos.devolverGarfos(id);//filosofo devolve os garfos
            } catch (Exception e) {
                e.printStackTrace();
            }
        }//fim do while true
    }//fim do metodo run

    public long getId(){
        return id;
    }//metodo nao utilizado

    /*******************************************************************
    * nome........:Comer
    * funcao......:iniciar a animacao de comer dos filosofos
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/

    public void comer(){
        int velocidadeComer;
        try {
            velocidadeComer = 10000 - (controladorTelaPrincipal.getSliderComer(id) * 1000);
            //velocidadeComer = 10000 / controladorTelaPrincipal.getSliderComer(id);//por parao todos os filosofos vao demorar 10 segundos para pensar, alterar o valor do slider ira diminuir o tempo
            System.out.println("Estou comendo id : " + id + " vou demorar " + velocidadeComer/1000 + " segundos");//divindo por 1000 pois os ultimos dois digitos da divisao eh os segundos
            controladorTelaPrincipal.alterarImagensComer(id);//chama o metodo de atlerar imagens do controlador
            sleep(velocidadeComer);//esperando
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//fim do metodo comer

    /*******************************************************************
    * nome........:pensar
    * funcao......:inicia animacao de pensar
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/

    public void pensar(){
        int velocidadePensar;
        try {
            velocidadePensar = 10000 - (controladorTelaPrincipal.getSliderPensar(id) * 1000);
            //velocidadePensar = 10000 / controladorTelaPrincipal.getSliderPensar(id);
            System.out.println("Estou pensando id : " + id + " vou demorar " + velocidadePensar/1000 + " segundos");
            controladorTelaPrincipal.alterarImagensPensar(id);
            sleep(velocidadePensar);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }//fim do metodo pensar
}//fim da Thread Filosofo