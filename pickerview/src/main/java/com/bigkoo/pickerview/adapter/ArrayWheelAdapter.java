package com.bigkoo.pickerview.adapter;

import java.util.ArrayList;

/**
 * The simple Array wheel adapter
 * @param <T> the element type
 */
public class ArrayWheelAdapter<T> implements WheelAdapter {
	
	/** The default items length */
	public static final int DEFAULT_LENGTH = 4;
	
	// items
	private ArrayList<T> items;
	// length
	private int length;

	/**
	 * Constructor
	 * @param items the items
	 * @param length the max items length
	 */
	public ArrayWheelAdapter(ArrayList<T> items, int length) {
		this.items = items;
		this.length = length;
	}
	
	/**
	 * Contructor
	 * @param items the items
	 */
	public ArrayWheelAdapter(ArrayList<T> items) {
		this(items, DEFAULT_LENGTH);
	}

	@Override
	public Object getItem(int index) {
		if (index >= 0 && index < items.size()) {
//			String str = items.get(index).toString();
//			String className = str.substring(0,str.indexOf("=")).trim();
//			String json = str.substring(str.indexOf("[") + 1,str.indexOf("]")).trim();
////			if(((RAreaList)items.get(index)) instanceof RAreaList){
//			RAreaList areaList = GSONUtils.fromJson(items.get(index).toString(),RAreaList.class);
//				return areaList.getName();
////			}else {
////
////			}

//			if("RAreaList".equals(className)){
////				RAreaList rAreaList = GSONUtils.fromJson(json,RAreaList.class);
//				String result = json.split(",")[0].replace("{","").replace("}","").split(":")[1];
//				return result;
//			}else{
				return items.get(index);
//			}
		}
		return "";
	}

	@Override
	public int getItemsCount() {
		return items.size();
	}

	@Override
	public int indexOf(Object o){
		return items.indexOf(o);
	}

}
