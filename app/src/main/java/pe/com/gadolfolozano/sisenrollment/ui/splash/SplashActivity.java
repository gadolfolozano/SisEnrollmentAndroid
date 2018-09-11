package pe.com.gadolfolozano.sisenrollment.ui.splash;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import pe.com.gadolfolozano.sisenrollment.BR;
import pe.com.gadolfolozano.sisenrollment.R;
import pe.com.gadolfolozano.sisenrollment.databinding.ActivitySplashBinding;
import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseActivity;
import pe.com.gadolfolozano.sisenrollment.ui.login.LoginActivity;
import pe.com.gadolfolozano.sisenrollment.ui.main.MainActivity;

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
        mSplashViewModel.getSession().observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(@Nullable LoginResponseModel loginResponseModel) {
                if (loginResponseModel != null
                        && BaseModel.STATUS_SUCCESS.equalsIgnoreCase(loginResponseModel.getStatus())) {
                    openMainActivity();
                } else {
                    openLoginActivity();
                }
            }
        });
    }

    @Override
    public void openLoginActivity() {
        Intent intent = LoginActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openMainActivity() {
        Intent intent = MainActivity.newIntent(SplashActivity.this);
        startActivity(intent);
        finish();
    }
}
