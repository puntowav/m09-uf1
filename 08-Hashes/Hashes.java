import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Hashes {

    int npass = 0;

    public String getSHA512AmbSalt(String pw, String salt) throws Exception {
        String concatPwSalt = pw + salt;
        byte[] concat = concatPwSalt.getBytes();
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        md.update(concat);
        byte[] hash = md.digest();
        HexFormat hex = HexFormat.of();
        return hex.formatHex(hash);
    }

    public String getPBKDF2AmbSalt(String pw, String salt) throws Exception {
        int iteracions = 65536;
        int longitud = 128;

        char[] passwd = pw.toCharArray();
        byte[] saltBytes = salt.getBytes(StandardCharsets.UTF_8);

        PBEKeySpec spec = new PBEKeySpec(passwd, saltBytes, iteracions, longitud);
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");

        byte[] hash = factory.generateSecret(spec).getEncoded();
        HexFormat hex = HexFormat.of();

        return hex.formatHex(hash);
    }

    public String forcaBruta(String alg, String hash, String salt) throws Exception {
        char[] charset = " abcdefABCDEF1234567890!".toCharArray();
        char[] c = new char[6];

        for (int i0 = 0; i0 < charset.length; i0++) {
            c[0] = charset[i0];
            for (int i1 = 0; i1 < charset.length; i1++) {
                c[1] = charset[i1];
                for (int i2 = 0; i2 < charset.length; i2++) {
                    c[2] = charset[i2];
                    for (int i3 = 0; i3 < charset.length; i3++) {
                        c[3] = charset[i3];
                        for (int i4 = 0; i4 < charset.length; i4++) {
                            c[4] = charset[i4];
                            for (int i5 = 1; i5 < charset.length; i5++) {
                                c[5] = charset[i5];
                                String hash2 = null;
                                String pw2 = new String(c).trim();
                                if (alg.equals("SHA-512")) {
                                    hash2 = getSHA512AmbSalt(pw2, salt);
                                } else if (alg.equals("PBKDF2")) {
                                    hash2 = getPBKDF2AmbSalt(pw2, salt);
                                }
                                if (hash2.equals(hash))
                                    return pw2;
                                npass++;
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    public String getInterval(long t1, long t2) {
        return String.format("%d dies / %d hores / %d minuts / %d segons / %d millis", (t2 - t1) / 86400000,
                ((t2 - t1) / 3600000) % 24, ((t2 - t1) / 3600000) % 60, ((t2 - t1) / 1000) % 60,
                (t2 - t1) / 1000);
    }

    public static void main(String[] args) throws Exception {
        String salt = "qpoweiruañslkdfjz";
        String pw = "aaabF!";
        Hashes h = new Hashes();
        String[] aHashes = { h.getSHA512AmbSalt(pw, salt),
                h.getPBKDF2AmbSalt(pw, salt) };
        String pwTrobat = null;
        String[] algorismes = { "SHA-512", "PBKDF2" };
        for (int i = 0; i < aHashes.length; i++) {
            System.out.printf("===========================\n");
            System.out.printf("Algorisme: %s\n", algorismes[i]);
            System.out.printf("Hash: %s\n", aHashes[i]);
            System.out.printf("---------------------------\n");
            System.out.printf("-- Inici de força bruta ---\n");

            long t1 = System.currentTimeMillis();
            pwTrobat = h.forcaBruta(algorismes[i], aHashes[i], salt);
            long t2 = System.currentTimeMillis();

            System.out.printf("Pass : %s\n", pwTrobat);
            System.out.printf("Provats: %d\n", h.npass);
            System.out.printf("Temps : %s\n", h.getInterval(t1, t2));
            System.out.printf("---------------------------\n\n");
        }
    }
}