import controller.ControladorTelaPrincipal;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Principal extends Application{
    public static void main(String[] args) {
        launch(args);   
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoaderTelaPrincipal = new FXMLLoader(getClass().getResource("/view/TelaPrincipal.fxml"));
        Parent root = fxmlLoaderTelaPrincipal.load();
        Scene tela = new Scene(root);

        
        primaryStage.setTitle("Leitor e Escritor");
        primaryStage.setScene(tela);
        primaryStage.show();
    }
}