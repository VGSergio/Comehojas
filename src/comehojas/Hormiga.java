/*
 * Clase Hormiga, nos permite saber y establecer las coordenadas de la hormiga
 */
package comehojas;

/**
 *
 * @author SVG
 */
public class Hormiga {
    
    public static int x;
    public static int y;
    
    /**
     * Constructor hormiga, inicialmente [0][0]
     * 
     */
    public void Hormiga () {
        Hormiga.x = 0;
        Hormiga.y = 0;
    }
    
    /**
     * Metodo para guardar coordenadas de la hormiga
     * @param a
     * @param b
     */
    public void setHormiga (int a, int b) {
        Hormiga.x = a;
        Hormiga.y = b;
    }
}
