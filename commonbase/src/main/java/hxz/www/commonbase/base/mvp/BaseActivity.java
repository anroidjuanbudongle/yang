package hxz.www.commonbase.base.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.umeng.message.PushAgent;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import hxz.www.commonbase.R;
import hxz.www.commonbase.util.Logger;


/**
 * Dec: Activity 基类
 *
 * @author andy
 */
public abstract class BaseActivity extends AppCompatActivity {

    protected View mView;
    private View mEmptyView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        ImmersionBar.with(this).statusBarDarkFont(true).init();
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            mView = LayoutInflater.from(this).inflate(getLayoutId(), null);
            mEmptyView = LayoutInflater.from(this).inflate(R.layout.empty_view, null);
            setContentView(mView);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        init(savedInstanceState);
    }


    /**
     * 初始化
     */
    protected abstract void init(@Nullable Bundle savedInstanceState);

    /**
     * @return layoutId
     */
    @LayoutRes
    protected abstract int getLayoutId();


    /**
     * 查看当前ActivityName生命周期
     */
    @Override
    protected void onStart() {
        super.onStart();
        Logger.w(this.getClass().getSimpleName() + "<----当前生命周期");
        PushAgent.getInstance(this).onAppStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.w(this.getClass().getSimpleName() + "<----当前生命周期");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Logger.w(this.getClass().getSimpleName() + "<----当前生命周期");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Logger.w(this.getClass().getSimpleName() + "<----当前生命周期");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.w(this.getClass().getSimpleName() + "<----当前生命周期");
    }

    @Override
    protected void onDestroy() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        super.onDestroy();
        ImmersionBar.destroy(this, null);
        Logger.w(this.getClass().getSimpleName() + "<----当前生命周期");
    }


    public void showLoadDialog(String text) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(this);
        }
        progressDialog.setMessage(text);
        progressDialog.setCancelable(true);
        progressDialog.show();
    }


    public void hideLoadDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }

    private ProgressDialog progressDialog;


    public static void jumpActivity(Context context, Class clazz) {
        Intent intent = new Intent();
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    public static void jumpActivity(Context context, Class clazz, String key, String value) {
        Intent intent = new Intent();
        intent.putExtra(key, value);
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }

    public static void jumpActivity(Context context, Class clazz, String key, ArrayList<String> value) {
        Intent intent = new Intent();
        intent.putStringArrayListExtra(key, value);
        intent.setClass(context, clazz);
        context.startActivity(intent);
    }


    public void hintKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 获取空视图
     *
     * @return
     */
    public View getEmptyView() {
        if (mEmptyView != null)
            return mEmptyView;
        return null;
    }
}
