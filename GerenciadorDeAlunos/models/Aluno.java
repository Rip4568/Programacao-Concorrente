package models;

import java.io.Serializable;

public class Aluno implements Serializable{
    String matricula;
    Double nota1 = (double) 0,nota2 = (double) 0,nota3 = (double) 0, media;
    
    public Aluno(String matricula){
        this.matricula = matricula;
        calcularMedia();
    }

    public void calcularMedia(){
        this.media = (nota1 + nota2 + nota3) / 3;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public Double getNota1() {
        return nota1;
    }

    public void setNota1(Double nota1) {
        this.nota1 = nota1;
        calcularMedia();
    }

    public Double getNota2() {
        return nota2;
    }

    public void setNota2(Double nota2) {
        this.nota2 = nota2;
        calcularMedia();
    }

    public Double getNota3() {
        return nota3;
    }

    public void setNota3(Double nota3) {
        this.nota3 = nota3;
        calcularMedia();
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

}
