package com.loyalty.authentication.encrypt;

import sun.misc.BASE64Encoder;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

public class ShaEncryptor {
 public String getEncrypt(String encryt){
    try {
        MessageDigest messageDigest= MessageDigest.getInstance("SHA-512");
        byte[] digest = messageDigest.digest(encryt.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < digest.length; ++i) {
            sb.append(Integer.toHexString((digest[i] & 0xFF) | 0x100).substring(1, 3));
        }

        return sb.toString();


    }catch (Exception e){
        return null;
    }
 }
    public String aplicarBase64(String a){
        Base64.Encoder encoder = Base64.getEncoder();
        String b = encoder.encodeToString(a.getBytes(StandardCharsets.UTF_8) );
        return b;
    }
}
