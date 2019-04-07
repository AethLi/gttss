package cn.aethli.gttss.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StringUtils {
    public static String charset_utf8(String res) {
        String des = null;
        des = new String(res.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        return des;
    }

    public static String toMD5(String res) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        byte[] resByteArray = res.getBytes();
        byte[] desByteArray = md.digest(resByteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < desByteArray.length; i++) {
            int val = ((int) desByteArray[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }
}
