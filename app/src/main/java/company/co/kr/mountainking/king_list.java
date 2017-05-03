package company.co.kr.mountainking;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Jaeheon on 2017-04-21.
 */

public class king_list extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.king_list_activity);
        Fragment_kinglist kingFragment = (Fragment_kinglist) getSupportFragmentManager().findFragmentById(R.id.kinglist_fragment);



        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");
        kingFragment.addItem((ContextCompat.getDrawable(this, R.drawable.mountains)), "800m", "광교산", "정승범", 5, "김준영", 250, "경기 수원시");

    }
}
