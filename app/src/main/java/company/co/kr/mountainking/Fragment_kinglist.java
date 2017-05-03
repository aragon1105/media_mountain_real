package company.co.kr.mountainking;

//import android.app.ListFragment;
import android.support.v4.app.ListFragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;


/**
 * Created by Jaeheon on 2017-04-25.
 */

public class Fragment_kinglist extends ListFragment {

    kinglistAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        adapter = new kinglistAdapter();
        setListAdapter(adapter);

        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.mountains), "1500.2m", "지리산", "정승범", 5, "김준영", 250, "경남 지리시");
        adapter.addItem(ContextCompat.getDrawable(getActivity(), R.drawable.mountains), "1400.5m", "설악산", "박재현", 3, "김준영", 277, "강원 설악시");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        ListViewItem item = (ListViewItem) l.getItemAtPosition(position);

        String name = item.getMt_name();
        String speedking = item.getSpeedking();

        super.onListItemClick(l, v, position, id);
    }

    public void addItem(Drawable icon, String height, String name, String jungbok, Integer count, String speedking, Integer record, String location) {
        adapter.addItem(icon, height, name, jungbok, count, speedking, record, location);
    }
}