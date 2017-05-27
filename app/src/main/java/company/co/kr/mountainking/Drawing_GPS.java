package company.co.kr.mountainking;

import android.Manifest;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
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
    public static boolean Continue=true;
   // public static int timer=0;


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

    private Boolean flag=false;

    private int timer;
    private double dist;
    private double avg_speed;
    private int hour;
    private int minute;
    private int second;

    private int s_timer;//시작 시간
    private int f_timer;//끝나는 시간
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

            }
        }

        btn_str=(Button) findViewById(R.id.gps_btstart);
        btn_fini=(Button) findViewById(R.id.gps_finishbt);
        btn_rst=(Button) findViewById(R.id.gps_resetbt);
        btn_sts=(Button) findViewById(R.id.gps_statbt);

        handler=new Handler(){
            public void handleMessage(Message message){

                timer=message.arg1;

                gps = new GpsInfo(Drawing_GPS.this);
                if (gps.isGetLocation()) {

                    double latitude1 = gps.getLatitude();
                    double longitude1 = gps.getLongitude();

                    latitude = String.valueOf(latitude1);
                    longitude = String.valueOf(longitude1);

                    f_lat=latitude1;
                    f_long=longitude1;

                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude1,longitude1),12));
                    googleMap.addPolyline(polyop
                            .add(new LatLng(latitude1,longitude1))
                            .width(5)
                            .color(Color.RED));


                } else {
                    gps.showSettingsAlert();
                }
            }
        };

        MapFragment mapFragment =(MapFragment) getFragmentManager().findFragmentById(R.id.map);//구글맵 띄우기 !
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        googleMap = map;
        googleMap.clear();//리셋 에서 넣어둘려고 잡아둔것임!

        polyop=new PolylineOptions();

        googleMap.moveCamera(CameraUpdateFactory.newLatLng(SUWON));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        btn_str.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) { gps = new GpsInfo(Drawing_GPS.this);

                Drawing_GPS.Continue=true;

                Intent intent = new Intent(Drawing_GPS.this, GPSService.class);
                Messenger messenger=new Messenger(handler);
                intent.putExtra("MESSENGER",messenger);
                startService(intent);

                if (gps.isGetLocation()) {

                    double latitude1 = gps.getLatitude();
                    double longitude1 = gps.getLongitude();


                    s_lat=latitude1;//시작 지점 위치 저장
                    s_log=longitude1;//시작 지점 위치 저장
                    latitude = String.valueOf(latitude1);
                    longitude = String.valueOf(longitude1);

                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude1,longitude1)).title("Start")).showInfoWindow();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude1,longitude1),12));

                    googleMap.addPolyline(polyop
                            .add(new LatLng(latitude1,longitude1), new LatLng(latitude1,longitude1))
                            .width(5)
                            .color(Color.RED));

                } else {
                    gps.showSettingsAlert();
                }

            }
        });
        btn_fini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //flag=false;

                Drawing_GPS.Continue=false;
                //여기서도 f_timer 시간을 넣어야함
                gps = new GpsInfo(Drawing_GPS.this);
                // GPS 사용유무 가져오기
                if (gps.isGetLocation()) {

                    double latitude1 = gps.getLatitude();
                    double longitude1 = gps.getLongitude();

                    f_lat=latitude1;//끝지점 위치 저장
                    f_long=longitude1;//끝지점 위치 저장

                    latitude = String.valueOf(latitude1);
                    longitude = String.valueOf(longitude1);

                    googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude1,longitude1)).title("Finish")).showInfoWindow();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude1,longitude1),12));


                    googleMap.addPolyline(polyop
                            .add(new LatLng(latitude1,longitude1))
                            .width(5)
                            .color(Color.RED));

                } else {
                    gps.showSettingsAlert();
                }
            }
        });
        btn_rst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                googleMap.clear();
                polyop=new PolylineOptions();


            }
        });
        btn_sts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("asdf",""+timer);//타이머가 이동한 시간 거리 계산도하자 !
                CalDistance cal=new CalDistance(s_lat,s_log,f_lat,f_long);

                dist=cal.getDistance();
                dist=(int)(dist*100)/100.0;
                avg_speed=dist/timer;
                avg_speed=(int)(avg_speed*100)/100.0;
                hour=timer/3600;
                minute=timer%3600/60;
                second=timer%3600%60;

                //timer , dist , avg_speed 타이머 이동거리 평균속도 세가지 넘기면됨!!

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

}