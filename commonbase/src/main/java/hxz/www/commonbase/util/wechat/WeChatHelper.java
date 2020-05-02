package hxz.www.commonbase.util.wechat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 韩湘子 on  2019/4/29
 * Email:15901419691@163.com
 * Dec:微信辅助类
 */
public class WeChatHelper {


    /***
     *  两个常量 区别不同type  注册/取消注册不同的  接口
     */
    public static final int REGISTER_TYPE_LOGIN = 1;
    public static final int REGISTER_TYPE_SHARE = 2;


    private List<IWeChatShareListener> mShareListener = new ArrayList<>();
    private List<IWeChatLoginListener> mLoginListener = new ArrayList<>();

    private static class Holder {
        private static final WeChatHelper INSTANCE = new WeChatHelper();
    }

    private WeChatHelper() {
    }

    public static final WeChatHelper get() {
        return WeChatHelper.Holder.INSTANCE;
    }


    public void shareSuccess() {
        for (int i = 0; i < mShareListener.size(); i++) {
            mShareListener.get(i).onShareSuccess();
        }
    }

    public void loginSuccess(String code) {
        for (int i = 0; i < mLoginListener.size(); i++) {
            mLoginListener.get(i).onLoginSuccess(code);
        }
    }

    /**
     * @param listener     回调
     * @param registerType 监听事件的类型
     */
    public void register(IWeChatListener listener, int registerType) {
        if (registerType == REGISTER_TYPE_SHARE) {
            mShareListener.add((IWeChatShareListener) listener);
        } else if (registerType == REGISTER_TYPE_LOGIN) {
            mLoginListener.add((IWeChatLoginListener) listener);
        }
    }

    public void unregister(IWeChatListener listener) {
        mLoginListener.remove(listener);
        mShareListener.remove(listener);

    }
}
