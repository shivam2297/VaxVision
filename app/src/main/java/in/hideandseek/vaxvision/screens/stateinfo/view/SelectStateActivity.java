package in.hideandseek.vaxvision.screens.stateinfo.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import butterknife.ButterKnife;
import in.hideandseek.vaxvision.R;

public class SelectStateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_state);

        ButterKnife.bind(this);
    }
}