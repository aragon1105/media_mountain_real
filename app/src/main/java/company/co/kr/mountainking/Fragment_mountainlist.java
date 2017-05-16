package company.co.kr.mountainking;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
    MainActivity intent;


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

        mAdapter.setItemClick(new MountainAdapter.ItemClick(){
            @Override
            public void onClick(View view, int position){
                intent.displayView(3);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        initDataset();
    }

    private void initDataset(){

        myDataset = new ArrayList<>();
        myDataset.add(new MyData("#지리산",R.mipmap.jiri));
        myDataset.add(new MyData("#설악산",R.mipmap.hanra));
        myDataset.add(new MyData("#한라산",R.mipmap.seolak));
    }



}


class MountainAdapter extends RecyclerView.Adapter<MountainAdapter.ViewHolder> {
    private ArrayList<MyData> mDataset;
    private ItemClick itemClick;

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
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int Position = position;


        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position).text);
        holder.mImageView.setImageResource(mDataset.get(position).img);

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(itemClick != null){

                    //itemClick.onClick(view, Position);
                }
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
        public View view;

        public ViewHolder(View view) {
            super(view);
            mImageView = (ImageView)view.findViewById(R.id.image);
            mTextView = (TextView)view.findViewById(R.id.textview);
            this.view = view;
        }
    }

    //아이템 클릭시 실행 함수
    public interface ItemClick{
        public void onClick(View view, int position);

    }

    //아이템 클릭시 실행 함수를 등록하는 함수
    public void setItemClick(ItemClick itemClick){


        this.itemClick = itemClick;
    }




}

class MyData{
    public String text;
    public int img;
    public MyData(String text, int img){
        this.text = text;
        this.img = img;
    }
}

