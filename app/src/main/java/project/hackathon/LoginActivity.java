package project.hackathon;

import android.app.ActivityOptions;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import static maes.tech.intentanim.CustomIntent.customType;

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
                startActivityWithTransition();

            }
        });


    }

    private void startActivityWithTransition() {
        startActivity(new Intent(LoginActivity.this, DashboardActivity.class));
        customType(LoginActivity.this, "fadein-to-fadeout");

//*left-to-right
//*right-to-left
//*bottom-to-up
//*up-to-bottom
//*fadein-to-fadeout
//*rotateout-to-rotatein
    }
}
