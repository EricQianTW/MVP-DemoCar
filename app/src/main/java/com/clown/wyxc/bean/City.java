package com.clown.wyxc.bean;

public class City {
	private String name;
	private String pinyi;
	private String jin;
	private String wei;

	public City(String name, String pinyi, String jin, String wei) {
		super();
		this.name = name;
		this.pinyi = pinyi;
		this.jin = jin;
		this.wei = wei;
	}
	public City(String name, String pinyi) {
		super();
		this.name = name;
		this.pinyi = pinyi;
	}

	public City() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinyi() {
		return pinyi;
	}

	public void setPinyi(String pinyi) {
		this.pinyi = pinyi;
	}

	public String getJin() {
		return jin;
	}

	public void setJin(String jin) {
		this.jin = jin;
	}

	public String getWei() {
		return wei;
	}

	public void setWei(String wei) {
		this.wei = wei;
	}

}
