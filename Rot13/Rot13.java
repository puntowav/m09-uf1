/**
 * Aquest programa fara la ecryptacio de un text en sistema Rot13
 */
public class Rot13 {
    private static final String ABC = "A,Á,À,B,C,Ç,D,E,É,È,F,G,H,I,Í,Ï,J,K,L,M,N,Ñ,O,Ó,Ò,P,Q,R,S,T,U,Ú,Ü,V,W,X,Y,Z";
    
    private static final String[] ABCLOWER = ABC.toLowerCase().split(",");
    private static final String[] ABCUPPER = ABC.toUpperCase().split(",");

    public StringBuffer xifraRot13(String cadena){
        StringBuffer xifrat = new StringBuffer();
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                if(Character.isUpperCase(cadena.charAt(i))){
                    for(int x = 0; x < ABCUPPER.length; x++){
                        if(ABCUPPER[x].equals(Character.toString(cadena.charAt(i)))){
                            xifrat.append(ABCUPPER[(x + 13)%ABCUPPER.length]);
                        }
                    }
                }else{
                    for(int y = 0; y < ABCLOWER.length; y++){
                        if(ABCLOWER[y].equals(Character.toString(cadena.charAt(i)))){
                            xifrat.append(ABCLOWER[(y + 13)%ABCLOWER.length]);
                        }
                    }
                }
            }else if (cadena.charAt(i) == ' ') xifrat.append(" ");
        }
        return xifrat;
    }

    public StringBuffer desxifraRot13(String cadena){
        StringBuffer desxifrat = new StringBuffer();
        for(int i = 0; i < cadena.length(); i++){
            if(Character.isLetter(cadena.charAt(i))){
                if(Character.isUpperCase(cadena.charAt(i))){
                    for(int x = 0; x < ABCUPPER.length; x++){
                        if(ABCUPPER[x].equals(Character.toString(cadena.charAt(i)))){
                            if ((x -13) < 0){
                                desxifrat.append(ABCUPPER[((x+ABCUPPER.length) - 13)%ABCUPPER.length]);                               
                            }else{
                                desxifrat.append(ABCUPPER[(x - 13)%ABCUPPER.length]);
                            }                              
                        }
                    }
                }else{
                    for(int y = 0; y < ABCLOWER.length; y++){
                        if(ABCLOWER[y].equals(Character.toString(cadena.charAt(i)))){
                            if ((y -13) < 0){
                                desxifrat.append(ABCLOWER[((y + ABCLOWER.length) - 13)%ABCLOWER.length]);                               
                            }else{
                                desxifrat.append(ABCLOWER[(y - 13)%ABCLOWER.length]);
                            } 
                        }
                    }
                }
            }else if (cadena.charAt(i) == ' ') desxifrat.append(" ");
        }
        return desxifrat;
    }
}
