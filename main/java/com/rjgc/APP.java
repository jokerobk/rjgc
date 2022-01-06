package com.rjgc;

import com.rjgc.main.view.LoginView;
import com.rjgc.util.CreateDBTableUtil;

/**
 * @Author: "下铺死楠彤"
 * @Date: 12/27/21
 * @Time: 4:23 PM
 */
public class APP {
    public static void main(String[] args) {
        new CreateDBTableUtil();
        new LoginView();
    }
}
