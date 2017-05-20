package company.co.kr.mountainking;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Jaeheon on 2017-05-13.
 */

public class Drawing_GPS extends Activity implements OnMapReadyCallback , View.OnClickListener{

    public static Integer pos = 0;// 산번호


    static final LatLng SUWON = new LatLng(37.280291, 127.007802);//수원 위치 는 기본적으로 띄어주기 위해서


    Button btn_str;
    Button btn_fini;
    Button btn_rst;
    Button btn_sts;

    private GoogleMap googleMap;

    private double s_lat;//시작 위도
    private double s_log;//시작 경도
    private double f_lat;//끝 위도
    private double f_long;//끝 경도




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_gps_activity);

        btn_str=(Button) findViewById(R.id.gps_btstart);
        btn_fini=(Button) findViewById(R.id.gps_finishbt);
        btn_rst=(Button) findViewById(R.id.gps_resetbt);
        btn_sts=(Button) findViewById(R.id.gps_statbt);


        MapFragment mapFragment =(MapFragment) getFragmentManager().findFragmentById(R.id.map);//구글맵 띄우기 !
        mapFragment.getMapAsync(this);

    }



    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;

        googleMap.addMarker(new MarkerOptions().position(SUWON).title("Suwon")).showInfoWindow();//수원만 항시 찍어줌

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SUWON));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(11));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

            case R.id.gps_btstart://처음에 시작 누르면 폴리 라인 만들고 나서 플래그 주어야하나? <-이것좀 잘 생각해 보고 처음 시작 위도 경도 위치 저장!
                PolylineOptions polyop=new PolylineOptions().add(new LatLng(36, 125));

                Toast.makeText(getApplicationContext(),"asdf",Toast.LENGTH_SHORT);
                Polyline poly=googleMap.addPolyline(polyop);

                break;
            case R.id.gps_finishbt://끝 위도 경도 위치 저장 !
                break;

            case R.id.gps_resetbt:
                break;

            case R.id.gps_statbt:
                break;

        }
    }
}
