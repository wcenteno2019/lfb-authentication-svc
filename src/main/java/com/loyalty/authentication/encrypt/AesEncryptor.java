package com.loyalty.authentication.encrypt;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;

public class AesEncryptor {

    public String aesEncrypt(String word, String aesKey, byte[] iv) {
        try {
            String message;
            byte[] sessionKey = aesKey.getBytes(StandardCharsets.US_ASCII);
            byte[] plaintext = word.getBytes(StandardCharsets.US_ASCII);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //You can use ENCRYPT_MODE or DECRYPT_MODE
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(sessionKey, "AES"), new IvParameterSpec(iv));

            byte[] ciphertext = cipher.doFinal(plaintext);
            message = new Base64().encodeToString(ciphertext);

            return message;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @SuppressWarnings("static-access")
    public String aesDecrypt(String encryptedText,String aesKey, byte[] iv) {
        try{
            String message;
            byte[] sessionKey = aesKey.getBytes(StandardCharsets.US_ASCII);
            byte[] plaintext = new Base64().decode(encryptedText);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            //You can use ENCRYPT_MODE or DECRYPT_MODE
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(sessionKey, "AES"), new IvParameterSpec(iv));
            byte[] ciphertext = cipher.doFinal(plaintext);
            message = new String(ciphertext, StandardCharsets.UTF_8);

            return message;
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
