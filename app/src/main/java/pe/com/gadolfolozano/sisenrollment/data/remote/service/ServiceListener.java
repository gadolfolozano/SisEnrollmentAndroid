package pe.com.gadolfolozano.sisenrollment.data.remote.service;

/**
 * Created by adolfo on 9/09/18.
 */

public interface ServiceListener<T> {
    void onSucess(T response);

    void onError(Throwable t);
}
