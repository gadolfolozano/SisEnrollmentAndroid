package pe.com.gadolfolozano.sisenrollment.ui.splash;

import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import pe.com.gadolfolozano.sisenrollment.BR;
import pe.com.gadolfolozano.sisenrollment.R;
import pe.com.gadolfolozano.sisenrollment.databinding.ActivitySplashBinding;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseActivity;

/**
 * Created by adolfo on 5/09/18.
 */

public class SplashActivity extends BaseActivity<ActivitySplashBinding, SplashViewModel> implements SplashNavigator {

    @Inject
    SplashViewModel mSplashViewModel;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    public SplashViewModel getViewModel() {
        return mSplashViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSplashViewModel.setNavigator(this);
        mSplashViewModel.startApp();
    }

    @Override
    public void openLoginActivity() {

    }
}
