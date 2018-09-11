package pe.com.gadolfolozano.sisenrollment.data.remote.local.preferences;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import pe.com.gadolfolozano.sisenrollment.di.PreferenceInfo;
import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.model.UserModel;
import pe.com.gadolfolozano.sisenrollment.util.Constants;

/**
 * Created by adolfo on 10/09/18.
 */

public class PreferenceHelperImplements implements PreferenceHelper {

    private static final String PREF_KEY_TOKEN = "PREF_KEY_TOKEN";
    private static final String PREF_KEY_USER_CPF = "PREF_KEY_USER_CPF";
    private static final String PREF_KEY_USER_NAME = "PREF_KEY_USER_NAME";
    private static final String PREF_KEY_USER_ADDRESS = "PREF_KEY_USER_ADDRESS";
    private static final String PREF_KEY_USER_STATE = "PREF_KEY_USER_STATE";
    private static final String PREF_KEY_USER_CITY = "PREF_KEY_USER_CITY";
    private static final String PREF_KEY_USER_PHONE = "PREF_KEY_USER_PHONE";
    private static final String PREF_KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL";

    private final SharedPreferences mPrefs;

    @Inject
    public PreferenceHelperImplements(Context context, @PreferenceInfo String prefFileName) {
        this.mPrefs = context.getSharedPreferences(prefFileName, Context.MODE_PRIVATE);
    }

    @Override
    public LiveData<BaseModel> saveSession(LoginResponseModel loginResponseModel) {
        SharedPreferences.Editor prefEdit = mPrefs.edit();
        prefEdit.putString(PREF_KEY_TOKEN, loginResponseModel.getToken());
        prefEdit.putString(PREF_KEY_USER_CPF, loginResponseModel.getUser().getCpf());
        prefEdit.putString(PREF_KEY_USER_NAME, loginResponseModel.getUser().getName());
        prefEdit.putString(PREF_KEY_USER_ADDRESS, loginResponseModel.getUser().getAddress());
        prefEdit.putString(PREF_KEY_USER_STATE, loginResponseModel.getUser().getState());
        prefEdit.putString(PREF_KEY_USER_CITY, loginResponseModel.getUser().getCity());
        prefEdit.putString(PREF_KEY_USER_PHONE, loginResponseModel.getUser().getPhone());
        prefEdit.putString(PREF_KEY_USER_EMAIL, loginResponseModel.getUser().getEmail());
        prefEdit.apply();
        MutableLiveData<BaseModel> data = new MutableLiveData<>();
        data.setValue(getSucess());
        return data;
    }

    @Override
    public LiveData<BaseModel> clearSession() {
        mPrefs.edit().clear().apply();
        MutableLiveData<BaseModel> data = new MutableLiveData<>();
        data.setValue(getSucess());
        return data;
    }

    @Override
    public LiveData<LoginResponseModel> getSession() {
        MutableLiveData<LoginResponseModel> data = new MutableLiveData<>();

        LoginResponseModel model = null;
        try {
            String token = mPrefs.getString(PREF_KEY_TOKEN, Constants.EMPTY_STRING);

            if (!Constants.EMPTY_STRING.equalsIgnoreCase(token)) {
                model = new LoginResponseModel();

                model.setToken(token);

                UserModel user = new UserModel();
                user.setCpf(mPrefs.getString(PREF_KEY_USER_CPF, Constants.EMPTY_STRING));
                user.setName(mPrefs.getString(PREF_KEY_USER_NAME, Constants.EMPTY_STRING));
                user.setAddress(mPrefs.getString(PREF_KEY_USER_ADDRESS, Constants.EMPTY_STRING));
                user.setState(mPrefs.getString(PREF_KEY_USER_STATE, Constants.EMPTY_STRING));
                user.setCity(mPrefs.getString(PREF_KEY_USER_CITY, Constants.EMPTY_STRING));
                user.setPhone(mPrefs.getString(PREF_KEY_USER_PHONE, Constants.EMPTY_STRING));
                user.setEmail(mPrefs.getString(PREF_KEY_USER_EMAIL, Constants.EMPTY_STRING));

                model.setUser(user);
            }
        } catch (Exception e) {
            //Do Nothing;
        }

        if (model == null) {
            model = new LoginResponseModel();
            model.setLoading(false);
            model.setStatus(BaseModel.STATUS_ERROR);
        } else {
            model.setLoading(false);
            model.setStatus(BaseModel.STATUS_SUCCESS);
        }

        data.setValue(model);

        return data;
    }

    private BaseModel getSucess() {
        BaseModel baseModel = new BaseModel();
        baseModel.setLoading(false);
        baseModel.setStatus(BaseModel.STATUS_SUCCESS);

        return baseModel;
    }
}
