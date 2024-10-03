/**
 * Aquest programa fara la ecryptacio de un text en sistema Rot13
 */
public class RotX {
    private static final char[] ABCLOWER = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toLowerCase().toCharArray();
    private static final char[] ABCUPPER = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    public StringBuilder xifraRotX(String cadena, int desplacament){
        StringBuilder xifrat = new StringBuilder();
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                if(Character.isUpperCase(cadena.charAt(i))){
                    for(int x = 0; x < ABCUPPER.length; x++){
                        if(ABCUPPER[x] == cadena.charAt(i)){
                            xifrat.append(ABCUPPER[(x + desplacament)%ABCUPPER.length]);
                        }
                    }
                }else{
                    for(int y = 0; y < ABCLOWER.length; y++){
                        if(ABCLOWER[y] == cadena.charAt(i)){
                            xifrat.append(ABCLOWER[(y + desplacament)%ABCLOWER.length]);
                        }
                    }
                }
            }else xifrat.append(cadena.charAt(i));
        }
        return xifrat;
    }

    public StringBuilder desxifraRotX(String cadena, int desplacament){
        StringBuilder desxifrat = new StringBuilder();
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                if(Character.isUpperCase(cadena.charAt(i))){
                    for(int x = 0; x < ABCUPPER.length; x++){
                        if(ABCUPPER[x] == cadena.charAt(i)){
                            if ((x - desplacament) < 0){
                                desxifrat.append(ABCUPPER[((x+ABCUPPER.length) - desplacament)%ABCUPPER.length]);                               
                            }else{
                                desxifrat.append(ABCUPPER[(x - desplacament)%ABCUPPER.length]);
                            }                              
                        }
                    }
                }else{
                    for(int y = 0; y < ABCLOWER.length; y++){
                        if(ABCLOWER[y] == cadena.charAt(i)){
                            if ((y - desplacament) < 0){
                                desxifrat.append(ABCLOWER[((y + ABCLOWER.length) - desplacament)%ABCLOWER.length]);                               
                            }else{
                                desxifrat.append(ABCLOWER[(y - desplacament)%ABCLOWER.length]);
                            } 
                        }
                    }
                }
            }else desxifrat.append(cadena.charAt(i));
        }
        return desxifrat;
    }

    public String[] forcaBrutaX (String cadena){
        String[] toReturn = new String[ABCLOWER.length];
        for(int i = 0; i < ABCLOWER.length; i++){
            toReturn[i] = desxifraRotX(cadena, i + 1).toString();
        }
        return  toReturn;
    }
}
