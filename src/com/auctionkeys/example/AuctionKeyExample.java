package com.auctionkeys.example;


import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class AuctionKeyExample {
    public static void main(String[] args) {
        try {
            // Step 1: Initialize the KeyGenerator for AES algorithm
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(256); // AES key size (256 bits)

            // Generate two symmetric keys
            SecretKey key1 = keyGen.generateKey();
            SecretKey key2 = keyGen.generateKey();

            // Prepare input string "madhu"
            String input = "madhu";

            // Step 2: Encrypt using key1 //assume we will save this in auctionBiddetail table
            String encryptedWithKey1 = encryptWithAES(input, key1);
            System.out.println("Encrypted with Key 1: " + encryptedWithKey1);

            // Encrypt using key2 // assume we will save this in auctionlivebid table 
            String encryptedWithKey2 = encryptWithAES(input, key2);
            System.out.println("Encrypted with Key 2: " + encryptedWithKey2);
            
            
            //assume when we submit the bid or loading live dashboard and when we complete the auction.
            // Step 3: Decrypt the encrypted texts
            String decryptedWithKey1 = decryptWithAES(encryptedWithKey1, key1);
            System.out.println("Decrypted with Key 1: " + decryptedWithKey1);
          
            
             
            
//            String decryptedWithKey2 = decryptWithAES(encryptedWithKey2, key2);
//            System.out.println("Decrypted with Key 2: " + decryptedWithKey2);
            
            String encryptedAliasName=encryptWithAES(decryptedWithKey1, key2);
            
            
            if(encryptedWithKey2.equals(encryptedAliasName))
            {
            	System.err.println("yes both are equal");
            }
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Helper method to perform AES encryption
    private static String encryptWithAES(String input, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Generate a random IV (Initialization Vector) (Here we use a fixed IV for simplicity)
        byte[] iv = new byte[16]; // AES block size is 16 bytes
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        // Initialize the cipher in encryption mode
        cipher.init(Cipher.ENCRYPT_MODE, key, ivParams);

        // Encrypt the input string
        byte[] encryptedBytes = cipher.doFinal(input.getBytes(StandardCharsets.UTF_8));

        // Encode the encrypted bytes into a Base64 string
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    // Helper method to perform AES decryption
    private static String decryptWithAES(String encryptedText, SecretKey key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");

        // Use the same IV for decryption (Fixed IV as used during encryption)
        byte[] iv = new byte[16]; // AES block size is 16 bytes
        IvParameterSpec ivParams = new IvParameterSpec(iv);

        // Initialize the cipher in decryption mode
        cipher.init(Cipher.DECRYPT_MODE, key, ivParams);

        // Decode the encrypted text from Base64
        byte[] decodedEncryptedBytes = Base64.getDecoder().decode(encryptedText);

        // Decrypt the data
        byte[] decryptedBytes = cipher.doFinal(decodedEncryptedBytes);

        // Convert decrypted bytes to a string
        return new String(decryptedBytes, StandardCharsets.UTF_8);
    }
}
