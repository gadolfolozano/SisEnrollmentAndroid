package pe.com.gadolfolozano.sisenrollment.data;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import pe.com.gadolfolozano.sisenrollment.data.remote.ApiHelper;

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

}
