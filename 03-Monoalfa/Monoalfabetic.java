
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Monoalfabetic{
    private static final char[] ABC = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();
    private char[] abcPermutat;
    
    public void permutaAlfabet(char[] alfabet){
        List<Character> abc = Arrays.asList(charArrayToCharacterArray(alfabet));
        Collections.shuffle(abc);
        abcPermutat = CharacterListToCharArray(abc);        
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
        StringBuilder c = new StringBuilder();
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                for(int x = 0; x < Monoalfabetic.ABC.length; x++){
                    if(Character.isLowerCase(cadena.charAt(i))){
                        if(cadena.charAt(i) ==  Character.toLowerCase(ABC[x])) c.append(Character.toLowerCase(abcPermutat[x]));
                    }else{
                        if(cadena.charAt(i) == ABC[x]) c.append(abcPermutat[x]);   
                    }
                }
            }else c.append(cadena.charAt(i));
        }
        return c.toString();
    }

    public String desxifraMonoAlfa(String cadena){
        StringBuilder c = new StringBuilder();
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                for(int x = 0; x < abcPermutat.length; x++){
                    if(Character.isLowerCase(cadena.charAt(i))){
                        if(cadena.charAt(i) ==  Character.toLowerCase(abcPermutat[x])) c.append(Character.toLowerCase(ABC[x]));
                    }else{
                        if(cadena.charAt(i) == abcPermutat[x]) c.append(ABC[x]);   
                    }
                }
            }else c.append(cadena.charAt(i));
        }
        return c.toString();
    }


    public static void main(String[] args) {
        Monoalfabetic mono = new Monoalfabetic();
        mono.permutaAlfabet(ABC);

        String[] test = {"HOLA QUE TAL", "hola que tal", "33 á -?_: Mira quin TEST", "Més temps NO és millor, millor es millor i més temps tan sols es més estona 4:31 Ezequiel"};
        for(String s : test){
            System.out.println("String original: " + s);
            System.out.println("String xifrada: "+ mono.xifraMonoAlfa(s));
            System.out.println("String desxifrada: "+mono.desxifraMonoAlfa(mono.xifraMonoAlfa(s)));
            System.out.println("");
        }

    }   
}