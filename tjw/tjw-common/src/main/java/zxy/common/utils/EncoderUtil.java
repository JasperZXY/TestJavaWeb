package zxy.common.utils;

import java.security.MessageDigest;

public class EncoderUtil {

    public static String md5(String input) {
        if (input == null) {
            return "";
        }
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());
            byte messageDigest[] = digest.digest();

            return byteToHex(messageDigest);
        } catch (Exception e) {
        }
        return "";
    }

    public static String sha1(String input) {
        if (input == null) {
            return "";
        }
        try {
            MessageDigest mDigest = MessageDigest.getInstance("SHA1");
            byte[] result = mDigest.digest(input.getBytes());
            return byteToHex(result);
        } catch (Exception e) {
        }
        return "";
    }

    private static String byteToHex(byte[] messageDigest) {
        StringBuilder buf = new StringBuilder();
        for (byte b : messageDigest) {
            int halfbyte = (b >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                buf.append(
                    (0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

}