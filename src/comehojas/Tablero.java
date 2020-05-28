/*
 * Clase tablero definida como un panel donde se define una tabla de TAMAÑOxTAMAÑO casillas.
 *
 * El constructor define todas las casillas como rectangulos con hojas 
 * en su interior
 *
 * El metodo paintComponent pinta las casillas del tablero
 *
 * El metodo getPreferredSize devuelve el tamaño del tablero
 */
package comehojas;

/**
 *
 * @author SVG
 */
import javax.swing.*;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.util.Random;

public final class Tablero extends JPanel {
    
    public static final int TAMAÑO = 20;                //Tablero de 20x20, modificable a cualquier tamaño
    
    private static final int TMIMG = 40;                //Imagenes de 40x40 pixeles
    private static final int MAXIMO = TAMAÑO*TMIMG;             
    private static final int LADO = MAXIMO/TAMAÑO;    
    
    private final Casilla t[][];
    private Pieza p;
    private final Hormiga h = new Hormiga();
    
    /**
    *Rellena el tablero con hojas.
    *Una vez rellenado coloca la hormiga en una posicion aleatoria. 
    */
    public Tablero() {
        t = new Casilla[TAMAÑO][TAMAÑO];
        int y = 0;
        for (int i = 0; i < TAMAÑO; i++) {
            int x = 0;
            for (int j = 0; j < TAMAÑO; j++) {
                Rectangle2D.Float r =
                        new Rectangle2D.Float(x, y, LADO, LADO);
                p = new Pieza(Pieza.HOJA);
                t[i][j] = new Casilla(r, p, true);
                x += LADO;
            }
            y += LADO;
        }
        //Una vez rellenado el tablero con hojas colocamos la hormiga aleatoriamente
        Random rand = new Random();         
        int a = rand.nextInt(TAMAÑO);       //Columna aleatoria
        int b = rand.nextInt(TAMAÑO);       //Fila aleatoria
        Coloca(Pieza.HOR_E,b,a);           //Colocamos hormiga
        h.setHormiga(a, b);                 //Guardamos posicion de la hormiga
    }
    
    @Override
    public void paintComponent(Graphics g) {
        for (int i = 0; i < TAMAÑO; i++) {
            for (int j = 0; j < TAMAÑO; j++) {                
                t[i][j].paintComponent(g);
            }
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(MAXIMO, MAXIMO);
    }
    
    /**
     *Metodo para colocar una pieza especifica en una posicion especifica
     */
    void Coloca(String s, int i, int j) {
        t[i][j].setPieza(new Pieza(s));
    }
    
    /**
     *Metodo que nos devuelve si la posicion esta ocupada
     */
    boolean isOcupada(int i, int j) {
        return t[i][j].GetOcupada();
    }
    
    /**
     *Metodo para establecer como NO ocupada una casilla
     */
    void vacio(int i, int j) {
        t[i][j].setOcupada(false);
    }

    Rectangle getRectangle(int i, int j) {
        return t[i][j].getRec().getBounds();
    }
}
