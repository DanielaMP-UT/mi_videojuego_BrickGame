/**
 * Representa un proyectil (bala) disparado por la nave del jugador.
 * La bala se mueve de forma vertical hacia arriba en la pantalla. 
 * Esta clase almacena su posición actual y gestiona su propio movimiento 
 * y su detección de límites.
 * 
 * @author DanielaMP
 * @version 1.0
 */
public class Bala {
    /** Coordenada X actual de la bala en la pantalla. */
    public int posicionX;
    /** Coordenada Y actual de la bala en la pantalla. */
    public int posicionY;
    /** Velocidad de desplazamiento vertical de la bala (píxeles por ciclo). */
    public final int VELOCIDAD = 10; 
    /**
     * Constructor de la clase Bala.
     * Crea una nueva bala en la posición especificada.
     * 
     * @param posicionX La coordenada X inicial donde aparecerá la bala.
     * @param posicionY La coordenada Y inicial donde aparecerá la bala.
     */
    public Bala(int posicionX, int posicionY) {
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
    /**
     * Actualiza la posición de la bala, moviéndola hacia arriba.
     * La distancia recorrida está determinada por el atributo {@link #VELOCIDAD}.
     */
    public void mover() {
        this.posicionY -= VELOCIDAD;
    }
    /**
     * Verifica si la bala ha salido del área de juego visible por la parte superior.
     * El área de juego comienza en la coordenada Y = 80 (debajo del HUD), por lo que
     * si la bala sube más allá de ese punto, se considera fuera de límites.
     * 
     * @return {@code true} si la bala está fuera del área de juego (Y menor a 80), 
     *         {@code false} en caso contrario.
     */
    // Verifica si sobrepasó el límite superior 
    public boolean fueraDeLimites() {
        return this.posicionY < 80;
    }
}

