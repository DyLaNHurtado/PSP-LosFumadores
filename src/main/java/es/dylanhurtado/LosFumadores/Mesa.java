package es.dylanhurtado.LosFumadores;

import java.util.Random;

public class Mesa {
    private final int TIEMPOFUMAR = 1000;
    private final int CANTIDADFUMADORES;
    private Boolean mesaVacia;
    private int ingrediente01, ingrediente02;

    public Mesa(int cantidadfumadores) {
        CANTIDADFUMADORES = cantidadfumadores;
        mesaVacia= true;
    }


    public boolean mesaVacia() {
        return !mesaVacia;
    }

    /**
     * Pone en la mesa los dos ingredientes posibles de forma aleatoria
     */
    public synchronized void ponerLaMesa(){
        while (!mesaVacia){
            try{
                wait();
            }
            catch (InterruptedException e){
                System.err.println ("Error en poner (Mesa): "+ e.toString());
            }
        }

        ingrediente01 = new Random().nextInt(CANTIDADFUMADORES);
        ingrediente02 = new Random().nextInt(CANTIDADFUMADORES);

        while(ingrediente01 == ingrediente02){
            ingrediente01 = new Random().nextInt(CANTIDADFUMADORES);
        }

        System.out.println ("El Estanquero ha dejado los ingredientes " + ingrediente01 + " y " + ingrediente02);
        mesaVacia = false;

        notifyAll();
    }

    /**
     * Método para que un fumador obtenga los ingredientes para poder fumar
     * @param nombrefumador Nombre del fumador que quiere los ingredientes
     * @param ingredientefumador Ingrediente que posee el fumador
     */
    public synchronized void cogerIngredientes(String nombrefumador, int ingredientefumador) {
        while((mesaVacia) || (ingredientefumador == ingrediente01) || (ingredientefumador == ingrediente02)) {
            try{
                wait();
            }
            catch (InterruptedException e){
                System.err.println ("Error en coger (Mesa): "+ e.toString());
            }
        }

        System.out.println ("Fumando el fumador: " + nombrefumador + ", que tiene el ingrediente: "+ ingredientefumador);
        try{
            Thread.sleep(TIEMPOFUMAR);
        }
        catch (Exception e){
            System.err.println ("Error 02 en coger (Mesa): "+ e.toString());
        }

        mesaVacia = Boolean.TRUE;
        notifyAll();
    }
}
