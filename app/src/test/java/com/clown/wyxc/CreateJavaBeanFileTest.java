package com.clown.wyxc;

import android.support.annotation.NonNull;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.clown.wyxc.Utils.createFile;
import static com.clown.wyxc.Utils.getFirstUpper;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class CreateJavaBeanFileTest {
    private String tabString = "    ";
    private String tabStrLn = "\n" + tabString;
    private String tabStrLn2 = "\n" + tabString + tabString;

    private final String packageName = "package com.clown.wyxc.x_bean;";
    private final String importPackageName1 = "import com.clown.wyxc.base.Message;";
    private final String importPackageName2 = "import com.google.gson.annotations.Expose;";

    @Test
    public void addition_isCorrect() throws Exception {
        String singlebean = "BannerQuery {\n" +
                "Type (integer, optional),\n" +
                "ZipCode (string, optional),\n" +
                "UserId (integer, optional)\n" +
                "}";

//        createSingleJavaBean(singlebean);
//
//
//        String multibean = "UserLoginRequest {\n" +
//                "UserName (string, optional): 用户名 ,\n" +
//                "UserPwd (string, optional): 密码 ,\n" +
//                "usertest1 (Usertest1, optional)\n" +
//                "}Usertest1 {\n" +
//                "test1 (string, optional),\n" +
//                "test2 (Usertest2, optional)\n" +
//                "}Usertest2 {\n" +
//                "test2 (string, optional)\n" +
//                "}";
//
        createMutiBean(singlebean);

    }

    private void createMutiBean(String multibean) {
        List<String> beanArr = Arrays.asList(multibean.split("}"));

        for (int i = 0; i < beanArr.size(); i++) {
            String str = beanArr.get(i) + "}";
            createSingleJavaBean(str);
        }
    }

    private void createSingleJavaBean(String ssss) {

        String s1 = ssss.replace(" ", "");

        String className = s1.substring(0, s1.indexOf("{"));

        String s2 = s1.substring(s1.indexOf("{") + 1, s1.indexOf("}"));

        List<String> array = Arrays.asList(s2.split(",\n"));

        String sum = packageName + "\n\n" + importPackageName1 + "\n" + importPackageName2 + "\npublic class " + className + " extends Message{ " + tabStrLn;

        List<String> ziduanmingArr = new ArrayList();
        List<String> leixingArr = new ArrayList();
        for (String s : array) {
            String ss1 = s.split(",")[0];
            List<String> arrr = Arrays.asList(ss1.split("\\("));

            String leixing = arrr.get(1);
            String ziduanming = arrr.get(0);

            if ("integer".equals(leixing)) {
                leixing = "int";
            } else if ("string".equals(leixing)) {
                leixing = "String";
            }
            leixingArr.add(leixing);

            ziduanming = ziduanming.toLowerCase().replace("\n", "");
            ziduanmingArr.add(ziduanming);

            String temp = "private " + leixing + " " + ziduanming + ";";

            String getString = "public " + leixing + " get" + getFirstUpper(ziduanming) + "(){" + tabStrLn2 + "return " + ziduanming + ";" + tabStrLn + "}";

            String setString = "public void set" + getFirstUpper(ziduanming) + "(" + leixing + " " + ziduanming + "){" + tabStrLn2 + "this. " + ziduanming + " = " + ziduanming + ";" + tabStrLn + "}";

            sum = sum + tabStrLn + "@Expose" + tabStrLn + temp + "\n" + tabStrLn + getString + "\n" + tabStrLn + setString + tabStrLn;
        }

        String constructor = createContrustor(className, ziduanmingArr, leixingArr);

        sum = sum + constructor + "\n}";

        createFile(className,sum);
    }

    @NonNull
    private String createContrustor(String className, List<String> ziduanmingArr, List<String> leixingArr) {
        StringBuffer constructor = new StringBuffer();


        constructor.append(tabStrLn + "public " + className + "(){");
        constructor.append(tabStrLn + "}\n");

        constructor.append(tabStrLn + "public " + className + "(");

        for (int i = 0; i < leixingArr.size(); i++) {
            if (i == 0) {
                constructor.append(leixingArr.get(i) + " " + ziduanmingArr.get(i));
            } else {
                constructor.append("," + leixingArr.get(i) + " " + ziduanmingArr.get(i));
            }
        }
        constructor.append("){");
        for (String s : ziduanmingArr) {
            constructor.append(tabStrLn2 + "this." + s + "=" + s + ";");
        }
        constructor.append(tabStrLn + "}");
        return constructor.toString();
    }


}