package dados;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import models.Aluno;

public class Dados implements Serializable{

    File arquivo = new File("alunosDados.txt");

    public void salvarAlunos(ArrayList<Aluno> novosAlunos) throws IOException, ClassNotFoundException{
        ArrayList<Aluno> alunos = new ArrayList<>();
        FileOutputStream fluxo = new FileOutputStream(arquivo);
        ObjectOutputStream gravarArquivo = new ObjectOutputStream(fluxo);
        if (arquivo.exists()){
            alunos = listarAlunos();
        }
        alunos.addAll(novosAlunos);
        gravarArquivo.writeObject(alunos);
        fluxo.close();
        gravarArquivo.close();

    }

    public void removerAluno(Aluno alunoRemover) throws IOException, ClassNotFoundException {
        ArrayList<Aluno> alunos = new ArrayList<>();
        
        if(arquivo.exists()){
            alunos = listarAlunos();
        }

        alunos.remove(alunoRemover);
        FileOutputStream fluxo = new FileOutputStream(arquivo);
        ObjectOutputStream gravarArquivo = new ObjectOutputStream(fluxo);
        gravarArquivo.writeObject(alunos);
        fluxo.close();
        gravarArquivo.close();


    }

    public ArrayList<Aluno> listarAlunos() throws IOException, ClassNotFoundException {
        
        FileInputStream fluxo = new FileInputStream(arquivo);
        ObjectInputStream lerArquivo = new ObjectInputStream(fluxo);


        ArrayList<Aluno> alunos = (ArrayList<Aluno>) lerArquivo.readObject();

        fluxo.close();
        lerArquivo.close();
        return alunos;
    }
}