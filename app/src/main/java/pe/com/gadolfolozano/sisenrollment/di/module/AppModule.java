package pe.com.gadolfolozano.sisenrollment.di.module;

import android.app.Application;
import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pe.com.gadolfolozano.sisenrollment.data.DataManager;
import pe.com.gadolfolozano.sisenrollment.data.DataManagerImplements;
import pe.com.gadolfolozano.sisenrollment.data.remote.ApiHelper;
import pe.com.gadolfolozano.sisenrollment.data.remote.ApiHelperImplements;

/**
 * Created by adolfo on 5/09/18.
 */

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(ApiHelperImplements apiHelperImplements) {
        return apiHelperImplements;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(DataManagerImplements dataManagerImplements) {
        return dataManagerImplements;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

}
