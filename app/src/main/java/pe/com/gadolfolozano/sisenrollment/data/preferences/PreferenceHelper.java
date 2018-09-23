package pe.com.gadolfolozano.sisenrollment.data.preferences;

import android.arch.lifecycle.LiveData;

import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;

/**
 * Created by adolfo on 10/09/18.
 */

public interface PreferenceHelper {
    LiveData<BaseModel> saveSession(LoginResponseModel loginResponseModel);

    LiveData<BaseModel> clearSession();

    LiveData<LoginResponseModel> getSession();
}
