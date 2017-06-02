package company.co.kr.mountainking;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by Jaeheon on 2017-05-06.
 */

public class Fragment_mypage extends Fragment {

    ImageView myimage;
  //  Button b;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mypage_fragment,null);
        myimage  = (ImageView)view.findViewById(R.id.myimage);
        /*b = (Button)view.findViewById(R.id.b);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "aaaaa", Toast.LENGTH_SHORT).show();
            }
        });*/
        myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "aaaaa", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getActivity(),Mypage_profile.class);
                startActivity(i);
                Log.d("string","sslsss");
            }
        });
        return view;

    }


}
