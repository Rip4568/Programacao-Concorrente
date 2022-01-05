package controller;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import dados.Dados;
import models.Aluno;

public class ControleAluno implements Serializable{
    
    ArrayList<Aluno> alunos = new ArrayList<>();

    Dados dados = new Dados();

    public void cadastrarNovoAluno(String matricula){
        alunos.add(new Aluno(matricula));
        try{
            dados.salvarAlunos(alunos);
        } catch(IOException | ClassNotFoundException e){
            System.out.println("Erro ao cadastrarNovoAluno;ControleAluno");
        }
    }

    public void cadastrarNovoAluno(Aluno aluno){
        alunos.add(aluno);
        try{
            dados.salvarAlunos(alunos);//cadastrando o novo aluno no arrayList desta classe e no arquivo
        } catch(IOException | ClassNotFoundException e){
            System.out.println("Erro ao cadastrarNovoAluno;ControleAluno");
        }
    }

    public void removerAluno(Aluno aluno){
        alunos.remove(aluno);
        try{
            dados.removerAluno(aluno); //Remover o aluno tanto dessa classe quanto dentro do arquivo
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Erro ao remover aluno ; ControleAluno");
        }
        
    }

    public void removerAlunoMatricula(String matricula){
        for(Aluno i: alunos){
            if(i.getMatricula().equals(matricula)){
                alunos.remove(i);
                try{
                    dados.removerAluno(i);//Remover o aluno tanto dessa classe quanto dentro do arquivo
                }catch(IOException | ClassNotFoundException e){
                    System.out.println("Erro ao remover aluno;removerAlunoMatricula");
                }
                break;
            }
        }
    }

    public ArrayList<Aluno> listarAlunos() {
        try{
            return dados.listarAlunos();//Tentar listar os alunos salvo no arquivo
        }catch(ClassNotFoundException | IOException e){
            e.printStackTrace();
            System.out.println("erro ao listar alunos;ControleAluno");
        }
        return alunos; // caso apresente erro listar os alunos dessa classe
    }   
}