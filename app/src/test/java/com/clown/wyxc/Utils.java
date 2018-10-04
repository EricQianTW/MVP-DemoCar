package com.clown.wyxc;

import android.support.annotation.NonNull;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by eric_shenn on 2017/4/30.
 */

public class Utils {

    @NonNull
    public static String getFirstLower(String ziduanming) {
        return ziduanming.substring(0, 1).toLowerCase() + ziduanming.substring(1);
    }

    @NonNull
    public static String getFirstUpper(String ziduanming) {
        return ziduanming.substring(0, 1).toUpperCase() + ziduanming.substring(1);
    }

    @NonNull
    public static void createFile(String className, String aa) {
        byte[] buff = new byte[]{};
        try {
            buff = aa.getBytes();
            FileOutputStream out = new FileOutputStream("output/" + className + ".java");
            out.write(buff, 0, buff.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    public static void createWholeFile(String className, String aa) {
        byte[] buff = new byte[]{};
        try {
            buff = aa.getBytes();
            FileOutputStream out = new FileOutputStream("output/" + className);
            out.write(buff, 0, buff.length);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
