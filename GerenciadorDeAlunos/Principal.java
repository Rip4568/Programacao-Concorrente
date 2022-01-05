/********************************************************************
* Autor: JONATHAS DAVID SANTOS DO NASCIMENTO
* Inicio: 30/07/2021 10:31:47
* Ultima alteracao: data da ultima alteração realizada no código
* Nome: Nome do programa
* Funcao: Descrição do programa
********************************************************************/

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


import controller.ControleGerenciadorDeAlunos; // necessario para compilar
import dados.Dados;

public class Principal extends Application { // A classe principal deve ser subclasse de Application
  public static void main(String[] args) {
    // LEMBREM-SE DE SEMPRE COMENTAR TUDO!!
    // Professor marlos aprova

    launch(args); // Para iniciar o programa é necessário chamar a função launch da classe Application
  } // fim da função main
  
  @Override
  public void start(Stage primaryStage) throws IOException {
    /***********************************************************
    * Metodo start: recebe um Stage (dado pelo próprio javafx)
    * Parametros:
    *     primaryStage=Stage dado pelo próprio JavaFX
    * Retorno:
    *     void
    * Erro:
    *     Como ao carregar o arquivo FXML pode retornar o erro IOException, é necessário adicionar Throw IOException
    ************************************************************/

    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/GerenciadorDeAlunos.fxml")); // Cria loader do FXML
    Parent root = loader.load(); // carrega o arquivo fxml
    
    
    Scene scene = new Scene(root); // parametro principal da Scene
    primaryStage.setTitle("Calcular media");
    primaryStage.setScene(scene); //parametro da scene
    primaryStage.show();// mostra o programa
  }// fim da funcao
}