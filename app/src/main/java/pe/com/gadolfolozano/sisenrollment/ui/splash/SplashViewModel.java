package pe.com.gadolfolozano.sisenrollment.ui.splash;

import android.arch.lifecycle.LiveData;

import pe.com.gadolfolozano.sisenrollment.data.DataManager;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseViewModel;

/**
 * Created by adolfo on 5/09/18.
 */

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public LiveData<LoginResponseModel> getSession() {
        return getDataManager().getSession();
    }
}
