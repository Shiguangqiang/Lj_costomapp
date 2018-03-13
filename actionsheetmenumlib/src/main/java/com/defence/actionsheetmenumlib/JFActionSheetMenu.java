package com.defence.actionsheetmenumlib;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * @author Frosty
 * @Description JFrostyActionSheet 提供两种风格的从界面底部向上画出的菜单效果，类似IOS ActionSheet
 * 支持自定义属性，如有无标题，字体颜色、大小，按钮背景图片等
 */
public class JFActionSheetMenu extends Dialog implements OnClickListener {


    private Context mContext;
    private OnActionSheetItemClickListener mListener;//JFrostyActionSheet回调接口(代理)
    private Attributes mAttrs;//JFrostyActionSheet的属性类，从资源文件读取配置
    private List<String> itemsText;//item标题
    private int itemsTextClolor;
    private boolean mCancelableOnTouchOutside;//点击透明部分是否取消显示dialog

    private View mView;
    private LinearLayout mPanel;
    private View mBg;
    private String titleText = "";
    private int titleTextColor;
    private boolean isShowTitle = false;//是否显示标题
    private String cancelText = "";
    private int cancelTextColor;
    private boolean mDismissed = true;//是否消失了


    private boolean useCustonStyle = false;
    private int titleBg;//标题背景
    private int itemBg;// 主item背景
    private int cancelBg;//取消背景


    //Constructor with context
    public JFActionSheetMenu(Context context, int styleNum) {
        super(context, android.R.style.Theme_Light_NoTitleBar);// 全屏
        this.mContext = context;
        if (styleNum < 0) {
            context.setTheme(R.style.ActionSheetStyleGray);
        } else if (styleNum > 0) {
            context.setTheme(R.style.ActionSheetStyleWhite);
        }
        //this.mListener = (OnActionSheetItemClickListener) context;
        initViews();
        getWindow().setGravity(Gravity.BOTTOM);
        Drawable drawable = new ColorDrawable();
        drawable.setAlpha(1);// 设置透明背景
        getWindow().setBackgroundDrawable(drawable);

    }

    /**
     * 获取主题属性的方法
     *
     * @return Attributes
     */
    private Attributes readAttribute() {
        Attributes attrs = new Attributes(mContext);
        TypedArray a = mContext.getTheme().obtainStyledAttributes(null, R.styleable.ActionSheet,
                R.attr.actionSheetStyle, 0);

        Drawable background = a.getDrawable(R.styleable.ActionSheet_actionSheetBackground);
        if (background != null) {
            attrs.background = background;
        }

        Drawable titleBackground = a.getDrawable(R.styleable.ActionSheet_titleButtonBackground);
        if (titleBackground != null) {
            attrs.titleButtonBackground = titleBackground;
        }

        Drawable cancelButtonBackground = a.getDrawable(R.styleable.ActionSheet_cancelButtonBackground);
        if (cancelButtonBackground != null)
            attrs.cancelButtonBackground = cancelButtonBackground;

        Drawable otherButtonTopBackground = a.getDrawable(R.styleable.ActionSheet_otherButtonTopBackground);
        if (otherButtonTopBackground != null)
            attrs.otherButtonTopBackground = otherButtonTopBackground;

        Drawable otherButtonMiddleBackground = a
                .getDrawable(R.styleable.ActionSheet_otherButtonMiddleBackground);
        if (otherButtonMiddleBackground != null)
            attrs.otherButtonMiddleBackground = otherButtonMiddleBackground;

        Drawable otherButtonBottomBackground = a
                .getDrawable(R.styleable.ActionSheet_otherButtonBottomBackground);
        if (otherButtonBottomBackground != null)
            attrs.otherButtonBottomBackground = otherButtonBottomBackground;

        Drawable otherButtonSingleBackground = a
                .getDrawable(R.styleable.ActionSheet_otherButtonSingleBackground);
        if (otherButtonSingleBackground != null)
            attrs.otherButtonSingleBackground = otherButtonSingleBackground;

        attrs.cancelButtonTextColor = a.getColor(R.styleable.ActionSheet_cancelButtonTextColor,
                attrs.cancelButtonTextColor);
        attrs.otherButtonTextColor = a.getColor(R.styleable.ActionSheet_otherButtonTextColor,
                attrs.otherButtonTextColor);
        attrs.padding = (int) a.getDimension(R.styleable.ActionSheet_actionSheetPadding, attrs.padding);
        attrs.otherButtonSpacing = (int) a.getDimension(R.styleable.ActionSheet_otherButtonSpacing,
                attrs.otherButtonSpacing);
        attrs.cancelButtonMarginTop = (int) a.getDimension(R.styleable.ActionSheet_cancelButtonMarginTop,
                attrs.cancelButtonMarginTop);
        attrs.actionSheetTextSize = a.getDimensionPixelSize(R.styleable.ActionSheet_actionSheetTextSize,
                (int) attrs.actionSheetTextSize);

        attrs.titleButtonMarginBottom = a.getDimensionPixelSize(R.styleable.ActionSheet_titleButtonMarginButton, attrs.titleButtonMarginBottom);

        a.recycle();
        return attrs;
    }

    //初始化actionSheet
    public void initViews() {
        /* 隐藏软键盘 */
        InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            View focusView = ((Activity) mContext).getCurrentFocus();
            if (focusView != null)
                imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
        }
        mAttrs = readAttribute();// 获取主题属性
        mView = createView();
        mBg.startAnimation(createAlphaInAnimation());
        mPanel.startAnimation(createTranslationInAnimation());
    }

    //创建移动动画
    private Animation createTranslationInAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 1, type, 0);
        an.setDuration(JFConstant.JFCActionSheet.TRANSLATE_DURATION);
        return an;
    }

    //创建时渐变动画
    private Animation createAlphaInAnimation() {
        AlphaAnimation an = new AlphaAnimation(0, 1);
        an.setDuration(JFConstant.JFCActionSheet.ALPHA_DURATION);
        return an;
    }

    //移除时移动动画
    private Animation createTranslationOutAnimation() {
        int type = TranslateAnimation.RELATIVE_TO_SELF;
        TranslateAnimation an = new TranslateAnimation(type, 0, type, 0, type, 0, type, 1);
        an.setDuration(JFConstant.JFCActionSheet.TRANSLATE_DURATION);
        an.setFillAfter(true);
        return an;
    }

    //透明度渐变动画
    private Animation createAlphaOutAnimation() {
        AlphaAnimation an = new AlphaAnimation(1, 0);
        an.setDuration(JFConstant.JFCActionSheet.ALPHA_DURATION);
        an.setFillAfter(true);
        return an;
    }


    /**
     * 点击外部边缘是否可取消
     *
     * @param cancelable
     * @return
     */
    public JFActionSheetMenu setCancelableOnTouchMenuOutside(boolean cancelable) {
        mCancelableOnTouchOutside = cancelable;
        return this;
    }

    /**
     * 为控件添加item成员和标题
     *
     * @param titles
     * @return
     */
    public JFActionSheetMenu addItems(String... titles) {
        if (titles == null || titles.length == 0)
            return this;
        itemsText = Arrays.asList(titles);
        createItems();
        return this;
    }

    /**
     * 为控件添加item成员和标题
     *
     * @param titles
     * @return
     */
    public JFActionSheetMenu addItemsWithColor(int color, String... titles) {
        if (titles == null || titles.length == 0)
            return this;
        itemsTextClolor = color;
        itemsText = Arrays.asList(titles);
        createItems();
        return this;
    }


    public JFActionSheetMenu setItemsTextClolor(int itemsTextClolor) {
        this.itemsTextClolor = itemsTextClolor;
        return this;
    }

    public JFActionSheetMenu setTitleTextColor(int titleTextColor) {
        this.titleTextColor = titleTextColor;
        return this;
    }

    public JFActionSheetMenu setCancelTextColor(int cancelTextColor) {
        this.cancelTextColor = cancelTextColor;
        return this;
    }

    /**
     * 创建基本的背景视图
     */
    private View createView() {
        FrameLayout parent = new FrameLayout(mContext);
        FrameLayout.LayoutParams parentParams = new FrameLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
        parentParams.gravity = Gravity.BOTTOM;
        parent.setLayoutParams(parentParams);
        mBg = new View(mContext);
        mBg.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        mBg.setBackgroundColor(Color.argb(136, 0, 0, 0));
        mBg.setId(JFConstant.JFCActionSheet.BG_VIEW_ID);
        mBg.setOnClickListener(this);

        mPanel = new LinearLayout(mContext);
        FrameLayout.LayoutParams mPanelParams = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        mPanelParams.gravity = Gravity.BOTTOM;
        mPanel.setLayoutParams(mPanelParams);
        mPanel.setOrientation(LinearLayout.VERTICAL);
        parent.addView(mBg);
        parent.addView(mPanel);
        return parent;
    }

    /**
     * ***************************创建MenuItem* ***************************
     */
    @SuppressWarnings("deprecation")
    private void createItems() {

        if (isShowTitle) {
            //设置标题按钮
            TextView titleBtn = new TextView(mContext);
            titleBtn.getPaint().setFakeBoldText(true);//加粗
            titleBtn.setText(titleText);
            titleBtn.setOnClickListener(this);
            titleBtn.setId(JFConstant.JFCActionSheet.TITLE_BUTTON_ID);
            titleBtn.setTextSize(TypedValue.COMPLEX_UNIT_PX, mAttrs.titleButtonTextSize);
            titleBtn.setTextColor(titleTextColor == 0 ? mAttrs.titleButtonTextColor : titleTextColor);
            if (useCustonStyle && titleBg > 0) {
                titleBtn.setBackgroundResource(titleBg);
            } else {
                titleBtn.setBackgroundDrawable(mAttrs.titleButtonBackground);
            }
            titleBtn.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams paramsTitle = createButtonLayoutParams();
            titleBtn.setPadding(0, mAttrs.titleButtonPadding, 0, mAttrs.titleButtonPadding);
            paramsTitle.bottomMargin = mAttrs.titleButtonMarginBottom;
            mPanel.addView(titleBtn, paramsTitle);
        }


        if (itemsText != null && itemsText.size() > 0)
            for (int i = 0; i < itemsText.size(); i++) {
                Button bt = new Button(mContext);
                bt.setId(JFConstant.JFCActionSheet.CANCEL_BUTTON_ID + i + 1);
                bt.setOnClickListener(this);
                if (useCustonStyle && itemBg > 0) {
                    bt.setBackgroundResource(itemBg);
                } else {
                    bt.setBackgroundDrawable(getOtherButtonBg(itemsText.toArray(new String[itemsText.size()]), i));
                }
                bt.setText(itemsText.get(i));
                bt.setTextColor(itemsTextClolor == 0 ? mAttrs.otherButtonTextColor : itemsTextClolor);
                bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, mAttrs.actionSheetTextSize);
                if (i > 0) {
                    LinearLayout.LayoutParams params = createButtonLayoutParams();
                    params.topMargin = mAttrs.otherButtonSpacing;
                    mPanel.addView(bt, params);
                } else
                    mPanel.addView(bt);
            }


        //取消按钮的设置
        Button bt = new Button(mContext);
        bt.getPaint().setFakeBoldText(true);
        bt.setTextSize(TypedValue.COMPLEX_UNIT_PX, mAttrs.actionSheetTextSize);
        bt.setId(JFConstant.JFCActionSheet.CANCEL_BUTTON_ID);//-1
        if (useCustonStyle && cancelBg > 0) {
            bt.setBackgroundResource(cancelBg);
        } else {
            bt.setBackgroundDrawable(mAttrs.cancelButtonBackground);
        }
        bt.setText(cancelText);
        bt.setTextColor(cancelTextColor == 0 ? mAttrs.cancelButtonTextColor : cancelTextColor);
        bt.setOnClickListener(this);
        LinearLayout.LayoutParams params = createButtonLayoutParams();
        params.topMargin = mAttrs.cancelButtonMarginTop;
        mPanel.addView(bt, params);

        mPanel.setBackgroundDrawable(mAttrs.background);
        mPanel.setPadding(mAttrs.padding, mAttrs.padding, mAttrs.padding, mAttrs.padding);
    }


    public LinearLayout.LayoutParams createButtonLayoutParams() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT);
        return params;
    }


    /**
     * item按钮的颜色
     *
     * @param titles
     * @param i
     * @return
     */
    private Drawable getOtherButtonBg(String[] titles, int i) {
        if (titles.length == 1)
            if (isShowTitle) {
                return mAttrs.otherButtonBottomBackground;
            } else {
                return mAttrs.otherButtonSingleBackground;
            }
        else if (titles.length == 2)
            switch (i) {
                case 0:
                    if (isShowTitle) {
                        return mAttrs.otherButtonMiddleBackground;
                    } else {
                        return mAttrs.otherButtonTopBackground;
                    }
                case 1:
                    return mAttrs.otherButtonBottomBackground;
            }
        else if (titles.length > 2) {
            if (i == 0) {
                if (isShowTitle) {
                    return mAttrs.otherButtonMiddleBackground;
                }
                return mAttrs.otherButtonTopBackground;
            } else if (i == (titles.length - 1))
                return mAttrs.otherButtonBottomBackground;
            return mAttrs.getOtherButtonMiddleBackground();
        }
        return null;
    }

    /**
     * 显示菜单
     */
    public void showMenu() {
        if (!mDismissed)
            return;
        show();
        getWindow().setContentView(mView);
        mDismissed = false;
    }

    /**
     * dissmiss Menu菜单
     */
    public void dismissMenu() {
        if (mDismissed)
            return;
        onDismiss();
        mDismissed = true;
    }

    /**
     * dismiss时的处理
     */
    private void onDismiss() {
        mPanel.startAnimation(createTranslationOutAnimation());
        mBg.startAnimation(createAlphaOutAnimation());
        mView.postDelayed(new Runnable() {
            @Override
            public void run() {
                dismiss();
            }
        }, JFConstant.JFCActionSheet.ALPHA_DURATION);
    }


    /**
     * 标题按钮的标题文字
     *
     * @param title
     * @return
     */
    public JFActionSheetMenu setTitleButtonTextAndColor(String title, int color) {
        if (TextUtils.isEmpty(title)) {
            isShowTitle = false;
        } else {
            this.titleText = title;
            this.titleTextColor = color;
            isShowTitle = true;
        }
        return this;
    }

    /**
     * 标题按钮的标题文字
     *
     * @param strId
     * @return
     */
    public JFActionSheetMenu setTitleButtonTextAndColor(int strId, int color) {
        return setTitleButtonTextAndColor(mContext.getString(strId), color);
    }


    /**
     * 取消按钮的标题文字
     *
     * @param title
     * @return
     */
    public JFActionSheetMenu setCancelButtonTextAndColor(String title, int color) {
        this.cancelText = title;
        this.cancelTextColor = color;
        return this;
    }

    /**
     * 取消按钮的标题文字
     *
     * @param strId
     * @return
     */
    public JFActionSheetMenu setCancelButtonTextAndColor(int strId, int color) {
        return setCancelButtonTextAndColor(mContext.getString(strId), color);
    }


    /**
     * 设置实现了本控件代理的对象
     *
     * @param listener
     * @return JFrostyActionSheet具体实例
     */
    public JFActionSheetMenu setItemClickListener(OnActionSheetItemClickListener listener) {
        this.mListener = listener;
        return this;
    }

    /**
     * 设置是否使用自定义按钮背景，当为true时才有效
     *
     * @param listener
     * @return JFrostyActionSheet具体实例
     */
    public JFActionSheetMenu setUseCustonStyle(boolean use) {
        this.useCustonStyle = use;
        return this;
    }


    public JFActionSheetMenu setTitleBg(int titleBg) {
        this.titleBg = titleBg;
        return this;
    }

    public JFActionSheetMenu setItemBg(int itemBg) {
        this.itemBg = itemBg;
        return this;
    }

    public JFActionSheetMenu setCancelBg(int cancelBg) {
        this.cancelBg = cancelBg;
        return this;
    }

    /**
     * 点击背景是否消失
     */
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub

        if (v.getId() == JFConstant.JFCActionSheet.TITLE_BUTTON_ID || (v.getId() == JFConstant.JFCActionSheet.BG_VIEW_ID && !mCancelableOnTouchOutside))
            return;
        dismissMenu();
        if (v.getId() != JFConstant.JFCActionSheet.CANCEL_BUTTON_ID && v.getId() != JFConstant.JFCActionSheet.BG_VIEW_ID) {
            if (mListener != null)
                mListener.onItemClick(v, v.getId() - JFConstant.JFCActionSheet.CANCEL_BUTTON_ID - 1);
        } else if (v.getId() == JFConstant.JFCActionSheet.CANCEL_BUTTON_ID) {
            mListener.onCanceClick(v);
        }

    }


    /**
     * @author JFrosty
     * @description 自定义属性的控件主题属性
     */
    private class Attributes {
        private Context mContext;

        private Drawable background;
        private Drawable titleButtonBackground;//
        private Drawable cancelButtonBackground;
        private Drawable otherButtonTopBackground;
        private Drawable otherButtonMiddleBackground;
        private Drawable otherButtonBottomBackground;
        private Drawable otherButtonSingleBackground;
        private int titleButtonTextColor;//
        private int cancelButtonTextColor;
        private int otherButtonTextColor;
        private int padding;
        private int otherButtonSpacing;
        private int cancelButtonMarginTop;
        private float actionSheetTextSize;
        private float titleButtonTextSize;//
        private int titleButtonPadding;
        private int titleButtonMarginBottom;

        public Attributes(Context context) {
            mContext = context;
            this.background = new ColorDrawable(Color.TRANSPARENT);
            this.cancelButtonBackground = new ColorDrawable(Color.BLACK);
            ColorDrawable gray = new ColorDrawable(Color.GRAY);
            this.titleButtonBackground = gray;
            this.otherButtonTopBackground = gray;
            this.otherButtonMiddleBackground = gray;
            this.otherButtonBottomBackground = gray;
            this.otherButtonSingleBackground = gray;
            this.cancelButtonTextColor = Color.WHITE;
            this.otherButtonTextColor = Color.BLACK;
            this.titleButtonTextColor = Color.RED;
            this.padding = dp2px(10);
            this.otherButtonSpacing = dp2px(2);
            this.cancelButtonMarginTop = dp2px(10);
            this.actionSheetTextSize = dp2px(16);
            this.titleButtonTextSize = dp2px(14);
            this.titleButtonPadding = dp2px(20);
            this.titleButtonMarginBottom = dp2px(0);

        }

        //dp转px
        private int dp2px(int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, mContext.getResources()
                    .getDisplayMetrics());
        }

        //获取中间部分item背景（无圆角）
        public Drawable getOtherButtonMiddleBackground() {
            if (otherButtonMiddleBackground instanceof StateListDrawable) {
                TypedArray a = mContext.getTheme().obtainStyledAttributes(null, R.styleable.ActionSheet,
                        R.attr.actionSheetStyle, 0);
                otherButtonMiddleBackground = a
                        .getDrawable(R.styleable.ActionSheet_otherButtonMiddleBackground);
                a.recycle();
            }
            return otherButtonMiddleBackground;
        }

    }

    /**
     * JFrostyActionSheet回调接口
     */
    public static interface OnActionSheetItemClickListener {
        void onItemClick(View view, int itemPosition);

        void onCanceClick(View view);
    }


}
