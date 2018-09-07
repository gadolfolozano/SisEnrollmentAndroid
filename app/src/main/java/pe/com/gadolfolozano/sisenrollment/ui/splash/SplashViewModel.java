package pe.com.gadolfolozano.sisenrollment.ui.splash;

import pe.com.gadolfolozano.sisenrollment.data.DataManager;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseViewModel;

/**
 * Created by adolfo on 5/09/18.
 */

public class SplashViewModel extends BaseViewModel<SplashNavigator> {
    public SplashViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public void startApp() {
        getNavigator().openLoginActivity();
    }
}
