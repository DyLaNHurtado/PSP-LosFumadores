package es.dylanhurtado.LosFumadores;

public class Main {

    final static int CANTIDADFUMADORES = 3;
    static Mesa mesa;
    static Thread estanquero;
    static Thread[] fumadores;

    public static void main(String[] args) {
        mesa = new Mesa(CANTIDADFUMADORES);
        fumadores = new Thread[CANTIDADFUMADORES];

        // Creo los fumadores
        for (int i = 0; i < CANTIDADFUMADORES; i++) {
            String nombrefumador = "Fumador " + i;
            fumadores[i] = new Thread(new Fumador(nombrefumador, i, mesa));
        }

        // Inicio los fumadores
        for (int i = 0; i < CANTIDADFUMADORES; i++) {
            fumadores[i].start();
        }

        // Creo e inicio al estanquero
        estanquero = new Thread(new Agente(mesa));
        estanquero.start();
    }
}
