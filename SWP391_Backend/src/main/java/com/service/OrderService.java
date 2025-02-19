package com.service;

import com.util.AESDecryption;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class OrderService {

    public void createOrder(String encryptedKey) throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        String decryptedKey = AESDecryption.decryptAES(encryptedKey);
        String[] parts = decryptedKey.split("&");
        String uid = parts[0].split("=")[1];
        String courseIds = parts[1].split("=")[1];
        System.out.println("uid: " + uid);
    }
}
