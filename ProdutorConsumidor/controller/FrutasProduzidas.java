package controller;

import java.util.ArrayList;

import model.Fruta;

public class FrutasProduzidas {

    private static ArrayList<Fruta> frutas = new ArrayList<>();

    public static ArrayList<Fruta> frutasRemovidas = new ArrayList<>();

    public FrutasProduzidas(){
    }

    public void adicionarFrutaRemovida(Fruta fruta){
        frutasRemovidas.add(fruta);
    }

    public ArrayList<Fruta> getFrutasRemovidas(){
        return frutasRemovidas;
    }

    public  void adicionarFruta(Fruta fruta){
        frutas.add(fruta);
    }

    public  void removerFruta(Fruta fruta){
        frutas.remove(fruta);
    }

    public ArrayList<Fruta> getFrutas(){
        return frutas;
    }
    
}
