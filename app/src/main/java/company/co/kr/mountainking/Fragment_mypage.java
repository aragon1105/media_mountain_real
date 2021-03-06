package company.co.kr.mountainking;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.BubbleChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.BubbleData;
import com.github.mikephil.charting.data.BubbleDataSet;
import com.github.mikephil.charting.data.BubbleEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jaeheon on 2017-05-06.
 */

public class Fragment_mypage extends Fragment {

    ImageView myimage;
    ImageButton mycalory;
    ImageButton myhiketime;
    LinearLayout chart_container;

    private RecyclerView mymtRecyclerView;
    private MyMTAdapter mymtAdapter;
    private RecyclerView.LayoutManager mymtLayoutManager;
    private ArrayList<MymtData> mymtDataset;

    private int weight=48;
    private double met=7.5;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.mypage_fragment, container ,false);


        myimage  = (ImageView)view.findViewById(R.id.myimage);
        mycalory = (ImageButton)view.findViewById(R.id.calory);
        myhiketime = (ImageButton)view.findViewById(R.id.hiketime);

        chart_container = (LinearLayout)view.findViewById(R.id.chart_container);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        myimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(getActivity(), Mypage_profile.class);
                startActivity(i);
                Log.d("string","sslsss");
            }
        });


        mymtLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        mymtRecyclerView = (RecyclerView) view.findViewById(R.id.mymt_recyclerview);
        mymtRecyclerView.setLayoutManager(mymtLayoutManager);
        // specify an adapter (see also next example)

        mymtAdapter = new MyMTAdapter(mymtDataset);
        mymtRecyclerView.setAdapter(mymtAdapter);



        final LineChart lineChart = new LineChart(getActivity());
        lineChart.setLayoutParams(params);

        ArrayList<Entry> entries = new ArrayList<>();
        if(Drawing_GPS.aflag==true) {
            entries.add(new Entry(123f, 0));//시간으로 할것!
            entries.add(new Entry(0f, 1));
            entries.add(new Entry(139f, 2));
            entries.add(new Entry(110f, 3));
            entries.add(new Entry(99f, 4));
            entries.add(new Entry(0f, 5));
            entries.add(new Entry(154, 6));
            entries.add(new Entry(Drawing_GPS.amin, 7));

        }
        else{
            entries.add(new Entry(123f, 0));//시간으로 할것!
            entries.add(new Entry(0f, 1));
            entries.add(new Entry(139f, 2));
            entries.add(new Entry(110f, 3));
            entries.add(new Entry(99f, 4));
            entries.add(new Entry(0f, 5));
            entries.add(new Entry(154, 6));
//            entries.add(new Entry(Drawing_GPS.amin, 7));

        }
        LineDataSet dataset = new LineDataSet(entries, "# 단위(분, m/s)");

        ArrayList<String> labels = new ArrayList<String>();
       if(Drawing_GPS.aflag==true) {
           labels.add("5/29");
           labels.add("5/30");
           labels.add("6/1");
           labels.add("6/2");
           labels.add("6/3");
           labels.add("6/4");
           labels.add("6/5");
           labels.add("6/9");
       }
       else{
           labels.add("5/29");
           labels.add("5/30");
           labels.add("6/1");
           labels.add("6/2");
           labels.add("6/3");
           labels.add("6/4");
           labels.add("6/5");
//           labels.add("6/9");

       }

        LineData data = new LineData(labels, dataset);
        lineChart.setData(data);
        dataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        dataset.setDrawCubic(true);
        dataset.setDrawFilled(true);
        lineChart.animateY(4000);


        final BubbleChart bubbleChart = new BubbleChart(getActivity());
        bubbleChart.setLayoutParams(params);

        ArrayList<BubbleEntry> entries2 = new ArrayList<>();
        if(Drawing_GPS.aflag==true) {
            int a=Drawing_GPS.amin;
            //int a=122;
            int k;
            k= (int) (7.5*(3.5*weight*a)/1000* 5);
            entries2.add(new BubbleEntry(0, 733f,733f));//이거 칼로리임!
            entries2.add(new BubbleEntry(1,0f,0f));
            entries2.add(new BubbleEntry(2, 839f, 839f));
            entries2.add(new BubbleEntry(3, 790f, 790f));
            entries2.add(new BubbleEntry(4,804f,804f));
            entries2.add(new BubbleEntry(5, 0f,0f));
            entries2.add(new BubbleEntry(6 ,754f, 754f));
       /*     int a=Drawing_GPS.amin;
            //int a=122;
            int k;
            k= (int) (7.5*(3.5*weight*a)/1000* 5);*/
            entries2.add(new BubbleEntry(7, k,k));

        }
        else{
            entries2.add(new BubbleEntry(0, 733f,733f));//이거 칼로리임!
            entries2.add(new BubbleEntry(1,0f,0f));
            entries2.add(new BubbleEntry(2, 839f, 839f));
            entries2.add(new BubbleEntry(3, 790f, 790f));
            entries2.add(new BubbleEntry(4,804f,804f));
            entries2.add(new BubbleEntry(5, 0f,0f));
            entries2.add(new BubbleEntry(6 ,754f, 754f));
//            entries.add(new Entry(Drawing_GPS.amin, 7));

        }
        BubbleDataSet caldataset = new BubbleDataSet(entries2, "# 단위(1칼로리)");

        ArrayList<String> labels2 = new ArrayList<String>();
        if(Drawing_GPS.aflag==true) {
            labels2.add("5/29");
            labels2.add("5/30");
            labels2.add("6/1");
            labels2.add("6/2");
            labels2.add("6/3");
            labels2.add("6/4");
            labels2.add("6/5");
            labels2.add("6/9");
        }
        else{
            labels2.add("5/29");
            labels2.add("5/30");
            labels2.add("6/1");
            labels2.add("6/2");
            labels2.add("6/3");
            labels2.add("6/4");
            labels2.add("6/5");
//           labels.add("6/9");

        }

        BubbleData caldata = new BubbleData(labels2, caldataset);
        bubbleChart.setData(caldata);
        caldataset.setColors(ColorTemplate.COLORFUL_COLORS); //
        caldataset.setHighlightEnabled(true);
        bubbleChart.animateY(4000);


        chart_container.addView(lineChart);

        mycalory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chart_container.removeAllViews();
                chart_container.addView(bubbleChart);
                bubbleChart.animateY(4000);

            }
        });

        myhiketime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chart_container.removeAllViews();
                chart_container.addView(lineChart);
                lineChart.animateY(4000);

            }
        });





        return view;


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset(){

        mymtDataset = new ArrayList<>();



        if(Drawing_GPS.aflag==true) {
            mymtDataset.add(new MymtData("광교산", "5회", R.drawable.kwangkyo));
            mymtDataset.add(new MymtData("지리산", "8회", R.drawable.jiri));
            mymtDataset.add(new MymtData("설악산", "2회", R.drawable.seolak));
            mymtDataset.add(new MymtData("한라산", "3회", R.drawable.hanra));
            mymtDataset.add(new MymtData("관악산", "4회", R.mipmap.gwanak));
            mymtDataset.add(new MymtData("내장산", "1회", R.mipmap.naejang));
            mymtDataset.add(new MymtData("치악산", "1회", R.mipmap.chiak));
            mymtDataset.add(new MymtData("계룡산", "2회", R.mipmap.gyeryong));
            mymtDataset.add(new MymtData("도봉산", "3회", R.mipmap.dobong));
        }
        else{
            mymtDataset.add(new MymtData("광교산","4회", R.drawable.kwangkyo));
            mymtDataset.add(new MymtData("지리산","8회", R.drawable.jiri));
            mymtDataset.add(new MymtData("설악산","2회", R.drawable.seolak));
            mymtDataset.add(new MymtData("한라산","3회", R.drawable.hanra));
            mymtDataset.add(new MymtData("관악산", "4회", R.mipmap.gwanak));
            mymtDataset.add(new MymtData("내장산", "1회", R.mipmap.naejang));
            mymtDataset.add(new MymtData("치악산", "1회", R.mipmap.chiak));
            mymtDataset.add(new MymtData("계룡산", "2회", R.mipmap.gyeryong));
            mymtDataset.add(new MymtData("도봉산", "3회", R.mipmap.dobong));
        }
    }


}



class MyMTAdapter extends RecyclerView.Adapter<MyMTAdapter.ViewHolder> {
    private ArrayList<MymtData> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyMTAdapter(ArrayList<MymtData> mymtDataset) {

        mDataset = mymtDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyMTAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_mountain_list_item, parent, false);

        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).text);
        holder.mTextView2.setText(mDataset.get(position).text2);
        holder.mImageView.setImageResource(mDataset.get(position).img);

        // 클릭된 산의 index를 보냄
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }



    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public ImageView mImageView;
        public TextView mTextView;
        public TextView mTextView2;
        public View view;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.my_mt_image);
            mTextView = (TextView)view.findViewById(R.id.my_mt_name);
            mTextView2 = (TextView)view.findViewById(R.id.my_mt_count);
            this.view = view;
        }
    }


}

class MymtData{
    public String text;
    public String text2;
    public int img;
    public MymtData(String text, String text2, int img){
        this.text = text;
        this.text2 = text2;
        this.img = img;
    }
}