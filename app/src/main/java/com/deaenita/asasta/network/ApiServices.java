package com.deaenita.asasta.network;

import com.deaenita.asasta.model.hotel.HotelModel;
import com.deaenita.asasta.model.restaurant.RestaurantModel;
import com.deaenita.asasta.model.restaurant.ResultsItem;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiServices {

    @GET("json?location=-7.5178785,110.5624369&rankby=distance&types=restaurant&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM")
    Call<RestaurantModel> ambilDataRestaurant();

    @GET("json?location=-7.5178785,110.5624369&rankby=distance&types=lodging&key=AIzaSyD9M2Vrygo9eDa5uV_adg-ls2lJ3sk7tqM")
    Call<HotelModel> ambilDataHotel();



}
