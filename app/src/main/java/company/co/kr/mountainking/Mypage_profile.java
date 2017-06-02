package company.co.kr.mountainking;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by YEONGBIN on 2016-11-08.
 */
// 사진 수정하기 할 때 넘어가는 엑티비티 class
public class Mypage_profile extends AppCompatActivity implements View.OnClickListener{

    public static final String UPLOAD_KEY = "image";

    private static int RESULT_LOAD_IMAGE = 1;

    private int PICK_IMAGE_REQUEST = 1;

    private Button buttonChoose;
    private Button buttonUpload;

    private ImageView imageView;
    String id;
    private Bitmap bitmap;

    private Uri filePath;

    ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage_profile);
        if(Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(Color.GRAY);
        }

        id = GlobalApplication.getString(getApplicationContext(),"email");
        buttonChoose = (Button) findViewById(R.id.buttonChoose);
        buttonUpload = (Button) findViewById(R.id.buttonUpload);

        imageView = (ImageView) findViewById(R.id.imageView);

        buttonChoose.setOnClickListener(this);
        buttonUpload.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == buttonChoose) {
            showFileChooser(); // 사진 고르기 버튼 클릭시 함수 호출
        }
        if(v == buttonUpload){
            profile(getStringImage(bitmap),id);
        }

    }
// 사진 앨범 인텐트
    private void showFileChooser() {
        Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        i.setType("image/*");

        try {
            i.putExtra("return-data", true);
            startActivityForResult(Intent.createChooser(i,"Select Picture"), RESULT_LOAD_IMAGE);
        } catch (ActivityNotFoundException e) {
            // Do nothing for now
        }
    }
    // 사진 이미지 결과값 비트맵 형식으로 가져오고 이미지뷰에 가져오기
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    // 이미지값 get 비트맵 형식, compress함수를 화질 축소
    public String getStringImage(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 20, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }


    private void profile(final String image, final String email) {
        // Tag used to cancel the request
        String tag_string_req = "req_profile_upload";

        StringRequest strReq = new StringRequest(Request.Method.POST,
                "http://202.30.23.51/~sap16t11/foodfolder/index.php", new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        //loading.dismiss();
                        Toast.makeText(Mypage_profile.this, "업로드 성공", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Mypage_profile.this,Fragment_mypage.class);
                        intent.putExtra("email", id);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(Mypage_profile.this, "업로드 실패", Toast.LENGTH_SHORT).show();
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
                params.put("tag", "profileimage");
                params.put("image", image);
                params.put("email",email);
                return params;
            }

        };

        // Adding request to request queue
        GlobalApplication.getInstance().addToRequestQueue(strReq, tag_string_req);
    }


}


