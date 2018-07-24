package com.deaenita.asasta.model.restaurant;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PhotosItem implements Parcelable{

	@SerializedName("photo_reference")
	private String photoReference;

	@SerializedName("width")
	private int width;

	@SerializedName("html_attributions")
	private List<String> htmlAttributions;

	@SerializedName("height")
	private int height;

	protected PhotosItem(Parcel in) {
		photoReference = in.readString();
		width = in.readInt();
		htmlAttributions = in.createStringArrayList();
		height = in.readInt();
	}

	public static final Creator<PhotosItem> CREATOR = new Creator<PhotosItem>() {
		@Override
		public PhotosItem createFromParcel(Parcel in) {
			return new PhotosItem(in);
		}

		@Override
		public PhotosItem[] newArray(int size) {
			return new PhotosItem[size];
		}
	};

	public void setPhotoReference(String photoReference){
		this.photoReference = photoReference;
	}

	public String getPhotoReference(){
		return photoReference;
	}

	public void setWidth(int width){
		this.width = width;
	}

	public int getWidth(){
		return width;
	}

	public void setHtmlAttributions(List<String> htmlAttributions){
		this.htmlAttributions = htmlAttributions;
	}

	public List<String> getHtmlAttributions(){
		return htmlAttributions;
	}

	public void setHeight(int height){
		this.height = height;
	}

	public int getHeight(){
		return height;
	}

	@Override
 	public String toString(){
		return 
			"PhotosItem{" + 
			"photo_reference = '" + photoReference + '\'' + 
			",width = '" + width + '\'' + 
			",html_attributions = '" + htmlAttributions + '\'' + 
			",height = '" + height + '\'' + 
			"}";
		}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(photoReference);
		dest.writeInt(width);
		dest.writeStringList(htmlAttributions);
		dest.writeInt(height);
	}
}