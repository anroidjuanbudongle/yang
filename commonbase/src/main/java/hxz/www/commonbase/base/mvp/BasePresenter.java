package hxz.www.commonbase.base.mvp;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by 韩湘子 on  2019/5/11
 * Email:15901419691@163.com
 */
public abstract class BasePresenter<V extends IBaseView> {

    protected V mView;

    /**
     * Disposable容器，收集Disposable，主要用于内存泄漏管理
     */
    private CompositeDisposable mDisposables;


    /**
     * 绑定 View
     *
     * @param view
     */
    public <T extends IBaseView> void attachView(T view) {
        this.mView = (V) view;
        mDisposables = new CompositeDisposable();
    }

    /**
     * 解绑 View
     */
    public void detachView() {
        mDisposables.clear();
        mDisposables = null;
        mView = null;

    }

    /**
     * @param disposable 添加Disposable到CompositeDisposable
     *                   通过解除disposable处理内存泄漏问题
     */
    protected boolean addDisposable(Disposable disposable) {
        if (isNullOrDisposed(disposable)) {
            return false;
        }
        return mDisposables.add(disposable);
    }

    /**
     * @param d 判断d是否为空或者dispose
     * @return true:一次任务未开始或者已结束
     */
    protected boolean isNullOrDisposed(Disposable d) {
        return d == null || d.isDisposed();
    }

    /**
     * @param d 判断d是否dispose
     * @return true:一次任务还未结束
     */
    protected boolean isNotDisposed(Disposable d) {
        return d != null && !d.isDisposed();
    }

    /**
     * 获取 Model 实例
     */
    protected <M extends IBaseModel> M getModel(Class<M> clazz) {
        return ModelManger.getInstance().create(clazz);
    }


}
