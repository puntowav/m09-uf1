
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic{
    private static final char[] ABC = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    public char[] permutaAlfabet(char[] alfabet){
        List<Character> abc = Arrays.asList(charArrayToCharacterArray(alfabet));
        Collections.shuffle(abc);
        return CharacterListToCharArray(abc);        
    }

    private Character[] charArrayToCharacterArray (char[] alfabet){
        Character[] characterArray = new Character[alfabet.length];  
        for(int i = 0; i < alfabet.length; i++){
            characterArray[i] = alfabet[i]; 
        }
        return characterArray; 
    }

    private char[] CharacterListToCharArray (List<Character> list){
        char[] array = new char[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }

    public String xifraMonoAlfa(String cadena){
        StringBuilder strB = new StringBuilder();
        char[] abcPermutat = permutaAlfabet(cadena.toCharArray());
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLowerCase(cadena.charAt(i))){
                strB.append(Character.toLowerCase(abcPermutat[i]));
            }
        }
        return strB.toString();
    }

    public static void main(String[] args) {
        Monoalfabetic mono = new Monoalfabetic();
        System.out.println(mono.permutaAlfabet(ABC));   
    }   
}