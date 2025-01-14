package com.amstech.tinkus.backend.dto;


public class CityDTO {

	private int id;
	//private int state_id;
	private String name;
	private String image;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
//	public int getState_id() {
//		return state_id;
//	}
//	public void setState_id(int state_id) {
//		this.state_id = state_id;
//	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image=image;
	}
	
	
}