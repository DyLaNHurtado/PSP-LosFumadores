package es.dylanhurtado.LosFumadores;

public class Fumador extends Thread{
    private String nombre;
    private Mesa mesa;
    private int ingrediente;

    public Fumador(String nombre,int ingrediente,Mesa mesa){
        this.ingrediente = ingrediente;
        this.mesa = mesa;
        this.nombre = nombre;
    }

    public void run(){

        while (!mesa.mesaVacia()){
            mesa.cogerIngredientes(nombre,ingrediente);
        }

    }
}
