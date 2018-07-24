package com.deaenita.asasta.fragment;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.deaenita.asasta.R;
import com.deaenita.asasta.adapter.AdapterViewPager;
import com.deaenita.asasta.adapter.GridviewAdapter;
import com.deaenita.asasta.slider.FragmentSlider;
import com.deaenita.asasta.slider.SliderIndicator;
import com.deaenita.asasta.slider.SliderPagerAdapter;
import com.deaenita.asasta.slider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private SliderPagerAdapter mAdapter;
    private SliderIndicator mIndicator;

    private SliderView sliderView;
    private LinearLayout mLinearLayout;

    private TabLayout tabLayout;
    private ViewPager viewPager;
//    TabPageAdapter tabPageAdapter;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        View rootView = inflater.inflate(R.layout.fragment_content_menu, container, false);


        sliderView = view.findViewById(R.id.sliderView);
        mLinearLayout = view.findViewById(R.id.pagesContainer);
        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager = view.findViewById(R.id.view_pager);

        viewPager.setAdapter(new AdapterViewPager(getFragmentManager(), getResources().getStringArray(R.array.titles_tab)));

        tabLayout.setupWithViewPager(viewPager);

        final GridView gridView = rootView.findViewById(R.id.gv_menu);
        gridView.setAdapter(new GridviewAdapter(getActivity()));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.layout_untuk_fragment, new KulinerFragment())
                                .commit();
                        break;
                    case 1:
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.layout_untuk_fragment, new WisataFragment())
                                .commit();
                        break;
                    case 2:
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.layout_untuk_fragment, new HotelFragment())
                                .commit();
                        break;
                    case 3:
                        getActivity().getSupportFragmentManager()
                                .beginTransaction()
                                .replace(R.id.layout_untuk_fragment, new PelayananPublikFragment())
                                .commit();
                        break;
                    case 4:
//                        getActivity().getSupportFragmentManager()
//                                .beginTransaction()
//                                .replace(R.id.layout_untuk_fragment, new PelayananPublikFragment())
//                                .commit();
//                        Intent i = new Intent(getActivity(), StreetviewActivity.class);
//                        startActivity(i);
                        break;

                }

            }
        });


        setupSlider();

        return view;
    }
    private void setupSlider() {
        sliderView.setDurationScroll(800);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FragmentSlider.newInstance("https://cdn-images-1.medium.com/max/800/1*i9SF4LwvaWd-FaIjPwYPBg.png"));
        fragments.add(FragmentSlider.newInstance("https://cdn-images-1.medium.com/max/800/1*azIatDsTVDI-qHAeOhs4jQ.jpeg"));
        fragments.add(FragmentSlider.newInstance("https://cdn-images-1.medium.com/max/800/1*bKBiJ6MhZd2tE3zmjY9uBw.png"));
        fragments.add(FragmentSlider.newInstance("https://cdn-images-1.medium.com/max/800/1*3VIiIOjLpBxsUsmV1v_djg.jpeg"));

        mAdapter = new SliderPagerAdapter(getFragmentManager(), fragments);
        sliderView.setAdapter(mAdapter);
        mIndicator = new SliderIndicator(this, mLinearLayout, sliderView, R.drawable.indicator_circle);
        mIndicator.setPageCount(fragments.size());
        mIndicator.show();
    }
}
