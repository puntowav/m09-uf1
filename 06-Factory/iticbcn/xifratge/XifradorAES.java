package iticbcn.xifratge;

import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class XifradorAES implements Xifrador {
    public final String ALGORISME_XIFRAT = "AES";
    public final String ALGORISME_HASH = "SHA-256";
    public final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    public XifradorAES() {

    }

    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    public TextXifrat xifra(String msg, String clau) throws ClauNoSuportada {
        try {
            return new TextXifrat(xifraAES(msg, clau));
        } catch (Exception e) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }

    public byte[] xifraAES(String msg, String clau) throws Exception {
        // Get Bytes
        byte[] msgByte = msg.getBytes();
        // Genera el IvParameterSpec
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(iv);
        IvParameterSpec IPS = new IvParameterSpec(iv);
        // Genera Hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes());
        SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);
        // Encrypta
        Cipher c = Cipher.getInstance(FORMAT_AES);
        c.init(Cipher.ENCRYPT_MODE, key, IPS);
        byte[] msgX = c.doFinal(msgByte);
        // Combida IV i parxifrada
        byte[] IViMsg = new byte[MIDA_IV + msgX.length];
        System.arraycopy(iv, 0, IViMsg, 0, iv.length);
        System.arraycopy(msgX, 0, IViMsg, iv.length, msgX.length);
        // return iv + msgxifrat
        return IViMsg;
    }

    public String desxifra(TextXifrat xifrat, String clua) throws ClauNoSuportada {
        try {
            return desxifraAES(xifrat.getBytes(), clua);
        } catch (Exception e) {
            throw new ClauNoSuportada("Xifratxe monoalfabètic no suporta clau != null");
        }
    }

    public String desxifraAES(byte[] IViMsg, String clau) throws Exception {
        // Extreu iv
        System.arraycopy(IViMsg, 0, iv, 0, MIDA_IV);
        IvParameterSpec IPS = new IvParameterSpec(iv);
        // Extru part xifrada
        byte[] msgX = new byte[IViMsg.length - MIDA_IV];
        System.arraycopy(IViMsg, MIDA_IV, msgX, 0, IViMsg.length - MIDA_IV);
        // Fer hash de la clau
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes());
        SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);
        // Dexifra
        Cipher c = Cipher.getInstance(FORMAT_AES);
        c.init(Cipher.DECRYPT_MODE, key, IPS);
        byte[] msgDes = c.doFinal(msgX);
        // return String
        return new String(msgDes);
    }
}
