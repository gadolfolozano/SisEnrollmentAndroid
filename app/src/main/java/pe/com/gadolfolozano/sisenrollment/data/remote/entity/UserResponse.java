package pe.com.gadolfolozano.sisenrollment.data.remote.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by adolfo on 9/09/18.
 */

public class UserResponse implements Serializable {
    @SerializedName("cpf")
    private String cpf;

    @SerializedName("name")
    private String name;

    @SerializedName("address")
    private String address;

    @SerializedName("state")
    private String state;

    @SerializedName("city")
    private String city;

    @SerializedName("phone")
    private String phone;

    @SerializedName("email")
    private String email;

    public UserResponse() {
        //Required
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
