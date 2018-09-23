package pe.com.gadolfolozano.sisenrollment.data.remote.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adolfo on 9/09/18.
 */

public class LoginRequest implements Serializable{
    @SerializedName("email")
    private String email;

    @SerializedName("password")
    private String password;

    public LoginRequest() {
        //Required
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
