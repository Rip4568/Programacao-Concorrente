package controller;

import java.net.URL;
import java.util.ResourceBundle;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ControladorTelaPrincipal extends Thread implements Initializable {
    @FXML
    private Label lblPai;

    @FXML
    private Label lblFilho1;

    @FXML
    private Label lblFilho2;

    @FXML
    private Label lblFilho3;

    @FXML
    private Label lblBisneto;

    @FXML
    private Label lblNeto1;

    @FXML
    private Label lblNeto2;

    @FXML
    private ImageView imagemNeto2;

    @FXML
    private ImageView imagemNeto1;

    @FXML
    private ImageView imagemBisneto1;

    @FXML
    private ImageView imagemFilho2;

    @FXML
    private ImageView imagemFilho3;

    @FXML
    private ImageView imagemPai;

    @FXML
    private ImageView imagemFilho1;



    //Caminho das imagens
    String caminhoDoge = "/assets/images/Adulto_Doge/DOGE.png";
    String caminhoDoge2 = "/assets/images/Adulto_Doge/DOGE2.png";
    String caminhoBabyDoge = "/assets/images/Filhote_Doge/BabyDoge.png";
    String caminhoBabyDoge2 = "/assets/images/Filhote_Doge/BabyDoge2.png";
    String caminhoDogeVelho = "/assets/images/Velho_Doge/VelhoDoge.png";
    String caminhoDogeVelho2 = "/assets/images/Velho_Doge/VelhoDoge2.png";
    String caminhoMorte = "/assets/images/mortes/icone_morte.png";
    

    @Override//assim que compilar sera o primeiro a executar
    public void initialize(URL arg0, ResourceBundle arg1) {
            Pai pai = new Pai();
            pai.start();
    }


    public class Pai extends Thread{
        public void run(){
            System.out.println("Nasce o pai");
            for(int ano = 0; ano <= 90; ano++){
                final int anoFinal = ano;
                try {
                    sleep(1000);
                    switch(ano){
                        case 0:{//filhote 
                            imagemPai.setImage(new Image(caminhoBabyDoge));
                            break;
                        }
                        case 18:{//18 anos alterara a imagem
                            imagemPai.setImage(new Image(caminhoDoge));    
                            break;
                        }
                        case 22:{//primeiro Filho
                            System.out.println("O pai tem o primeiro filho aos 22 anos");
                            Filho1 filho1 = new Filho1();
                            filho1.start();
                            break;
                        }
                        case 25:{//segundo filho
                            System.out.println("O pai tem o segundo filho aos 25 anos");
                            Filho2 filho2 = new Filho2();
                            filho2.start();
                            break;
                        }
                        case 32:{//terceiro filho
                            System.out.println("O pai tem o terceiro filho aos 32 anos");
                            Filho3 filho3 = new Filho3();
                            filho3.start();
                            break;
                        }
                        case 80:{
                            imagemPai.setImage(new Image(caminhoDogeVelho));
                            break;
                        }
                        case 90:{
                            System.out.println("O pai morre aos 90 anos");
                            imagemPai.setImage(new Image(caminhoMorte));
                            break;
                        }
                    }
                    Platform.runLater( () -> {
                        lblPai.setText("ano : " + anoFinal);
                      });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Filho1 extends Thread{
        public void run(){
            System.out.println("Nasce o primeiro Filho");
            for(int ano = 0; ano <= 61; ano++){
                final int anoFinal = ano;
                try {
                    sleep(1000);//1 segundo
                    switch(ano){
                        case 0:{
                            imagemFilho1.setImage(new Image(caminhoBabyDoge2));
                            break;
                        }
                        case 16:{//16 + 22 = 38 anos passados
                            System.out.println("O pai eh avo (primeiro filho) aos 38 anos");
                            Neto1 neto1 = new Neto1();
                            neto1.start();
                            break;
                        }
                        case 18:{//
                            imagemFilho1.setImage(new Image(caminhoDoge2));
                            break;
                        }
                        case 61:{
                            System.out.println("O primeiro filho morre aos 61 anos");
                            imagemFilho1.setImage(new Image(caminhoMorte));
                            break;
                        }
                    }
                    Platform.runLater( () -> {
                        lblFilho1.setText("ano : " + anoFinal);
                      });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public class Filho2 extends Thread{
        public void run(){
            System.out.println("Nasce o segundo filho");
            for(int ano = 0; ano <= 55; ano++){
                final int anoFinal = ano;
                try {
                    sleep(1000);
                    switch(ano){
                        case 0:{
                            imagemFilho2.setImage(new Image(caminhoBabyDoge2));
                            break;
                        }
                        case 18:{
                            imagemFilho2.setImage(new Image(caminhoDoge2));
                            break;
                        }
                        case 20:{//45 anos se passaram
                            System.out.println("O pai é avô (segundo filho) aos 45 anos");
                            Neto2 neto2 = new Neto2();
                            neto2.start();
                            break;
                        }
                        case 55:{
                            imagemFilho2.setImage(new Image(caminhoMorte));
                            break;
                        }

                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater( () -> {
                    lblFilho2.setText("ano : " + anoFinal);
                  });
            }
        }
    }

    public class Filho3 extends Thread{
        public void run(){
            System.out.println("Nasce o terceiro filho");
            for(int ano = 0; ano <= 55; ano++){
                final int anoFinal = ano;
                try {
                    sleep(1000);//38 anos se passaram
                    switch(ano){
                        case 0:{
                            imagemFilho3.setImage(new Image(caminhoBabyDoge2));
                            break;
                        }
                        case 18:{
                            imagemFilho3.setImage(new Image(caminhoDoge2));
                            break;
                        }
                        case 40:{
                            imagemFilho3.setImage(new Image(caminhoDogeVelho2));
                            break;
                        }
                        case 55:{
                            imagemFilho3.setImage(new Image(caminhoMorte));
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater( () -> {
                    lblFilho3.setText("ano : " + anoFinal);
                  });
            }
        }
    }

    public class Neto1 extends Thread{
        public void run(){
            System.out.println("O neto do primeiro Filho nasce");
            for(int ano = 0; ano <= 35; ano++){
                final int anoFinal = ano;
                try {
                    sleep(1000);
                    switch(ano){
                        case 0:{//nasce o primeiro neto
                            imagemNeto1.setImage(new Image(caminhoBabyDoge2));
                            break;
                        }
                        case 18:{
                            imagemNeto1.setImage(new Image(caminhoDoge2));
                            break;
                        }
                        case 30:{//68 segundos se passaram
                            System.out.println("O pai eh bisavo (primeiro filho) aos 68 anos");
                            Bisneto bisneto = new Bisneto();
                            bisneto.start();
                            break;
                        }
                        case 35:{
                            System.out.println("O primeiro neto morre aos 35 anos");
                            imagemNeto1.setImage(new Image(caminhoMorte));
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater( () -> {
                    lblNeto1.setText("ano : " + anoFinal);
                  });
            }
        }
    }

    public class Neto2 extends Thread {
        public void run(){
            System.out.println("Nasce o segundo neto");
            for(int ano = 0; ano <= 33; ano++){
                final int anoFinal = ano;
                try {
                    sleep(1000);
                    switch(ano){
                        case 0:{
                            imagemNeto2.setImage(new Image(caminhoBabyDoge2));
                            break;
                        }
                        case 18:{
                            imagemNeto2.setImage(new Image(caminhoDoge2));
                            break;
                        }
                        case 33:{
                            System.out.println("Morre o segundo neto aos 33 anos");
                            imagemNeto2.setImage(new Image(caminhoMorte));
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater( () -> {
                    lblNeto2.setText("ano : " + anoFinal);
                  });
            }
        }
    }

    public class Bisneto extends Thread{
        public void run(){
            System.out.println("Nasce o bisneto");
            for(int ano = 0; ano <= 12; ano++){
                final int anoFinal = ano;
                try {
                    sleep(1000);
                    switch(ano){
                        case 0:{
                            imagemBisneto1.setImage(new Image(caminhoBabyDoge2));
                            break;
                        }
                        case 12:{
                            System.out.println("O bisneto morre aos 12 anos");
                            imagemBisneto1.setImage(new Image(caminhoMorte));
                            break;
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Platform.runLater( () -> {
                    lblBisneto.setText("ano : " + anoFinal);
                  });
            }
        }
    }
}