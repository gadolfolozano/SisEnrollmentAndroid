package pe.com.gadolfolozano.sisenrollment.di.builder;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import pe.com.gadolfolozano.sisenrollment.ui.login.LoginActivity;
import pe.com.gadolfolozano.sisenrollment.ui.login.LoginActivityModule;
import pe.com.gadolfolozano.sisenrollment.ui.splash.SplashActivity;
import pe.com.gadolfolozano.sisenrollment.ui.splash.SplashActivityModule;

/**
 * Created by adolfo on 5/09/18.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = SplashActivityModule.class)
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = LoginActivityModule.class)
    abstract LoginActivity bindLoginActivity();

}
