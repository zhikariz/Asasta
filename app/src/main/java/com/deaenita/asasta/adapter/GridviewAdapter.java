package com.deaenita.asasta.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.deaenita.asasta.R;

public class GridviewAdapter extends BaseAdapter {
    private Context mContext;

    public GridviewAdapter(Context c){
        this.mContext = c;
    }


    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View v = inflater.inflate(R.layout.gridview_home_item, parent, false);

        ImageView imageView = v.findViewById(R.id.iv_img_item);
        TextView textView = v.findViewById(R.id.tv_item_gv);

        imageView.setImageResource(images[position]);
        textView.setText(values[position]);

        textView.setText(values[position]);
        imageView.setImageResource(images[position]);
        return v;
    }

    private String[] values = {
            "Kuliner",
            "Wisata",
            "Hotel",
            "Pelayanan Publik",
            "Streetview",
    };

    private int[] images = {
            R.drawable.kulinerimg,
            R.drawable.map,
            R.drawable.hotel,
            R.drawable.communication,
            R.drawable.communication,

    };
}


