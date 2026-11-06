/**
 * Representa una nave dentro del videojuego.
 * Esta clase es utilizada tanto para la nave del jugador como para las naves enemigas.
 * Almacena su posición en el eje X e Y, su alias, y contiene la lógica de movimiento
 * para desplazarse por la pantalla respetando los límites del área de juego.
 * 
 * @author DanielaMP
 * @version 1.0
 */
public class Nave {

    /** Coordenada X actual de la nave en el área de juego. */
    public int posicionX; 

    /** Coordenada Y actual de la nave en el área de juego. */
    public int posicionY;

    /** Nombre o alias identificador de la nave. */
    public String alias;

    /** Ancho total de la nave en píxeles (3 columnas x 10px). */
    private final int ANCHO_NAVE = 30; 

    /** Límite derecho del área de juego (ancho del Canvas). */
    private final int LIMITE_DERECHO = 600;

    /**
     * Constructor de la clase Nave.
     * Inicializa una nave con un alias y una posición inicial específica.
     * 
     * @param alias El nombre o identificador de la nave.
     * @param posicionX La coordenada X inicial de la nave.
     * @param posicionY La coordenada Y inicial de la nave.
     */
    // El constructor recibe la posición inicial (no está fija aquí)
    public Nave(String alias, int posicionX, int posicionY) {
        this.alias = alias;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }
    /**
     * Obtiene la coordenada X actual de la nave.
     * 
     * @return La posición X actual.
     */
    public int getPosicionX(){
        return posicionX;
    }
    /**
     * Obtiene la coordenada Y actual de la nave.
     * 
     * @return La posición Y actual.
     */
    public int getPosicionY(){
        return posicionY;
    }
    /**
     * Obtiene el alias de la nave.
     * 
     * @return El alias de la nave.
     */
    public String getAlias(){
        return alias;
    }
    /**
     * Obtiene el nombre del modelo de la nave.
     * 
     * @return Una cadena de texto con el nombre del modelo.
     */
    public String getModelo() {
        return "DAMPSKILL-AERO D-12";
    }
   /**
     * Imprime en la consola el alias y la posición actual de la nave (X e Y).
     */
    // Imprime la posición en consola
    public void MostrarPosicion() {
        System.out.println("Nave '" + alias + "' -> Posición X: " + posicionX + " | Posición Y: " + posicionY);
    }
  /**
     * Desplaza la nave hacia la izquierda.
     * 
     * El movimiento se realiza en pasos de 15 píxeles y solo se efectúa si 
     * la nave no choca con el borde izquierdo del área de juego (X = 0).
     * Si no puede avanzar, muestra una alerta en la consola.
     */
    public void DesplazarIzquierda() {
        if (posicionX - 15 >= 0) {
            posicionX -= 15;
            System.out.println("[CONSOLA] Moviendo a la IZQUIERDA -> Nueva X: " + posicionX);
        } else {
            System.out.println("[ALERTA] Límite izquierdo alcanzado.");
        }
    }
     /** Desplaza la nave hacia la derecha.
      
     * El movimiento se realiza en pasos de 15 píxeles y solo se efectúa si 
     * la nave no sobrepasa el límite derecho del área de juego, considerando
     * el ancho total de la nave.
     * Si no puede avanzar, muestra una alerta en la consola.
     */
    public void DesplazarDerecha() {
        if (posicionX + 15 + ANCHO_NAVE <= LIMITE_DERECHO) {
            posicionX += 15;
            System.out.println("[CONSOLA] Moviendo a la DERECHA -> Nueva X: " + posicionX);
        } else {
            System.out.println("[ALERTA] Límite derecho alcanzado.");
        }
    }
    //----------------------------------METODO PARA EL DESPLAZAMIENTO DE NAVES ENEMIGAS----------------------------
    /**
     * Desplaza la nave hacia abajo (utilizado principalmente para naves enemigas).

     * El movimiento se realiza en pasos de 2 píxeles. Se verifica que la parte 
     * inferior de la nave no sobrepase el límite inferior del Canvas (Y = 600).
     * Si alcanza el límite, muestra una alerta en la consola.
     */
    public void DesplazarAbajo() {
        int altoNave = 40; 
        int limiteInferiorCanvas = 600; 
        
        if (this.posicionY + 2 + altoNave <= limiteInferiorCanvas) {
            this.posicionY += 2; 
            System.out.println("[CONSOLA] Nave '" + alias + "' avanzó a Y: " + this.posicionY);
        } else {
            System.out.println("[ALERTA] Nave '" + alias + "' llegó al límite inferior.");
        }
    }
}


