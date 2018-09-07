package pe.com.gadolfolozano.sisenrollment.ui.login;

import dagger.Module;
import dagger.Provides;
import pe.com.gadolfolozano.sisenrollment.data.DataManager;

@Module
public class LoginActivityModule {
    @Provides
    LoginViewModel provideLoginViewModel(DataManager dataManager) {
        return new LoginViewModel(dataManager);
    }
}
