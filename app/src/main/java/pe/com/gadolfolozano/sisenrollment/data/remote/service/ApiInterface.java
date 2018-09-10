package pe.com.gadolfolozano.sisenrollment.data.remote.service;

import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginRequest;
import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by adolfo on 9/09/18.
 */

public interface ApiInterface {
    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);
}
