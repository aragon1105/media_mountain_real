package company.co.kr.mountainking;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.ListViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Jaeheon on 2017-04-25.
 */

public class kinglistAdapter extends BaseAdapter {

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
