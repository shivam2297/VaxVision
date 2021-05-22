package in.hideandseek.vaxvision.screens.downloadcert.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import in.hideandseek.vaxvision.R;
import in.hideandseek.vaxvision.common.BaseActivity;
import in.hideandseek.vaxvision.common.lib.ErrorResponse;
import in.hideandseek.vaxvision.common.libapi.certificate.request.DownloadCertificateApiRequest;
import in.hideandseek.vaxvision.screens.downloadcert.manager.CertificateServiceManager;
import in.hideandseek.vaxvision.screens.downloadcert.presenter.CertPresenterImpl;
import in.hideandseek.vaxvision.screens.downloadcert.presenter.ICertPresenter;

public class CertificateActivity extends BaseActivity implements ICertificateView {

    @BindView(R.id.et_refid)
    TextInputEditText mrefIDET;

    @BindView(R.id.tv_prompt)
    AppCompatTextView mPromptTV;

    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_TXN_ID = "KEY_TXN_ID";

    private String txnID;
    private String token;

    private ICertPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_certificate);

        ButterKnife.bind(this);

        Intent intent = getIntent();

        txnID = intent.getStringExtra(KEY_TXN_ID);
        token = intent.getStringExtra(KEY_TOKEN);

        mPresenter = new CertPresenterImpl(new CertificateServiceManager(new DownloadCertificateApiRequest()));
        mPresenter.onViewBeingCreated(this);

        mPromptTV.setText(R.string.please_enter_your_reference_id_for_downloading_the_certificate);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onViewBeingDestroyed();
        super.onDestroy();
    }

    @Override
    public void onCertDownloadSuccess(String data) {
        mPromptTV.setText(R.string.cert_downloaded);
    }

    @Override
    public void onApiFailure(ErrorResponse errorResponse) {
        super.onApiFailure(errorResponse);
        showSnackBar(errorResponse.getErrorMessage());
    }

    @OnClick(R.id.btn_download_cert)
    void downloadBtnTapped() {
        if (!isValidRefID()) {
            mPromptTV.setText(R.string.invalid_ref_id);
            return;
        }

        mPresenter.downloadCert(mrefIDET.getText().toString().trim(), token);
    }

    private boolean isValidRefID() {
        if ("".equals(mrefIDET.getText().toString().trim()))
            return false;
        return true;
    }
}