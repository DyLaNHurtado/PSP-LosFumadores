package es.dylanhurtado.LosFumadores;

public class Agente extends Thread{

    private Mesa mesa;

    public Agente(Mesa mesa){
        this.mesa = mesa;
    }

    public void run(){

        while (true){
            mesa.ponerLaMesa();
        }
    }
}
