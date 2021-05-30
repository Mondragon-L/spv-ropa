package mx.venado.pvr.seguridad;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

public class AES {

    private static final String ALGORITMO_AES = "AES";
    private static final String KEY = "Clave OK";

    public static final String ERROR_CIFRADO = "[NR]";

    public AES() {
    }

    public String Cifrar(String txt) {
        try {
            byte[] cifrado;
            String cadenafinal = "";
            Cipher cipher = Cipher.getInstance(ALGORITMO_AES);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO_AES);
            keyGenerator.init(128);
//            SecretKey secretKey = keyGenerator.generateKey();
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), 0, 16, ALGORITMO_AES);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            cifrado = cipher.doFinal(txt.getBytes());
            for (byte b : cifrado) {
                cadenafinal += Integer.toHexString(0xFF & b);
            }
            return cadenafinal;
//            return new String(cifrado);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//            System.out.println(e);
            return e.toString();
        }
    }

    public String Descifrar(String txt) {
        try {
            byte[] descifrado;
            Cipher cipher = Cipher.getInstance(ALGORITMO_AES);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO_AES);
            keyGenerator.init(128);
//            SecretKey secretKey = keyGenerator.generateKey();
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), 0, 16, ALGORITMO_AES);
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            descifrado = cipher.doFinal(txt.getBytes());
            return new String(descifrado);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            System.out.println(e);
            return ERROR_CIFRADO;
        }
    }

    public String C(String txt) {
        try {
            byte[] cifrado;
            String cadenafinal = "";
            Cipher cipher = Cipher.getInstance(ALGORITMO_AES);
            KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITMO_AES);
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
//            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), 0, 16, ALGORITMO_AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            cifrado = cipher.doFinal(txt.getBytes());
            for (byte b : cifrado) {
                cadenafinal += Integer.toHexString(0xFF & b);
            }
            return cadenafinal;
//            return new String(cifrado);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
//            System.out.println(e);
            return e.toString();
        }
    }

    public static String sha1(String input) {
        String sha1 = "";

        try {
            MessageDigest msdDigest;
            msdDigest = MessageDigest.getInstance("SHA-512");
            msdDigest.update(input.getBytes("UTF-8"), 0, input.length());
            sha1 = DatatypeConverter.printHexBinary(msdDigest.digest());
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            System.out.println(ex.getMessage());
        }

        return sha1.toLowerCase();
    }

//    public void ejem() {
//        // Generamos una clave de 128 bits adecuada para AES
//      KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
//      keyGenerator.init(128);
//      Key key = keyGenerator.generateKey();
//      
//      // Alternativamente, una clave que queramos que tenga al menos 16 bytes
//      // y nos quedamos con los bytes 0 a 15
//      key = new SecretKeySpec("una clave de 16 bytes".getBytes(),  0, 16, "AES");
//      
//      // Ver como se puede guardar esta clave en un fichero y recuperarla
//      // posteriormente en la clase RSAAsymetricCrypto.java
//
//      // Texto a encriptar
//      String texto = "Este es el texto que queremos encriptar";
//
//      // Se obtiene un cifrador AES
//      Cipher aes = Cipher.getInstance("AES/ECB/PKCS5Padding");
//
//      // Se inicializa para encriptacion y se encripta el texto,
//      // que debemos pasar como bytes.
//      aes.init(Cipher.ENCRYPT_MODE, key);
//      byte[] encriptado = aes.doFinal(texto.getBytes());
//
//      // Se escribe byte a byte en hexadecimal el texto
//      // encriptado para ver su pinta.
//      for (byte b : encriptado) {
//         System.out.print(Integer.toHexString(0xFF & b));
//      }
//      System.out.println();
//
//      // Se iniciliza el cifrador para desencriptar, con la
//      // misma clave y se desencripta
//      aes.init(Cipher.DECRYPT_MODE, key);
//      byte[] desencriptado = aes.doFinal(encriptado);
//
//      // Texto obtenido, igual al original.
//      System.out.println(new String(desencriptado));
//    }
}
