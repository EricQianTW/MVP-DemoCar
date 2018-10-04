package com.clown.wyxc.scheme;


public class UrlConst {
	
//	public static String URL_SETTLEMENT = getUrl(SchemeUtil.CLASSIFY_PAGE,SchemeUtil.RULES_PAGE_SETTLEMENT);
	
	public static String getWholeUrl(String url, String param){
		return url + param;
	}
	
	public static String getUrl(String classify, String rules){
		return Constants.SCHEME_URL + classify  + "/" + rules +  "?";
	}
	
}
