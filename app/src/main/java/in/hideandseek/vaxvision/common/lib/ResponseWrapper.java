package in.hideandseek.vaxvision.common.lib;

import in.hideandseek.vaxvision.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResponseWrapper<T> implements Callback<T> {
    private ResponseCallback<T> mResponseCallback;
    private ErrorMessageResolver mErrorMessageResolver;

    public ResponseWrapper(ResponseCallback<T> mResponseCallback, ErrorMessageResolver mErrorMessageResolver) {
        this.mResponseCallback = mResponseCallback;
        this.mErrorMessageResolver = mErrorMessageResolver;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            if (response != null) {
                try {
                    ErrorModel errorModel = (ErrorModel) response.body();
                    if (errorModel != null)
                        mResponseCallback.onFailure(new ErrorResponse(mErrorMessageResolver.resolve(errorModel.getErrorCode())));
                    else
                        mResponseCallback.onSuccess((T) response.body());
                } catch (Exception e) {
//                    ErrorResponse error = new ErrorResponse(R.string.some_error_occured);
//                    error.setErrorExp(e);
//                    mResponseCallback.onFailure(error);

//                    if exception in converting to error model
//                    then convert to success model
                    mResponseCallback.onSuccess((T) response.body());
                }
            } else {
                mResponseCallback.onFailure(new ErrorResponse(R.string.some_error_occured));
            }
        } else {
            ErrorResponse error = new ErrorResponse(R.string.some_error_occured);
            mResponseCallback.onFailure(error);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {

        ErrorResponse error = new ErrorResponse(R.string.some_error_occured);
        error.setErrorExp(t);

        mResponseCallback.onFailure(error);

    }
}
