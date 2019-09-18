package br.ufjf.getsi.talktwome.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Md5 {

    public String encriptografar (String senha)
    {
        String senhaParaEncriptografar = senha;
        String senhaGerada = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(senhaParaEncriptografar.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            senhaGerada = sb.toString();
        }
        catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();
        }
        return senhaGerada;
    }
}