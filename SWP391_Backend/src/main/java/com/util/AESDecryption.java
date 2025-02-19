package com.util;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AESDecryption {

    private static final String SECRET_KEY = "mDGat1uxKDVhtfi4s+mvoA==";
    private static final String INIT_VECTOR = "1234567890123456";

    public static String decryptAES(String encryptedText) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        byte[] decodedKey = Base64.getUrlDecoder().decode(SECRET_KEY);
        SecretKey secretKey = new SecretKeySpec(decodedKey, "AES");

        byte[] iv = INIT_VECTOR.getBytes();
        IvParameterSpec ivSpec = new IvParameterSpec(iv);

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivSpec);

        byte[] decodedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(decodedBytes);

        return new String(decryptedBytes);
    }
}
