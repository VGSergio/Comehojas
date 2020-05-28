/**
 * Juego de la hormiga comehojas.
 * Una hormiga se desplaza a traves de una array bidimensional llena de hojas,
 * a medida que la hormiga se va comiendo las hojas se va vaciando el tablero.
 * Al no quedar hojas finaliza el juego.
 */
package comehojas;
/**
 * @author SVG
 */
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;

public class Comehojas extends JFrame implements KeyListener {

    private static Tablero tablero;
    private final int MAX = Tablero.TAMAÑO;
    private int hojas = MAX*MAX-1;    //Numero de hojas en el tablero - 1 (hormiga)
    
    private int i, j, in, jn;         //Para trabajar con las coordenadas
    private String p;                 //Para guardar direccion hormiga
    
    /**
     * Constructor del juego, pone el titulo a la ventana, inicializa las 
     * componentes, coloca la ventana en el centro de la pantalla y
     * habilita poder cerrar la ventana
     */
    public Comehojas() {    
        super("Juego de la hormiga come hojas");                //Nombre programa
        tablero = new Tablero();                                //Conatructor del tablero de juego  
        addKeyListener(this);                                   //Listener de teclas de direccion
        this.getContentPane().add(tablero);                     //Añadimos el tablero a la ventana
        this.setSize(tablero.getPreferredSize());               //Establecemos tamaño ventana 
        this.pack();                                            
        this.setResizable(false);                               //Tamaño ventana invariable
        this.setLocationRelativeTo(null);                       //Ventana en centro de pantalla
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //Salir del programa al puslar X

        //Inicializacion movimiento de la hormiga, necesario por si directamente pulsan espacio
        p = Pieza.HOR_E;
        i= Hormiga.y;   //Obtenemos la fila donde se encuentra la hormiga 
        j= Hormiga.x;   //Obtenemos la columna donde se encuentra la hormiga                
        in = i;
        jn = j;
        //Establecemos proxima posicion segun su posicion actual y direccion
        if (j==MAX-1)   //Limite Right
            jn = 0;
        else
            jn++;
    }
    
    /**
     * Inicia el juego y lo hace visible
     * @param args
     */
    public static void main(String[] args) {
        Comehojas juego = new Comehojas();
        juego.setVisible(true);
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();       //Para saber que flecha se ha pulsado

        //FLECHA ARRIBA
        if (key == KeyEvent.VK_UP && hojas > 0){
            p = Pieza.HOR_N;//Establecemos imagen a poner
            i= Hormiga.y;   //Obtenemos la fila donde se encuentra la hormiga 
            j= Hormiga.x;   //Obtenemos la columna donde se encuentra la hormiga                
            in = i;         
            jn = j;         
            tablero.repaint(tablero.getRectangle(i, j));                  
            tablero.Coloca(p, i,j);                         //Actualizamos direccion hormiga  
            //Establecemos proxima posicion segun su posicion actual y direccion
            if (in==0)      //Limite Up
                in = MAX-1;
            else
                in--;
        }
        
        //FLECHA ABAJO
        if (key == KeyEvent.VK_DOWN && hojas > 0){
            p = Pieza.HOR_S;//Establecemos imagen a poner
            i= Hormiga.y;   //Obtenemos la fila donde se encuentra la hormiga 
            j= Hormiga.x;   //Obtenemos la columna donde se encuentra la hormiga                
            in = i;
            jn = j;
            tablero.repaint(tablero.getRectangle(i, j));
            tablero.Coloca(p, i,j);                         //Actualizamos direccion hormiga 
            //Establecemos proxima posicion segun su posicion actual y direccion
            if (i==MAX-1)   //Limite Down
                in = 0;
            else 
                in++;
        }
        
        //FLECHA IZQUIERDA
        if (key == KeyEvent.VK_LEFT && hojas > 0){
            p = Pieza.HOR_O;//Establecemos imagen a poner
            i= Hormiga.y;   //Obtenemos la fila donde se encuentra la hormiga 
            j= Hormiga.x;   //Obtenemos la columna donde se encuentra la hormiga                
            in = i;
            jn = j;
            tablero.repaint(tablero.getRectangle(i, j));
            tablero.Coloca(p, i,j);                         //Actualizamos direccion hormiga 
            //Establecemos proxima posicion segun su posicion actual y direccion
            if (j==0)       //Limite Left
                jn = MAX-1;
            else 
                jn--;
        }
        
        //FLECHA DERECHA
        if (key == KeyEvent.VK_RIGHT && hojas > 0) {
            p = Pieza.HOR_E;//Establecemos imagen a poner
            i= Hormiga.y;   //Obtenemos la fila donde se encuentra la hormiga 
            j= Hormiga.x;   //Obtenemos la columna donde se encuentra la hormiga                
            in = i;         
            jn = j;         
            tablero.repaint(tablero.getRectangle(i, j));
            tablero.Coloca(p, i,j);                         //Actualizamos direccion hormiga 
            //Establecemos proxima posicion segun su posicion actual y direccion
            if (j==MAX-1)   //Limite Right      
                jn = 0;
            else 
                jn++;
        }
        
        //ESPACIO
        if (key == KeyEvent.VK_SPACE && hojas > 0){
            if (tablero.isOcupada(in, jn)) {        //Si la posicion siguiente esta ocupada
                tablero.vacio(i,j);                 //La vaciamos
                hojas--;                            //Y bajamos el contador de hojas pendientes
            }
            tablero.repaint(tablero.getRectangle(in, jn));
            tablero.Coloca(p, in, jn);              //Colocamos la hormiga en su nueva posicion
            Hormiga.x = jn;                         //Actualizamos las coordenadas de la hormiga
            Hormiga.y = in;                         //Actualizamos las coordenadas de la hormiga
            tablero.repaint(tablero.getRectangle(i, j));
            tablero.Coloca(Pieza.NADA, i,j);        //Colocamos la imagen vacia en la posicion que ha abandonado la hormiga
            
            //Guardamos la nueva posicion de la hormiga
            i = in;
            j = jn;
            
            if (null != p) //Calculamos su proxima posicion si no cambian de direccion
            switch (p) {
                case Pieza.HOR_N:
                    //Arriba
                    if (in==0)                  //Limite
                        in = MAX-1;
                    else
                        in--;
                    break;
                case Pieza.HOR_S:
                    //Abajo
                    if (i==MAX-1)               //Limite
                        in = 0;
                    else
                        in++;
                    break;
                case Pieza.HOR_O:
                    //Izquierda
                    if (j==0)                   //Limite
                        jn = MAX-1;
                    else
                        jn--;
                    break;
                case Pieza.HOR_E:
                    //Derecha
                    if (j==MAX-1)               //Limite
                        jn = 0;
                    else
                        jn++;
                    break;
                default:
                    break;
            } 
        }
        
        //HOJAS == 0 -> VICTORIA
        if (0 == hojas) {
                Toolkit.getDefaultToolkit().beep();
                String mensaje = "                        ENHORABUENA\n"
                        + "             ¡¡¡has completado el juego!!!\n"
                        + "LA HORMIGA SE HA COMIDO TODAS LAS HOJAS";
                JOptionPane.showMessageDialog(null, mensaje,
                        "Mensaje", JOptionPane.INFORMATION_MESSAGE);
            }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }
    
}
