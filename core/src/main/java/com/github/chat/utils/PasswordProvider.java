package com.github.chat.utils;

import com.github.chat.exceptions.PasswordException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordProvider {

    private static final Logger log = LoggerFactory.getLogger(PasswordProvider.class);

    public static String[] encodePassReg(String password) {
        if(password == null) {
            throw new PasswordException("Password is null");
        }
        String[] hashPass = new String[2];
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[16];
            random.nextBytes(salt);
            hashPass[0] = bytesToHex(salt);
            md.update(hashPass[0].getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());
            hashPass[1] = bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            log.error("Enter{}:" + e);
        }
        return hashPass;
    }

    public static boolean checkPass(String password, String salt, String hashPassword) {
        if(password == null) {
            throw new PasswordException("Password is null");
        }
        MessageDigest md;
        String result = null;
        try {
            md = MessageDigest.getInstance("SHA-256");
            md.update(salt.getBytes());
            byte[] hashedPassword = md.digest(password.getBytes());

            result = bytesToHex(hashedPassword);
        } catch (NoSuchAlgorithmException e) {
            log.error("Enter{}:" + e);
        }
        if (result == null || hashPassword == null) {
            return false;
        } else {
            return result.equals(hashPassword);
        }

    }
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
