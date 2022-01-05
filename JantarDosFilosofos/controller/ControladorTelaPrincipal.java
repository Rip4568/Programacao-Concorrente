package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Filosofo;

public class ControladorTelaPrincipal implements Initializable {
    /**
     * Embaixo vc vera todas as variaveis que utulizei durante o desenvolvimento do projeto
     * sliders e imagem
     */

    @FXML
    private Slider sliderVelocidadeComer3;

    @FXML
    private Slider sliderVelocidadeComer4;

    @FXML
    private Slider sliderVelocidadeComer0;

    @FXML
    private Slider sliderVelocidadeComer1;

    @FXML
    private Slider sliderVelocidadeComer2;

    @FXML
    private Slider sliderVelocidadePensar0;

    @FXML
    private Slider sliderVelocidadePensar1;

    @FXML
    private Slider sliderVelocidadePensar2;

    @FXML
    private Slider sliderVelocidadePensar3;

    @FXML
    private Slider sliderVelocidadePensar4;

    @FXML
    private ImageView comida0;

    @FXML
    private ImageView comida1;

    @FXML
    private ImageView comida2;

    @FXML
    private ImageView comida3;

    @FXML
    private ImageView comida4;

    @FXML
    private ImageView filosofo0;

    @FXML
    private ImageView filosofo1;

    @FXML
    private ImageView filosofo2;

    @FXML
    private ImageView filosofo3;

    @FXML
    private ImageView filosofo4;

    //caminho dos arquivos e imagens que utilizei

    String caminhoDogePensando = "/assets/images/filosofoFR.png";
    String caminhoDogePensandoVerso = "/assets/images/filosofoVFR.png";

    String caminhoDogeComendo = "/assets/images/cheemsComendo.gif";

    String caminhoDogeFome = "/assets/images/filosofoFomeFR.png";
    String caminhoDogeFomeVerso = "/assets/images/filosofoFomeVFR.png";

    int quantidadeFilosofos = 5;


    /*******************************************************************
    * nome........:initialize
    * funcao......:Inicializar assim que o JavaFX comecar
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/

    /*******************************************************************
    * nome........:
    * funcao......:
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        
        Filosofo filosofo[] = new Filosofo[quantidadeFilosofos];
        ControladorFilosofos controladorFilosofos = new ControladorFilosofos(quantidadeFilosofos,this);
        for(int i = 0; i < quantidadeFilosofos; i ++){
            filosofo[i] = new Filosofo(i,controladorFilosofos,this);
            filosofo[i].start();
        }
    }

    /*Outra forma de fazer seria essa, porem acredito que teria problemas em alterar e mover as imagens 
    dos hambuergues quando eles comerem, nao sei dizer se esse metodo iria reduzir linhas, fazer testes depois
    public void alterarImagens(int id,int opcao){//id serve para identificar qual filosfo eh, opcao e a imagem a ser setada
        switch(opcao){
            case 1:{//alterara a imgem pra fome
                switch(id){//qual filosofo esta chamando ?
                    case 0:{

                    }
                    case 1:{

                    }
                    case 2:{

                    }
                    case 3:{

                    }
                    case 4:{

                    }

                }
                break;
            }
            case 2:{//alterar imagem para pensar
                switch(id){//fazer os outros cases

                }
                break;
            }
            case 3:{//alterar imagem para esfomeando
                switch(id){//fazer os outros cases

                }
                break;
            }
        }
    }
    */


    /*******************************************************************
    * nome........:alterarImagensPensar
    * funcao......:alterar as imagens do filosofo para a imagem de pensar
    passando o ID que eh a identificacao dos filosofos
    * Parametros..:inteiro id
    * retorno.....:nao ha retorno (ele altera a imagem)
    ******************************************************************/

    public void alterarImagensPensar(int id){
        switch(id){
            case 0:{
                filosofo0.setImage(new Image(caminhoDogePensando));
                Platform.runLater(() -> {
                    comida0.setLayoutX(258);
                    comida0.setLayoutY(148);

                    comida4.setLayoutX(445);
                    comida4.setLayoutY(148);
                });
                break;
                //filosofo0;
            }
            case 1:{
                filosofo1.setImage(new Image(caminhoDogePensando));

                Platform.runLater(() -> {
                    comida0.setLayoutX(258);
                    comida0.setLayoutY(148);

                    comida1.setLayoutX(217);
                    comida1.setLayoutY(224);
                });
                break;
                //filosofo1
            }
            case 2:{
                filosofo2.setImage(new Image(caminhoDogePensando));

                Platform.runLater(() -> {
                    comida1.setLayoutX(217);
                    comida1.setLayoutY(224);

                    comida2.setLayoutX(352);
                    comida2.setLayoutY(294);
                });
                break;
                //filosofo2
            }
            case 3:{
                filosofo3.setImage(new Image(caminhoDogePensandoVerso));

                Platform.runLater(() -> {
                    comida2.setLayoutX(352);
                    comida2.setLayoutY(294);

                    comida3.setLayoutX(475);
                    comida3.setLayoutY(233);
                });
                break;
                //filosofo3
            }
            case 4:{
                filosofo4.setImage(new Image(caminhoDogePensandoVerso));

                Platform.runLater(() -> {
                    comida3.setLayoutX(475);
                    comida3.setLayoutY(233);

                    comida4.setLayoutX(445);
                    comida4.setLayoutY(148);
                });
                break;
                //filosofo4
            }
            default:{
                System.out.println("outro id gerado : " + id);
            }
        }
        
    }//fim do metodo alterarImagens

    /*******************************************************************
    * nome........:alterarImagensComer
    * funcao......:o mesmo que a funcao anterio mas agora ele altera a imagem para comer
    * Parametros..:inteiro ID, para saber a identificacao do filoso que esta chamando esse parametro
    * retorno.....:nao ha retorno
    ******************************************************************/

    public void alterarImagensComer(int id){
        switch(id){
            case 0:{
                filosofo0.setImage(new Image(caminhoDogeComendo));
                Platform.runLater(() -> {
                    comida0.setLayoutX(358);
                    comida0.setLayoutY(148);

                    comida4.setLayoutX(358);
                    comida4.setLayoutY(148);
                });
                break;
                //filosofo0;
            }
            case 1:{
                filosofo1.setImage(new Image(caminhoDogeComendo));

                Platform.runLater(() -> {
                    comida0.setLayoutX(199);
                    comida0.setLayoutY(166);

                    comida1.setLayoutX(199);
                    comida1.setLayoutY(166);
                });
                break;
                //filosofo1
            }
            case 2:{
                filosofo2.setImage(new Image(caminhoDogeComendo));

                Platform.runLater(() -> {
                    comida1.setLayoutX(251);
                    comida1.setLayoutY(268);

                    comida2.setLayoutX(251);
                    comida2.setLayoutY(268);
                });
                break;
                //filosofo2
            }
            case 3:{
                filosofo3.setImage(new Image(caminhoDogeComendo));

                Platform.runLater(() -> {
                    comida2.setLayoutX(445);
                    comida2.setLayoutY(282);

                    comida3.setLayoutX(445);
                    comida3.setLayoutY(282);
                });
                break;
                //filosofo3
            }
            case 4:{
                filosofo4.setImage(new Image(caminhoDogeComendo));

                Platform.runLater(() -> {
                    comida3.setLayoutX(497);
                    comida3.setLayoutY(180);

                    comida4.setLayoutX(497);
                    comida4.setLayoutY(180);
                    
                });
                break;
                //filosofo4
            }
            default:{
                System.out.println("outro id gerado : " + id);
            }
        }
    }

    /*******************************************************************
    * nome........:alterarImagensEsfomeando
    * funcao......:o mesmo que a funcao anterior porem agora ele altera as imagens
    para esfomeando
    * Parametros..:inteiro id, para saber a identificao do filosofo que esta chamando
    * retorno.....:nao ha retorno
    ******************************************************************/

    public void alterarImagensEsfomeando(int id){
        switch(id){
            case 0:{
                filosofo0.setImage(new Image(caminhoDogeFome));
                break;
                //filosofo0;
            }
            case 1:{
                filosofo1.setImage(new Image(caminhoDogeFome));
                break;
                //filosofo1
            }
            case 2:{
                filosofo2.setImage(new Image(caminhoDogeFome));
                break;
                //filosofo2
            }
            case 3:{
                filosofo3.setImage(new Image(caminhoDogeFomeVerso));
                break;
                //filosofo3
            }
            case 4:{
                filosofo4.setImage(new Image(caminhoDogeFomeVerso));
                break;
                //filosofo4
            }
            default:{
                System.out.println("outro id gerado : " + id);
            }
        }
    }

    /*******************************************************************
    * nome........:getSliderPensar
    * funcao......:pegar os valores dos slider de Pensar para os filosofos que chamrem
    * Parametros..:Inteiro ID, para saber qual filosofo esta chamando e qual valor de slider entregar
    * retorno.....:nao ha retorno
    ******************************************************************/
    public int getSliderPensar(int id){
        switch(id){
            case 0:{
                return (int)sliderVelocidadePensar0.getValue();
            }
            case 1:{
                return (int)sliderVelocidadePensar1.getValue();
            }
            case 2:{
                return (int)sliderVelocidadePensar2.getValue();
            }
            case 3:{
                return (int)sliderVelocidadePensar3.getValue();
            }
            case 4:{
                return (int)sliderVelocidadePensar4.getValue();
            }
        }
            
        return 1;
    }

    /*******************************************************************
    * nome........:getSliderComer
    * funcao......:pegar os valores dos slider de Comer para os filosofos que chamrem
    * Parametros..:Inteiro ID, para saber qual filosofo esta chamando e qual valor de slider entregar
    * retorno.....:nao ha retorno
    ******************************************************************/
    public int getSliderComer(int id){
        switch(id){
            case 0:{
                return (int)sliderVelocidadeComer0.getValue();
            }
            case 1:{
                return (int)sliderVelocidadeComer1.getValue();
            }
            case 2:{
                return (int)sliderVelocidadeComer2.getValue();
            }
            case 3:{
                return (int)sliderVelocidadeComer3.getValue();
            }
            case 4:{
                return (int)sliderVelocidadeComer4.getValue();
            }
        }
        return 1;
    }
}