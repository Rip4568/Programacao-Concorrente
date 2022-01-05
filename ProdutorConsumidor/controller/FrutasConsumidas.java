package controller;

import java.util.ArrayList;

import model.Fruta;

public class FrutasConsumidas {
    
    private static ArrayList<Fruta> frutas = new ArrayList<>();
    
    public FrutasConsumidas(){
    }

    public void adicionarFruta(Fruta fruta){
        frutas.add(fruta);
    }

    public void removerFruta(Fruta fruta){
        frutas.remove(fruta);
    }

    public ArrayList<Fruta> getFrutas(){
        return frutas;
    }
    
}
