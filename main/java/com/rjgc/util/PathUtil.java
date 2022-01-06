package com.rjgc.util;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/29/21
 * @Time: 3:41 PM
 */
public class PathUtil {
    private static final String P_Path = "resources/";
    public static String getRealPath(String relativePath){
        return P_Path + relativePath;
    }
}
