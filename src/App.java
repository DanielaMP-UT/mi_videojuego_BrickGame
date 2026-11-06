import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import javax.swing.Timer;

/**
 * Clase principal del videojuego Brick Game - Striker Aero.
 * 
 * Esta clase extiende de {@link Frame} para crear la ventana principal del juego
 * e implementa {@link KeyListener} para capturar los eventos del teclado.
 * Se encarga de gestionar el bucle de juego, el renderizado (con doble búfer),
 * la lógica de las naves y las balas.
 *
 * @author DanielaMP
 * @version 1.0
 */
public class App extends Frame implements KeyListener {
    /** Posición inicial en el eje X de la nave del jugador */ 
    public static final int NAVE_POSICION_INICIAL_X = 285;

    /** Posición inicial en el eje Y de la nave del jugador */
    public static final int NAVE_POSICION_INICIAL_Y = 480;

    /** Imagen utilizada como búfer para dibujar la pantalla */
    private Image bufferImagen;

    /** Contexto gráfico asociado al búfer de imagen */
    private Graphics bufferGraphics;

    /** Imagen de fondo del espacio.*/
    private Image fondoEspacio = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/espacio.jpg"));
    
    /**
     * Contador utilizado para controlar la frecuencia de actualización
     * del movimiento de las balas y los enemigos.
     */
    private int acumulador = 0;
    /** Objeto que representa la nave del jugador */
    private Nave jugador;

    /** Objeto que representa la nave enemiga principal */
    private Nave enemigo;

     /**Lista que almacena las balas disparadas por el jugador */
    private ArrayList<Bala> balas = new ArrayList<>();
//-------------------------------------------------------------------------------------------------------------------
    /**
     * Matriz de colores que define la apariencia de la nave del jugador.
     * Cada posición representa un píxel de la figura.
     * Cada celda contiene un código de color hexadecimal
     * El valor TRANSPARENTE indica que no se debe dibujar ese píxel.
     */
    private String[][] spritePersonaje = {
        {"#FF66A3", "TRANSPARENTE", "#FF66A3"}, 
        {"#000000", "#DDA0DD",      "#000000"}, 
        {"TRANSPARENTE", "#000000", "TRANSPARENTE"}, 
        {"#FF007F", "#555555",      "#FF007F"}, 
        {"#222222", "TRANSPARENTE", "#222222"}, 
        {"#FF5500", "TRANSPARENTE", "#FF5500"}  
    };
    /**
     * Matriz de colores que define la apariencia de las neves enemigas.
     * Cada posición representa un píxel de la figura.
     * Cada celda contiene un código de color hexadecimal
     * El valor TRANSPARENTE indica que no se debe dibujar ese píxel.
     */
    private String[][] spriteEnemigo = {
        {"TRANSPARENTE", "#FF0000", "TRANSPARENTE"}, 
        {"#000000",      "#555555", "#000000"},     
        {"#FF0000",      "#000000", "#FF0000"},     
        {"#000000",      "TRANSPARENTE", "#000000"}  
    };
//-------------------------------------------------------------------------------------------------------------------
    /**
     * Construye la ventana principal del videojuego e inicializa
     * los elementos necesarios para su funcionamiento.
     * Configura el tamaño de la ventana, crea las naves, registra
     * los eventos del teclado e inicia el temporizador del juego.
     */
    public App() {
        //QUÉ HACE CADA UNO----------------------------------------------------------------------------
        //QUÉ HACE CADA UNO----------------------------------------------------------------------------
        setTitle("Brick Game - Striker Aero");
        setSize(800, 600);//
        setLayout(null);//
        setResizable(false);//
        setVisible(true);//

         // Inicializa las naves dentro del área del juego.
        jugador = new Nave("La Pinta", NAVE_POSICION_INICIAL_X, NAVE_POSICION_INICIAL_Y);
        enemigo = new Nave("Enemigo", 280, 100);

        // Registra los eventos del teclado.
        addKeyListener(this);

        // Inicia el temporizador que actualiza el juego cada 16 milisegundos.
        Timer timer = new Timer(16, e -> actualizarJuego());
        timer.start();
        //-------------------------------------------------------------------------------------------------------------------
        // Permitir cerrar la ventana desde la X
        addWindowListener(new WindowAdapter() {

            /**
             * Finaliza la aplicación cuando se cierra la ventana.
             *
             * @param e evento generado al cerrar la ventana
             */ 
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
//----------------------------------------------BALAS-----------------------------------------------------
    /**
     * Actualiza el estado del juego, controla el movimiento de las balas
     * y desplaza al enemigo de acuerdo con el contador de actualización.
     * Finalmente, solicita que la pantalla se vuelva a dibujar.
     */
private void actualizarJuego() {
    acumulador++;

    // Actualiza el movimiento de las balas
    if (acumulador % 2 == 0) {
        for (int i = 0; i < balas.size(); i++) {
            Bala b = balas.get(i);
            b.mover();

    // Elimina las balas que salen del área permitida 
            if (b.fueraDeLimites()) {
                balas.remove(i);
                i--;
            }
        }
    }
    // Desplaza al enemigo cada quince actualizaciones del contador.
    if (acumulador % 15 == 0) {
        if (enemigo != null) {
            enemigo.DesplazarAbajo();
        }
    }
    // Solicita la actualización visual de la ventana.
    repaint();
}
//------------------------------------------------EVENTOS DEL TECLADO----------------------------------------
    /**
     * Procesa las teclas presionadas por el usuario.
     * Permite mover la nave hacia la izquierda o la derecha
     * y disparar balas mediante la barra espaciadora.
     *
     * @param e evento que contiene la información de la tecla presionada
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int tecla = e.getKeyCode();

        // Mueve la nave hacia la izquierda con la flecha o la tecla A.
        if (tecla == KeyEvent.VK_LEFT || tecla == KeyEvent.VK_A) {
            jugador.DesplazarIzquierda();
            repaint(); 
        }
        // Mueve la nave hacia la derecha con la flecha o la tecla D.
        if (tecla == KeyEvent.VK_RIGHT || tecla == KeyEvent.VK_D) {
            jugador.DesplazarDerecha();
            repaint(); 
        }
        // Crea una nueva bala cuando se presiona la barra espaciadora.
        if (tecla == KeyEvent.VK_SPACE) {

            // Se calcula el centro de la nave para la bala (10px a la derecha del origen)
            int balaX = jugador.getPosicionX() + 10;
            int balaY = jugador.getPosicionY();
            
            balas.add(new Bala(balaX, balaY));

            // Muestra las coordenadas de la bala en la consola.
            System.out.println("[DISPARO] Bala creada en X: " + balaX + " | Y: " + balaY);
        }
    }
//-------------------------------------------------------------------------------------------------------------------
    /**
     * Atiende el evento de escritura de un carácter en el teclado.
     * Actualmente no realiza ninguna acción.
     *
     * @param e evento generado al escribir un carácter
     */
    @Override
    public void keyTyped(KeyEvent e) {}
    /**
     * Atiende el evento de liberación de una tecla.
     * Actualmente no realiza ninguna acción.
     *
     * @param e evento generado al soltar una tecla
     */
    @Override
    public void keyReleased(KeyEvent e) {}
//-------------------------------------------------------------------------------------------------------------------
    /**
     * Actualiza el contenido gráfico de la ventana utilizando
     * una imagen intermedia para reducir el parpadeo durante el dibujo.
     *
     * @param g contexto gráfico en el que se presenta la imagen final
     */
   @Override
    public void update(Graphics g) {
        // Crea el búfer gráfico si todavía no existe.
        if (bufferImagen == null) {
            bufferImagen = createImage(getWidth(), getHeight());
            bufferGraphics = bufferImagen.getGraphics();
        }

        // Limpia el contenido anterior del búfer.
        bufferGraphics.setColor(getBackground());
        bufferGraphics.fillRect(0, 0, getWidth(), getHeight());

        // Dibuja el contenido actualizado en el búfer.
        paint(bufferGraphics);

        // Presenta el búfer en la ventana.
        g.drawImage(bufferImagen, 0, 0, this);
    }
//-----------------------------------------RENDERING Y DIBUJO DE LA INTERFAZ-----------------------------------
    /**
     * Dibuja los elementos visuales del videojuego, incluyendo
     * la barra superior, el área de juego, las balas, la nave
     * del jugador, los enemigos y el panel lateral de información.
     *
     * @param g contexto gráfico utilizado para dibujar los elementos
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 1. BARRA SUPERIOR 
        g.setColor(Color.decode("#b6f776")); 
        g.fillRect(0, 0, 800, 80);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("PUNTOS: 0000", 30, 48);
        g.drawString("VIDAS: ***", 650, 48);

        // 2. ÁREA DE JUEGO 
        g.setColor(Color.decode("#080808"));
        g.fillRect(0, 80, 600, 520); 
        g.drawImage(fondoEspacio, 0, 80, 600, 520, this);

        // 3.DIBUJO DE BALAS ACTIVAS
        g.setColor(Color.YELLOW);
        for (Bala b : balas) {
            g.fillRect(b.posicionX, b.posicionY, 4, 10); 
        }
//-----------------------------------------DIBUJO DE LA NAVE-----------------------------------------------
        // Dibuja la nave del jugador a partir de su matriz de colores.
        int pixelSize = 10; 
        for (int fila = 0; fila < spritePersonaje.length; fila++) {
            for (int col = 0; col < spritePersonaje[fila].length; col++) {
                String colorHex = spritePersonaje[fila][col];
                
                // Omite los píxeles definidos como transparentes.
                if (!colorHex.equals("TRANSPARENTE")) {
                    g.setColor(Color.decode(colorHex));
                    g.fillRect(
                        jugador.getPosicionX() + (col * pixelSize), 
                        jugador.getPosicionY() + (fila * pixelSize), 
                        pixelSize, 
                        pixelSize
                    );
                }
            }
        }
//--------------------------------------------DIBUJO DE LOS ENEMIGOS-------------------------------------
        // Define las coordenadas de los enemigos que se dibujarán.
        int[][] posicionesEnemigos = {
            {100, 110},
            {280, 130},
            {450, 100}
        };
        // Establece el tamaño de cada píxel de los enemigos.
        int pixelSizeEnemigo = 10; 

        // Recorre las posiciones para dibujar cada enemigo.
        for (int[] pos : posicionesEnemigos) {
            int enemigoX = pos[0];
            int enemigoY = pos[1];

            for (int fila = 0; fila < spriteEnemigo.length; fila++) {
                for (int col = 0; col < spriteEnemigo[fila].length; col++) {
                    String colorHex = spriteEnemigo[fila][col];

                     // Dibuja únicamente los píxeles que no son transparentes
                    if (!colorHex.equals("TRANSPARENTE")) {
                        g.setColor(Color.decode(colorHex));
                        g.fillRect(
                            enemigoX + (col * pixelSizeEnemigo), 
                            enemigoY + (fila * pixelSizeEnemigo), 
                            pixelSizeEnemigo, 
                            pixelSizeEnemigo
                        );
                    }
                }
            }
        }
//----------------------------------PANEL LATERAL DE INFORMACIÓN-----------------------------------------------
        // Dibuja el panel lateral con los datos del jugador y los controles.
        g.setColor(Color.decode("#f0da84")); 
        g.fillRect(600, 80, 200, 520);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("ALIAS: " + jugador.getAlias(), 610, 120);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("MODELO: " + jugador.getModelo(), 610, 145);
        
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("NIVEL: 1", 610, 180);
        
        g.drawString("CONTROLES:", 610, 240);
        g.drawString("Disparar: [Esp]", 610, 270);
        g.drawString("Mover: <- / ->", 610, 300);
        g.drawString("Pausar: [P]", 610, 330);
    }
    /**
     * Método principal que inicia la ejecución del videojuego
     * creando una instancia de la ventana principal.
     *
     * @param args argumentos recibidos desde la línea de comandos
     */
    public static void main(String[] args) {
        new App();
    }
}

    