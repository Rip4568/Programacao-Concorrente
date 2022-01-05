package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ControladorTelaPrincipal implements Initializable{

    @FXML
    private Label lblTextoTesteTrem1;

    @FXML
    private Label lblTextoTesteTrem2;

    @FXML
    private Slider sliderTrem2;

    @FXML
    private Slider sliderTrem1;

    @FXML
    private ImageView imagemTrem1;

    @FXML
    private ImageView imagemTrem2;

    @FXML
    private ImageView imagemSinal1;

    @FXML
    private ImageView imagemSinal2;

    String caminhoDogeVerde = "/assets/images/dogeSinalVerde.png";
    String caminhoDogeVermelho = "/assets/images/dogeSinalVermelho.png";

    int velocidadePrimeiroTrem;//variavel global assim podde ser acessada pelos metodos
    int velocidadeSegundoTrem;

    boolean sinalPassagem = true;//de inicio todas as passagens vao estar liberadas
    boolean sinalPassagem2 = true;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        imagemSinal1.setImage(new Image(caminhoDogeVerde));
        imagemSinal2.setImage(new Image(caminhoDogeVerde));

        PrimeiroTrem primeiroTrem = new PrimeiroTrem();
        SegundoTrem segundoTrem = new SegundoTrem();
        primeiroTrem.start();
        segundoTrem.start();
    }
        
    public class PrimeiroTrem extends Thread {
        public void run(){
            System.out.println("Inicei o primeiroTrem");
            AndarPrimeiroTrem andarPrimeiroTrem = new AndarPrimeiroTrem();
            andarPrimeiroTrem.start();
            sliderTrem1.valueProperty().addListener(new ChangeListener<Number>(){//Observador do slider
                @Override
                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {//variavel de mudanca do slider
                    velocidadePrimeiroTrem = (int) sliderTrem1.getValue();
                    lblTextoTesteTrem1.setText(Integer.toString(velocidadePrimeiroTrem));
                }
            });
            /* podePassar ? se sim passa e altera o sinal do podePassar
            podePassar ? se não espera até o sinal ser verdadeiro
            */
            

        }
        
    }
    public class SegundoTrem extends Thread {
        public void run(){
            System.out.println("Iniciei o segundo Trem");
            AndarSegundoTrem andarSegundoTrem = new AndarSegundoTrem();
            andarSegundoTrem.start();
            sliderTrem2.valueProperty().addListener(new ChangeListener<Number>(){
                @Override
                public void changed(ObservableValue<? extends Number> arg0, Number arg1, Number arg2) {
                    velocidadeSegundoTrem = (int) sliderTrem2.getValue();
                    lblTextoTesteTrem2.setText(Integer.toString(velocidadeSegundoTrem));
                }
            });
            /* podePassar ? se sim passa e altera o sinal do podePassar
            podePassar ? se não espera até o sinal ser verdadeiro
            */
        }
    }


    public class AndarPrimeiroTrem extends Thread{
        public void run(){
        for(int pontos = 1; pontos <= 13; pontos++){
            try{
                switch(pontos){
                    case 1:{//99x 77y   AUDITADO
                        andarTremAutomatico(99, 77, imagemTrem1);
                        break;
                    }//case 1
                    case 2:{//159,138   AUDITADO
                        andarTremAutomatico(160,140,imagemTrem1);
                        break;
                    }
                    case 3:{//205, 160  AUDITADO
                        while(abrirPassagemTunel1() != true){
                            sleep(1000);
                        }
                        andarTremAutomatico(200,160,imagemTrem1);
                        break;
                    }//case 3
                    case 4:{//303, 160 problema no x no ponto 4
                        andarTremAutomatico(300, 160, imagemTrem1);
                        break;
                    }
                    case 5:{//327, 135 AUDITADO
                        andarTremAutomatico(320, 130, imagemTrem1);
                        fecharPassagemTunel1();
                        break;
                    }
                    case 6:{//401x e 77y AUDITADO
                        andarTremAutomatico(400, 80, imagemTrem1);
                        break;
                    }
                    case 7:{//525 77y AUDITADO
                        andarTremAutomatico(525, 80, imagemTrem1);
                        break;
                    }
                    case 8:{//577x , 138y
                        andarTremAutomatico(580, 140, imagemTrem1);
                        break;
                    }
                    case 9:{//612x, 160y
                        while(abrirPassagemTunel2() == false){
                            sleep(1000);
                        }
                        andarTremAutomatico(610, 160, imagemTrem1);
                        break;
                    }
                    case 10:{//715x, 160y
                        andarTremAutomatico(715, 160, imagemTrem1);
                        break;
                    }
                    case 11:{//745x, 125y
                        andarTremAutomatico(745, 105, imagemTrem1);
                        fecharPassagemTunel2();
                        break;
                    }
                    case 12:{//810x, 55y
                        andarTremAutomatico(810, 55, imagemTrem1);
                        break;
                    }
                    case 13:{//900x, 55y
                        andarTremAutomatico(900, 55, imagemTrem1);
                        break;
                    }
                    
                    
                }//switch pontos
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }//for pontos
        }
    }

    public class AndarSegundoTrem extends Thread{
        public void run(){
            for(int pontos = 1; pontos <= 13; pontos++){//contar de 1 ate 13, poderia fazer de 0 ate 13
                try{
                    switch(pontos){
                        case 1:{//810x,227y
                            andarTremAutomatico2(810, 227, imagemTrem2);
                            break;
                        }
                        case 2:{//774x,202y
                            andarTremAutomatico2(774, 202, imagemTrem2);
                            break;
                        }
                        case 3:{//724x, 160y
                            while(abrirPassagemTunel2() == false){
                                sleep(1000);
                            }
                            andarTremAutomatico2(724, 160, imagemTrem2);
                            break;
                        }
                        case 4:{//594x,160y
                            andarTremAutomatico2(594, 160, imagemTrem2);
                            break;
                        }
                        case 5:{//548x, 194y
                            andarTremAutomatico2(548, 194, imagemTrem2);
                            fecharPassagemTunel2();
                            break;
                        }
                        case 6:{//516x, 227y
                            andarTremAutomatico2(516, 227, imagemTrem2);
                            break;
                        }
                        case 7:{//398x, 227y
                            andarTremAutomatico2(398, 227, imagemTrem2);
                            break;
                        }
                        case 8:{//372x, 202y
                            andarTremAutomatico2(372, 202, imagemTrem2);
                            break;
                        }
                        case 9:{//303x, 172y
                            while(abrirPassagemTunel1() == false){
                                sleep(1000);
                            }
                            andarTremAutomatico2(303, 172, imagemTrem2);
                            break;
                        }
                        case 10:{//181x, 172y
                            andarTremAutomatico2(181, 172, imagemTrem2);
                            break;
                        }
                        case 11:{//143x, 215y
                            andarTremAutomatico2(143, 215, imagemTrem2);
                            fecharPassagemTunel1();
                            break;
                        }
                        case 13:{//105x 250y
                            andarTremAutomatico2(105, 250, imagemTrem2);
                            break;
                        }
                        /*
                        case 14:{//99x,77y
                            andarTremAutomatico2(50,77,imagemTrem2);
                            break;
                        }
                     */
                    }//switch pontos

                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }//for pontos
        }//run()
    }//andar segundoTrem


    public void andarTremAutomatico(int posicaoX, int posicaoY, ImageView imagemTrem){
        boolean chegueiNoX = false;
        boolean chegueiNoY = false;
        int movimentar;
        try {
            while(true){
                if(chegueiNoX == false){
                    if(imagemTrem.getLayoutX() < posicaoX){
                        //andar para direita + 10
                        movimentar = (10 + velocidadePrimeiroTrem);
                        if((imagemTrem.getLayoutX() + movimentar) > posicaoX){
                            imagemTrem.setLayoutX(posicaoX);
                            //System.out.println("cheguei no x indo pra direita");
                            chegueiNoX = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutX(imagemTrem.getLayoutX() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagemTrem.getLayoutX() > posicaoX){
                        //andar para esquerda - 10
                        movimentar = (10 + velocidadePrimeiroTrem);
                        if((imagemTrem.getLayoutX() - movimentar) < posicaoX){//passou do ponto, ou seja menor que a propria posicção
                            imagemTrem.setLayoutX(posicaoX);
                            //System.out.println("Cheguei no x indo pra esquerda");
                            chegueiNoX = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutX(imagemTrem.getLayoutX() - movimentarValorFinal);
                            });
                        }
                    }
                    if (imagemTrem.getLayoutX() == posicaoX){
                        //System.out.println("Cheguei no x com valores sendo iguais");
                        chegueiNoX = true;
                    }
                }

                if(chegueiNoY == false){
                    if(imagemTrem.getLayoutY() < posicaoY){
                        //andar para baixo + 10
                        movimentar = (10 + velocidadePrimeiroTrem);
                        if((imagemTrem.getLayoutY() + movimentar) > posicaoY){
                            imagemTrem.setLayoutY(posicaoY);
                            //System.out.println("cheguei no Y indo para baixo");
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutY(imagemTrem.getLayoutY() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagemTrem.getLayoutY() > posicaoY){
                        //andar para cima - 10
                        movimentar = (10 + velocidadePrimeiroTrem);
                        if((imagemTrem.getLayoutY() + movimentar) < posicaoY){
                            imagemTrem.setLayoutY(posicaoY);
                            //System.out.println("Cheguei no Y indo para cima");
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutY(imagemTrem.getLayoutY() - movimentarValorFinal);
                            });
                        }
                    }
                    if (imagemTrem.getLayoutY() == posicaoY){
                        //System.out.println("Cheguei no y com valores sendo iguais");
                        chegueiNoY = true;
                    }
                }
                if(chegueiNoX && chegueiNoY){
                    //System.out.println("Cheguei no x e Cheguei no y");
                    break;
                }
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void andarTremAutomatico2(int posicaoX, int posicaoY, ImageView imagemTrem){
        boolean chegueiNoX = false;
        boolean chegueiNoY = false;
        int movimentar;
        try {
            while(true){
                if(chegueiNoX == false){
                    if(imagemTrem.getLayoutX() < posicaoX){ 
                        //andar para direita + 10
                        movimentar = (10 + velocidadeSegundoTrem);
                        if((imagemTrem.getLayoutX() + movimentar) > posicaoX){
                            imagemTrem.setLayoutX(posicaoX);
                            //System.out.println("cheguei no x indo pra direita");
                            chegueiNoX = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutX(imagemTrem.getLayoutX() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagemTrem.getLayoutX() > posicaoX){
                        //andar para esquerda - 10
                        movimentar = (10 + velocidadeSegundoTrem);
                        if((imagemTrem.getLayoutX() - movimentar) < posicaoX){//passou do ponto, ou seja menor que a propria posicção
                            imagemTrem.setLayoutX(posicaoX);
                            //System.out.println("Cheguei no x indo pra esquerda");
                            chegueiNoX = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutX(imagemTrem.getLayoutX() - movimentarValorFinal);
                            });
                        }
                    }
                    if (imagemTrem.getLayoutX() == posicaoX){
                        //System.out.println("Cheguei no x com valores sendo iguais");
                        chegueiNoX = true;
                    }
                }

                if(chegueiNoY == false){
                    if(imagemTrem.getLayoutY() < posicaoY){
                        //andar para baixo + 10
                        movimentar = (10 + velocidadeSegundoTrem);
                        if((imagemTrem.getLayoutY() + movimentar) > posicaoY){
                            imagemTrem.setLayoutY(posicaoY);
                            //System.out.println("cheguei no Y indo para baixo");
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutY(imagemTrem.getLayoutY() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagemTrem.getLayoutY() > posicaoY){
                        //andar para cima - 10
                        movimentar = (10 + velocidadeSegundoTrem);
                        if((imagemTrem.getLayoutY() + movimentar) < posicaoY){
                            imagemTrem.setLayoutY(posicaoY);
                            //System.out.println("Cheguei no Y indo para cima");
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimentar;
                            Platform.runLater(() -> {
                                imagemTrem.setLayoutY(imagemTrem.getLayoutY() - movimentarValorFinal);
                            });
                        }
                    }
                    if (imagemTrem.getLayoutY() == posicaoY){
                        //System.out.println("Cheguei no y com valores sendo iguais");
                        chegueiNoY = true;
                    }
                }
                if(chegueiNoX && chegueiNoY){
                    //System.out.println("Cheguei no x e Cheguei no y");
                    break;
                }
                Thread.sleep(1000);
            }
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

    public boolean abrirPassagemTunel1(){
        if(sinalPassagem == true){//se a passagem esta liberada
            sinalPassagem = false;//então fecha a passagem pois o trem vai passar
            imagemSinal1.setImage(new Image(caminhoDogeVermelho));
            return true;//retornar o sinal que pode passar
        } else {
            return false;//senao estiver aberta retorna false, ou seja nao pode passar
        }
    }

    public void fecharPassagemTunel1(){//neste caso não precisa retornar nada pois a unica funcao eh fechar e nao verificar
        if(sinalPassagem == false){//se o trem que chamou o metodo realmente estiver com a passagem fechada
            sinalPassagem = true;//então ele pode abrir
            imagemSinal1.setImage(new Image(caminhoDogeVerde));
        }
    }

    public boolean abrirPassagemTunel2(){
        if(sinalPassagem2 == true){
            sinalPassagem2 = false;//se esta liberado logo ele ira entrar no tunel e fechar automatico
            imagemSinal2.setImage(new Image(caminhoDogeVermelho));
            return true;//sinal positivo para ir
        } else {
            return false;//nao pode abrir a passagem, entao espere
        }
    }

    public void fecharPassagemTunel2(){
        if(sinalPassagem2 == false){
            sinalPassagem2 = true;
            imagemSinal2.setImage(new Image(caminhoDogeVerde));
        }
    }
    
    
}
