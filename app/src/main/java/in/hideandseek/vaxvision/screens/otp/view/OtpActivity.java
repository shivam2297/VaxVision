package in.hideandseek.vaxvision.screens.otp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.otp.request.ConfirmOtpApiRequest;
import in.hideandseek.vaxvision.common.libapi.otp.request.GenerateOtpApiRequest;
import in.hideandseek.vaxvision.screens.downloadcert.view.CertificateActivity;
import in.hideandseek.vaxvision.screens.otp.manager.OtpServiceManager;
import in.hideandseek.vaxvision.screens.otp.presenter.IOtpPresenter;
import in.hideandseek.vaxvision.screens.otp.presenter.OtpPresenterImpl;
import okhttp3.ResponseBody;

public class OtpActivity extends BaseActivity implements IOtpView {

    @BindView(R.id.tv_prompt)
    AppCompatTextView mPromptMsgTV;

    @BindView(R.id.et_phone)
    TextInputEditText mPhoneET;

    @BindView(R.id.et_otp)
    TextInputEditText mOtpET;

    @BindView(R.id.til_otp)
    TextInputLayout mOtpLayout;

    @BindView(R.id.btn_gen_otp)
    Button mGenOtpBtn;

    @BindView(R.id.btn_submit_otp)
    Button mConfirmOtpBtn;

    private final String PROMPT_MOBILE = "Please enter 10 digit registered mobile number";
    private final String PROMPT_OTP = "Please enter the otp received via sms on your registered mobile number";

    private IOtpPresenter mPresenter;

    private String txnId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        ButterKnife.bind(this);

        mOtpLayout.setVisibility(View.GONE);
        mConfirmOtpBtn.setVisibility(View.GONE);

        mPromptMsgTV.setText(PROMPT_MOBILE);

        mPresenter = new OtpPresenterImpl(new OtpServiceManager(new GenerateOtpApiRequest(), new ConfirmOtpApiRequest()));
        mPresenter.onViewBeingCreated(this);

    }

    @OnClick(R.id.btn_gen_otp)
    void generateOtpBtnTapped() {
        mPresenter.generateOtp(mPhoneET.getText().toString());
    }

    @OnClick(R.id.btn_submit_otp)
    void confirmOtpTapped() {
        mPresenter.confirmOtp(mOtpET.getText().toString(), txnId);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onViewBeingDestroyed();
        super.onDestroy();
    }

    @Override
    public void onSuccessGenerateOtp(String txnId) {
        this.txnId = txnId;
        mPromptMsgTV.setText(PROMPT_OTP);
        mPhoneET.setFocusable(false);
        mPhoneET.setFocusableInTouchMode(false);

        mOtpLayout.setVisibility(View.VISIBLE);
        mConfirmOtpBtn.setVisibility(View.VISIBLE);
        mGenOtpBtn.setVisibility(View.GONE);
        mOtpET.requestFocus();
    }

    @Override
    public void onOtpConfirmed(String token) {
        Intent intent = new Intent(this, CertificateActivity.class);
        intent.putExtra(CertificateActivity.KEY_TOKEN, token);
        intent.putExtra(CertificateActivity.KEY_TXN_ID, txnId);
        launchActivity(intent);
        finish();
    }

    @Override
    public void onFailure(ErrorResponse errorResponse) {
        showSnackBar(errorResponse.getErrorMessage());
    }
}