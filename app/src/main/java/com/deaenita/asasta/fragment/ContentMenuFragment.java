package com.deaenita.asasta.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.deaenita.asasta.R;
import com.deaenita.asasta.adapter.GridviewAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContentMenuFragment extends Fragment {

    public ContentMenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_content_menu, container, false);

        final GridView gridView = view.findViewById(R.id.gv_menu);

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
//                    case 4:
////                        getActivity().getSupportFragmentManager()
////                                .beginTransaction()
////                                .replace(R.id.layout_untuk_fragment, new SupportStreetViewPanoramaFragment())
////                                .commit();
//                        Intent i = new Intent(getActivity(), StreetviewActivity.class);
//                        startActivity(i);
//                        break;


                }

            }
        });
        return view;
    }
}
