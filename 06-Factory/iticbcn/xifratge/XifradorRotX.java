package iticbcn.xifratge;

public class XifradorRotX implements Xifrador {
    private static final char[] ABCLOWER = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toLowerCase().toCharArray();
    private static final char[] ABCUPPER = "AÁÀBCÇDEÉÈFGHIÍÏJKLMNÑOÓÒPQRSTUÚÜVWXYZ".toCharArray();

    public TextXifrat xifra(String msg, String clua) throws ClauNoSuportada {
        try {
            return new TextXifrat(xifraRotx(msg, Integer.parseInt(clua)).getBytes());
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }

    public TextXifrat xifraRotx(String msg, int desplacament) {
        StringBuilder xifrat = new StringBuilder();

        for (int i = 0; i < msg.length(); i++) {
            if (Character.isLetter(msg.charAt(i))) {
                if (Character.isUpperCase(msg.charAt(i))) {
                    for (int x = 0; x < ABCUPPER.length; x++) {
                        if (ABCUPPER[x] == msg.charAt(i)) {
                            xifrat.append(ABCUPPER[(x + desplacament) % ABCUPPER.length]);
                        }
                    }
                } else {
                    for (int y = 0; y < ABCLOWER.length; y++) {
                        if (ABCLOWER[y] == msg.charAt(i)) {
                            xifrat.append(ABCLOWER[(y + desplacament) % ABCLOWER.length]);
                        }
                    }
                }
            } else
                xifrat.append(msg.charAt(i));
        }

        return new TextXifrat(xifrat.toString().getBytes());
    }

    public String desxifra(TextXifrat xifrat, String clau) throws ClauNoSuportada {
        try {
            return desxifraRotx(xifrat.toString(), Integer.parseInt(clau));
        } catch (Exception e) {
            throw new ClauNoSuportada(e.getMessage());
        }
    }

    public String desxifraRotx(String msg, int desplacament) {
        StringBuilder desxifrat = new StringBuilder();

        for (int i = 0; i < msg.toString().length(); i++) {
            if (Character.isLetter(msg.toString().charAt(i))) {
                if (Character.isUpperCase(msg.toString().charAt(i))) {
                    for (int x = 0; x < ABCUPPER.length; x++) {
                        if (ABCUPPER[x] == msg.toString().charAt(i)) {
                            if ((x - desplacament) < 0) {
                                desxifrat.append(ABCUPPER[((x + ABCUPPER.length) - desplacament) % ABCUPPER.length]);
                            } else {
                                desxifrat.append(ABCUPPER[(x - desplacament) % ABCUPPER.length]);
                            }
                        }
                    }
                } else {
                    for (int y = 0; y < ABCLOWER.length; y++) {
                        if (ABCLOWER[y] == msg.charAt(i)) {
                            if ((y - desplacament) < 0) {
                                desxifrat.append(ABCLOWER[((y + ABCLOWER.length) - desplacament) % ABCLOWER.length]);
                            } else {
                                desxifrat.append(ABCLOWER[(y - desplacament) % ABCLOWER.length]);
                            }
                        }
                    }
                }
            } else
                desxifrat.append(msg.charAt(i));
        }
        return desxifrat.toString();
    }

    public String[] forcaBrutaX(String cadena) throws ClauNoSuportada {
        String[] toReturn = new String[ABCLOWER.length];

        for (int i = 0; i < ABCLOWER.length; i++) {
            toReturn[i] = desxifra(new TextXifrat(cadena.getBytes()), String.valueOf(i + 1)).toString();
        }
        return toReturn;
    }
}
