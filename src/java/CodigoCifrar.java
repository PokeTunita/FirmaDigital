import java.security.*;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.*;
public class CodigoCifrar {
    private static final String instancia = "AES";
    
    public static String encrypt(String Data, String llave) throws Exception {
        Key key = generateKey(llave.getBytes());
        Cipher cifrado = Cipher.getInstance(instancia);
        cifrado.init(Cipher.ENCRYPT_MODE, key);
        byte[] encValores = cifrado.doFinal(Data.getBytes());
        String valoresencriptados = new BASE64Encoder().encode(encValores);
        return valoresencriptados;
    }
    
    
    
    private static Key generateKey(byte[] keyvalue) throws Exception {
        Key key = new SecretKeySpec(keyvalue, instancia);
        return key;
    }
}
