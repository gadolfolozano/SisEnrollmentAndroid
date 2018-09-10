package pe.com.gadolfolozano.sisenrollment.data;

import android.arch.lifecycle.LiveData;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.sisenrollment.data.remote.ApiHelper;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;

/**
 * Created by adolfo on 5/09/18.
 */

@Singleton
public class DataManagerImplements implements DataManager {

    private ApiHelper mApiHelper;
    private Context mContext;

    @Inject
    public DataManagerImplements(Context context, ApiHelper apiHelper) {
        this.mContext = context;
        this.mApiHelper = apiHelper;
    }

    @Override
    public LiveData<LoginResponseModel> login(String username, String password) {
        return mApiHelper.login(username, password);
    }
}
