package com.br.peladafc.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by alan on 15/02/2017.
 */

public final class CryptoHelper {

    public static String GenerateSHA256(String text){

        MessageDigest digest = null;

        try{
            digest = MessageDigest.getInstance("SHA-256");
        }catch (NoSuchAlgorithmException ex){
            return null;
        }

        return new String(digest.digest(text.getBytes(StandardCharsets.UTF_8)));
    }
}
