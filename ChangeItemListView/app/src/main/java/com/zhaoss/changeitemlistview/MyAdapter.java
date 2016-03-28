package com.zhaoss.changeitemlistview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zhaoss on 2016/3/25.
 */
public class MyAdapter extends RecyclerView.Adapter {

    private String[] datas;
    private Activity activity;
    //将所有的viewHolder存储起来,方便调用改变其中view的大小
    private Map<Integer, MyViewHolder> map;

    public MyAdapter(Activity activity, String[] datas){
        this.activity = activity;
        this.datas = datas;

        map = new HashMap<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(activity, R.layout.item, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        MyViewHolder vh = (MyViewHolder) holder;
        map.put(position, vh);
        vh.textView.setText(datas[position]);
        int x = position % 3;
        if (x == 0) {
            vh.imageView.setImageResource(R.mipmap.image1);
        } else if (x == 1) {
            vh.imageView.setImageResource(R.mipmap.image2);
        } else if (x == 2) {
            vh.imageView.setImageResource(R.mipmap.image3);
        }
        vh.imageView.setScaleType(ImageView.ScaleType.CENTER);
    }

    public MyViewHolder getView(int position){
        return map.get(position);
    }

    public Map<Integer, MyViewHolder> getMap(){
        return map;
    }

    @Override
    public int getItemCount() {
        return datas.length;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        RelativeLayout relativeLayout;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            textView = (TextView) itemView.findViewById(R.id.textView);
            relativeLayout = (RelativeLayout) itemView.findViewById(R.id.relativeLayout);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            Bitmap bitmap = BitmapFactory.decodeResource(activity.getResources(), R.mipmap.image1);
            ViewGroup.LayoutParams layoutParams = imageView.getLayoutParams();
            layoutParams.height = bitmap.getHeight()/2;
            imageView.setLayoutParams(layoutParams);
        }
    }
}