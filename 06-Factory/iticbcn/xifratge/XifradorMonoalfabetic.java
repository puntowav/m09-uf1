package iticbcn.xifratge;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class XifradorMonoalfabetic implements Xifrador {
    private final char[] ABC = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();
    private char[] abcPermutat;

    public void permutaAlfabet(char[] alfabet) {
        List<Character> abc = Arrays.asList(charArrayToCharacterArray(alfabet));
        Collections.shuffle(abc);
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
        if (clau == null)
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        permutaAlfabet(ABC);
        return new TextXifrat(xifraMonoAlfa(msg).getBytes());
    }

    public String xifraMonoAlfa(String cadena) {
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                for (int x = 0; x < this.ABC.length; x++) {
                    if (Character.isLowerCase(cadena.charAt(i))) {
                        if (cadena.charAt(i) == Character.toLowerCase(ABC[x]))
                            c.append(Character.toLowerCase(abcPermutat[x]));
                    } else {
                        if (cadena.charAt(i) == ABC[x])
                            c.append(abcPermutat[x]);
                    }
                }
            } else
                c.append(cadena.charAt(i));
        }
        return c.toString();
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        if (clau == null)
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        return desxifraMonoAlfa(xifrat.toString());
    }

    public String desxifraMonoAlfa(String cadena) {
        StringBuilder c = new StringBuilder();
        for (int i = 0; i < cadena.length(); i++) {
            if (Character.isLetter(cadena.charAt(i))) {
                for (int x = 0; x < abcPermutat.length; x++) {
                    if (Character.isLowerCase(cadena.charAt(i))) {
                        if (cadena.charAt(i) == Character.toLowerCase(abcPermutat[x]))
                            c.append(Character.toLowerCase(ABC[x]));
                    } else {
                        if (cadena.charAt(i) == abcPermutat[x])
                            c.append(ABC[x]);
                    }
                }
            } else
                c.append(cadena.charAt(i));
        }
        return c.toString();
    }

}