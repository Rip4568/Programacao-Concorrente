package controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Semaphore;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Escritor;
import model.Leitor;

public class ControladorTelaPrincipal implements Initializable {

    @FXML
    private Label lblNomeFilme;

    @FXML
    private ImageView ImagemEscritor0;
    
    @FXML
    private ImageView ImagemEscritor01;
    
    @FXML
    private ImageView ImagemEscritor02;

    @FXML
    private ImageView ImagemEscritor03;

    @FXML
    private ImageView ImagemEscritor04;

    @FXML
    private ImageView ImagemLeitor09;

    @FXML
    private ImageView ImagemLeitor08;

    @FXML
    private ImageView ImagemLeitor07;

    @FXML
    private ImageView ImagemLeitor02;

    @FXML
    private ImageView ImagemLeitor01;

    @FXML
    private ImageView ImagemLeitor0;

    @FXML
    private ImageView ImagemLeitor06;

    @FXML
    private ImageView ImagemLeitor05;

    @FXML
    private ImageView ImagemLeitor04;

    @FXML
    private ImageView ImagemLeitor03;

    @FXML
    private Slider sliderVelocidadeLeitor;

    @FXML
    private Slider sliderVelocidadeEscritor;


    String caminhoImagemLeitor = "/assets/images/LeitorFR.png";
    String caminhoImagemLeitorVerso = "/assets/images/LeitorFRV.png";
    String caminhoImagemLeitorSentado = "/assets/images/LeitorSentadoFR.png";
    String caminhoImagemEscritor = "/assets/images/EscritorFR.png";
    String caminhoImagemEscritorVerso = "/assets/images/EscritorFRV.png";

    
    int[] cadeiras;//a quantidade das cadeiras sao as mesmas que esta disponivel no arquivo layout FXMl
    /**
     * esta classe chamada Informacao guarda 3 valores inteiros
     * sabendo disso criei um vetor de inforamcoes em que cada um guarda 3 valores inteiros
     * isso sera util para os leitores saberem em que cadeira esta sentados e para qual posicao devem ir e tambe qual cadeira ocupou, para poder informar que desocupou (x , y E i)
     */
    Informacao informacoes[];//a quantidade de informacoes eh a quantidade de leitores
    //Semaphore mutex;//achei que seria util...

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        informacoes = new Informacao[10];
        cadeiras = new int[10];
        BancoDeDados bancoDeDados = new BancoDeDados();
        iniciarLeitores(5,bancoDeDados);
        iniciarEscritores(5,bancoDeDados);
        iniciarInformacoes();
        setarImagensEscritores();
        
    }

    public void iniciarLeitores(int quantidade,BancoDeDados bancoDeDados){
        Leitor[] leitores = new Leitor[quantidade];
        for(int i = 0; i < quantidade; i++){
            leitores[i] = new Leitor(i,bancoDeDados,this);
            leitores[i].start();
        }
    }

    public void iniciarEscritores(int quantidade,BancoDeDados bancoDeDados){
        Escritor[] escritores = new Escritor[quantidade];
        for(int i = 0; i < quantidade; i++){
            escritores[i] = new Escritor(i, bancoDeDados,this);
            escritores[i].start();
        }
    }

    public void iniciarInformacoes(){
        for(int i = 0; i < 10;i++){
            informacoes[i] = new Informacao();
        }

    }

    public void setarImagensEscritores(){
        ImagemEscritor0.setImage(new Image(caminhoImagemEscritorVerso));
        ImagemEscritor01.setImage(new Image(caminhoImagemEscritorVerso));
        ImagemEscritor02.setImage(new Image(caminhoImagemEscritorVerso));
        ImagemEscritor03.setImage(new Image(caminhoImagemEscritorVerso));
        ImagemEscritor04.setImage(new Image(caminhoImagemEscritorVerso));
    }

    

    public void ocuparCadeira_e_Assistir(int id){
        /**
         * este metodo tem por finalidade fazer os leitores caminhar ate uma cadeira vazia
         * ocupar, sentar, esperar determinado tempo(acredito que este metodo nao devera se preocupar com isso)
         * lembrete, alterar a imagem para sentar assim que chegar no determinado ponto ou a propria cadeira
         */

        switch(id){
            case 0:{
                ImagemLeitor0.setImage(new Image(caminhoImagemLeitor));
                /**
                 * o metodo procurarCadeiraDisponivel procura uma cadeira disponivel no vetor
                 * o valor deve ser diferente de 1
                 * o primeiro que encontrar ele passa as informações em um vetor como:
                 * posicao X da cadeira em que esta localizada
                 * posicao Y da cadeira que esta localizada
                 * posica i de qual cadeira ele esta sentado para avisar quando for sair
                 */
                int vetor[] = procurarCadeiraDisponivel();
                /**
                 * movimenta passando o id do personagem(acredito que nao sera util)
                 * a posicao x salva no vetor de informacoes
                 * 
                 * lembrete a posicao do vetor das informacoes[posicao] e definido pelo id de cada leitor e nao aleatorio rsrs
                 */
                informacoes[0].salvarPosicaoX(vetor[0]);
                informacoes[0].salvarPosicaoY(vetor[1]);
                informacoes[0].salvarPosicaoCadeira(vetor[2]);
                //informacoes[0].salvarPosicoesXYiVetor(procurarCadeiraDisponivel()); por algum motivo isso nao funciona... fazer testes depois

                /*
                System.out.println("informacoes[0]" + 
                "\n posicaoX:" + informacoes[0].getPosicaoX() + 
                "\n posicaoY:" + informacoes[0].getPosicaoY() + 
                "\n posicaoCadeira:" + informacoes[0].getPosicaoCadeira() + 
                "\n id leitor:" + id);
                */
                
                movimentarLeitor(id, informacoes[0].getPosicaoX(),informacoes[0].getPosicaoY() ,ImagemLeitor0);//provavelmente poderia tirar o [0] por [id] maaaaas deixa isso pra depois
                ImagemLeitor0.setImage(new Image(caminhoImagemLeitorSentado));
                break;
            }
            case 1:{
                ImagemLeitor01.setImage(new Image(caminhoImagemLeitor));
                
                int vetor[] = procurarCadeiraDisponivel();
                
                informacoes[1].salvarPosicaoX(vetor[0]);
                informacoes[1].salvarPosicaoY(vetor[1]);
                informacoes[1].salvarPosicaoCadeira(vetor[2]);
                
                movimentarLeitor(id, informacoes[1].getPosicaoX(), informacoes[1].getPosicaoY(), ImagemLeitor01);
                ImagemLeitor01.setImage(new Image(caminhoImagemLeitorSentado));
                break;
            }
            case 2:{
                ImagemLeitor02.setImage(new Image(caminhoImagemLeitor));
                
                int vetor[] = procurarCadeiraDisponivel();
                
                informacoes[2].salvarPosicaoX(vetor[0]);
                informacoes[2].salvarPosicaoY(vetor[1]);
                informacoes[2].salvarPosicaoCadeira(vetor[2]);
                
                movimentarLeitor(id, informacoes[2].getPosicaoX(), informacoes[2].getPosicaoY(), ImagemLeitor02);
                ImagemLeitor02.setImage(new Image(caminhoImagemLeitorSentado));
                break;
            }
            case 3:{
                ImagemLeitor03.setImage(new Image(caminhoImagemLeitor));
                
                int vetor[] = procurarCadeiraDisponivel();
                
                informacoes[3].salvarPosicaoX(vetor[0]);
                informacoes[3].salvarPosicaoY(vetor[1]);
                informacoes[3].salvarPosicaoCadeira(vetor[2]);
                
                movimentarLeitor(id, informacoes[3].getPosicaoX(), informacoes[3].getPosicaoY(), ImagemLeitor03);
                ImagemLeitor03.setImage(new Image(caminhoImagemLeitorSentado));
                break;
            }
            case 4:{
                ImagemLeitor04.setImage(new Image(caminhoImagemLeitor));
                
                int vetor[] = procurarCadeiraDisponivel();
                
                informacoes[4].salvarPosicaoX(vetor[0]);
                informacoes[4].salvarPosicaoY(vetor[1]);
                informacoes[4].salvarPosicaoCadeira(vetor[2]);
                
                movimentarLeitor(id, informacoes[4].getPosicaoX(), informacoes[4].getPosicaoY(), ImagemLeitor04);
                ImagemLeitor04.setImage(new Image(caminhoImagemLeitorSentado));
                break;
            }
            case 5:{
                //ImagemLeitor05.setImage(new Image(caminhoImagemLeitor));
                break;
            }
            case 6:{
                //ImagemLeitor06.setImage(new Image(caminhoImagemLeitor));
                break;
            }
            case 7:{
                //ImagemLeitor07.setImage(new Image(caminhoImagemLeitor));
                break;
            }
            case 8:{
                //ImagemLeitor08.setImage(new Image(caminhoImagemLeitor));
                break;
            }
            case 9:{
                //ImagemLeitor09.setImage(new Image(caminhoImagemLeitor));
                break;
            }

            default:{//essa default em hipotese alguma deve ser executada!
                System.out.println("alguma Thread acesou o default Leitor");
            }

        }
    }

    public void descouparCadeira_e_Sair(int id){
        /**
         * este metodo tem por finaidade descoupar a cadeira assim que terminar de assistir
         * e ir para algum ponto longe das cadeiras
         * 
         */
        int sairX = 23, sairY = 239;//ponto qualquer para se afastar
        switch(id){
            case 0:{
                System.out.println("Leitor ID:" + id +
                "posicao da cadeira :" + informacoes[1].getPosicaoCadeira() + 
                "situacao da posicao da cadeira :" + cadeiras[informacoes[1].getPosicaoCadeira()]);
                cadeiras[informacoes[0].getPosicaoCadeira()] = 0;//sinaliza de que o leitor saiu e deixou a cadeira desocupada
                System.out.println("Leitor ID:" + id +
                "posicao da cadeira :" + informacoes[1].getPosicaoCadeira() + 
                "situacao da posicao da cadeira :" + cadeiras[informacoes[1].getPosicaoCadeira()]);
                ImagemLeitor0.setImage(new Image(caminhoImagemLeitorVerso));//altera a imagem para em pe
                movimentarLeitor(id, sairX, sairY, ImagemLeitor0);//Lembrete, ele tem que ir para algum lugar fora do mapa a esquerda...
                break;
            }
            case 1:{
                cadeiras[informacoes[1].getPosicaoCadeira()] = 0;
                ImagemLeitor01.setImage(new Image(caminhoImagemLeitorVerso));
                movimentarLeitor(id,sairX + 10, sairY + 10, ImagemLeitor01);
                break;
            }
            case 2:{
                cadeiras[informacoes[2].getPosicaoCadeira()] = 0;
                ImagemLeitor02.setImage(new Image(caminhoImagemLeitorVerso));
                movimentarLeitor(id,sairX + 20, sairY + 20, ImagemLeitor02);
                break;
            }
            case 3:{
                cadeiras[informacoes[3].getPosicaoCadeira()] = 0;
                ImagemLeitor03.setImage(new Image(caminhoImagemLeitorVerso));
                movimentarLeitor(id,sairX + 30, sairY + 30, ImagemLeitor03);
                break;
            }
            case 4:{
                cadeiras[informacoes[4].getPosicaoCadeira()] = 0;
                ImagemLeitor04.setImage(new Image(caminhoImagemLeitorVerso));
                movimentarLeitor(id,sairX + 40, sairY + 40, ImagemLeitor04);
                break;
            }
            case 5:{
                break;
            }
            case 6:{
                break;
            }
            case 7:{
                break;
            }
            case 8:{
                break;
            }
            case 9:{
                break;
            }
        }
    }

    

    public int[] procurarCadeiraDisponivel(){
        /**
         * como eu preciso retornar uma quantidade de informacoes como posicao x e y de onde esta
         * localizada a cadeira achei melhor retornar um vetor com esssas informacoes
         * 
         * informacoes[id do leitor].setValor de 1 a 3 onde
         * setValor1 e a posicao x
         * setValor2 ea posicao y
         * setValor3 eh a cadeira a qual o leitor esta sentado
         * vetor[0] guarda as informacoes da posicao x
         * vetor[1] guarda as informacoes da posicao y
         * vetor[2] guarda as informacoes da posicao da cadeira que estou sentado
         */
        int [] valores = new int[3];
        
        for(int i = 0; i < cadeiras.length; i++){
            if(cadeiras[i] != 1){//se o valor for diferente de 1, ou seja se nao estiver ocupado...
                cadeiras[i] = 1;//altera o valor 0 para 1 informado que tem uma pessoa que ira sentar nessa posicao
                switch(i){
                    case 0:{
                        /**
                         * qual eh a posicao dessa cadeira 0 ?
                         * x = 305
                         * y = 97
                         * i = 0, logo ela eh a primera cadeira a ser ocupada
                         */
                        valores[0] = 305;//posicao da cadeira no eixo X
                        valores[1] = 97;//posicao da cadeira no eixo Y
                        valores[2] = i;//qual cadeira esta ocupando
                        return valores;//retornar o vetor que contem essas informacoes
                    }
                    case 1:{
                        valores[0] = 465;
                        valores[1] = 97;
                        valores[2] = i;
                        return valores;
                    }
                    case 2:{
                        valores[0] = 632;
                        valores[1] = 97;
                        valores[2] = i;
                        return valores;
                    }
                    case 3:{
                        valores[0] = 804;
                        valores[1] = 97;
                        valores[2] = i;
                        return valores;

                    }
                    case 4:{
                        valores[0] = 310;
                        valores[1] = 220;
                        valores[2] = i;
                        return valores;
                    }
                    case 5:{
                        valores[0] = 470;
                        valores[1] = 220;
                        valores[2] = i;
                        return valores;
                    }
                    case 6:{
                        valores[0] = 630;
                        valores[1] = 220;
                        valores[2] = i;
                        return valores;
                    }
                    case 7:{
                        valores[0] = 807;
                        valores[1] = 220;
                        valores[2] = i;
                        return valores;
                    }
                    case 8:{
                        valores[0] = 435;
                        valores[1] = 317;
                        valores[2] = i;
                        return valores;
                    }
                    case 9:{
                        valores[0] = 703;
                        valores[1] = 317;
                        valores[2] = i;
                        return valores;
                    }
                }
            }
        }
        
        
        return valores;
        
    }


    

    public void irAte_a_Camera(int id){
        /**
         * este metodo tem por finalidade fazer os ESCRITORES  irem ate a camera
         * rotacionar o personagem
         */
        int cameraX = 595, cameraY = 24;
        switch(id){
            case 0:{
                ImagemEscritor0.setImage(new Image(caminhoImagemEscritorVerso));
                movimentarEscritor(id, cameraX, cameraY, ImagemEscritor0);
                break;
            }
            case 1:{
                ImagemEscritor01.setImage(new Image(caminhoImagemEscritorVerso));
                movimentarEscritor(id, cameraX, cameraY, ImagemEscritor0);
                break;
            }
            case 2:{
                ImagemEscritor02.setImage(new Image(caminhoImagemEscritorVerso));
                movimentarEscritor(id, cameraX, cameraY, ImagemEscritor0);
                break;
            }
            case 3:{
                ImagemEscritor03.setImage(new Image(caminhoImagemEscritorVerso));
                movimentarEscritor(id, cameraX, cameraY, ImagemEscritor0);
                break;
            }
            case 4:{
                ImagemEscritor04.setImage(new Image(caminhoImagemEscritorVerso));
                movimentarEscritor(id, cameraX, cameraY, ImagemEscritor0);
                break;
            }
        }
    }//fim do metodo irAte_a_Camera

    public void seAfastarDaCamera(int id){
        /**
         * este metodo faz com que os ESCRITORES saiam de perto da camera voltando a ficar de volta a base
         * rotacionar o personagem
         */
        int longeCameraX = 987,longeCameraY = 273;
        switch(id){
            case 0:{
                ImagemEscritor0.setImage(new Image(caminhoImagemEscritor));
                movimentarEscritor(id, longeCameraX, longeCameraY, ImagemEscritor0);
                break;
            }
            case 1:{
                ImagemEscritor01.setImage(new Image(caminhoImagemEscritor));
                movimentarEscritor(id, longeCameraX + 10, longeCameraY + 10, ImagemEscritor01);
                break;
            }
            case 2:{
                ImagemEscritor02.setImage(new Image(caminhoImagemEscritor));
                movimentarEscritor(id, longeCameraX + 20, longeCameraY + 20, ImagemEscritor02);
                break;
            }
            case 3:{
                ImagemEscritor03.setImage(new Image(caminhoImagemEscritor));
                movimentarEscritor(id, longeCameraX + 30, longeCameraY + 30, ImagemEscritor03);
                break;
            }
            case 4:{
                ImagemEscritor04.setImage(new Image(caminhoImagemEscritor));
                movimentarEscritor(id, longeCameraX + 40, longeCameraY + 40, ImagemEscritor04);
                break;
            }
        }
        
    }//fim do metodo seAfastarDaCamera

    public void movimentarLeitor(int id,int x,int y,ImageView imagem){//desenvolver a movimentacao
        boolean chegueiNoX = false, chegueiNoY = false;
        int movimento;
        
        while(true){
            try {
                if(chegueiNoX == false){
                    if(imagem.getLayoutX() < x){
                        //andar para direita (+10)
                        movimento = 30 + (int)sliderVelocidadeLeitor.getValue();
                        if(imagem.getLayoutX() + movimento > x){
                            //significa que passamos do ponto x
                            imagem.setLayoutX(x);
                            chegueiNoX = true;
                            
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutX(imagem.getLayoutX() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutX() > x){
                        //andar para esquerda (-10)
                        movimento = 30 + (int)sliderVelocidadeLeitor.getValue();
                        if(imagem.getLayoutX() - movimento < x){
                            imagem.setLayoutX(x);
                            chegueiNoX = true;
                            
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutX(imagem.getLayoutX() - movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutX() == x){//pode deixar um else sem a verificacao do igual
                        chegueiNoX = true;
                    }
    
                }
                if(chegueiNoY == false){
                    if(imagem.getLayoutY() < y){
                        //movimentar para baixo +10
                        movimento = 30 +(int)sliderVelocidadeLeitor.getValue();
                        if(imagem.getLayoutY() + movimento > y){
                            imagem.setLayoutY(y);
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutY(imagem.getLayoutY() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutY() > y){
                        //movimentar para cima -10
                        movimento = 30 + (int)sliderVelocidadeLeitor.getValue();
                        if(imagem.getLayoutY() - movimento < y){
                            imagem.setLayoutY(y);
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutY(imagem.getLayoutY() - movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutY() == y){
                        chegueiNoY = true;
                    }
                }
                if(chegueiNoX == true && chegueiNoY == true){
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
            
        }//fim do while
        
    }//fim do metodo movimentarLeitor


    public void alterarNomeDoFilme(String texto){
        lblNomeFilme.setText(texto);
    }

    public void movimentarEscritor(int id, int x, int y, ImageView imagem){
        boolean chegueiNoX = false, chegueiNoY = false;
        int movimento;
        
        while(true){
            try {
                if(chegueiNoX == false){
                    if(imagem.getLayoutX() < x){
                        //andar para direita (+10)
                        movimento = 30 + (int)sliderVelocidadeEscritor.getValue();
                        if(imagem.getLayoutX() + movimento > x){
                            //significa que passamos do ponto x
                            imagem.setLayoutX(x);
                            chegueiNoX = true;
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutX(imagem.getLayoutX() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutX() > x){
                        //andar para esquerda (-10)
                        movimento = 30 + (int)sliderVelocidadeEscritor.getValue();
                        if(imagem.getLayoutX() - movimento < x){
                            imagem.setLayoutX(x);
                            chegueiNoX = true;
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutX(imagem.getLayoutX() - movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutX() == x){//pode deixar um else sem a verificacao do igual
                        chegueiNoX = true;
                    }
    
                }
                if(chegueiNoY == false){
                    if(imagem.getLayoutY() < y){
                        //movimentar para baixo +10
                        movimento = 30 +(int)sliderVelocidadeEscritor.getValue();
                        if(imagem.getLayoutY() + movimento > y){
                            imagem.setLayoutY(y);
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutY(imagem.getLayoutY() + movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutY() > y){
                        //movimentar para cima -10
                        movimento = 30 + (int)sliderVelocidadeEscritor.getValue();
                        if(imagem.getLayoutY() - movimento < y){
                            imagem.setLayoutY(y);
                            chegueiNoY = true;
                        } else {
                            final int movimentarValorFinal = movimento;
                            Platform.runLater(() -> {
                                imagem.setLayoutY(imagem.getLayoutY() - movimentarValorFinal);
                            });
                        }
                    }
                    if(imagem.getLayoutY() == y){
                        chegueiNoY = true;
                    }
                }
                if(chegueiNoX == true && chegueiNoY == true){
                    break;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }//fim do while
    }//fim do metodo movimentarEscritor

    public int getVelocidadeSliderLeitor(){
        return (int)sliderVelocidadeLeitor.getValue();
    }
    public int getVelocidadeSliderEscritor(){
        return (int)sliderVelocidadeEscritor.getValue();
    }
}//fim da classe ControladorTelaPrincipal