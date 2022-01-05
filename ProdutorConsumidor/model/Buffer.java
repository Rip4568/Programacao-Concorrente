package model;

import java.util.concurrent.Semaphore;

import controller.FrutasProduzidas;

public class Buffer {
    private final int BUFFER_SIZE = 20;
    private Semaphore cheio;
    private Semaphore vazio;
    private Semaphore mutex;
    private int in,out,contador;
    private Object[] buffer;
    private FrutasProduzidas frutasProduzidas;

    public Buffer(FrutasProduzidas frutasProduzidas){
        this.frutasProduzidas = frutasProduzidas;
        this.cheio = new Semaphore(0);//espaços ocupados
        this.vazio = new Semaphore(BUFFER_SIZE);//(N), espacos vazios disponiveis
        this.mutex = new Semaphore(1);//1 pode entrar na RC, 0 nao pode
        buffer = new Object[BUFFER_SIZE];//(N), criar uma variavel global como tamanho!
        in = 0; out = 0;contador =0;
    }

    public void produzir(Object item){
        try {
            vazio.acquire();//decrementa os espaços vazios 
            mutex.acquire();//alterar o valor para 0, informado que vai entrar na região
            
            ++contador;
            buffer[in] = item;//o vetor buffer[posicao in] vai receber o item produzido
            in = (in + 1) % BUFFER_SIZE;
            //System.out.println(" item produzido :" + ((Fruta)item).getNomeFruta() + " contador : " + contador);
            frutasProduzidas.adicionarFruta((Fruta)item);
            //ControladorTelaPrincipal.atualizarTableViewBuffer();
            

            mutex.release();//altera para o valor 1 saindo da região
            cheio.release();//incrementa os espaços cheios ocupados
        } catch (InterruptedException e) {
            System.out.println("BUFFER: erro ao produzir");
        }
    }
    Object item;
    public Object consumir(){//consumir os itens gerados pela classe Produtor
        try {
            cheio.acquire();//decrementa os espaços cheios
            mutex.acquire();//alterar o mutex para 0 informando que ira entrar na RC
            
            contador--;
            item = buffer[out];//Consumindo o primeiro item que esta do topo da lista, funcionamento tipo FILA (First in First ou)
            out = (out + 1) % BUFFER_SIZE;
            //System.out.println(" item consumido : " + ((Fruta)item).getNomeFruta() + " contador : " + contador);
            frutasProduzidas.removerFruta((Fruta)item);//removendo a fruta da lista, assim podera atualizar a TableView
            frutasProduzidas.adicionarFrutaRemovida((Fruta)item);//adicionar TODAS as frutas removidas para listar
            

            mutex.release();//alterar para o valor 1 informando que saiu da RC
            vazio.release();//incrementa as posições vazias disponiveis
            //contador_posicoes_vazias++;
            return item;//retornanado item
        } catch (InterruptedException e) {
            System.out.println("BUFFER: Erro ao consumir");
        }
        return item;//retornando item removido, caso precisar
        
    }

    public int getBUFFER_SIZE(){
        return BUFFER_SIZE;
    }
}