package com.zhaoss.changeitemlistview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private int itemHeight;
    private LinearLayoutManager linearLayoutManager;
    private MyAdapter myAdapter;
    private MyRecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] datas = new String[30];
        for (int x=1; x<=30; x++){
            datas[x-1] = x+"";
        }

        recyclerView = (MyRecyclerView) findViewById(R.id.recyclerView);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //设置adapter
        myAdapter = new MyAdapter(this, datas);
        recyclerView.setAdapter(myAdapter);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
                if(newState == 100){
                    Log.i("Log", firstPosition+"   aaaaaaaa");
                    Map<Integer, MyAdapter.MyViewHolder> map = myAdapter.getMap();
                    Set<Map.Entry<Integer, MyAdapter.MyViewHolder>> set = map.entrySet();
                    for (Map.Entry<Integer, MyAdapter.MyViewHolder> entry : set){
                        Integer key = entry.getKey();
                        if(key!=firstPosition && key!=firstPosition+1){
                            Log.i("Log", key+"   "+entry.getValue().textView.getText().toString());
                            ViewGroup.LayoutParams layoutParams = entry.getValue().imageView.getLayoutParams();
                            layoutParams.height = itemHeight;
                            entry.getValue().imageView.setLayoutParams(layoutParams);
                        }
                    }
                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {//当recyclerView滚动时调用此代码
                //得到处于最上的item条目position
                int firstPosition = linearLayoutManager.findFirstVisibleItemPosition();
                //得到最上且可视的itemView
                ImageView lastView = myAdapter.getView(firstPosition).imageView;
                //得到对应应该改变的view
                ImageView view = myAdapter.getView(firstPosition + 1).imageView;
                if (dy == 0 && itemHeight == 0) {//初始化第一个item为放大状态
                    itemHeight = lastView.getHeight();
                    ViewGroup.LayoutParams layoutParams = lastView.getLayoutParams();
                    layoutParams.height = itemHeight * 2;
                    lastView.setLayoutParams(layoutParams);
                    setTextSize(myAdapter.getView(firstPosition).textView, 2);
                } else {
                    int lastHeight = lastView.getMeasuredHeight();
                    int height = view.getHeight();

                    //放大或缩小
                    ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                    layoutParams.height = height + dy;
                    //控制高度最大值
                    if (layoutParams.height > itemHeight * 2) layoutParams.height = itemHeight * 2;
                    //控制高度最小值
                    if (layoutParams.height < itemHeight) layoutParams.height = itemHeight;
                    setTextSize(myAdapter.getView(firstPosition + 1).textView, (float) layoutParams.height / itemHeight);
                    view.setLayoutParams(layoutParams);

                    ViewGroup.LayoutParams lastLayoutParams = lastView.getLayoutParams();
                    lastLayoutParams.height = lastHeight - dy;
                    if (lastLayoutParams.height > itemHeight * 2) lastLayoutParams.height = itemHeight * 2;
                    if (lastLayoutParams.height < itemHeight) lastLayoutParams.height = itemHeight;
                    setTextSize(myAdapter.getView(firstPosition).textView, (float) lastLayoutParams.height / itemHeight);
                    lastView.setLayoutParams(lastLayoutParams);
                }
            }
        });
    }

    /**
     * 设置文字大小
     */
    private void setTextSize(TextView textView, float i) {
        textView.setTextSize(20*i);
    }
}