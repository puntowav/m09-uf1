/**
 * Aquest programa fara la ecryptacio de un text en sistema Rot13
 */
public class Rot13 {
    private static final String abc = "A,Á,À,B,C,Ç,D,E,É,È,F,G,H,I,Í,Ï,J,K,L,M,N,Ñ,O,Ó,Ò,P,Q,R,S,T,U,Ú,Ü,V,W,X,Y,Z";
    
    String[] abcMinus = abc.toLowerCase().split(",");
    String[] abcUpper = abc.toUpperCase().split(",");

    public String xifraRot13(String cadena){
        String xifrat = "";
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                if(Character.isUpperCase(cadena.charAt(i))){
                    for(int x = 0; x < abcUpper.length; x++){
                        if(abcUpper[x].equals(Character.toString(cadena.charAt(i)))){
                            xifrat = xifrat + abcUpper[(x + 13)%abcUpper.length];
                        }
                    }
                }else{
                    for(int y = 0; y < abcMinus.length; y++){
                        if(abcMinus[y].equals(Character.toString(cadena.charAt(i)))){
                            xifrat = xifrat + abcMinus[(y + 13)%abcMinus.length];
                        }
                    }
                }
            }
        }
        return xifrat;
    }

    public String desxifraRot13(String cadena){
        String desxifrat = "";
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                if(Character.isUpperCase(cadena.charAt(i))){
                    for(int x = 0; x < abcUpper.length; x++){
                        if(abcUpper[x].equals(Character.toString(cadena.charAt(i)))){
                            desxifrat = desxifrat + abcUpper[(x - 13)%abcUpper.length];
                        }
                    }
                }else{
                    for(int y = 0; y < abcMinus.length; y++){
                        if(abcMinus[y].equals(Character.toString(cadena.charAt(i)))){
                            desxifrat = desxifrat + abcMinus[(y - 13)%abcMinus.length];
                        }
                    }
                }
            }
        }
        return desxifrat;
    }
}
