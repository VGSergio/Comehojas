/*
 * Clase casilla
 *
 * El constructor define una casilla como un rectangulo con una imagen e indica 
 * si esta ocupada   
 *
 * El metodo paintComponent pinta el rectangulo de la casilla
 */

package comehojas;

/**
 *
 * @author SVG
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

class Casilla {
    
    private final Rectangle2D.Float rec;
    private boolean ocupada;
    private Pieza pieza;
    
    /**
    * Constructor casilla
    * @param r
    * @param p
    * @param ocu
    */
    public Casilla(Rectangle2D.Float r, Pieza p, Boolean ocu) {
        this.rec = r;
        this.pieza = p;
        this.ocupada = ocu;
    }

    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.fill(this.rec);
        this.pieza.paintComponent(g, this.rec.x, this.rec.y);
    }
    
    /**
    * Cambia la pieza por la indicada
    * Establece ocupada a falso 
    * Lo usaremos para colocar NADA y la hormiga así que no es necesario 
    * que ocupada sea true
    * 
    * @param s
    */
    void setPieza(Pieza s) {
        this.pieza = s;
        this.ocupada = false;
    }
    
    public Rectangle2D.Float getRec() {
        return rec;
    }
    
    /**
    * Devuelve si la posicion esta ocupada
    */
    public boolean GetOcupada () {
        return ocupada;
    }
    
    /**
    * Establece ocupada
    * @param b
    */
    void setOcupada(boolean b) {
        ocupada = b;
    }
    
}
