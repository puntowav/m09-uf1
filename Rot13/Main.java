/**
 * Aquest es el main en el que es testa el programa
*/
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main{
    public static void main(String[] args) throws IOException{
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Vols xifrar o desxifrar? [x/d]");
        Rot13 xifra = new Rot13();

        String cadena = reader.readLine();
        if (cadena.toLowerCase().equals("x")){
            System.out.println("Quin missatge vols xifrar?");
            cadena = reader.readLine();
            System.out.println(xifra.xifraRot13(cadena));
        }else if(cadena.toLowerCase().equals("d")){
            System.out.println("Quin missatge vols desxifrar?");
            cadena = reader.readLine();
            System.out.println(xifra.desxifraRot13(cadena));
        }
        reader.close();
    }
}