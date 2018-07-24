package com.deaenita.asasta.model.restaurant;

import com.google.gson.annotations.SerializedName;

public class Viewport{

	@SerializedName("southwest")
	private Southwest southwest;

	@SerializedName("northeast")
	private Northeast northeast;

	public void setSouthwest(Southwest southwest){
		this.southwest = southwest;
	}

	public Southwest getSouthwest(){
		return southwest;
	}

	public void setNortheast(Northeast northeast){
		this.northeast = northeast;
	}

	public Northeast getNortheast(){
		return northeast;
	}

	@Override
 	public String toString(){
		return 
			"Viewport{" + 
			"southwest = '" + southwest + '\'' + 
			",northeast = '" + northeast + '\'' + 
			"}";
		}
}