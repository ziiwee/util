package com.ziiwee.util.detail;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * Created by ziiwee on 2016/11/8.
 */
public class SecurityUtil {

    private static final String PASSWORD_CRYPT_KEY = "com.ziiwee";

    /**
     * DES解密
     * @param input
     * @return
     */
    public final static String decrypt(String input) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = skf.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
            byte[] bs0 = input.getBytes();
            byte[] bs1 = new byte[bs0.length / 2];
            for (int i = 0; i < bs0.length; i += 2) {
                String item = new String(bs0, i, 2);
                bs1[i / 2] = (byte) Integer.parseInt(item, 16);
            }
            return new String(cipher.doFinal(bs1));
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * DES加密
     * @param input
     * @return
     */
    public final static String encrypt(String input) {
        try {
            SecureRandom sr = new SecureRandom();
            DESKeySpec dks = new DESKeySpec(PASSWORD_CRYPT_KEY.getBytes());
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            SecretKey securekey = skf.generateSecret(dks);
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
            byte[] bs = cipher.doFinal(input.getBytes());
            String output = "";
            for(byte b: bs){
                String s = (Integer.toHexString(b & 0XFF));
                if (s.length() == 1)
                    output += "0" + s;
                else
                    output += s;
            }
            return output.toUpperCase();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    /**
     * MD5不可逆加密
     *
     * @param input
     * @return
     */
    public static String md5(String input) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = input.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char c[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                c[k++] = hexDigits[byte0 >>> 4 & 0xf];
                c[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(c);
        } catch (Exception e) {
            return null;
        }
    }


}
