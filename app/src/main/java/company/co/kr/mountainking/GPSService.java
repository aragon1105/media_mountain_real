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

        while (true) {

            Message msg = Message.obtain(); //메시지 객체 획득

            if (Drawing_GPS.Continue == true) {

                try {

                    Thread.sleep(1000);
                    timer++;
                    msg.arg1 =timer;
                    if(timer%60==0){
                        messenger.send(msg);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }

            } else {
                stopSelf();
                return ;
            }
        }
    }
}
