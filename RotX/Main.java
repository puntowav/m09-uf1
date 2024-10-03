/**
 * Aquest es el main en el que es testa el programa
*/
import java.io.IOException;
import java.util.Random;

public class Main{
    public static void main(String[] args) throws IOException{
        RotX rotX = new RotX();
        Random random = new Random();
        int x = random.nextInt(0, 37);
        String[] arrayTest = {"Hola", "això", "és", "un TEST", "del", "Xifra ROTx ÁÀ ü", "33", "-_?"};

        for(String s: arrayTest){
            System.out.println("Original: " + s);
            System.out.println("Xifrat: " + rotX.xifraRotX(s, x));
            System.out.println("Desxifrat: " + rotX.desxifraRotX(rotX.xifraRotX(s, x).toString(), x));
            System.out.println("");
        }

        System.out.println("Original: Hola això és un TEST del Xifra ROTx ÁÀ ü 33 -_?");
        System.out.println("Xifrat a Rot15: Rzvï ïshá óç ex DOÇD ñov Hspcï CZDh JK è 33 -_?");
        for(String s: rotX.forcaBrutaX("Rzvï ïshá óç ex DOÇD ñov Hspcï CZDh JK è 33 -_?")){
            System.out.println("Força bruta: " + s);
        }
    }
}