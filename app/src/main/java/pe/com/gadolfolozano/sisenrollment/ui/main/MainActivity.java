package pe.com.gadolfolozano.sisenrollment.ui.main;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import javax.inject.Inject;

import pe.com.gadolfolozano.sisenrollment.BR;
import pe.com.gadolfolozano.sisenrollment.R;
import pe.com.gadolfolozano.sisenrollment.databinding.ActivityMainBinding;
import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.model.UserModel;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseActivity;
import pe.com.gadolfolozano.sisenrollment.ui.login.LoginActivity;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    MainViewModel mMainViewModel;

    ActivityMainBinding mBinding;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainViewModel.setNavigator(this);

        mBinding = getViewDataBinding();

        setupViews();

        mMainViewModel.getUserSession().observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(@Nullable LoginResponseModel loginResponseModel) {
                if (loginResponseModel == null) {
                    return;
                }

                if (BaseModel.STATUS_SUCCESS.equalsIgnoreCase(loginResponseModel.getStatus())) {
                    setupUserInfo(loginResponseModel.getUser());
                }
            }
        });
    }

    private void setupUserInfo(UserModel userModel) {
        TextView tvUsername = null;

        try {
            tvUsername = mBinding.navigationDrawer.getHeaderView(0).findViewById(R.id.tvUserName);
        } catch (Exception e) {
            Log.e(MainActivity.class.getName(), "error ", e);
            //Do nothing
        }

        if (tvUsername != null) {
            tvUsername.setText(userModel.getName());
        }
    }

    private void setupViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        setTitle(R.string.text_title_courses);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mBinding.drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        mBinding.drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mBinding.navigationDrawer.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (mBinding.drawerLayout.isDrawerOpen(mBinding.navigationDrawer)) {
            mBinding.drawerLayout.closeDrawer(mBinding.navigationDrawer);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.logout:
                logout();
                break;
            default:
                //Do nothing
        }
        mBinding.drawerLayout.closeDrawer(mBinding.navigationDrawer);
        return true;
    }

    private void logout() {
        showLoading();
        mMainViewModel.logout().observe(this, new Observer<BaseModel>() {
            @Override
            public void onChanged(@Nullable BaseModel baseModel) {
                hideLoading();
                openLogin();
            }
        });
    }

    @Override
    public void openLogin() {
        startActivity(LoginActivity.newIntent(this));
        finish();
    }
}
