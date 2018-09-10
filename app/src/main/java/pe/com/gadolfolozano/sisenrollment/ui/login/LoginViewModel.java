package pe.com.gadolfolozano.sisenrollment.ui.login;

import android.arch.lifecycle.LiveData;

import pe.com.gadolfolozano.sisenrollment.data.DataManager;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseViewModel;

public class LoginViewModel extends BaseViewModel<LoginNavigator> {

    public LoginViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public LiveData<LoginResponseModel> login(String username, String password){
        return getDataManager().login(username, password);
    }

}
