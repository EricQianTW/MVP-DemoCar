package com.clown.wyxc;

import android.app.Activity;

import com.clown.wyxc.utils.GSONUtils;
import com.clown.wyxc.x_bean.x_parambean.BannerQuery;

import java.util.HashMap;

/**
 * Created by eric_clown on 2017/5/10.
 */

public class Test extends Activity {

    @org.junit.Test
    public void TestSomething() {

        try {
            HashMap map = GSONUtils.objectToMap(new BannerQuery(null, null, 1));
            String value = GSONUtils.toJson(map);
            System.out.println(value);

            System.out.print(GSONUtils.toJson(new BannerQuery(null,null,1)));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        StringBuffer text = new StringBuffer();
//        try {
//            InputStreamReader isr = new InputStreamReader(getAssets().open("testjson.json"),"UTF-8");
//            BufferedReader br = new BufferedReader(isr);
//            String line;
//            StringBuilder builder = new StringBuilder();
//            while((line = br.readLine()) != null){
//                builder.append(line);
//            }
//            br.close();
//            isr.close();
//            JSONObject testjson = new JSONObject(builder.toString());//builder读取了JSON中的数据。
//            //直接传入JSONObject来构造一个实例
//            JSONArray array = testjson.getJSONArray("role");         //从JSONObject中取出数组对象
//            for (int i = 0; i < array.length(); i++) {
//                JSONObject role = array.getJSONObject(i);    //取出数组中的对象
//                text.append(role.getString("name") + ": ");  //取出数组中对象的各个值
//                text.append(role.getString("say") + "\n");
//            }//
//
//            text.append("now the " +testjson.getString("dog") + " is here");
//            System.out.println(text);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
