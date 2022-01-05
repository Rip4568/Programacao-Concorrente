/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor....: Jonathas David Santos do Nascimento
Matrícula: 201912179
Inicio...: 17 de Setembro de 2021
Alteracao: 24 de Setembro de 2021
Nome.....: Problema do "Jantar dos Filosofos"
Funcao...: Exemplo de uso de N Threads acessando uma regiao critica
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.ControladorTelaPrincipal;

public class Principal extends Application {

    /**
     * Contrutor padrao quando se lida com JavaFX, implementando o Application
     * no metodo main fazemos um launc(args) para iniciar o metodo start
     * start() inicia o javaFX
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoaderTelaPrincipal = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
        Parent root = fxmlLoaderTelaPrincipal.load();
        Scene tela = new Scene(root);
        primaryStage.setTitle("Programa Teste Jantar dos Filosofos");
        primaryStage.setScene(tela);
        primaryStage.show();
        
    }
}