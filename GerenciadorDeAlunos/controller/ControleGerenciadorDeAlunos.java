package controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Aluno;

public class ControleGerenciadorDeAlunos implements Initializable{

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtNota3;

    @FXML
    private TextField txtNota2;

    @FXML
    private TextField txtNota1;

    @FXML
    private Button btnAdicionar;
    
    @FXML
    private TableColumn<Aluno, Double> colunaNota1;

    @FXML
    private TableColumn<Aluno, Double> colunaNota2;

    @FXML
    private TableColumn<Aluno, Double> colunaNota3;

    @FXML
    private TableColumn<Aluno, String> colunaMatricula;

    @FXML
    private TableColumn<Aluno, Double> colunaMedia;

    @FXML
    private TableView<Aluno> tabelaPrincipal;

    @FXML
    private Button btnRemover;

    ControleAluno controleAluno = new ControleAluno();

    private ObservableList<Aluno> observableListAluno;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        carregarTableViewAluno();
    }

    public void carregarTableViewAluno(){
        colunaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));//Nomeando cada coluna
        colunaNota1.setCellValueFactory(new PropertyValueFactory<>("nota1"));
        colunaNota2.setCellValueFactory(new PropertyValueFactory<>("nota2"));
        colunaNota3.setCellValueFactory(new PropertyValueFactory<>("nota3"));
        colunaMedia.setCellValueFactory(new PropertyValueFactory<>("media"));



        atualizarTableViewAluno();//chamar essa função ao realizar qualquer acao
    }

    public void atualizarTableViewAluno(){
        observableListAluno = FXCollections.observableArrayList(controleAluno.listarAlunos());
        tabelaPrincipal.setItems(observableListAluno);
    }

    @FXML
    void remover(ActionEvent event) {//Remover um item selecionado
        Aluno alunoRemover = tabelaPrincipal.getSelectionModel().getSelectedItem();
        controleAluno.removerAluno(alunoRemover);
        tabelaPrincipal.getItems().remove(alunoRemover);
    }

    @FXML
    void adicionar(ActionEvent event) {//Adicionar novoAluno na lista
        Aluno novoAluno = new Aluno(txtMatricula.getText());
        novoAluno.setNota1(Double.parseDouble(txtNota1.getText()));
        novoAluno.setNota2(Double.parseDouble(txtNota2.getText()));
        novoAluno.setNota3(Double.parseDouble(txtNota3.getText()));
        novoAluno.calcularMedia();
        controleAluno.cadastrarNovoAluno(novoAluno);
        atualizarTableViewAluno();
    }
}