package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.control.Slider;
import model.*;

public class ControladorTelaPrincipal implements Initializable{


    @FXML
    private TableView<Fruta> tabelaBuffer;
    
    @FXML
    private TableView<Fruta> tabelaConsumido;
    
    @FXML
    private TableColumn<Fruta, String> colunaProdutoBuffer;

    @FXML
    private TableColumn<Fruta, String> colunaProdutoConsumido;

    @FXML
    private ImageView imagemProdutor;

    @FXML
    private ImageView imagemConsumidor;

    @FXML
    private Slider sliderProdutor;

    @FXML
    private Slider sliderConsumidor;

    private ObservableList<Fruta> observableListFrutasBuffer;
    private ObservableList<Fruta> observableListFrutasConsumidas;
    private FrutasProduzidas frutasProduzidas;
    Produtor produtor;
    Consumidor consumidor;

    /*******************************************************************
    * nome........:initialize
    * funcao......:construtor da classe, inicia assim que for compilado
    * Parametros..:URL,ResourceBundle
    ******************************************************************/

    /*******************************************************************
    * nome........:
    * funcao......:
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        frutasProduzidas = new FrutasProduzidas();//classe para controlar a lista das frutas produzidas e consumidas
        Buffer buffer = new Buffer(frutasProduzidas);//a classe frutasProduzidas sera passada para o buffer assim podera serr adicionada e removida
        consumidor = new Consumidor(buffer,this);//o consumidor passara a classe buffer e esta classe, ControladorTelaPrincipal assim podera compartilhar os recursos
        produtor = new Produtor(buffer,this);//o mesmo do consumidor
        /*a classe bot serve para atualizar as tableView dentro do javaFx porem descobri outra forma de faze
        como o trabalho ja esta pronto nao quis mexer */
        BOTatualizarTabela bot = new BOTatualizarTabela();
        //iniciando todos as Threads
        consumidor.start();
        produtor.start();
        bot.start();
        carregarTableViewsBuffer();
        carregarTableViewConsumidor();

    }
    /*******************************************************************
    * nome........:CarregarTableViewsBuffer
    * funcao......:construir a table view da interface JavaFx, so precisa ser executada uma vez
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/
    public  void carregarTableViewsBuffer(){//carregando a tableViewBuffer
        colunaProdutoBuffer.setCellValueFactory(new PropertyValueFactory<>("nomeFruta"));
        atualizarTableViewBuffer();
        //atualizarTableViewBuffer();
    }


    /*******************************************************************
    * nome........:CarregarTableViewConsumidor
    * funcao......:construir a tableView do javaFx do consumidor, executada apenas uma vez
    * Parametros..:nao ha parametros
    * retorno.....:nao ha retorno
    ******************************************************************/
    public void carregarTableViewConsumidor(){//carregando a tableViewConsumidor
        colunaProdutoConsumido.setCellValueFactory(new PropertyValueFactory<>("nomeFruta"));
        atualizarTableViewFrutasConsumidor();
    }

    /*poderia colocar o carregar tableView dentro de um unico metodo agora que fui parar pra perceber */

    /*******************************************************************
    * nome........:atualizarTableViewBuffer
    * funcao......:atualizar sempre que for chamada a tableView do buffer
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/

    public void atualizarTableViewBuffer() {//metodo para atualizar 
        observableListFrutasBuffer = FXCollections.observableArrayList(frutasProduzidas.getFrutas());
        tabelaBuffer.setItems(observableListFrutasBuffer);
    }

    /*******************************************************************
    * nome........:atualizarTableViewFrutasConsumidor
    * funcao......:atualizar sempre que for chamada todas as frutas consumidas pelor consumidor
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/
    public void atualizarTableViewFrutasConsumidor() {
        observableListFrutasConsumidas = FXCollections.observableArrayList(frutasProduzidas.getFrutasRemovidas());
        tabelaConsumido.setItems(observableListFrutasConsumidas);
    }

    /*******************************************************************
    * nome........:getVelocidadeProdutor
    * funcao......:retornar a velocidade do sliderProdutor atual 
    * Parametros..:nao tem parametros
    * retorno.....:inteiro
    ******************************************************************/

    public int getVelocidadeProdutor(){
        return (int)sliderProdutor.getValue();
    }
    /*******************************************************************
    * nome........:getVelocidadeConsumidor
    * funcao......:retornar a velocidade do sliderConsumidor atual
    * Parametros..:nao tem parametros
    * retorno.....:inteiro
    ******************************************************************/

    public int getVelocidadeConsumidor(){
        return (int)sliderConsumidor.getValue();
    }


    /*******************************************************************
    * nome........:BOTatualizarTabel
    * funcao......:um Thread que fica sempre em execucao para atualizar a tabela automatico
    porem nao era necessario pois so precisava chamar no Buffer o metodo sempre que algum item fosse adicionado
    decidi manter pois funciona perfeitamente
    * Parametros..:nao tem parametros
    * retorno.....:nao ha retorno
    ******************************************************************/
    public class BOTatualizarTabela extends Thread {
        public void run(){
            while(true){
                try {
                    atualizarTableViewFrutasConsumidor();
                    atualizarTableViewBuffer();
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}