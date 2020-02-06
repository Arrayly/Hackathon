package project.hackathon;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class LoginActivity extends AppCompatActivity {

    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_card_overlap);

        mButton = findViewById(R.id.sign_in_btn);

        mButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(final View v) {
               startActivity(new Intent(LoginActivity.this,DashboardActivity.class));
            }
        });



    }
}
