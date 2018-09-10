package pe.com.gadolfolozano.sisenrollment.ui.main;

import dagger.Module;
import dagger.Provides;
import pe.com.gadolfolozano.sisenrollment.data.DataManager;

/**
 * Created by adolfo on 5/09/18.
 */

@Module
public class MainActivityModule {
    @Provides
    MainViewModel provideSplashViewModel(DataManager dataManager) {
        return new MainViewModel(dataManager);
    }
}
