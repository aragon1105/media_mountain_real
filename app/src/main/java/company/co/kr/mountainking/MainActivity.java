package company.co.kr.mountainking;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;


/**
 * Created by Jaeheon on 2017-04-21.
 */

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.king_list_activity);
        ImageButton mtlist_bt = (ImageButton) findViewById(R.id.bt_mountain_list);
        ImageButton honor_bt = (ImageButton) findViewById(R.id.bt_honor);
        ImageButton king_bt = (ImageButton) findViewById(R.id.bt_king_list);
        ImageButton map_bt = (ImageButton) findViewById(R.id.bt_map);
        ImageButton mypage_bt = (ImageButton) findViewById(R.id.bt_mypage);


        //Fragment_kinglist kingFragment = (Fragment_kinglist) getSupportFragmentManager().findFragmentById(R.id.kinglist_fragment);
        //Fragment_kinglist kingFragment = (Fragment_kinglist) getSupportFragmentManager().findFragmentById(R.id.kinglist_fragment);


/*
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
*/
        displayView(1);
        king_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(1);
            }
        });

        honor_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(0);
            }
        });

        map_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Drawing_GPS.class);
                startActivity(i);
                Log.d("값은:", Drawing_GPS.pos.toString());
            }
        });

        mypage_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(3);
            }
        });

        mtlist_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayView(4);
            }
        });

    }

    public void displayView(int position){
        Fragment fragment = null;
        ListFragment listfragment = null;


        switch (position){
            case 0:
                fragment = new Fragment_honor();
                break;
            case 1:
                listfragment = new Fragment_kinglist();
                break;
            case 2:

                break;
            case 3:
                fragment = new Fragment_mypage();
                break;

            case 4:
                fragment = new Fragment_mountainlist();

            default:
                break;
        }

        if(fragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).commit();
        }

        else if (listfragment != null){
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, listfragment).commit();
        }

    }

    public void changeActivity(Class _class){
        Intent intent = new Intent(this, _class);
        startActivity(intent);
        //finish();
    }
}
