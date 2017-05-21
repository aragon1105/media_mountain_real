package company.co.kr.mountainking;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
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
import java.util.Timer;
import android.os.Handler;
import java.util.TimerTask;

/**
 * Created by Jaeheon on 2017-05-13.
 */

public class Drawing_GPS extends Activity implements OnMapReadyCallback{

    public static Integer pos = 0;// 산번호


    static final LatLng SUWON = new LatLng(37.280291, 127.007802);//수원 위치 는 기본적으로 띄어주기 위해서


    Button btn_str;
    Button btn_fini;
    Button btn_rst;
    Button btn_sts;

    private GoogleMap googleMap;
    private GpsInfo gps;

    private double s_lat;//시작 위도
    private double s_log;//시작 경도
    private double f_lat;//끝 위도
    private double f_long;//끝 경도
    private String latitude;
    private String longitude;

    private Boolean flag=true;

    private Timer timer;
    private Handler handler;
    private  PolylineOptions polyop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawing_gps_activity);

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {


            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an expanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.

            } else {

                // No explanation needed, we can request the permission.

                ActivityCompat.requestPermissions(this,
                        new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        0);

                // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
                // app-defined int constant. The callback method gets the
                // result of the request.
            }
        }


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
        googleMap.clear();//리셋 에서 넣어둘려고 잡아둔것임!
        /*googleMap.addPolyline(new PolylineOptions()
                .add(new LatLng(36,125), new LatLng(32,124))
                .width(5)
                .color(Color.RED));*/

        googleMap.addMarker(new MarkerOptions().position(SUWON).title("Suwon")).showInfoWindow();//수원만 항시 찍어줌

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SUWON));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(11));

        btn_str.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { gps = new GpsInfo(Drawing_GPS.this);
                // GPS 사용유무 가져오기
                if (gps.isGetLocation()) {

                    double latitude1 = gps.getLatitude();
                    double longitude1 = gps.getLongitude();


                    s_lat=latitude1;
                    s_log=longitude1;

                    latitude = String.valueOf(latitude1);
                    longitude = String.valueOf(longitude1);

              /*      Toast.makeText(getApplicationContext(),
                            "당신의 위치 - \n위도: " + gps.getLatitude() + "\n경도: " + gps.getLongitude(),
                            Toast.LENGTH_LONG).show();*/

                    //  locationStore(id,number,longitude,latitude);
                    //글의 번호 및 지금 회원의 아이디 위도 경도 를 디비에 저장시킴
                    Log.d("asd" ," "+longitude1+latitude1);//값이 0.0 0.0 이니깐 값 자체를 못받아 오는것 같은데
                    polyop.add(new LatLng(latitude1, longitude1));
                    polyop.color(Color.RED);
                    polyop.width(5);

                    googleMap.addPolyline(polyop);
                    Polyline polyline=googleMap.addPolyline(polyop);

                } else {
                    gps.showSettingsAlert();
                }


                //여기서 flag가 트루일때 무한루프로 돌게 만들어서 계속 위치 저장? 피니쉬 버튼 누르면 flag를 false로 바꾸기 이렇게 빠져나가기!

                while(flag==true) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    //여기서 슬립을 걸어줘서 멈추게 해야할듯!!!!!!
                    gps = new GpsInfo(Drawing_GPS.this);
                    // GPS 사용유무 가져오기
                    if (gps.isGetLocation()) {

                        double latitude1 = gps.getLatitude();
                        double longitude1 = gps.getLongitude();

                        latitude = String.valueOf(latitude1);
                        longitude = String.valueOf(longitude1);

                /*        Toast.makeText(getApplicationContext(),
                                "당신의 위치 - \n위도: " + gps.getLatitude() + "\n경도: " + gps.getLongitude(),
                                Toast.LENGTH_LONG).show();*/

                        //  locationStore(id,number,longitude,latitude);
                        //글의 번호 및 지금 회원의 아이디 위도 경도 를 디비에 저장시킴
                        polyop.add(new LatLng(latitude1, longitude1));
                        polyop.color(Color.RED);
                        polyop.width(5);

                        googleMap.addPolyline(polyop);
                        Polyline polyline=googleMap.addPolyline(polyop);

                    } else {
                        gps.showSettingsAlert();
                    }
                }


            }
        });
        btn_fini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=false;
                gps = new GpsInfo(Drawing_GPS.this);
                // GPS 사용유무 가져오기
                if (gps.isGetLocation()) {

                    double latitude1 = gps.getLatitude();
                    double longitude1 = gps.getLongitude();

                    f_lat=latitude1;
                    f_long=longitude1;

                    latitude = String.valueOf(latitude1);
                    longitude = String.valueOf(longitude1);

                  /*  Toast.makeText(getApplicationContext(),
                            "당신의 위치 - \n위도: " + gps.getLatitude() + "\n경도: " + gps.getLongitude(),
                            Toast.LENGTH_LONG).show();*/

                    //  locationStore(id,number,longitude,latitude);
                    //글의 번호 및 지금 회원의 아이디 위도 경도 를 디비에 저장시킴
                    polyop.add(new LatLng(latitude1, longitude1));
                    polyop.color(Color.RED);
                    polyop.width(5);

                    googleMap.addPolyline(polyop);
                    Polyline polyline=googleMap.addPolyline(polyop);

                } else {
                    gps.showSettingsAlert();
                }

            }
        });
        btn_rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                flag=true;//만약 다시 시작하게 되면 돌기 시작하여야 함으로 트루로 바꾼것임!


            }
        });
        btn_sts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }



}
