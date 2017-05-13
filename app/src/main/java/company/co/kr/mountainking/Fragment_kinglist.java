package company.co.kr.mountainking;

import android.app.ListFragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;


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

class kinglistAdapter extends BaseAdapter {

    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>();

    public kinglistAdapter(){
    }


    // Adapter에 사용되는 데이터의 개수를 리턴 : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size();
    }


    // position에 위치한 데이터를화면에 출력하는데 사용될 View를 리턴 : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "mountain_list_item" 레이아웃을 inflate하여 convertView 참조 획득.
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.mountain_list_item, parent, false);
        }

        ImageView mt_Image = (ImageView) convertView.findViewById(R.id.list_mt_image);
        TextView mt_height = (TextView) convertView.findViewById(R.id.mountain_height);
        TextView mt_name = (TextView) convertView.findViewById(R.id.mountain_name);
        TextView jungbok = (TextView) convertView.findViewById(R.id.jungbok_king);
        TextView jungbok_count = (TextView) convertView.findViewById(R.id.jungbok_count);
        TextView speedking = (TextView) convertView.findViewById(R.id.speed_king);
        TextView speed_record = (TextView) convertView.findViewById(R.id.speed_record);
        TextView mt_location = (TextView) convertView.findViewById(R.id.mountain_location);


        // Data set(listViewItemList)에서 postion에 위치한 데이터 참조 획득
        ListViewItem Item = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        mt_Image.setImageDrawable(Item.getIcon());
        mt_height.setText(Item.getMt_height());
        mt_name.setText(Item.getMt_name());
        jungbok.setText(Item.getJungbok());
        jungbok_count.setText(Item.getJungbok_count().toString());
        speedking.setText(Item.getSpeedking());
        speed_record.setText(Item.getSpeed_record().toString());
        mt_location.setText(Item.getMt_location());

        return convertView;

    }

    // 지정한 위치(postion)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴 : 필수 구현
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }


    // 아이템 데이터 추가를 위한 함수.
    public void addItem(Drawable icon, String height, String name, String jungbok, Integer count, String speedking, Integer record, String location){
        ListViewItem item = new ListViewItem();

        item.setIcon(icon);
        item.setMt_height(height);
        item.setMt_name(name);
        item.setJungbok(jungbok);
        item.setJungbok_count(count);
        item.setSpeedking(speedking);
        item.setSpeed_record(record);
        item.setMt_location(location);

        listViewItemList.add(item);

    }


}

class ListViewItem {

    private Drawable iconDrawable;
    private String mt_name;
    private String mt_height;
    private String jungbok;
    private Integer jungbok_count;
    private String speedking;
    private Integer speed_record;
    private String mt_location;


    public void setIcon(Drawable icon) { iconDrawable = icon;}
    public void setMt_name(String name) { mt_name = name;}
    public void setMt_height(String height) { mt_height = height;}
    public void setJungbok(String person) { jungbok = person;}
    public void setJungbok_count(Integer count){ jungbok_count = count;}
    public void setSpeedking(String person) { speedking = person;}
    public void setSpeed_record(Integer record) { speed_record = record;}
    public void setMt_location(String location) { mt_location = location;}


    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getMt_name() { return this.mt_name; }
    public String getMt_height() { return this.mt_height;}
    public String getJungbok() {return this.jungbok;}
    public Integer getJungbok_count() {return  this.jungbok_count;}
    public String getSpeedking() { return this.speedking;}
    public Integer getSpeed_record() { return  this.speed_record;}
    public String getMt_location() { return  this.mt_location;}

}