package com.deaenita.asasta.model.restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class RestaurantModel implements Parcelable{

	@SerializedName("next_page_token")
	private String nextPageToken;

	@SerializedName("html_attributions")
	private List<Object> htmlAttributions;

	@SerializedName("results")
	private List<ResultsItem> results;

	@SerializedName("status")
	private String status;

	protected RestaurantModel(Parcel in) {
		nextPageToken = in.readString();
		status = in.readString();
	}

	public static final Creator<RestaurantModel> CREATOR = new Creator<RestaurantModel>() {
		@Override
		public RestaurantModel createFromParcel(Parcel in) {
			return new RestaurantModel(in);
		}

		@Override
		public RestaurantModel[] newArray(int size) {
			return new RestaurantModel[size];
		}
	};

	public void setNextPageToken(String nextPageToken){
		this.nextPageToken = nextPageToken;
	}

	public String getNextPageToken(){
		return nextPageToken;
	}

	public void setHtmlAttributions(List<Object> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public List<Object> getHtmlAttributions(){
		return htmlAttributions;
	}

	public void setResults(List<ResultsItem> results){
		this.results = results;
	}

	public ArrayList<ResultsItem> getResults(){
		return (ArrayList<ResultsItem>) results;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"RestaurantModel{" + 
			"next_page_token = '" + nextPageToken + '\'' + 
			",html_attributions = '" + htmlAttributions + '\'' + 
			",results = '" + results + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(nextPageToken);
		dest.writeString(status);
	}
}