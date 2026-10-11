public class Nave {
    public String alias;
    public int posicionX;
    public int posicionY;

    // El constructor recibe la posición inicial (no está fija aquí)
    public Nave(String alias, int posicionX, int posicionY) {
        this.alias = alias;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
    }

    public String GetModelo() {
        return "DAMPSKILL-AERO D-12";
    }

    // Imprime la posición en consola
    public void MostrarPosicion() {
        System.out.println("Nave '" + alias + "' -> Posición X: " + posicionX + " | Posición Y: " + posicionY);
    }

    // Cambia el número X e imprime en consola (Requerimiento del profesor)
    public void DesplazarIzquierda() {
        this.posicionX -= 15;
        System.out.println("[CONSOLA] Moviendo a la IZQUIERDA -> Nueva X: " + this.posicionX);
    }

    public void DesplazarDerecha() {
        this.posicionX += 15;
        System.out.println("[CONSOLA] Moviendo a la DERECHA -> Nueva X: " + this.posicionX);
    }
}



/*public class Nave {
    private String modelo;    
    private String alias;       
    private int anchoPixeles;   
    private int altoPixeles;    
    private int vidas;          
    public int posicionX;      
    public int posicionY; 
    public int tamanoArenaX;
    public int tamanoArenaY;
     

public Nave(String alias, int posicionInicialX, int posicionInicialY) {
        this.modelo = "DAMPSKILL-AERO D-12"; 
        this.alias = alias;
        this.anchoPixeles = 3;              
        this.altoPixeles = 5;
        this.vidas = 3;                     
        this.posicionX = posicionInicialX;
        this.posicionY = posicionInicialY;
        this.tamanoArenaX= 200;
        this.tamanoArenaY= 600;

    }

//nuevo nombre
public void SetModelo(String modelo){
this.modelo=modelo;
}

//retornar el valor actual del modelo
public String GetModelo(){
return this.modelo;
}

public void ImprimirInformacion(){
     System.out.println("Datos de la nave: \n Modelo: " + this.modelo + "\n Alias: " +this.alias);
}

public void DesplazarDerecha(){
    if (this.posicionX < this.tamanoArenaX - this.anchoPixeles) {
        this.posicionX += this.anchoPixeles;
    }

}

public void DesplazarIzquierda(){
    if (this.posicionX > this.anchoPixeles) {
        this.posicionX -= this.anchoPixeles;
    }

}

public void MostrarPosicion(){
     System.out.println("Posición X: " + this.posicionX + "\n Posicion Y: " +this.posicionY);
}

}*/



