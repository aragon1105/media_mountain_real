package company.co.kr.mountainking;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jaeheon on 2017-04-24.
 */

public class signup_activity extends AppCompatActivity implements View.OnClickListener{

    private ProgressDialog pDialog;
    EditText id_et;
    EditText name_et;
    EditText password_et;
    EditText password_et2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        Button create_account = (Button) findViewById(R.id.bt_createaccount);
        Button kakao_lingking = (Button) findViewById(R.id.bt_kakaolinking);

        id_et = (EditText) findViewById(R.id.signup_id_et);
        name_et = (EditText) findViewById(R.id.signup_name_et);
        password_et = (EditText) findViewById(R.id.signup_pw_et);
        password_et2 = (EditText) findViewById(R.id.signup_pw2_et);

        TextView title = (TextView) findViewById(R.id.signup_title);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "TT Masters DEMO Birds Regular.otf");
        Typeface pricedown = Typeface.createFromAsset(getAssets(), "pricedown bl.ttf");
        Typeface male = Typeface.createFromAsset(getAssets(), "PentaGrams Malefissent.ttf");
        Typeface tangak = Typeface.createFromAsset(getAssets(), "tangak.otf");
        title.setTypeface(tangak);
        create_account.setOnClickListener(this);

    }
    private void registerUser(final String id, final String name, final String password) {
        String tag_string_req = "req_register";

        pDialog.setMessage("Ready...");
        showDialog();

        //서버에 연결
        StringRequest strReq = new StringRequest(Request.Method.POST, "http://52.78.108.188/mtking/index.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {

                        Toast.makeText(getApplicationContext(), "회원가입이 완료되었습니다. 환영합니다!", Toast.LENGTH_SHORT).show();

                        String regMsg = jObj.getString("reg_msg");
                        Toast.makeText(getApplicationContext(),
                                regMsg, Toast.LENGTH_LONG).show();

                        // Launch login activity
                        Intent intent = new Intent(
                                signup_activity.this,
                                Login.class);
                        startActivity(intent);
                        finish();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),
                                errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("tag", "register");
                params.put("id", id);
                params.put("name", name);
                params.put("password", password);

                //회원정보 서버로 보내기

                return params;
            }

        };

        // Adding request to request queue
        GlobalApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.bt_createaccount:

                String id = id_et.getText().toString();
                String password = password_et.getText().toString();
                String name = name_et.getText().toString();
                String password2 = password_et2.getText().toString();


                if (!(password.equals(password2)))
                    Toast.makeText(signup_activity.this, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
                else {

                    if (!id.isEmpty() && !password.isEmpty()&&!name.isEmpty()) {
                        registerUser(id,name,password);
                    } else {
                        Toast.makeText(signup_activity.this, "모든 정보를 입력해 주세요.", Toast.LENGTH_SHORT).show();
                    }
                }
                break;

        }
    }

}
