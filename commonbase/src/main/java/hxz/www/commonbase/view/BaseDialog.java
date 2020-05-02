package hxz.www.commonbase.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import hxz.www.commonbase.R;


/**
 * @author 韩湘子 2019年7月8日   自定义basedialog
 */
public abstract class BaseDialog extends Dialog {


    public BaseDialog(Context context) {
        super(context);
        initConfiguration();
        initView();
    }


    /**
     * 舒适化View
     */
    private void initView() {
        mRootView = LayoutInflater.from(getContext()).inflate(getLayoutId(), null);
        bindView();
        bindListener();
        mRootView.setBackground(getBackDrawable());
        setContentView(mRootView);
    }

    /**
     * 初始化配置
     */
    private void initConfiguration() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setCanceledOnTouchOutside(true);
        Window window = getWindow();
        assert window != null;
        WindowManager.LayoutParams params = window.getAttributes();
        params.gravity = Gravity.CENTER;
        window.getDecorView().setPadding(0, 0, 0, 0);
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(0x00000000));
        window.setWindowAnimations(R.style.dialog);
        intiAttrs(params, window, false);
        params.dimAmount = 0.4f;
        window.setAttributes(params);
    }

    /**
     * 初始化dialog位置
     *
     * @param params
     * @param window
     * @param isTransparent
     */
    protected void intiAttrs(WindowManager.LayoutParams params, Window window, boolean isTransparent) {

    }


    /**
     * 本地设置背景
     *
     * @return
     */
    protected Drawable getBackDrawable() {
        GradientDrawable drawable = new GradientDrawable();
        drawable.setColor(Color.parseColor("#ffffff"));
        return drawable;
    }


    @Override
    public <T extends View> T findViewById(int id) {
        return mRootView.findViewById(id);
    }


    /**
     * 加载布局
     */
    protected View mRootView;


    /**
     * 获取布局
     *
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 初始化布局
     */
    protected abstract void bindView();

    /**
     * 处理监听事件
     */
    protected abstract void bindListener();
}
