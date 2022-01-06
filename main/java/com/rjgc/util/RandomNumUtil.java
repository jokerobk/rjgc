package com.rjgc.util;

import java.util.Random;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/30/21
 * @Time: 5:43 PM
 */
public class RandomNumUtil {
    public Integer gerRandomNum(){
        Random random = new Random();
        StringBuilder result= new StringBuilder();
        for (int i=0;i<6;i++)
        {
            if(i == 0){
                result.append(random.nextInt(9) + 1);
            }
            else {
                result.append(random.nextInt(10));
            }
        }
        return Integer.valueOf(String.valueOf(result));
    }
}
