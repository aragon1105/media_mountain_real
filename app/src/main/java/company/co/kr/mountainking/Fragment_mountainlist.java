package company.co.kr.mountainking;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

/**
 * Created by Jaeheon on 2017-05-06.
 */

public class Fragment_mountainlist extends Fragment{

    //MyActivity 시작
    private RecyclerView mRecyclerView;
    private MountainAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.mountain_list_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.mt_recyclerview);

        // 퍼포먼스 증가시킴
        // RecylerView의 사이즈를 못바꾸게함
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)

        mAdapter = new MountainAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);




        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset(){

        myDataset = new ArrayList<>();
        myDataset.add(new MyData("#광교산","552","581m","광교산은 이런산이요",R.drawable.kwangkyo));
        myDataset.add(new MyData("#월출산","3217","810.7m","광교산은 이런산이요",R.mipmap.wolchul));
        myDataset.add(new MyData("#황매산","4533","1113m","광교산은 이런산이요",R.mipmap.hwangmae));
        myDataset.add(new MyData("#덕유산","2230","1614.2m","광교산은 이런산이요",R.mipmap.deogyu));
        myDataset.add(new MyData("#소백산","3794","1439.6m","광교산은 이런산이요",R.mipmap.sobaek));
        myDataset.add(new MyData("#속리산","982","1058.4m","광교산은 이런산이요",R.mipmap.songni));
        myDataset.add(new MyData("#지리산","4230","1915.4m","광교산은 이런산이요",R.mipmap.jiri));
        myDataset.add(new MyData("#설악산","5980","1707.9m","광교산은 이런산이요",R.mipmap.seolak));
        myDataset.add(new MyData("#한라산","7283","1947.2m","광교산은 이런산이요",R.mipmap.hanra));
        myDataset.add(new MyData("#관악산","1201","632.2m","광교산은 이런산이요",R.mipmap.gwanak));
        myDataset.add(new MyData("#치악산","1323","1282m","광교산은 이런산이요",R.mipmap.chiak));
        myDataset.add(new MyData("#북한산","954","835.5m","광교산은 이런산이요",R.mipmap.bukhan));
        myDataset.add(new MyData("#태백산","2282","1566.7m","광교산은 이런산이요",R.mipmap.taebaek));
        myDataset.add(new MyData("#도봉산","1589","740m","광교산은 이런산이요",R.mipmap.dobong));
        myDataset.add(new MyData("#내장산","875","763.5m","광교산은 이런산이요",R.mipmap.naejang));
        myDataset.add(new MyData("#계룡산","2231","846.4m","광교산은 이런산이요",R.mipmap.gyeryong));
    }



}


class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;

    // Provide a suitable constructor (depends on the kind of dataset)
    public MountainAdapter(ArrayList<MyData> myDataset) {

        mDataset = myDataset;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MountainAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview, parent, false);

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
        holder.mImageView.setImageResource(mDataset.get(position).img);
        holder.like.setText(mDataset.get(position).like);
        holder.heightTextView.setText(mDataset.get(position).height);

        // 클릭된 산의 index를 보냄
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Drawing_GPS.pos = position;
                //Toast.makeText(, "정복할 산이 설정되었습니다.", Toast.LENGTH_LONG);
                Log.d("아이템","클릭됨");

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
        public TextView heightTextView;
        public TextView like;
        public TextView explain;
        public View view;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.image);
            mTextView = (TextView)view.findViewById(R.id.textview);
            heightTextView = (TextView)view.findViewById(R.id.mt_height);
            like = (TextView)view.findViewById(R.id.textLike);
            explain = (TextView)view.findViewById(R.id.textExplain);
            this.view = view;
        }
    }


}

class MyData{
    public String text;
    public String height;
    public String like;
    public String explain;
    public int img;
    public MyData(String text, String like, String height, String ex, int img){
        this.text = text;
        this.like = like;
        this.height = height;
        this.img = img;
        this.explain = ex;
    }
}

