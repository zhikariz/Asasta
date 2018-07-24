package com.deaenita.asasta.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.deaenita.asasta.R;
import com.deaenita.asasta.model.restaurant.ResultsItem;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    ArrayList<ResultsItem> listMaps = new ArrayList<>();
    double lat,lng;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //Todo. Terima data dari detail
        lat = getIntent().getDoubleExtra("DATA_LAT", 0);
        lng = getIntent().getDoubleExtra("DATA_LNG", 0);

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera

//        ImageView iv_gambar_kosong = findViewById(R.id.iv_backdrop_kuliner);
//        iv_gambar_kosong.setImageResource(gambar_kosong);
//

        //Todo. setdata maps
//        Log.d("mapsbyl", "onMapReady: " +(listMaps.get(posisi).getGeometry().getLocation().getLat()));
        LatLng maps = new LatLng(lat, lng);
        mMap.addMarker(new MarkerOptions().position(maps).title("Marker in Boyolali"));
//        mMap.moveCamera(CameraUpdateFactory.newLatLng(boyolali));
        float zoomLevel = 17.0f;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(maps, zoomLevel));


    }
}
//    LatLng boyolali = new LatLng(-7.5179636,110.5624368);
//listMaps.get(posisi).getGeometry().getLocation().getLng(), posisi