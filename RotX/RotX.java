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
            }else if (cadena.charAt(i) == ' ') xifrat.append(" ");
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
            }else if (cadena.charAt(i) == ' ') desxifrat.append(" ");
        }
        return desxifrat;
    }

    public void forcaBrutaX (String cadena){
        for(int i = 1; i < ABCLOWER.length; i++){
            System.out.println(desxifraRotX(cadena, i));
        }
    }
}