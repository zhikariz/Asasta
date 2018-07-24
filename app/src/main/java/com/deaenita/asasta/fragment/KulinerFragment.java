package com.deaenita.asasta.fragment;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.deaenita.asasta.R;
import com.deaenita.asasta.adapter.KulinerAdapter;
import com.deaenita.asasta.model.restaurant.PhotosItem;
import com.deaenita.asasta.model.restaurant.RestaurantModel;
import com.deaenita.asasta.model.restaurant.ResultsItem;
import com.deaenita.asasta.network.ApiServices;
import com.deaenita.asasta.network.RetrofitConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class KulinerFragment extends Fragment {
    private ArrayList<ResultsItem> listdata = new ArrayList<>();
    private KulinerAdapter kulinerAdapter;

    private RecyclerView recyclerView;


    public KulinerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_kuliner, container, false);

        recyclerView = view.findViewById(R.id.rv_kuliner_item_list);
        KulinerAdapter kulinerAdapter = new KulinerAdapter(listdata, getActivity());
        RecyclerView.LayoutManager manager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(kulinerAdapter);

        //get data
        ambilDataKuliner();

        return view;
    }

    private void ambilDataKuliner() {
        final ProgressDialog progress = new ProgressDialog(getActivity());
        progress.setTitle("Loading");
        progress.setMessage("Mohon Bersabar");
        progress.show();

        ApiServices api = RetrofitConfig.getApiServices();
        Call<RestaurantModel> call = api.ambilDataRestaurant();

        call.enqueue(new Callback<RestaurantModel>() {
            @Override
            public void onResponse(Call<RestaurantModel> call, Response<RestaurantModel> response) {
                try {
                    progress.dismiss();
                    if (response.isSuccessful()) {
                        listdata = response.body().getResults();
                        Log.d("kuliner", "onResponse: " + response.body().getResults().get(0).getName());
                        KulinerAdapter kulinerAdapter = new KulinerAdapter(listdata, getActivity());
                        recyclerView.setAdapter(kulinerAdapter);

                        Log.d("buka", "onResponse: "+listdata.get(0).getOpeningHours().isOpenNow());

                    } else {
                        Toast.makeText(getActivity(), "Response is not succesfull", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<RestaurantModel> call, Throwable t) {
                progress.dismiss();
                Toast.makeText(getActivity(), "Response Failure" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

}
