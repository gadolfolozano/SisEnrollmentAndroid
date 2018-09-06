package pe.com.gadolfolozano.sisenrollment.ui.splash;

import dagger.Module;
import dagger.Provides;
import pe.com.gadolfolozano.sisenrollment.data.DataManager;

/**
 * Created by adolfo on 5/09/18.
 */

@Module
public class SplashActivityModule {
    @Provides
    SplashViewModel provideSplashViewModel(DataManager dataManager) {
        return new SplashViewModel(dataManager);
    }
}
