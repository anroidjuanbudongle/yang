package hxz.www.commonbase.base.mvp;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.collection.ArraySet;


/**
 * Created by 韩湘子 on  2019/6/28
 * Email:15901419691@163.com
 * Dec:mvp  fragment
 */
public abstract class BaseMvpFragment<P extends BasePresenter<? extends IBaseView>> extends BaseFragment implements IBaseView {

    protected P mPresenter;

    private ArraySet<BasePresenter> mPresenters = new ArraySet<>(1);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutId(), container, false);
        mRootView = view;
        mPresenter = getPresenter();
        addToPresenters(mPresenter);
        initView();
        return view;
    }


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

    /**
     * 销毁所有任务
     */
    @Override
    public void onDestroy() {
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
