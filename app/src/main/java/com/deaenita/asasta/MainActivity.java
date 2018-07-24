package com.deaenita.asasta;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

import com.deaenita.asasta.fragment.ContentMenuFragment;
import com.deaenita.asasta.fragment.HomeFragment;
import com.deaenita.asasta.fragment.HotelFragment;
import com.deaenita.asasta.fragment.KulinerFragment;
import com.deaenita.asasta.view.LoginActivity;
import com.deaenita.asasta.fragment.PelayananPublikFragment;
import com.deaenita.asasta.view.ProfilActivity;
import com.deaenita.asasta.fragment.WisataFragment;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    GridView gridView;

    String[] gridViewString = {
            "Kuliner","Wisata","Hotel","Pelayanan Publik",
    };

    int[] gridViewImageId = {
            R.drawable.kulinerimg, R.drawable.map, R.drawable.hotel, R.drawable.communication,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        printKeyHash();

        Fragment fragment = null;
        Class fragmentClass = null;
        fragmentClass = HomeFragment.class;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.layout_untuk_fragment, new HomeFragment())
                .commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        HomeFragment costomGridviewHome = new HomeFragment(MainActivity.this, gridViewString, gridViewImageId);
//        costomGridviewHome=(GridView)findViewById(R.id.gridview_home);
//        costomGridviewHome.setAdapter((ListAdapter) costomGridviewHome);
//        costomGridviewHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int i, long id) {
//                Toast.makeText(MainActivity.this, "GridView Item: " + gridViewString[+i], Toast.LENGTH_LONG).show();
//            }
//        });
    }

    private void printKeyHash() {
            try{
                PackageInfo packageInfo = getPackageManager().getPackageInfo("com.deaenita.asasta", PackageManager.GET_SIGNATURES);
                for (Signature signature:packageInfo.signatures)
                {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                    messageDigest.update(signature.toByteArray());
                    Log.d("KeyHash", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            fragment = new HomeFragment();
        }else if (id == R.id.nav_kuliner) {
            fragment = new KulinerFragment();
        } else if (id == R.id.nav_wisata) {
            fragment = new WisataFragment();
        } else if (id == R.id.nav_hotel) {
            fragment = new HotelFragment();
        } else if (id == R.id.nav_public) {
            fragment = new PelayananPublikFragment();
        } else if (id == R.id.nav_profil) {
            Intent intent = new Intent(this, ProfilActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_login) {
            Intent intent2 = new Intent(this, LoginActivity.class);
            startActivity(intent2);
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.layout_untuk_fragment, fragment);
            fragmentTransaction.commit();
        }




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
