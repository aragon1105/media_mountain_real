package company.co.kr.mountainking;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

    public static Integer pos=100 ;// 산번호
    public static boolean Continue=true;
   // public static int timer=0;

    public static Dialog statDialog2;
    //lat : 37.312690191690834  long: 127.03076560224923

    static final LatLng GGMT = new LatLng(37.312690191690834, 127.03076560224923);//수원 위치 는 기본적으로 띄어주기 위해서

   // static final LatLng GWang=new LatLng()
    Button btn_str;
    Button btn_fini;
    Button btn_rst;
    Button btn_sts;

    private GoogleMap googleMap;
    private GpsInfo gps;

    private double s_lat=0;//시작 위도
    private double s_log=0;//시작 경도
    private double f_lat=0;//끝 위도
    private double f_long=0;//끝 경도
    private String latitude;
    private String longitude;
    private String mtname;

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
    private Handler handler2;
    private  PolylineOptions polyop;
    private  TextView gpsname;

    private boolean flag1=false;
    private boolean flag2=false;
    private boolean flag3=false;
    private boolean flag4=false;
    private boolean flag5=false;

    MarkerOptions m1,m2,m3,m4,m5,m11,m22,m33,m44,m55;


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


        gpsname = (TextView) findViewById(R.id.gps_mtname);

        if(pos==0){
            mtname="광교산";
        }
        else if(pos==1){
            mtname="지리산";
        }
        else if(pos==2){
            mtname="설악산";
        }
        else if(pos==3){
            mtname="한라산";
        }
        else if(pos==100){
            mtname="oo산";
        }
        gpsname.setText(mtname+" 정복중");

        handler=new Handler(){
            public void handleMessage(Message message) {

                timer = message.arg1;

                gps = new GpsInfo(Drawing_GPS.this);
                    if (gps.isGetLocation()) {

                        double latitude1 = gps.getLatitude();
                        double longitude1 = gps.getLongitude();

                        positpoint(latitude1,longitude1);

                        latitude = String.valueOf(latitude1);
                        longitude = String.valueOf(longitude1);
                        //이쯤에서 longitude1 과 latitude1 을 함수로 넘겨서 1/2 1/4 1/6 지점을 판별해보자!!

                        f_lat = latitude1;
                        f_long = longitude1;
                        if(timer%60==0){
                            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude1, longitude1), 16));
                            googleMap.addPolyline(polyop
                                    .add(new LatLng(latitude1, longitude1))
                                    .width(5)
                                    .color(Color.GREEN));

                        }
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


        gpsname.setText(mtname+" 정복중");
        googleMap = map;
        googleMap.clear();//리셋 에서 넣어둘려고 잡아둔것임!

        polyop=new PolylineOptions();


        googleMap.moveCamera(CameraUpdateFactory.newLatLng(GGMT));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        m1=new MarkerOptions().position(new LatLng(37.312690191690834,127.03076560224923)).alpha(0.4f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
        m2=new MarkerOptions().position(new LatLng(37.3198922579834,127.04203124267818)).alpha(0.4f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
        m3=new MarkerOptions().position(new LatLng(37.32233092283948,127.03966952133092)).alpha(0.4f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
        m4=new MarkerOptions().position(new LatLng(37.32721903312214,127.03709969785915)).alpha(0.4f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
        m5=new MarkerOptions().position(new LatLng(37.32816133934622,127.03809482865788)).alpha(0.4f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));

        googleMap.addMarker(m1);
        googleMap.addMarker(m2);
        googleMap.addMarker(m3);
        googleMap.addMarker(m4);
        googleMap.addMarker(m5);

        /*
           1. lat : 37.312690191690834  long: 127.03076560224923 //시작지점
           3. 아마 천년 약수터?
                lat: 37.3198922579834
long:127.04203124267818
                [정승범] [오후 7:51] 4. lat: 37.32233092283948
long:127.03966952133092
                [정승범] [오후 7:52] 5. lat:37.32721903312214
long:127.03709969785915
                [정승범] [오후 7:53] 6. lat:37.32816133934622
long:127.03809482865788//정상지점
*/
        //마커 찍어야할곳 5개

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
                    f_lat=latitude1;
                    f_long=longitude1;
                    latitude = String.valueOf(latitude1);
                    longitude = String.valueOf(longitude1);

                    //googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude1,longitude1)).title("Start")).showInfoWindow();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude1,longitude1),16));

                    googleMap.addPolyline(polyop
                            .add(new LatLng(latitude1,longitude1), new LatLng(latitude1,longitude1))
                            .width(5)
                            .color(Color.GREEN));

                } else {
                    gps.showSettingsAlert();
                }

            }
        });
        btn_fini.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gpsname.setText(mtname+" 등산 끝!");
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

                    //googleMap.addMarker(new MarkerOptions().position(new LatLng(latitude1,longitude1)).title("Finish")).showInfoWindow();
                    googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude1,longitude1),16));


                    googleMap.addPolyline(polyop
                            .add(new LatLng(latitude1,longitude1))
                            .width(5)
                            .color(Color.GREEN));

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

                hour=0;
                minute=0;
                second=0;

                flag1=false;
                flag2=false;
                flag3=false;
                flag4=false;
                flag5=false;





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

                final Dialog statDialog = new Dialog(Drawing_GPS.this);
                statDialog.setContentView(R.layout.average_dialog);

                TextView time_dialog = (TextView) statDialog.findViewById(R.id.time_dialog);
                TextView distance_dialog = (TextView) statDialog.findViewById(R.id.distance_dialog);
                TextView velocity_dialog = (TextView) statDialog.findViewById(R.id.velocity_dialog);
                time_dialog.setText(hour+"시간"+minute+"분"+second +"초");
                distance_dialog.setText(dist+"m");
                velocity_dialog.setText(avg_speed+"m/s");

                Button close = (Button) statDialog.findViewById(R.id.close_dialog);
                ImageView icon_dialog = (ImageView) statDialog.findViewById(R.id.icon_dialog);
                icon_dialog.setImageResource(R.drawable.mountain_cc);

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        statDialog.dismiss();
                    }
                });

                statDialog.show();
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    public void positpoint(double latitude3,double longitude3){
  /*
           1. lat : 37.312690191690834  long: 127.03076560224923
           3. 아마 천년 약수터?
                lat: 37.3198922579834
long:127.04203124267818
                [정승범] [오후 7:51] 4. lat: 37.32233092283948
long:127.03966952133092
                [정승범] [오후 7:52] 5. lat:37.32721903312214
long:127.03709969785915
                [정승범] [오후 7:53] 6. lat:37.32816133934622
long:127.03809482865788
*/
        if(37.31269019169083<latitude3&&latitude3<37.31269019169084&&127.0307656022492<longitude3&&longitude3<127.0307656022493) {
            if (flag1 == true) {

                return ;
            }
            m11=new MarkerOptions().position(new LatLng(37.312690191690834,127.03076560224923)).alpha(1.0f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
            googleMap.addMarker(m11);

            final Dialog startdialog = new Dialog(Drawing_GPS.this);
            startdialog.setContentView(R.layout.startpoint_dialog);

            Button close = (Button) startdialog.findViewById(R.id.startpointbt);

            close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startdialog.dismiss();
                }
            });

            startdialog.show();
            flag1=true;
            return ;
        }
        else if(37.319892257983<latitude3&&latitude3<37.319892257984&&127.0420312426781<longitude3&&longitude3<127.0420312426782) {
            if (flag2 == true) {
                return ;
            }

            m22=new MarkerOptions().position(new LatLng(37.3198922579834,127.04203124267818)).alpha(1.0f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
            googleMap.addMarker(m22);

            final Dialog savepoint1 = new Dialog(Drawing_GPS.this);
            savepoint1.setContentView(R.layout.savepoint_dialog);

            Button close4 = (Button) savepoint1.findViewById(R.id.savepointbt);

            close4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    savepoint1.dismiss();
                }
            });

            savepoint1.show();

            flag2=true;
            return ;
        }
        else if(37.3223309228394<latitude3&&latitude3<37.3223309228395&&127.0396695213309<longitude3&&longitude3<127.0396695213310) {
            if (flag3 == true) {
                return ;
            }

            m33=new MarkerOptions().position(new LatLng(37.32233092283948,127.03966952133092)).alpha(1.0f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
            googleMap.addMarker(m33);


            final Dialog savepoint2 = new Dialog(Drawing_GPS.this);
            savepoint2.setContentView(R.layout.savepoint_dialog);

            Button close3 = (Button) savepoint2.findViewById(R.id.savepointbt);

            close3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    savepoint2.dismiss();
                }
            });

            savepoint2.show();

            flag3=true;
            return ;
        }
        else if(37.3272190331221<latitude3&&latitude3<37.3272190331222&&127.0370996978591<longitude3&&longitude3<127.0370996978592) {
            if (flag4 == true) {
                return ;
            }

            m44=new MarkerOptions().position(new LatLng(37.32721903312214,127.03709969785915)).alpha(1.0f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
            googleMap.addMarker(m44);

            final Dialog statDialog2 = new Dialog(Drawing_GPS.this);
            statDialog2.setContentView(R.layout.average_dialog);


            final Dialog savepoint3 = new Dialog(Drawing_GPS.this);
            savepoint3.setContentView(R.layout.savepoint_dialog);

            Button close2 = (Button) savepoint3.findViewById(R.id.savepointbt);

            close2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    savepoint3.dismiss();
                }
            });

            savepoint3.show();

            flag4=true;
            return ;
        }
        else if(37.3281613393462<latitude3&&latitude3<37.3281613393463&&127.0380948286578<longitude3&&longitude3<127.0380948286579) {
            if (flag5 == true) {
                return ;
            }

            m55=new MarkerOptions().position(new LatLng(37.32816133934622,127.03809482865788)).alpha(1.0f).icon(BitmapDescriptorFactory.fromResource(R.drawable.flag2));
            googleMap.addMarker(m55);


            final Dialog finishpoint = new Dialog(Drawing_GPS.this);
            finishpoint.setContentView(R.layout.finishpoint_dialog);

            Button close1 = (Button) finishpoint.findViewById(R.id.finishpointbt);

            close1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finishpoint.dismiss();
                }
            });

            finishpoint.show();

            flag5=true;
            return ;
        }

        return ;

    }

    }

