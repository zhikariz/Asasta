package com.deaenita.asasta.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.deaenita.asasta.R;
import com.deaenita.asasta.slider.FragmentSlider;
import com.deaenita.asasta.slider.SliderPagerAdapter;
import com.deaenita.asasta.slider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class PelayananPublikFragment extends Fragment {


    public PelayananPublikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pelayanan_publik, container, false);

        return view;
    }

}
