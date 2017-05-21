package com.example.dell.actplus;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by DELL on 2017/4/19.
 */

public class Myadpter extends BaseAdapter {
    private List<ActItem> listData;
    //app context
    private static LayoutInflater inflater=null;
    private Context appContext;
    @Override
    public int getCount() {
        return listData.size();
    }
    public Myadpter(Context context, List<ActItem> data) {
        appContext = context;
        listData = data;
        //set inflater, why? see next
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        try {
            if (convertView == null) {
                //convertView = inflater.inflate(R.layout.cartitem_layout, null);
                //convertView = LayoutInflater.from(appContext).inflate(R.layout.cartitem_layout, parent, false);

                LayoutInflater inflater = LayoutInflater.from(appContext);
                convertView= (LinearLayout) inflater.inflate(R.layout.cartitem_layout, null);

                TextView title_text = (TextView) convertView.findViewById(R.id.act_name);
                TextView time_text = (TextView) convertView.findViewById(R.id.act_time);
                TextView place_text = (TextView) convertView.findViewById(R.id.act_place);
                ImageView poster = (ImageView) convertView.findViewById(R.id.act_poster);
                //set item
                ActItem actItem = listData.get(position);
                time_text.setText(actItem.getTime());
                title_text.setText(actItem.getTitle());
                place_text.setText(actItem.getActPlace());
                //probably null
                if (actItem.getImage() != null) {
                    poster.setImageBitmap(actItem.getImage());
                }
                return convertView;
            }
        } catch (Exception e) {
            Log.i("adapter getView", "error");
        }
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return listData.get(position).getId();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
}
