import java.security.MessageDigest;
import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {
    public static final String ALGORISME_XIFRAT = "AES";
    public static final String ALGORISME_HASH = "SHA-256";
    public static final String FORMAT_AES = "AES/CBC/PKCS5Padding";

    private static final String CLAU = "kaodjapwd0e30id980fu30c023id2ipd";  
    private final int MIDA_IV = 16;
    private byte[] iv = new byte[MIDA_IV];

    public byte[] xifraAES(String msg, String clau)throws Exception{
        //Get Bytes
        byte[] msgByte = msg.getBytes();
        //Genera el IvParameterSpec
        SecureRandom sr = new SecureRandom();
        sr.nextBytes(iv);
        IvParameterSpec IPS = new IvParameterSpec(iv);
        //Genera Hash
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes());
        SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);
        //Encrypta
        Cipher c = Cipher.getInstance(FORMAT_AES);
        c.init(Cipher.ENCRYPT_MODE, key, IPS);
        byte[] msgX = c.doFinal(msgByte);
        //Combida IV i parxifrada
        byte[] IViMsg = new byte[MIDA_IV + msgX.length];
        System.arraycopy(iv, 0, IViMsg, 0, iv.length);
        System.arraycopy(msgX, 0, IViMsg, iv.length, msgX.length);
        //return iv + msgxifrat
        return IViMsg;
    }

    public String desxifraAES(byte[] IViMsg, String clau)throws Exception{
        //Extreu iv
        System.arraycopy(IViMsg, 0,iv , 0, MIDA_IV);
        IvParameterSpec IPS = new IvParameterSpec(iv);
        //Extru part xifrada
        byte[] msgX = new byte[IViMsg.length - MIDA_IV];
        System.arraycopy(IViMsg, MIDA_IV,msgX , 0, IViMsg.length - MIDA_IV);
        //Fer hash de la clau
        MessageDigest md = MessageDigest.getInstance(ALGORISME_HASH);
        byte[] hash = md.digest(clau.getBytes());
        SecretKeySpec key = new SecretKeySpec(hash, ALGORISME_XIFRAT);
        //Dexifra
        Cipher c = Cipher.getInstance(FORMAT_AES);
        c.init(Cipher.DECRYPT_MODE, key, IPS);
        byte[] msgDes = c.doFinal(msgX);
        //return String
        return new String(msgDes);
    }

    public static void main(String[] args) {
        AES aes = new AES();
        byte[] xifrat = null;
        String desxifrat = "";

        String[] test = {"HOLA QUE TAL", "hola que tal", "33 á -?_: Mira quin TEST", "Més temps NO és millor, millor es millor i més temps tan sols es més estona 4:31 Ezequiel", "üUç 910 -;, Més test DEl poliAlfabetic -- áÑñ à Klja"};
        for (String s : test) {
            try{
                xifrat = aes.xifraAES(s, CLAU);
                desxifrat = aes.desxifraAES(xifrat, CLAU);
            }catch(Exception e){
                System.err.println("Error: " + e.getLocalizedMessage());
            }
            System.out.println(new String(xifrat));
            System.out.println(desxifrat);
        }       
    }
}
