package com.clown.wyxc.x_utils;

import android.util.Xml;

import com.clown.wyxc.x_utils.bean.RouterXml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by eric_qiantw on 2016/10/26.
 */

public class RouterParser {
    public static List<RouterXml> parse(InputStream is) throws Exception {
        List<RouterXml> mList = null;
        RouterXml beauty = null;

        // 由android.util.Xml创建一个XmlPullParser实例  
        XmlPullParser xpp = Xml.newPullParser();
        // 设置输入流 并指明编码方式  
        xpp.setInput(is, "UTF-8");
        // 产生第一个事件  
        int eventType = xpp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                // 判断当前事件是否为文档开始事件  
                case XmlPullParser.START_DOCUMENT:
                    mList = new ArrayList<RouterXml>(); // 初始化books集合
                    break;
                // 判断当前事件是否为标签元素开始事件  
                case XmlPullParser.START_TAG:
                    if (xpp.getName().equals("router")) { // 判断开始标签元素是否是book
                        beauty = new RouterXml();
                    } else if (xpp.getName().equals("host")) {
                        eventType = xpp.next();//让解析器指向name属性的值  
                        // 得到name标签的属性值，并设置beauty的name  
                        beauty.setHost(xpp.getText());
                    } else if (xpp.getName().equals("path")) { // 判断开始标签元素是否是book
                        eventType = xpp.next();//让解析器指向age属性的值  
                        // 得到age标签的属性值，并设置beauty的age  
                        beauty.setPath(xpp.getText());
                    } else if (xpp.getName().equals("activity")) { // 判断开始标签元素是否是book
                        eventType = xpp.next();//让解析器指向age属性的值
                        // 得到age标签的属性值，并设置beauty的age
                        beauty.setActivity(xpp.getText());

                    }
                    break;

                // 判断当前事件是否为标签元素结束事件  
                case XmlPullParser.END_TAG:
                    if (xpp.getName().equals("router")) { // 判断结束标签元素是否是book
                        if (beauty.getActivity() != null) {
                            mList.add(beauty); // 将book添加到books集合
                        }
                        beauty = null;
                    }
                    break;
            }
            // 进入下一个元素并触发相应事件  
            eventType = xpp.next();
        }
        return mList;

    }
}