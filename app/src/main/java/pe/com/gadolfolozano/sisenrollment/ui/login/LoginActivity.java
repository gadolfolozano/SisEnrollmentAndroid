package pe.com.gadolfolozano.sisenrollment.ui.login;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import pe.com.gadolfolozano.sisenrollment.BR;
import pe.com.gadolfolozano.sisenrollment.R;
import pe.com.gadolfolozano.sisenrollment.databinding.ActivityLoginBinding;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseActivity;
import pe.com.gadolfolozano.sisenrollment.util.Constants;
import pe.com.gadolfolozano.sisenrollment.util.StringUtil;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    LoginViewModel mLoginViewModel;

    private ActivityLoginBinding mBinding;

    private CpfTextWatcher mCpfTextWatcher;
    private PasswordWatcher mPasswordWatcher;

    public static Intent newIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mLoginViewModel.setNavigator(this);

        mBinding = getViewDataBinding();

        mCpfTextWatcher = new CpfTextWatcher();
        mBinding.tvUsername.addTextChangedListener(mCpfTextWatcher);

        mPasswordWatcher = new PasswordWatcher();
        mBinding.tvPassword.addTextChangedListener(mPasswordWatcher);
        mBinding.tvPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE && validateInputs()) {
                    mBinding.buttonLogin.performClick();
                }
                return false;
            }
        });

        mBinding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonLoginClicked();
            }
        });
    }

    private void onButtonLoginClicked() {
        hideKeyboard();

        String cpf = StringUtil.cleanString(Constants.CPF_MASK, mBinding.tvUsername.getText().toString());
        String password = StringUtil.SHA1(mBinding.tvPassword.getText().toString());

        mLoginViewModel.login(cpf, password).observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(@Nullable LoginResponseModel loginResponseModel) {
                if (loginResponseModel == null) {
                    return;
                }

                if (loginResponseModel.isLoading()) {
                    showLoading();
                } else {
                    hideLoading();
                }
            }
        });
    }

    class CpfTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            mBinding.tvUsername.removeTextChangedListener(mCpfTextWatcher);
            mBinding.tvUsername.setText("");
            mBinding.tvUsername.append(StringUtil.formatString(Constants.CPF_MASK, editable.toString()));
            mBinding.tvUsername.addTextChangedListener(mCpfTextWatcher);
            checkEnableButton();
        }
    }

    class PasswordWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkEnableButton();
        }
    }

    private void checkEnableButton() {
        mBinding.buttonLogin.setEnabled(validateInputs());
    }

    private boolean validateInputs() {
        return mBinding.tvUsername.getText().length() == 14 &&
                mBinding.tvPassword.getText().length() >= 6;
    }

    @Override
    public void openMainActivity() {
        Toast.makeText(this, "will call a service and open Main Activity", Toast.LENGTH_LONG).show();
    }
}
