package com.deaenita.asasta.maps;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.deaenita.asasta.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.OnStreetViewPanoramaReadyCallback;
import com.google.android.gms.maps.StreetViewPanorama;
import com.google.android.gms.maps.StreetViewPanoramaFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StreetviewActivity extends FragmentActivity implements OnStreetViewPanoramaReadyCallback {
    StreetViewPanorama sMap;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streetview);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        StreetViewPanoramaFragment streetViewPanoramaFragment = (StreetViewPanoramaFragment)
                getFragmentManager().findFragmentById(R.id.map_streetview);
        streetViewPanoramaFragment.getStreetViewPanoramaAsync(this);

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
    public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {
        sMap = panorama;

        //TODO. set data maps
        LatLng maps = new LatLng(lat, lng);
//        sMap.addMarker(new MarkerOptions().position(maps).title("Marker in Boyolali"));
//////        mMap.moveCamera(CameraUpdateFactory.newLatLng(boyolali));
//        float zoomLevel = 17.0f;
//        sMap.moveCamera(CameraUpdateFactory.newLatLngZoom(maps, zoomLevel));
//

        //TODO. test data static
//        panorama.setPosition(new LatLng(-7.54209,110.595414));
//        Toast.makeText(this, "holaa", Toast.LENGTH_SHORT).show();


//        Log.d("streetview", "onStreetViewPanoramaReady: "+panorama);

    }
}
