package company.co.kr.mountainking;

import android.app.IntentService;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by tofot_000 on 2017-05-27.
 */

public class GPSService extends IntentService{


    public GPSService() {
        super("Gps Service");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        int timer=0;
        Bundle extras = intent.getExtras();
        Messenger messenger = (Messenger) extras.get("MESSENGER");


        Log.d("asdf","1");

        while (true) {

            Log.d("asdf","2");
            Message msg = Message.obtain(); //메시지 객체 획득
           // MainActivity.updated_value += increment;    //보내려는 값을 바꿔줌
                  //보내려는 값을 메신저 객체에 저장
            //msg.obj = MainActivity.updated_value;



            if (Drawing_GPS.Continue == true) {

                msg.arg1 =timer;

                Log.d("asdf","3");
                try {

                    Log.d("asdf","4");
                    Thread.sleep(60000);
                  //  msg.arg1 =timer;
                   // messenger.send(msg);
                    timer++;
                    Log.d("asdf","5");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (timer % 60 == 0) {
                    try {
                      //  msg.arg1 =timer;
                        messenger.send(msg);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }

                }
            } else {
                stopSelf();
                return ;
            }
        }
    }
}
