package com.clown.wyxc;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static com.clown.wyxc.Utils.getFirstLower;

/**
 * Created by eric_shenn on 2017/4/30.
 */

public class CreateInterface {

    private String tabString = "    ";
    private String tabStrLn = "\n" + tabString;
    private String tabStrLn2 = "\n" + tabString + tabString;

    @Test
    public void acction() {
        String paramString = "/api/Banner/GetBannerListByQuery";
        String beanString = "Banner";
        createMethod(paramString, beanString);
    }

    private void createMethod(String paramString, String beanString) {


        List<String> paramArr = Arrays.asList(paramString.split("/"));

        String methodName = paramArr.get(paramArr.size() - 1);

        StringBuffer sbMethed = new StringBuffer();

        sbMethed.append(tabString + "@Override");
        sbMethed.append(tabStrLn + "public void " + getFirstLower(methodName) + "(String param){");
        sbMethed.append(tabStrLn + "    try {");
        sbMethed.append(tabStrLn + "        OkHttpUtils");
        sbMethed.append(tabStrLn + "           .post()");
        sbMethed.append(tabStrLn + "           .addHeader(\"Content-Type\",\"application/json\")");
        sbMethed.append(tabStrLn + "           .url(\"http://api.ixiuc.com/" + paramString + "\")");
        sbMethed.append(tabStrLn + "           .addParams(\"\", GSONUtils.toJson(param))");
        sbMethed.append(tabStrLn + "           .build()");
        sbMethed.append(tabStrLn + "           .execute(new Callback<String>() {");
        sbMethed.append(tabStrLn + "               @Override");
        sbMethed.append(tabStrLn + "               public String parseNetworkResponse(Response response) throws Exception {");
        sbMethed.append(tabStrLn + "                   return response.body().string();");
        sbMethed.append(tabStrLn + "               }");
        sbMethed.append(tabStrLn + "");
        sbMethed.append(tabStrLn + "               @Override");
        sbMethed.append(tabStrLn + "               public void onError(okhttp3.Call call, Exception e) {");
        sbMethed.append(tabStrLn + "                   Logger.e(e, \"something happend\");");
        sbMethed.append(tabStrLn + "               }");
        sbMethed.append(tabStrLn + "");
        sbMethed.append(tabStrLn + "               @Override");
        sbMethed.append(tabStrLn + "               public void onResponse(String response) {");
        sbMethed.append(tabStrLn + "                   try {");
        sbMethed.append(tabStrLn + "                       TypeToken<Message<" + beanString + ">> token = new TypeToken<Message<" + beanString + ">>() {");
        sbMethed.append(tabStrLn + "                       };");
        sbMethed.append(tabStrLn + "                       Message<" + beanString + "> dataPackage = GSONUtils.fromJson(response, token);");
        sbMethed.append(tabStrLn + "                       if (dataPackage.getStatusCode() == Constants.OKHTTP_RESULT_SUCESS) {");
        sbMethed.append(tabStrLn + "                           mView.set" + methodName + "Result(dataPackage.getBody());");
        sbMethed.append(tabStrLn + "                       } else {");
        sbMethed.append(tabStrLn + "                           mView.showError(dataPackage.getCustomCode(), dataPackage.getInfo());");
        sbMethed.append(tabStrLn + "                           Logger.e(TAG, dataPackage.getInfo());");
        sbMethed.append(tabStrLn + "                       }");
        sbMethed.append(tabStrLn + "");
        sbMethed.append(tabStrLn + "                   } catch (Exception e) {");
        sbMethed.append(tabStrLn + "                       Logger.e(e, TAG);");
        sbMethed.append(tabStrLn + "                   }");
        sbMethed.append(tabStrLn + "                }");
        sbMethed.append(tabStrLn + "            });");
        sbMethed.append(tabStrLn + "    }catch (Exception e){");
        sbMethed.append(tabStrLn + "        e.printStackTrace();");
        sbMethed.append(tabStrLn + "    }");
        sbMethed.append(tabStrLn + "}");
        sbMethed.append(tabStrLn + "");

        System.out.println(sbMethed.toString());

        StringBuffer sbSourceMethod = new StringBuffer();

        sbSourceMethod.append("void " + getFirstLower(methodName) + "(String param);");

        System.out.println(sbSourceMethod.toString());

        StringBuffer sbDistanceMethod = new StringBuffer();

        sbDistanceMethod.append("void set" + methodName + "Result(" + beanString + " result);");

        System.out.println(sbDistanceMethod.toString());
    }

}
