package controller;

public class Informacao {
    int valores[];
    public Informacao() {
        valores = new int[3];
    }

    public Informacao(int valor1,int valor2,int valor3){
        valores = new int[3];
        valores[0] = valor1;
        valores[1] = valor2;
        valores[2] = valor3;
    }

    public void salvarPosicoesXYiVetor(int vetor[]){
        //valores = new int[vetor.length];
        this.valores[0] = vetor[0];
        this.valores[1] = vetor[1];
        this.valores[2] = vetor[2];
        
    }

    public void salvarPosicaoX(int X){
        valores[0] = X;
    }

    public void salvarPosicaoY(int Y){
        valores[1] = Y;
    }

    public void salvarPosicaoCadeira(int i){
        valores[2] = i;
    }

    public int getPosicaoX(){
        return valores[0];
    }

    public int getPosicaoY(){
        return valores[1];
    }

    public int getPosicaoCadeira(){
        return valores[2];
    }
    
}