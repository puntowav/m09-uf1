
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Polialfabetic {

    private static final char[] ABC = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    private static long clau = 998701;
    private static Random random;
    private static char[] abcPermutat;

    public void permutaAlfabet(char[] alfabet) {
        List<Character> abc = Arrays.asList(charArrayToCharacterArray(alfabet));
        Collections.shuffle(abc, random);
        abcPermutat = CharacterListToCharArray(abc);
    }

    private Character[] charArrayToCharacterArray(char[] alfabet) {
        Character[] characterArray = new Character[alfabet.length];
        for (int i = 0; i < alfabet.length; i++) {
            characterArray[i] = alfabet[i];
        }
        return characterArray;
    }

    private char[] CharacterListToCharArray(List<Character> list) {
        char[] array = new char[list.size()];
        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    public String xifraPoliAlfabet(String msg) {
        initRandom();
        StringBuilder msgXifrat = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            permutaAlfabet(ABC);
            if (Character.isLetter(msg.charAt(i))) {
                for (int x = 0; x < ABC.length; x++) {
                    if (Character.isLowerCase(msg.charAt(i))) {
                        if (msg.charAt(i) == Character.toLowerCase(ABC[x])) {
                            msgXifrat.append(Character.toLowerCase(abcPermutat[x]));
                        }
                    } else {
                        if (msg.charAt(i) == ABC[x]) {
                            msgXifrat.append(abcPermutat[x]);
                        }
                    }
                }
            } else {
                msgXifrat.append(msg.charAt(i));
            }

        }

        return msgXifrat.toString();
    }

    public String desxifraPoliAlfabet(String msgXifrat) {
        initRandom();
        StringBuilder msgDesxifrat = new StringBuilder();

        for (int i = 0; i < msgXifrat.length(); i++) {
            permutaAlfabet(ABC);
            if (Character.isLetter(msgXifrat.charAt(i))) {
                for (int x = 0; x < abcPermutat.length; x++) {
                    if (Character.isLowerCase(msgXifrat.charAt(i))) {
                        if (msgXifrat.charAt(i) == Character.toLowerCase(abcPermutat[x])) {
                            msgDesxifrat.append(Character.toLowerCase(ABC[x]));
                        }
                    } else {
                        if (msgXifrat.charAt(i) == abcPermutat[x]) {
                            msgDesxifrat.append(ABC[x]);
                        }
                    }
                }
            } else {
                msgDesxifrat.append(msgXifrat.charAt(i));
            }
        }
        return msgDesxifrat.toString();
    }

    private static void initRandom() {
        random = new Random(clau);
    }

    public static void main(String[] args) {

        Polialfabetic p = new Polialfabetic();

        String[] test = {"HOLA QUE TAL", "hola que tal", "33 á -?_: Mira quin TEST", "Més temps NO és millor, millor es millor i més temps tan sols es més estona 4:31 Ezequiel", "üUç 910 -;, Més test DEl poliAlfabetic -- áÑñ à Klja"};
        for (String s : test) {
            String xifrat = p.xifraPoliAlfabet(s);

            System.out.println(xifrat);
            System.out.println(p.desxifraPoliAlfabet(xifrat));
        }
    }
}
