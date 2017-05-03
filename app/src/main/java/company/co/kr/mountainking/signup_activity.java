package company.co.kr.mountainking;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jaeheon on 2017-04-24.
 */

public class signup_activity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        Button create_account = (Button) findViewById(R.id.bt_createaccount);
        Button kakao_lingking = (Button) findViewById(R.id.bt_kakaolinking);
        TextView title = (TextView) findViewById(R.id.signup_title);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "TT Masters DEMO Birds Regular.otf");
        Typeface pricedown = Typeface.createFromAsset(getAssets(), "pricedown bl.ttf");
        Typeface male = Typeface.createFromAsset(getAssets(), "PentaGrams Malefissent.ttf");
        Typeface tangak = Typeface.createFromAsset(getAssets(), "tangak.otf");
        title.setTypeface(tangak);

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(signup_activity.this, Login.class);
                startActivity(i);
                finish();
            }
        });

    }
}
