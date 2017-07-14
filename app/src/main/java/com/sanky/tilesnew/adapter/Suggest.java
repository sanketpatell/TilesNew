package com.sanky.tilesnew.adapter;

import android.text.TextUtils;

import java.io.Serializable;

public class Suggest implements Serializable {
	/**
	 * added by xuexiaozhe 20140210
	 * 实现序列化接口
	 */
	private static final long serialVersionUID = -3145164796932855127L;
	
	public static final int TYPE_VENUS = 0;
	public static final int TYPE_POI = 1;
	public static final int TYPE_OTHER = 2;
	private int source;
	private long id;
	private String title;
	private String subTitle;
	private long venueId;
	private long poiId;
	private double lat;
	private double lng;
	private String poi_category;
	private boolean hasIndoor;
	private int category;
	private String thumbnailUrl;
	private String imgUrl;

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public long getVenueId() {
		return venueId;
	}

	public void setVenueId(long venueId) {
		this.venueId = venueId;
	}

	public long getPoiId() {
		return poiId;
	}

	public void setPoiId(long poiId) {
		this.poiId = poiId;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public String getPoi_category() {
		return poi_category;
	}

	public void setPoi_category(String poi_category) {
		this.poi_category = poi_category;
	}

	public boolean isHasIndoor() {
		return hasIndoor;
	}

	public void setHasIndoor(boolean hasIndoor) {
		this.hasIndoor = hasIndoor;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(title);
		if (!TextUtils.isEmpty(subTitle)) {
			sb.append("(").append(subTitle).append(")");
		}
		return sb.toString();
	}
}