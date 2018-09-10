package pe.com.gadolfolozano.sisenrollment.data.remote.service;

import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginRequest;
import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginResponse;
import retrofit2.Call;

/**
 * Created by adolfo on 9/09/18.
 */

public class LoginService extends ServiceBase<LoginResponse> {

    private LoginRequest body;

    public void setBody(LoginRequest body) {
        this.body = body;
    }

    @Override
    protected Call<LoginResponse> createCall() {
        return getAPIInterface().login(body);
    }
}
