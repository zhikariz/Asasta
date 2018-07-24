package com.deaenita.asasta.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.deaenita.asasta.fragment.ContentMenuFragment;
import com.deaenita.asasta.fragment.ContentPopulerMenuFragment;

public class AdapterViewPager extends FragmentPagerAdapter {
    private String[] mTabTitles;

    public AdapterViewPager(FragmentManager fm, String[] mTabTitles) {
        super(fm);
        this.mTabTitles = mTabTitles;
    }


    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ContentMenuFragment();
            case 1:
                return new ContentPopulerMenuFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return this.mTabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return this.mTabTitles[position];
    }
}
