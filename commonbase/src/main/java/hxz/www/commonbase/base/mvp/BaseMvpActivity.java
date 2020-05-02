package hxz.www.commonbase.base.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.collection.ArraySet;


/**
 * Created by 韩湘子 on  2019/5/11
 * Email:15901419691@163.com
 * Dec:  mvp Activity 基类
 */
public abstract class BaseMvpActivity<P extends BasePresenter<? extends IBaseView>> extends BaseActivity implements IBaseView {


    protected P mPresenter;

    /**
     * 存放N个Presenter
     */
    private ArraySet<BasePresenter> mPresenters = new ArraySet<>(1);

    /**
     * 销毁所有任务
     */
    @Override
    protected void onDestroy() {
        for (BasePresenter presenter : mPresenters) {
            presenter.detachView();
        }
        mPresenters.clear();
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
        super.onDestroy();
    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {
        mPresenter = getPresenter();
        addToPresenters(mPresenter);
        initView();
        initEvent();
        initData();
    }

    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * 初始化事件监听
     */
    protected abstract void initEvent();

    /**
     * 初始化View
     */
    protected abstract void initView();


    /**
     * 初始化  Presenter  同一调用此Presenter方法,不管多个N个
     */
    protected abstract P getPresenter();


    /**
     * Presenter添加到Presenters集合里
     * 自动绑定View和管理内存释放
     */
    protected <T extends BasePresenter> void addToPresenters(T presenter) {
        presenter.attachView(this);
        mPresenters.add(presenter);
    }


    @Override
    public void showLoadDialog(String text, Context context) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage(text);
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void hideLoadDialog() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

        }
    }

    private ProgressDialog progressDialog;


}
