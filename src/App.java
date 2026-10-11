import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class App extends Frame {

    private Nave jugador;

    // Matriz con los colores dela nave
    private String[][] spritePersonaje = {
        {"#FF66A3", "TRANSPARENTE", "#FF66A3"}, // Fila 0
        {"#000000", "#DDA0DD",      "#000000"}, // Fila 1
        {"TRANSPARENTE", "#000000", "TRANSPARENTE"}, // Fila 2
        {"#FF007F", "#555555",      "#FF007F"}, // Fila 3
        {"#222222", "TRANSPARENTE", "#222222"}, // Fila 4
        {"#FF5500", "TRANSPARENTE", "#FF5500"}  // Fila 5
    };

    public App() {
        setTitle("Brick Game - Striker Aero");
        setSize(800, 600);
        setLayout(null);
        setResizable(false);
        setVisible(true);

        // Posición inicial dentro del área de juego
        jugador = new Nave("La Pinta", 285, 480);

        // DESPLAZAMIENTO EN CONSOLA ---
        System.out.println("--- POSICIÓN INICIAL ---");
        jugador.MostrarPosicion();

        System.out.println("\n--- PRUEBA DE DESPLAZAMIENTOS ---");
        jugador.DesplazarIzquierda();
        jugador.DesplazarDerecha();
        jugador.DesplazarDerecha();

        // Cerrar ventana
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // 1. BARRA SUPERIOR (HUD)
        g.setColor(Color.decode("#bff885")); 
        g.fillRect(0, 0, 800, 80);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("PUNTOS: 0000", 30, 48);
        g.drawString("VIDAS: ***", 650, 48);

        // 2. ÁREA DE JUEGO (Gris)
        g.setColor(Color.decode("#615f5f")); 
        g.fillRect(0, 80, 600, 520);

        /*  Obstáculos
        g.setColor(Color.MAGENTA);
        g.fillRect(100, 110, 30, 30);
        g.fillRect(300, 130, 30, 30);
        g.fillRect(450, 100, 30, 30);*/

        // DIBUJO DE LA NAVE
        int pixelSize = 10; 
        for (int fila = 0; fila < spritePersonaje.length; fila++) {
            for (int col = 0; col < spritePersonaje[fila].length; col++) {
                String colorHex = spritePersonaje[fila][col];
                
                if (!colorHex.equals("TRANSPARENTE")) {
                    g.setColor(Color.decode(colorHex));
                    g.fillRect(
                        jugador.posicionX + (col * pixelSize), 
                        jugador.posicionY + (fila * pixelSize), 
                        pixelSize, 
                        pixelSize
                    );
                }
            }
        }

        // 3. PANEL LATERAL
        g.setColor(Color.decode("#ebe3c5")); 
        g.fillRect(600, 80, 200, 520);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("ALIAS: " + jugador.alias, 610, 120);
        g.setFont(new Font("Arial", Font.PLAIN, 11));
        g.drawString("MODELO: " + jugador.GetModelo(), 610, 145);
        
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("NIVEL: 1", 610, 180);
        
        g.drawString("CONTROLES:", 610, 240);
        g.drawString("Disparar: [Esp]", 610, 270);
        g.drawString("Mover: <- / ->", 610, 300);
        g.drawString("Pausar: [P]", 610, 330);
    }

    public static void main(String[] args) {
        new App();
    }
}
