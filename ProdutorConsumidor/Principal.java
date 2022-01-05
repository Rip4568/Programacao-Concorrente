/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor....: Jonathas David Santos do Nascimento
Matrícula: 201912179
Inicio...: 10 de Setembro de 2021
Alteracao: 15 de agosto de 2021
Nome.....: Problema do produtor e consumidor
Funcao...: Exemplo de uso de duas Thrads acessando o mesmo local com semaforos
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/

import controller.ControladorTelaPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{//Classe Principal
    public static void main(String[] args) {//Classe Main chama o metodo star ao launcar os args (argumentos)
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {//inicia o javaFX 
        FXMLLoader fxmlLoaderTelaPrincipal = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
        Parent root = fxmlLoaderTelaPrincipal.load();
        Scene tela = new Scene(root);
        primaryStage.setTitle("Produtor e Consumidor");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}