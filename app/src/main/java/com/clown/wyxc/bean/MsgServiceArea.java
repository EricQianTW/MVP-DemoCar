package com.clown.wyxc.bean;


import com.clown.wyxc.base.Message;

@SuppressWarnings({ "rawtypes", "serial" })
public class MsgServiceArea extends Message {
	
	private String AreaName;
	private String Longitude;
	private String Latitude;
	private Integer Type;
	private Integer Pid;
	private String Reamrk;
	private String Letter;
	public String getAreaName() {
		if(AreaName != null){
			return AreaName;
		}else{
			return "";
		}
	}
	public void setAreaName(String areaName) {
		AreaName = areaName;
	}
	public String getLongitude() {
		return Longitude;
	}
	public void setLongitude(String longitude) {
		Longitude = longitude;
	}
	public String getLatitude() {
		return Latitude;
	}
	public void setLatitude(String latitude) {
		Latitude = latitude;
	}
	public Integer getType() {
		return Type;
	}
	public void setType(Integer type) {
		Type = type;
	}
	public Integer getPid() {
		return Pid;
	}
	public void setPid(Integer pid) {
		Pid = pid;
	}
	public String getReamrk() {
		return Reamrk;
	}
	public void setReamrk(String reamrk) {
		Reamrk = reamrk;
	}
	public String getLetter() {
		return Letter;
	}
	public void setLetter(String letter) {
		Letter = letter;
	}

}
