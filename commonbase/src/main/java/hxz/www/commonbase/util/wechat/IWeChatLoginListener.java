package hxz.www.commonbase.util.wechat;

/**
 * Created by 韩湘子 on  2019/4/29
 * Email:15901419691@163.com
 * Dec: 微信登录回调接口
 */
public interface IWeChatLoginListener extends IWeChatListener {

    void onLoginSuccess(String code);
}
