package com.defence.costomapp.utils;

import android.content.Context;
import android.graphics.Color;

import com.defence.actionsheetmenumlib.JFActionSheetMenu;
import com.defence.costomapp.R;


/**
 * Created by fangfafengfu on 2017/12/7.
 */

public class ActionSheelUtil {
    private static JFActionSheetMenu menu = null;
    public static int IOS7 = 8;
    public static int IOS6 = -1;

    public static void showMenu(Context context, String title, String[] content, boolean isCuston, JFActionSheetMenu.OnActionSheetItemClickListener onActionSheetItemClickListener) {
        int style = IOS7;
        menu = new JFActionSheetMenu(context, style);//style<0:ios6，style>0:ios7
        menu.setUseCustonStyle(isCuston);
        /*如果是使用自定义（设置了menu.setUseCustonStyle(isCuston)），且设置对应的背景才有效
         * 且必须放在创建各按钮之前
         *注意：凡是在代码中对属性进行设置，都要在创建item之前实现，即，放在setCancelButtonTextAndColor()、addItems（）方法之前
         * */
        menu.setTitleBg(R.drawable.as_other_bt_bg);
        menu.setItemBg(R.drawable.btn_style_one_normal);
        menu.setCancelBg(R.drawable.as_cancel_bt_bg);
        if (isCuston) {
            menu.setItemsTextClolor(Color.WHITE);
        }
        //设置取消按钮
        menu.setCancelButtonTextAndColor("取消", Color.RED);// 在主item前面添加
        //设置标题（不设置则不显示标题）
        if (style >= 0) {
            menu.setTitleButtonTextAndColor(title, Color.BLUE);
        }
        //设置主item
        menu.addItems(content);
        //主item监听
        menu.setItemClickListener(onActionSheetItemClickListener);
        //取消按钮监听
        menu.setCancelableOnTouchMenuOutside(true);
        //显示menu
        menu.showMenu();

    }

}
