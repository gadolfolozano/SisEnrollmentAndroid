package pe.com.gadolfolozano.sisenrollment.data.remote.mapper;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.sisenrollment.data.remote.entity.LoginResponse;
import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.model.UserModel;

@Singleton
public class LoginMapper {

    @Inject
    public LoginMapper() {
        //Do nothing
    }

    public LoginResponseModel toLoading() {
        LoginResponseModel responseModel = new LoginResponseModel();
        responseModel.setLoading(true);
        return responseModel;
    }

    public LoginResponseModel toSuccess(LoginResponse entity) {
        LoginResponseModel responseModel = new LoginResponseModel();
        responseModel.setToken(entity.getToken());
        responseModel.setStatus(BaseModel.STATUS_SUCCESS);
        UserModel user = new UserModel();
        if (entity.getUser() != null) {
            user.setCpf(entity.getUser().getCpf());
            user.setAddress(entity.getUser().getAddress());
            user.setCity(entity.getUser().getCity());
            user.setEmail(entity.getUser().getEmail());
            user.setName(entity.getUser().getName());
            user.setPhone(entity.getUser().getPhone());
            user.setState(entity.getUser().getState());
        }
        responseModel.setUser(user);
        responseModel.setLoading(false);
        return responseModel;
    }

    public LoginResponseModel toError() {
        LoginResponseModel responseModel = new LoginResponseModel();
        responseModel.setStatus(BaseModel.STATUS_ERROR);
        responseModel.setLoading(false);
        return responseModel;
    }

}
