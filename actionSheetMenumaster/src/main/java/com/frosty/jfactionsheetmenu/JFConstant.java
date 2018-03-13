package com.frosty.jfactionsheetmenu;

import android.graphics.Color;

public class JFConstant {

	/**
	 * @description ActionSheet 相关常量内部类
	 * @author JFrosty
	 */
	public static class JFCActionSheet{
		public static final int TITLE_BUTTON_ID = 101;
		public static final int CANCEL_BUTTON_ID = -1;
		public static final int BG_VIEW_ID = 100;
		public static final int TRANSLATE_DURATION = 400;
		public static final int ALPHA_DURATION = 400;
	}

	/**
	 * @description BadgeView 
	 * @author  JFrosty
	 */
	public static class JFCBadgeView{
		public static final int POSITION_TOP_LEFT = 1;//左上角
		public static final int POSITION_TOP_RIGHT = 2;//右上角
		public static final int POSITION_BOTTOM_LEFT = 3;//左下角
		public static final int POSITION_BOTTOM_RIGHT = 4;//右下角
		public static final int POSITION_CENTER = 5;//中间
		public static final int POSITION_CENTER_VERTIC=6;//垂直居中(靠左)
		public static final int POSITION_CENTER_HORIZONTAL= 7;//水平居中(顶部)
		public static final int POSITION_CENTER_VERTIC_RIGHT=8;//垂直居中(靠左)
		public static final int POSITION_CENTER_HORIZONTAL_BOTTOM= 9;//水平居中(顶部)


		public static final int DEFAULT_MARGIN_DIP = 5;//默认margin参数值
		public static final int DEFAULT_LR_PADDING_DIP = 5;//默认padding参数值
		public static final int DEFAULT_CORNER_RADIUS_DIP = 8;//背景圆角弧度
		public static final int DEFAULT_POSITION = POSITION_TOP_RIGHT;//默认显示在目标控件右上方
		public static final int DEFAULT_BADGE_COLOR = Color.parseColor("#CCFF0000"); //默认背景颜色红色Color.RED;
		public static final int DEFAULT_TEXT_COLOR = Color.WHITE;//默认字体颜色白色
		
	}
	
	
	/**
	 * @description JFMessageBox
	 * @author JFrosty
	 *
	 */
	public static class JFCMessageBox{
		
		public static final int MESSAGEBOXPADDING_DIP = 10;
		public static final int MESSAGEBOXMARGIN_DIP = 80;
		
		public static final int MESSAGE_TITLE_GRAVITY_LEFT=0;//默认
		public static final int MESSAGE_TITLE_GRAVITY_CENTER=1;//中间
		
		public static final int BG_VIEW_ID = 100;
		
	}
	
	/**
	 * @description JFToastBox
	 * @author JFrosty
	 *
	 */
	public static class JFCToastBox{
		
		public static final int TOASTBOXPADDING_DIP = 10;
		public static final int TOASTBOXMARGIN_DIP = 80;
		
		public static final int TOAST_TITLE_GRAVITY_LEFT=0;//默认
		public static final int TOAST_TITLE_GRAVITY_CENTER=1;//中间
		
		public static final int BG_VIEW_ID = 100;
		
		public static final int STYLE_TOAST_MAIN_ORITATION_H = 0;//水平布局
		public static final int STYLE_TOAST_MAIN_ORITATION_V = 1;//垂直布局
	}

	
	
}
