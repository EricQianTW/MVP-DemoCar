package com.clown.wyxc.constant;

/**
 * Created by eric_qiantw on 16/4/21.
 */
public class Constants {

    // 设备ID
    public static String serialNumber = android.os.Build.SERIAL;

    public static final String HTTP_IP = "http://api.ixiuc.com/api";
    public static final String HTTP_IP_MATAN = "http://api-ys.tonggo.net";
    public static final String Aipkey = "mtyg226001";

    public static final String APP_UPDATE_SERVER_URL = "http://192.168.205.33:8080/Hello/api/update";

    /**
     * 项目5个tab的index
     */
    public final static int TAB_INDEX_HOME = 0;
    public final static int TAB_INDEX_SHOP = 2;

    public static int OKHTTP_RESULT_SUCESS = 1;
    public static int OKHTTP_RESULT_FAILD = 0;


    // RequestCode
    public final static int REQUEST_CODE_JUMP = 10001;

    public static final int REQUEST_TAKE_PHOTO = 0x12;  //拍照请求码

    public static final String HOTLINE = "0513xxxxx111";

    public static final String WULIU_URL = "http://meiye.tonggo.net/ordercenter/indexExpressMain.aspx?OrderId=";

    public final static int RC_PERM = 123;
    public final static int RC_LOCATION_PERM = 124;

    /**************** DB *********************/
    /**
     * 数据库名称
     */
    public static final String DB = "wyxcw.db";
    /**
     * 数据库版本标记
     */
    public static final int DB_VERSION = 20170415;

    //-------------------------------------------------中国城市表----------------------------------------------
    /**
     * 数据库-聊天一览表
     */
    public static final String STORE_CITY_TABLE = "store_city_table";
    /**
     * 数据库-中国城市表—进程ID
     */
    public static final String Store_City_AreaName = "areaname";

    /**
     * 数据库-中国城市表—用户头像
     */
    public static final String Store_City_Longitude = "longitude";

    /**
     * 数据库-中国城市表—用户名称
     */
    public static final String Store_City_Latitude = "latitude";
    /**
     * 数据库-中国城市表—房间名称
     */
    public static final String Store_City_Type = "type";
    /**
     * 数据库-中国城市表—是否私聊
     */
    public static final String Store_City_Pid = "pid";
    /**
     * 数据库-中国城市表—用户名称
     */
    public static final String Store_City_Reamrk = "reamrk";

    /**
     * 数据库-中国城市表—最新信息时间
     */
    public static final String Store_City_Letter = "letter";

    //-------------------------------------------------中国城市表----------------------------------------------

}
