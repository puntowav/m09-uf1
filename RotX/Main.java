/**
 * Aquest es el main en el que es testa el programa
*/
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        RotX rotX = new RotX();

        /* System.out.println("Hola");
        System.out.println(rotX.xifraRotX("MARCO", 15));
        System.out.println("pxúi");
        System.out.println(rotX.desxifraRotX("ÜIÀKX", 15));   */
        rotX.forcaBrutaX("Pxúi");
    }
}