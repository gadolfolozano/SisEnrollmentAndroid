package pe.com.gadolfolozano.sisenrollment.util;

import android.util.Log;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by adolfo on 7/09/18.
 */

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatString(String mask, String value) {

        String cleanedString = cleanString(mask, value);

        StringBuilder output = new StringBuilder();

        int maskCounter = 0;
        for (int i = 0; i < cleanedString.length(); i++) {
            while (maskCounter < mask.length() && mask.charAt(maskCounter) != '#') {
                output.append(mask.charAt(maskCounter));
                maskCounter++;
            }

            output.append(cleanedString.charAt(i));
            maskCounter++;
        }

        return output.toString();
    }

    public static String cleanString(String mask, String value) {
        String cleanedString = value;
        for (int i = 0; i < mask.length(); i++) {
            if (mask.charAt(i) != '#') {
                cleanedString = cleanedString.replace(String.valueOf(mask.charAt(i)), "");
            }
        }

        return cleanedString;
    }

    public static String sha1(String text) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] textBytes = text.getBytes("iso-8859-1");
            md.update(textBytes, 0, textBytes.length);
            byte[] sha1hash = md.digest();
            return convertToHex(sha1hash);
        } catch (NoSuchAlgorithmException|UnsupportedEncodingException e) {
            Log.e(StringUtil.class.getName(), "error ", e);
        }

        return Constants.EMPTY_STRING;
    }

    private static String convertToHex(byte[] data) {
        StringBuilder buf = new StringBuilder();
        for (byte b : data) {
            int halfbyte = (b >>> 4) & 0x0F;
            int twoHalfs = 0;
            do {
                buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
                halfbyte = b & 0x0F;
            } while (twoHalfs++ < 1);
        }
        return buf.toString();
    }
}
