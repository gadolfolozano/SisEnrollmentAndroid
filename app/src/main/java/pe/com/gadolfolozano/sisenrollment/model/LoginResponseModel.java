package pe.com.gadolfolozano.sisenrollment.model;

/**
 * Created by adolfo on 8/09/18.
 */

public class LoginResponseModel extends BaseModel {
    private String token;
    private UserModel user;

    public LoginResponseModel() {
        //Required
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }
}
