/*
 * Clase Pieza que define las piezas para jugar al come hojas. Las piezas son
 * imagenes que representan las piezas.
 *
 * El constructor lee el fichero con la imagen
 *
 * El metodo paintComponent pinta la imagen en la posicion indicada
 */
package comehojas;

/**
 *
 * @author SVG
 */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pieza {
    
    public static final String HOJA = "Piezas/hoja.png";
    public static final String NADA = "Piezas/nada.png";
    public static final String HOR_E = "Piezas/hormiga_e.png";
    public static final String HOR_N = "Piezas/hormiga_n.png";
    public static final String HOR_O = "Piezas/hormiga_o.png";
    public static final String HOR_S = "Piezas/hormiga_s.png";

    private BufferedImage img;

    /**
     * Constructor pieza
     * @param s
     */
    public Pieza(String s) {
        try {
            img = ImageIO.read(new File(s));
        } catch (IOException e) {
        }
    }

    void paintComponent(Graphics g, float x, float y) {
        g.drawImage(img,(int) x+1, (int) y+1, null);
    }
}
