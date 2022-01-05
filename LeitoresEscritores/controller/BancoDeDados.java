package controller;

import java.util.concurrent.Semaphore;

public class BancoDeDados {
    private Semaphore db;
    private int quantidade_leitores;

    public BancoDeDados(){
        this.db = new Semaphore(1);
        quantidade_leitores = 0;
    }

    public void acessarBancoDeDados(String classe,int id){
        try {
            db.acquire();
            System.out.println(classe + " ID:" + id + " acessou o banco de daddos e travou, Leitores" + getQuantidadeLeitores());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }    

    }

    public void liberarBancoDeDados(String classe, int id){
        db.release();
        System.out.println(classe + " ID:" + id + " liberou o acesso ao banco de dados, leitores:" + getQuantidadeLeitores());
        
    }

    public void incrementarQuantidadeLeitores(){
        quantidade_leitores = quantidade_leitores + 1;
    }

    public void decrementarQuantidadeLeitores(){
        quantidade_leitores = quantidade_leitores - 1;
    }
    
    public int getQuantidadeLeitores(){
        return quantidade_leitores;
    }
}
