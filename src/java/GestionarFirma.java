
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.BASE64Encoder;

public class GestionarFirma {
    
    public static boolean firmar(String texto){
        boolean seguir = false;
        try {
            byte[] data = texto.getBytes("UTF8");
            Signature firma = Signature.getInstance("MD5WithRSA");
            firma.initSign(Gestionarllaves.ObtenerllavePrivada());
            firma.update(data);
            byte[] firmadocumento = firma.sign();
            FileOutputStream fos = new FileOutputStream("DocumentoFirma.txt");
            fos.write(firmadocumento);
            fos.close();
            seguir = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        return seguir;
    }
    
    public static boolean Verificarfirma(String texto){
        boolean seguir = false;
        try{
            byte[] data = texto.getBytes("UTF8");
            FileInputStream fis = new FileInputStream("DocumentoFirma.txt");
            int numbytes = fis.available();
            byte[] docfirma = new byte[numbytes];
            fis.read(docfirma);
            fis.close();
            Signature firma = Signature.getInstance("MD5WithRSA");
            firma.initVerify(Gestionarllaves.ObtenerllavePublica());
            firma.update(data);
            seguir = firma.verify(docfirma);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return seguir;
    }
    
}
