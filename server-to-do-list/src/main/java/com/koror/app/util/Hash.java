package com.koror.app.util;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hash {

    public static String createHashString(String value) {
        String hashValue = null;
        value = PropertyConfig.SALT + value + PropertyConfig.SALT;
        for (int i = 0; i < PropertyConfig.CYCLE; i++) {
            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                md.update(value.getBytes());
                byte[] digest = md.digest();
                hashValue = DatatypeConverter
                        .printHexBinary(digest).toUpperCase();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }
        return hashValue;
    }

}
