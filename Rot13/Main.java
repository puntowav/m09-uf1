/**
 * Aquest es el main en el que es testa el programa
*/
import java.io.IOException;

public class Main{
    public static void main(String[] args) throws IOException{
        Rot13 rot13 = new Rot13();

        System.out.println("Hola");
        System.out.println(rot13.xifraRot13("MARCO"));
        System.out.println("pxúi");
        System.out.println(rot13.desxifraRot13("ÜIÀKX"));    
        
        /* BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Vols xifrar o desxifrar? [x/d]");

        String cadena = reader.readLine();
        if (cadena.toLowerCase().equals("x")){
            System.out.println("Quin missatge vols xifrar?");
            cadena = reader.readLine();
            System.out.println(rot13.xifraRot13(cadena));
        }else if(cadena.toLowerCase().equals("d")){
            System.out.println("Quin missatge vols desxifrar?");
            cadena = reader.readLine();
            System.out.println(rot13.desxifraRot13(cadena));
        }
        reader.close();*/
    }
}