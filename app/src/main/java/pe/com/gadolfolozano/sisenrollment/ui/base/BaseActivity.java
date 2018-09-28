package pe.com.gadolfolozano.sisenrollment.ui.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import dagger.android.AndroidInjection;
import pe.com.gadolfolozano.sisenrollment.util.CommonUtils;

/**
 * Created by adolfo on 5/09/18.
 *
 * Esta clase serve de base para todos os novos activities do app
 * e tambem abstrae a implementacao da injecao de dependecias
 * ao mesmo tempo que define a arquitetura mvvm
 */

public abstract class BaseActivity<T extends ViewDataBinding, V extends BaseViewModel> extends AppCompatActivity {

    private ProgressDialog mProgressDialog;
    private T mViewDataBinding;
    private V mViewModel;

    /**
     * Sobrecargar para definir a variable de binding
     * requerido para a implementacao da arquitetura mvvm
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Sobrecargar para definir o view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        performDependencyInjection();
        super.onCreate(savedInstanceState);
        performDataBinding();
    }

    /**
     * Requerido para a implementacao da arquitetura mvvm
     */
    public T getViewDataBinding() {
        return mViewDataBinding;
    }


    /**
     * Ocultar o teclado do celular
     */
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * Ocultar a vista de loading
     */
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    /**
     * Mostrar a vista de loading
     */
    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    /**
     * Requerido pela biblioteca de injecao de dependencias
     */
    public void performDependencyInjection() {
        AndroidInjection.inject(this);
    }

    /**
     * Requerido para a implementacao da arquitetura mvvm
     */
    private void performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId());
        this.mViewModel = mViewModel == null ? getViewModel() : mViewModel;
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }
}

