package com.deaenita.asasta.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.deaenita.asasta.DetailKulinerActivity;
import com.deaenita.asasta.R;
import com.deaenita.asasta.model.restaurant.ResultsItem;

import java.util.ArrayList;

public class KulinerAdapter extends RecyclerView.Adapter<KulinerAdapter.MyViewHolder> {
    private ArrayList<ResultsItem> listKuliner;
    private Context context;

    public KulinerAdapter(ArrayList<ResultsItem> listKuliner, Context context) {
        this.listKuliner = listKuliner;
        this.context = context;
    }


    @NonNull
    @Override
    public KulinerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_list_kuliner, parent, false);
        MyViewHolder holder = new MyViewHolder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final KulinerAdapter.MyViewHolder holder, final int position) {
//        RestaurantModel restaurantModel = listKuliner.get(position);
//
//        Log.d("adpter", "onBindViewHolder: " +listKuliner.get(position).getName());

//        Log.d("poto", "onBindViewHolder: "+listKuliner.get(position).getPhotos().get(0).getPhotoReference());


        if (listKuliner.get(position).getPhotos() != null){
            Glide.with(context).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=200&photoreference="+listKuliner.get(position).getPhotos().get(0).getPhotoReference()+"&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM").into(holder.iv_gambar_kuliner);

        }else{
            Glide.with(context).load(R.drawable.taco).into(holder.iv_gambar_kuliner);
        }





//        holder.tv_tempat_makan.setText(listKuliner.get(position).getVicinity());
        holder.tv_tempat_makan.setText(listKuliner.get(position).getName());
//        holder.tv_tempat_makan.setText(Double.toString(listKuliner.get(position).getRating()));

        //kirim data
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listKuliner.get(position).getPhotos() != null){
                    Intent intent = new Intent(context, DetailKulinerActivity.class);
//                intent.putParcelableArrayListExtra("DATA_KULINER", listKuliner);
//                intent.putParcelableArrayListExtra("PHOTOS", listKuliner);
//                    intent.putExtra("GAMBAR", Glide.with(context).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=200&photoreference="+listKuliner.get(position).getPhotos().get(0).getPhotoReference()+"&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM").into(holder.iv_gambar_kuliner));
                    intent.putExtra("GAMBAR_ISI", listKuliner.get(position).getPhotos().get(0).getPhotoReference());
                    intent.putExtra("NAMA", listKuliner.get(position).getName());
                    intent.putExtra("OPENING_HOURS", listKuliner.get(position).getOpeningHours().isOpenNow());
                    intent.putExtra("RATING", Double.toString(listKuliner.get(position).getRating()));
                    intent.putExtra("ALAMAT", listKuliner.get(position).getVicinity());

                    //kirim data kalau sebendel
//                    intent.putExtra("DATA_POSITION", position);
                    intent.putExtra("DATA_LAT", listKuliner.get(position).getGeometry().getLocation().getLat());
                    intent.putExtra("DATA_LNG", listKuliner.get(position).getGeometry().getLocation().getLng());


                    Log.d("bukapintu", "onClick: " + listKuliner.get(position).getOpeningHours().isOpenNow());

                    context.startActivity(intent);
                }else{
//                    Intent intent = new Intent(context, DetailKulinerActivity.class);
////                intent.putParcelableArrayListExtra("DATA_KULINER", listKuliner);
////                intent.putParcelableArrayListExtra("PHOTOS", listKuliner);
//                    intent.putExtra("NAMA", listKuliner.get(position).getName());
//                    intent.putExtra("OPENING_HOURS", listKuliner.get(position).getOpeningHours().isOpenNow());
//                    intent.putExtra("RATING", Double.toString(listKuliner.get(position).getRating()));
//                    intent.putExtra("ALAMAT", listKuliner.get(position).getVicinity());
//                    intent.putExtra("DATA_POSITION", listKuliner.get(position).getName());
//                    intent.putExtra("GAMBAR_KOSONG", R.drawable.taco);
//                    context.startActivity(intent);

                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return listKuliner.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_gambar_kuliner;
        TextView tv_tempat_makan;
        private MyViewHolder(View itemView) {
            super(itemView);
            iv_gambar_kuliner = itemView.findViewById(R.id.iv_img_item_kuliner);
            tv_tempat_makan = itemView.findViewById(R.id.tv_item_tempat_makan);
//            tv_rating = itemView.findViewById(R.id.tv_rating);


            //tv_item_tempat_makan

        }
    }


}
