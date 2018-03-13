package com.frosty.jfactionsheetmenu;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.frosty.jfactionsheetmenu.JFActionSheetMenu.OnActionSheetItemClickListener;

public class MainActivity extends Activity implements OnActionSheetItemClickListener{
    
    private JFActionSheetMenu  menu = null;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//全屏
        setContentView(R.layout.activity_main);
    }
    
    /*监听*/
    
    public void ActionBtn1(View view){
        showMenu(-1,false);//负数数为ios6风格
    }
    
    public void ActionBtn2(View view){
        showMenu(8,false);//正数为ios7风格
    }
    
    
    //自定义
    public void ActionBtn3(View view){
        showMenu(0,true);//0需要自定义样式
    }
    
    
    
    public void showMenu(int style,boolean isCuston){
        
        menu = new JFActionSheetMenu(this,style);//style<0:ios6，style>0:ios7
        
        menu.setUseCustonStyle(isCuston);
        
        /*如果是使用自定义（设置了menu.setUseCustonStyle(isCuston)），且设置对应的背景才有效
         * 且必须放在创建各按钮之前
         *注意：凡是在代码中对属性进行设置，都要在创建item之前实现，即，放在setCancelButtonTextAndColor()、addItems（）方法之前
         * */
        menu.setTitleBg(R.drawable.as_other_bt_bg);
        menu.setItemBg(R.drawable.btn_style_one_normal);
        menu.setCancelBg(R.drawable.as_cancel_bt_bg);
        if(isCuston){
            menu.setItemsTextClolor(Color.WHITE);
        }
        //设置取消按钮
        menu.setCancelButtonTextAndColor("cancel",Color.RED);// 在主item前面添加
        //设置标题（不设置则不显示标题）
        if( style >= 0){
            menu.setTitleButtonTextAndColor("请选择照片", Color.BLUE);
        }
        
        //设置主item
        menu.addItems("拍照","选择照片","网络获取");
        
        
        //主item监听
        menu.setItemClickListener(this);
        //取消按钮监听
        menu.setCancelableOnTouchMenuOutside(true);
        //显示menu
        menu.showMenu();
        
    }
    
    
    @Override
    public void onItemClick(View v,int itemPosition) {
        // TODO Auto-generated method stub
        Toast.makeText(this,"点击了第"+itemPosition+"个按钮,内容是:"+((Button)v).getText() , Toast.LENGTH_SHORT).show();
    }
    
    
    @Override
    public void onCanceClick(View v) {
        // TODO Auto-generated method stub
        Toast.makeText(this,"点击了取消按钮,内容是:"+((Button)v).getText() , Toast.LENGTH_SHORT).show();
    }
    
}
