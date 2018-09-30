package pe.com.gadolfolozano.sisenrollment.ui.login;

import android.app.AlertDialog;
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

import javax.inject.Inject;

import pe.com.gadolfolozano.sisenrollment.BR;
import pe.com.gadolfolozano.sisenrollment.R;
import pe.com.gadolfolozano.sisenrollment.databinding.ActivityLoginBinding;
import pe.com.gadolfolozano.sisenrollment.model.BaseModel;
import pe.com.gadolfolozano.sisenrollment.model.LoginResponseModel;
import pe.com.gadolfolozano.sisenrollment.ui.base.BaseActivity;
import pe.com.gadolfolozano.sisenrollment.ui.main.MainActivity;
import pe.com.gadolfolozano.sisenrollment.util.StringUtil;

/**
 * Tela de login do app
 */
public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    @Inject
    LoginViewModel mLoginViewModel;

    private ActivityLoginBinding mBinding;

    private EmailTextWatcher mEmailTextWatcher;

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

        mEmailTextWatcher = new EmailTextWatcher();
        mBinding.tvUsername.addTextChangedListener(mEmailTextWatcher);

        PasswordWatcher mPasswordWatcher = new PasswordWatcher();
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

        // Define o comportamento do botao de Entrar
        mBinding.buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonLoginClicked();
            }
        });
    }

    /**
     * Logica do botao de Entrar
     */
    private void onButtonLoginClicked() {
        hideKeyboard();

        //Obtem o email e password da tela
        String email = mBinding.tvUsername.getText().toString();
        String password = StringUtil.sha1(mBinding.tvPassword.getText().toString());

        //Chama o servicio de login e aguarda resposta
        mLoginViewModel.login(email, password).observe(this, new Observer<LoginResponseModel>() {
            @Override
            public void onChanged(@Nullable LoginResponseModel loginResponseModel) {
                if (loginResponseModel == null) {
                    return;
                }

                //Mostra loading ate obter resposta
                if (loginResponseModel.isLoading()) {
                    showLoading();
                } else {
                    //Resposta do servicio
                    if (BaseModel.STATUS_SUCCESS.equalsIgnoreCase(loginResponseModel.getStatus())) {
                        saveSession(loginResponseModel);
                    } else {
                        hideLoading();
                        showErrorMessage();
                    }
                }
            }
        });
    }

    /**
     * Guarda a sessao do usuario no aparelho
     */
    private void saveSession(LoginResponseModel loginResponseModel) {
        mLoginViewModel.saveSession(loginResponseModel).observe(this, new Observer<BaseModel>() {
            @Override
            public void onChanged(@Nullable BaseModel baseModel) {
                if (baseModel == null) {
                    return;
                }

                hideLoading();
                if (BaseModel.STATUS_SUCCESS.equalsIgnoreCase(baseModel.getStatus())) {
                    openMainActivity();
                } else {
                    showErrorMessage();
                }
            }
        });
    }

    /**
     * Mostra a mensagem de erro
     */
    private void showErrorMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this)
                .setTitle(R.string.text_login_error_title)
                .setMessage(R.string.text_login_error_message)
                .setPositiveButton(R.string.text_login_error_button, null);
        builder.create().show();
    }

    class EmailTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
        }

        @Override
        public void afterTextChanged(Editable editable) {
            checkEnableButton();
        }
    }

    class PasswordWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            //Do nothing
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
        return StringUtil.validateEmail(mBinding.tvUsername.getText().toString()) &&
                mBinding.tvPassword.getText().length() >= 6;
    }

    @Override
    public void openMainActivity() {
        startActivity(MainActivity.newIntent(this));
        finish();
    }
}
