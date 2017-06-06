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
        myDataset.add(new MyData("#광교산","552","581m","광교산은 수원의 북쪽에서 오는 찬바람을 막아주며 시가지를 품에 안고 있는 수원의 주산으로 원래 이름은 광악산이었으나 고려 태조 왕건에 의해 광교산으로 명명되었다고 전해진다.",R.drawable.kwangkyo));
        myDataset.add(new MyData("#월출산","3217","810.7m","월출산은 봄에는 철쭉과 진달래가 만발하고, 여름에는 푸르른 신록이 기암괴석과 어우러져 위용을 뽐내며, 가을에는 울긋불긋 단풍이 물들고, 겨울에는 암봉과 능선을 따라 눈이 내려 온통 하얀 색을 연출한다.",R.mipmap.wolchul));
        myDataset.add(new MyData("#황매산","4533","1113m","황매산은 주봉우리가 크게 하봉·중봉·상봉으로 나뉜다. 삼라만상을 전시해 놓은 듯한 모산재의 바위산이 절경이며 그 밖에 북서쪽 능선을 타고 펼쳐지는 황매평전의 철쭉 군락이 볼 만한 곳으로 꼽힌다." ,R.mipmap.hwangmae));
        myDataset.add(new MyData("#덕유산","2230","1614.2m","덕유산은 지리산, 설악산에 이어 등산인들의 사랑을 받는 내륙지역의 가장 아름다운 산으로 알려져있고, 아름다운 계곡으로 명성이 높다.",R.mipmap.deogyu));
        myDataset.add(new MyData("#소백산","3794","1439.6m","소백산은 장엄하나 완만한 산등성이와 끝없이 펼쳐지는 운해 그리고 울창한 산림이 수려한 계곡과 어울려 장관을 이루어 많은 등산객이 찾아든다. ",R.mipmap.sobaek));
        myDataset.add(new MyData("#속리산","982","1058.4m","속리산은 봄에 산벚꽃, 여름에 푸른 소나무, 가을에 붉게 물든 단풍, 겨울에 설경으로 계절마다 고유한 아름다움을 드러낸다. 그러나 그 아름다움의 백미는 역시 화강암이 만든 다양한 크기의 기암괴석들이다.",R.mipmap.songni));
        myDataset.add(new MyData("#지리산","4230","1915.4m","지리산은 천왕봉 바로 아래 위치하고 있는 천왕샘을 비롯하여 주능선 곳곳에서 아름다운 계곡과 폭포가 형성되어 탐방객의 발길을 붙잡는다.",R.mipmap.jiri));
        myDataset.add(new MyData("#설악산","5980","1707.9m","설악산은 천의 얼굴을 가져 봄이면 진달래와 철쭉이 만발, 여름이면 신록의 푸르름이 협곡과 어우러져 장관을 이루고, 가을에는 붉게 물든 단풍이 암봉 사이로 불타오르며, 겨울에는 설국을 이룬다.",R.mipmap.seolak));
        myDataset.add(new MyData("#한라산","7283","1947.2m","한라산은 폭포와 주상절리 등 아름다운 화산지형이 펼쳐지고, 해발고도에 따라 아열대·온대·냉대로 식생의 변화가 뚜렷하다. 봄의 철쭉·진달래·유채, 가을의 단풍, 겨울의 설경과 운해가 절경이다.",R.mipmap.hanra));
        myDataset.add(new MyData("#관악산","1201","632.2m","관악산은 능선마다 바위가 많고 큰 바위 봉우리가 연결되어 웅장한 산세를 이룬다. 북한산·남한산·계양산 등과 함께 서울분지를 이중으로 둘러싼 자연의 방벽으로, 옛 서울의 요새지를 이루었다",R.mipmap.gwanak));
        myDataset.add(new MyData("#치악산","1323","1282m","치악산은 아름다운 계곡과 원폭포로 볼거리가 많다. 이밖에 구룡사·상원사·석경사·국향사·보문사·입석사와 같은 오래된 절이 많이 있다.",R.mipmap.chiak));
        myDataset.add(new MyData("#북한산","954","835.5m","북한산은 백두산, 지리산, 금강산, 묘향산과 함께 대한민국 오악에 포함되는 명산이다. 삼국시대에는 부아악이라고 불렀는데 아기를 등에 업고 있는 형상을 닮았다고 지어진 이름이다.",R.mipmap.bukhan));
        myDataset.add(new MyData("#태백산","2282","1566.7m","태백산은 산 정상에는 예로부터 하늘에 제사를 지내던 천제단이 있어 매년 개천절에 태백제를 열고 천제를 지낸다. 볼거리로는 산 정상의 고산식물과 주목 군락, 6월 초순에 피는 철쭉이 유명하다.",R.mipmap.taebaek));
        myDataset.add(new MyData("#도봉산","1589","740m","도봉산은 산 전체가 큰 바위로 이루어져 있다. 각 봉우리는 기복과 굴곡이 다양하여 절경을 이루고 있으며 그외 도봉산의 능선을 이루고 있다. 그 중 선인봉은 암벽 등반코스로 유명하다.",R.mipmap.dobong));
        myDataset.add(new MyData("#내장산","875","763.5m","내장산은 가을철 단풍이 아름답고, 조선 8경의 하나로 꼽혔다. 금선폭포, 용수폭포, 신선문, 기름바위 등의 명소가 있다.",R.mipmap.naejang));
        myDataset.add(new MyData("#계룡산","2231","846.4m","계룡산은 풍수지리에서도 우리나라 4대 명산으로 꼽힐 뿐 아니라, 관광지로도 제5위를 차지하여 국립공원으로 지정되어 있다. 특히, 계룡팔경은 경치가 아름다워 많은 관광객이 찾아든다.",R.mipmap.gyeryong));
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
        holder.explain.setText(mDataset.get(position).explain);
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

