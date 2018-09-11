package pe.com.gadolfolozano.sisenrollment.util;

/**
 * Created by adolfo on 9/09/18.
 */

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_URL = "http://192.168.1.4:8080/EnrollmentManagement/rest/";

    public static final String PREF_NAME = "app_pref";

    public static final String CPF_MASK = "###.###.###-##";

    public static final String EMPTY_STRING = "";
}
