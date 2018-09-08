package pe.com.gadolfolozano.sisenrollment.util;

/**
 * Created by adolfo on 7/09/18.
 */

public class StringUtil {

    private StringUtil() {
        throw new IllegalStateException("Utility class");
    }

    public static String formatString(String mask, String value){

        String cleanedString = cleanString(mask, value);

        StringBuilder output = new StringBuilder();

        int maskCounter = 0;
        for(int i=0; i< cleanedString.length(); i++){
            while (maskCounter < mask.length() && mask.charAt(maskCounter) != '#'){
                output.append(mask.charAt(maskCounter));
                maskCounter ++;
            }

            output.append(cleanedString.charAt(i));
            maskCounter ++;
        }

        return output.toString();
    }

    private static String cleanString(String mask, String value){
        String cleanedString = value;
        for(int i=0; i<mask.length(); i++){
            if(mask.charAt(i) != '#'){
                cleanedString = cleanedString.replace(String.valueOf(mask.charAt(i)), "");
            }
        }

        return cleanedString;
    }
}
