package pe.com.gadolfolozano.sisenrollment.ui.main;

import android.arch.lifecycle.LiveData;

import pe.com.gadolfolozano.sisenrollment.data.DataManager;
import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseViewModel;

public class MainViewModel extends BaseViewModel<MainNavigator> {
    public MainViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public LiveData<LoginResponseModel> getUserSession() {
        return getDataManager().getSession();
    }

    public LiveData<BaseModel> logout() {
        return getDataManager().logout();
    }
}
