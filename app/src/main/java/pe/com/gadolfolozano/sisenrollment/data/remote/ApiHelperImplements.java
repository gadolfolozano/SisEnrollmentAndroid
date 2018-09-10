package pe.com.gadolfolozano.sisenrollment.data.remote;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginRequest;
import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginResponse;
import pe.com.gadolfolozano.sisenrollment.data.remote.mapper.LoginMapper;
import pe.com.gadolfolozano.sisenrollment.data.remote.service.LoginService;
import pe.com.gadolfolozano.sisenrollment.data.remote.service.ServiceListener;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;

/**
 * Created by adolfo on 5/09/18.
 */

@Singleton
public class ApiHelperImplements implements ApiHelper {

    private final LoginMapper mapper;

    @Inject
    public ApiHelperImplements(LoginMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public LiveData<LoginResponseModel> login(String username, String password) {
        final MutableLiveData<LoginResponseModel> data = new MutableLiveData<>();

        LoginRequest request = new LoginRequest();
        request.setCpf(username);
        request.setPassword(password);

        data.setValue(mapper.toLoading());

        LoginService service = new LoginService();
        service.setBody(request);
        service.setServiceListener(new ServiceListener<LoginResponse>() {
            @Override
            public void onSucess(LoginResponse response) {
                data.setValue(mapper.toSuccess(response));
            }

            @Override
            public void onError(Throwable t) {
                data.setValue(mapper.toError());
            }
        });
        service.execute();

        return data;
    }

}
