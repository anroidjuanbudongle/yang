package hxz.www.commonbase.base.mvp;

import android.content.Context;

/**
 * Created by 韩湘子 on  2019/5/11
 * Email:15901419691@163.com
 * Dec: 顶层约束
 *
 * @author andy
 */
public interface IBaseView {

    /**
     * toast提示信息
     *
     * @param errorMsg 信息内容
     */
    void showToast(String errorMsg);

    /**
     * 上传的dialog
     *
     * @param text
     */
    void showLoadDialog(String text, Context context);

    /**
     * 隐藏dialog
     */
    void hideLoadDialog();

}
