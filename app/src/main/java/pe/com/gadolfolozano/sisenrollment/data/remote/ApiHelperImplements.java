package pe.com.gadolfolozano.sisenrollment.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginRequest;
import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginResponse;
import pe.com.gadolfolozano.sisenrollment.data.remote.service.LoginService;
import pe.com.gadolfolozano.sisenrollment.data.remote.service.ServiceListener;
import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;

/**
 * Created by adolfo on 5/09/18.
 */

@Singleton
public class ApiHelperImplements implements ApiHelper {

    @Inject
    public ApiHelperImplements() {
        //Do nothing
    }

    @Override
    public LiveData<LoginResponseModel> login(String username, String password) {
        final MutableLiveData<LoginResponseModel> data = new MutableLiveData<>();

        LoginRequest request = new LoginRequest();
        request.setCpf(username);
        request.setPassword(password);

        LoginResponseModel responseModel = new LoginResponseModel();
        responseModel.setLoading(true);
        data.setValue(responseModel);

        LoginService service = new LoginService();
        service.setBody(request);
        service.setServiceListener(new ServiceListener<LoginResponse>() {
            @Override
            public void onSucess(LoginResponse response) {
                LoginResponseModel responseModel = new LoginResponseModel();
                responseModel.setToken(response.getToken());
                responseModel.setStatus(BaseModel.STATUS_SUCCESS);
                responseModel.setLoading(false);
                data.setValue(responseModel);
            }

            @Override
            public void onError(Throwable t) {
                LoginResponseModel responseModel = new LoginResponseModel();
                responseModel.setStatus(BaseModel.STATUS_ERROR);
                responseModel.setLoading(false);
                data.setValue(responseModel);
            }
        });
        service.execute();

        return data;
    }

}
