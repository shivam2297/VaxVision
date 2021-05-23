package in.hideandseek.vaxvision.screens.downloadcert.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import okhttp3.ResponseBody;

public class CertificateActivity extends BaseActivity implements ICertificateView {

    @BindView(R.id.et_refid)
    TextInputEditText mrefIDET;

    @BindView(R.id.tv_prompt)
    AppCompatTextView mPromptTV;

    @BindView(R.id.cert_activity_root)
    ConstraintLayout mRootLayout;

    public static final String KEY_TOKEN = "KEY_TOKEN";
    public static final String KEY_TXN_ID = "KEY_TXN_ID";

    private String txnID;
    private String token;

    private ICertPresenter mPresenter;

    String fileName;
    private String refID;

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

        isStoragePermissionGranted(this);
    }

    @Override
    protected void onDestroy() {
        mPresenter.onViewBeingDestroyed();
        super.onDestroy();
    }

    @Override
    public void onCertDownloadSuccess(ResponseBody data) {
        mPromptTV.setText(R.string.cert_downloaded);
        openFile(writeFileToDisk(data));
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
        refID = mrefIDET.getText().toString().trim();
        mPresenter.downloadCert(mrefIDET.getText().toString().trim(), token);
    }

    private boolean isValidRefID() {
        if ("".equals(mrefIDET.getText().toString().trim()))
            return false;
        return true;
    }

    public static boolean isStoragePermissionGranted(Activity activity) {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ContextCompat.checkSelfPermission(activity, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED)
                return true;
            else {
                //Toast.makeText(getApplicationContext(), "Permission is revoked",Toast.LENGTH_SHORT).show();
                //Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            //Toast.makeText(getApplicationContext(), "Permission is revoked",Toast.LENGTH_SHORT).show();
            //Log.v(TAG, "Permission is granted");
            return true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 &&
                        grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    finish();
                }
                return;
        }
    }

    private boolean writeFileToDisk(ResponseBody body) {
        try {
            // todo change the file location/name according to your needs
            Date date = new Date();
            fileName = refID + "_" + new SimpleDateFormat("dd_MM_yy_hh_mm_ss").format(date) + ".pdf";
            //File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            java.io.File certPdfFile = new java.io.File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + "/" + fileName);
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                byte[] fileReader = new byte[4096];
                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(certPdfFile);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                }
                outputStream.flush();
                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    private void openFile(boolean writtenToDisk) {
        if (writtenToDisk) {
            Toast.makeText(this, "Your certificate '" + fileName + "' successfully downloaded and saved on your device", Toast.LENGTH_LONG).show();
            //File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), fileName);
            java.io.File file = new java.io.File(Environment
                    .getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    + "/" + fileName);
            Log.w("FILE_DOWNLOADED", (file == null) ? "file null" : "file found at" + fileName);
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.fromFile(file), "application/pdf");
                intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Snackbar.make(mRootLayout, "No PDF reader found to open this file.", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}