package pe.com.gadolfolozano.sisenrollment.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import pe.com.gadolfolozano.sisenrollment.MyApplication;
import pe.com.gadolfolozano.sisenrollment.di.builder.ActivityBuilder;
import pe.com.gadolfolozano.sisenrollment.di.module.AppModule;

/**
 * Created by adolfo on 5/09/18.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(MyApplication app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
