package com.deaenita.asasta;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.deaenita.asasta.adapter.KulinerAdapter;
import com.deaenita.asasta.maps.MapsActivity;
import com.deaenita.asasta.maps.StreetviewActivity;
import com.deaenita.asasta.model.restaurant.RestaurantModel;
import com.deaenita.asasta.model.restaurant.ResultsItem;
import com.deaenita.asasta.network.ApiServices;
import com.deaenita.asasta.network.RetrofitConfig;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailKulinerActivity extends AppCompatActivity implements OnStreetViewPanoramaReadyCallback {
    ArrayList<ResultsItem> listData = new ArrayList<>();
    Integer posisi;
    ImageView iv_backdrop;
    Fragment fragment;

//    SharedPreferences pref;
//    Boolean isFavorit = false;
    RecyclerView recycler;
    TextView textView;
//    ArrayList<TrailerModel> listdatatrailer = new ArrayList<>();
    double lat, lng;
    private TabLayout tabLayout;
//    public AdapterDetailKuliner adapterDetailKuliner;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kuliner);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        tabLayout = findViewById(R.id.tabDetail);
//        viewPager = findViewById(R.id.view_pager);
//
//        viewPager.setAdapter(new AdapterDetailKuliner(getSupportFragmentManager(), getResources().getStringArray(R.array.tab_detail)));
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        TabLayout tabLayout = findViewById(R.id.tabDetail);
//        tabLayout.setupWithViewPager(viewPager);

//        Glide.with(DetailKulinerActivity.this)
//                .load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=200&photoreference="+listData
//                        .get(posisi).getPhotos().get(0).getPhotoReference()+"&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM")
//                .into(iv_backdrop);

        //setting preference
//        pref = PreferenceManager.getDefaultSharedPreferences(DetailActivity.this);



//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

//        //Todo. Kirim data ke maps
//        Intent intent = new Intent(DetailKulinerActivity.this, MapsActivity.class);
//        intent.putExtra("DATA_POSITION", listData.get(posisi));
//        intent.putExtra("DATA_LAT", listData.get(posisi).getGeometry().getLocation().getLat());
//        intent.putExtra("DATA_LNG", listData.get(posisi).getGeometry().getLocation().getLng());
//        startActivity(intent);

        //TODO. Terima data
        ImageView iv_backdrop = findViewById(R.id.iv_backdrop_kuliner);
        String itemImage = getIntent().getStringExtra("ITEM_IMAGE");
        String nama = getIntent().getStringExtra("NAMA");
        Boolean opening = getIntent().getBooleanExtra("OPENING_HOURS", true);
        String rating = getIntent().getStringExtra("RATING");
        String alamat = getIntent().getStringExtra("ALAMAT");
        int gambar_kosong = getIntent().getIntExtra("GAMBAR_KOSONG", R.drawable.taco);



        lat = getIntent().getDoubleExtra("DATA_LAT", 0);
        lng = getIntent().getDoubleExtra("DATA_LNG", 0);



//        int gambar_isi = getIntent().getIntExtra("GAMBAR_ISI",posisi);
//        posisi = getIntent().getIntExtra("DATA_POSISI", 0);

//        listData = getIntent().getParcelableArrayListExtra("DATA_FILM");


        //TODO. set data
        getSupportActionBar().setTitle("Info Restaurant");





        //        holder.tv_tempat_makan.setText(Double.toString(listKuliner.get(position).getRating()));

        //set data
//        ImageView iv_backdrop = findViewById(R.id.iv_backdrop_kuliner);
//        Glide.with(DetailKulinerActivity.this).load("https://maps.googleapis.com/maps/api/place/photo?maxwidth=200&photoreference="+listData.get(posisi).getPhotos().get(0).getPhotoReference()+"&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM").into(iv_backdrop);

        TextView tv_rating = findViewById(R.id.tv_rating);
        tv_rating.setText(rating);

        TextView tv_nama_rm = findViewById (R.id.tv_nama_rm);
        tv_nama_rm.setText(nama);

        TextView tv_alamat_rm = findViewById (R.id.tv_alamat_rm);
        tv_alamat_rm.setText(alamat);

        ImageView iv_gambar_kosong = findViewById(R.id.iv_backdrop_kuliner);
        iv_gambar_kosong.setImageResource(gambar_kosong);

//        ImageView iv_gambar_isi = findViewById(R.id.iv_backdrop_kuliner);
//        iv_gambar_isi.setImageResource(gambar_isi);

        TextView buka = findViewById(R.id.tv_open_close);
        buka.setText(opening.toString());

        //Todo. Jika keterangan restoran tutup atau buka kosong
        if (buka != null){
            buka.setText("0");
        }else{

        }

        //kondisi restoran buka dan tutup
        if (getIntent().getBooleanExtra("OPENING_HOURS", true)){
            buka.setText("Restaurant Buka");
        }else {
            buka.setText("Restaurant Tutup");
        }


        if (getIntent().getStringExtra("RATING") != null){
//            String rating = getIntent().getStringExtra("RATING");
//
//            TextView tv_rating = findViewById(R.id.tv_rating);
//            tv_rating.setText(rating);
        }else{

        }


        Log.d("bukaresto", "onCreate: " +opening);

        ambilDataOnline();

        StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment)
                getFragmentManager().findFragmentById(R.id.streetview);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

    }

    @Override
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {

        lat = getIntent().getDoubleExtra("DATA_LAT", 0);
        lng = getIntent().getDoubleExtra("DATA_LNG", 0);

        panorama.setPosition(new LatLng(lat,lng));
        Log.d("dwik", "onStreetViewPanoramaReady: "+panorama);




//        //data static
//        panorama.setPosition(new LatLng(-7.541245,110.594721));
        Toast.makeText(this, "holaa", Toast.LENGTH_SHORT).show();

//        Log.d("streetview", "onStreetViewPanoramaReady: "+panorama);

    }

    private void ambilDataOnline() {
//        final ProgressDialog progress = new ProgressDialog(DetailKulinerActivity.this);
//        progress.setTitle("Loading");
//        progress.setMessage("Mohon Bersabar");
//        progress.show();

        ApiServices api = RetrofitConfig.getApiServices();
        Call<RestaurantModel> call = api.ambilDataRestaurant();
        call.enqueue(new Callback<RestaurantModel>() {
            @Override
            public void onResponse(Call<RestaurantModel> call, Response<RestaurantModel> response) {
                try {
//                    progress.dismiss();
                    if (response.isSuccessful()) {
                        listData = response.body().getResults();
//                        Log.d("kuliner", "onResponse: " + response.body().getResults().get(0).getName());
                        KulinerAdapter kulinerAdapter = new KulinerAdapter(listData, DetailKulinerActivity.this);
                        recycler.setAdapter(kulinerAdapter);
                    } else {
                        Toast.makeText(DetailKulinerActivity.this, "Response is not succesfull", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<RestaurantModel> call, Throwable t) {
//                Toast.makeText(DetailKulinerActivity.this, "Respon Failure", Toast.LENGTH_SHORT).show();
//                progress.dismiss();
            }
        });
    }

    public void show_location(View view) {
        Intent intent = new Intent(DetailKulinerActivity.this, MapsActivity.class);

        intent.putExtra("DATA_LAT", lat);
        intent.putExtra("DATA_LNG", lng);
        startActivity(intent);
    }

    public void show_streetview(View view) {
        Intent intent1 = new Intent(DetailKulinerActivity.this, StreetviewActivity.class);
        startActivity(intent1);
    }
}
