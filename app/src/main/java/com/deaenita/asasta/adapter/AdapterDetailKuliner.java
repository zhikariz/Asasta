package com.deaenita.asasta.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.deaenita.asasta.MapsFragment;
import com.deaenita.asasta.fragment.ContentMenuFragment;
import com.deaenita.asasta.fragment.ContentPopulerMenuFragment;

public class AdapterDetailKuliner extends FragmentPagerAdapter {
    private String[] tabDetails;


    public AdapterDetailKuliner(FragmentManager fragmentManager, String[] tabDetails) {
        super(fragmentManager);
        this.tabDetails = tabDetails;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new MapsFragment();
            case 1:
                return new ContentPopulerMenuFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.tabDetails.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.tabDetails[position];
    }
}