package company.co.kr.mountainking;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity2);

        Button btn_login = (Button) findViewById(R.id.btn_login);
        Button btn_signup = (Button) findViewById(R.id.btn_signup);
        EditText edit_id = (EditText) findViewById(R.id.edit_id);
        EditText edit_pw = (EditText) findViewById(R.id.edit_password);
        Typeface tangak = Typeface.createFromAsset(getAssets(), "tangak.otf");
        TextView title = (TextView) findViewById(R.id.login_title);
        title.setTypeface(tangak);


        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);

            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this, signup_activity.class);
                startActivity(i);

            }
        });



    }
}
