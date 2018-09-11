package pe.com.gadolfolozano.sisenrollment.data;

import android.arch.lifecycle.LiveData;

import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;

/**
 * Created by adolfo on 5/09/18.
 */

public interface DataManager {
    LiveData<LoginResponseModel> login(String username, String password);

    LiveData<BaseModel> saveSession(LoginResponseModel loginResponseModel);

    LiveData<LoginResponseModel> getSession();

    LiveData<BaseModel> logout();
}
