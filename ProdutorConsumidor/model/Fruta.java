package model;

public class Fruta {
    String nomeFruta;
    public Fruta (String nomeFruta){
        this.nomeFruta = nomeFruta;
    }

    public void setNomeFruta(String novoNomeFruta){
        nomeFruta = novoNomeFruta;
    }

    public String getNomeFruta(){
        return this.nomeFruta;
    }
}
