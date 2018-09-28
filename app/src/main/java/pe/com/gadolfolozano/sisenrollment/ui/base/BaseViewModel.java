package pe.com.gadolfolozano.sisenrollment.ui.base;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;

import pe.com.gadolfolozano.sisenrollment.data.DataManager;

/**
 * Created by adolfo on 5/09/18.
 * Esta clase serve de base para todos os novos ViewModel do app
 * define objetos que podem ser usados pelos ViewModel
 */

public abstract class BaseViewModel<N> extends ViewModel {

    private final DataManager mDataManager;
    private final ObservableBoolean mIsLoading = new ObservableBoolean(false);
    private N mNavigator;

    public BaseViewModel(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    public DataManager getDataManager() {
        return mDataManager;
    }

    public ObservableBoolean getIsLoading() {
        return mIsLoading;
    }

    public void setIsLoading(boolean isLoading) {
        mIsLoading.set(isLoading);
    }

    public N getNavigator() {
        return mNavigator;
    }

    public void setNavigator(N navigator) {
        this.mNavigator = navigator;
    }

}
