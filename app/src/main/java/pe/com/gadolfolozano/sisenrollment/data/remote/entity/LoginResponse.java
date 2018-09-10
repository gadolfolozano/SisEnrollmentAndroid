package pe.com.gadolfolozano.sisenrollment.data.remote.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adolfo on 9/09/18.
 */

public class LoginResponse implements Serializable {
    @SerializedName("cpf")
    private String token;

    @SerializedName("user")
    private UserResponse user;

    public LoginResponse() {
        //Required
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UserResponse getUser() {
        return user;
    }

    public void setUser(UserResponse user) {
        this.user = user;
    }
}
