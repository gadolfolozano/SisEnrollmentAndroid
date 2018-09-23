package pe.com.gadolfolozano.sisenrollment.util;

import pe.com.gadolfolozano.sisenrollment.BuildConfig;

/**
 * Created by adolfo on 9/09/18.
 */

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String BASE_URL = String.format("%s%s", BuildConfig.SERVER_URL, "/EnrollmentManagement/rest/");

    public static final String PREF_NAME = "app_pref";

    public static final String EMPTY_STRING = "";
}
