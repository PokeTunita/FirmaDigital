import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import sun.misc.BASE64Decoder;


class CodigoDescifrar {
    private static final String instancia = "AES";
    
    public static String decrypt(String valoresencriptados, String llave) throws Exception {
        Key key = generateKey(llave.getBytes());
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.DECRYPT_MODE, key);
        byte [] decodificadorvalores = new BASE64Decoder().decodeBuffer(valoresencriptados);
        byte[] decValores = cifrado.doFinal(decodificadorvalores);
        String valoresdescifrados = new String(decValores);
        return valoresdescifrados;
    }
    
    private static Key generateKey(byte[] keyvalue) throws Exception {
        Key key = new SecretKeySpec(keyvalue, instancia);
        return key;
    }
}