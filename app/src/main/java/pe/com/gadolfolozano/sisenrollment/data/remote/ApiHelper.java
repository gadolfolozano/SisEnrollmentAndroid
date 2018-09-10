package pe.com.gadolfolozano.sisenrollment.data.remote;

import android.arch.lifecycle.LiveData;

import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;

/**
 * Created by adolfo on 5/09/18.
 */

public interface ApiHelper {
    LiveData<LoginResponseModel> login(String username, String password);
}
