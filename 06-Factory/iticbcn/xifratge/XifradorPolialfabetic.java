package iticbcn.xifratge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class XifradorPolialfabetic implements Xifrador {

    private static final char[] ABC = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();
    private static char[] abcPermutat;

    private long CLAU;
    private Random random;

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

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            this.CLAU = Long.parseLong(clau);
            this.random = initRandom(CLAU);

            return new TextXifrat(xifraPoliAlfabet(msg).getBytes());
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }

    public String xifraPoliAlfabet(String msg) {
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

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            this.CLAU = Long.parseLong(clau);
            this.random = initRandom(CLAU);

            return desxifraPoliAlfabet(xifrat.toString());
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }

    public String desxifraPoliAlfabet(String msgXifrat) {
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

    private Random initRandom(long clau) {
        return random = new Random(clau);
    }
}
