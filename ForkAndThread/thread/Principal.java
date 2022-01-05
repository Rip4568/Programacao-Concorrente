/*=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=
Autor....: Jonathas David Santos do Nascimento
Matrícula: 201912179
Inicio...: 18 de agosto de 2021
Alteracao: 19 de agosto de 2021
Nome.....: Principal.java
Funcao...: Exemplo do uso do comando Thread em java
=-=-=--=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=*/


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import controller.ControladorTelaPrincipal;
public class Principal extends Application{
    public static void main(String[] args) {
        launch(args);  
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loaderTelaPrincipal = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
        Parent rootTelaPrincipal = loaderTelaPrincipal.load();
        Scene tela = new Scene(rootTelaPrincipal);


        primaryStage.setTitle("Arvores Genetica");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}