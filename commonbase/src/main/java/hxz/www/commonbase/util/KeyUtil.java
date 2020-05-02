package hxz.www.commonbase.util;

public final class KeyUtil {
    /**
     * Activity 携带数据Key
     */
    public static final String JUMP_ACTIVITY = "JUMP_ACTIVITY";
    /**
     * 登录页选择code码
     */
    public static final String LOGINACTIVITY_COUNTRY_CODE = "10";
    /**
     * 注册页选择code码
     */
    public static final String REGISTEREDACTIVITY_COUNTRY_CODE = "11";

    /**
     * 换手机号页选择code码
     */
    public static final String SETNEWPHONEACTIVITY_COUNTRY_CODE = "12";
    /**
     * 是否第一次进入App
     */
    public static final String IS_COME_APP = "is_come_app";
    /**
     * 微信登陆
     */
    public static final String WX_API_LOGIN_CODE = "WX_API_LOGIN_CODE";
    /**
     * 微信分享
     */
    public static final String WX_API_SHARE_CODE = "WX_API_SHARE_CODE";
    /**
     * access token
     */
    public static final String WX_LOGIN_URL_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=";
    /**
     * USER INFO
     */
    public static final String WX_LOGIN_URL_USER_INFO = "  https://api.weixin.qq.com/sns/userinfo?access_token=";
    /**
     * alipay
     */
    public static final int ALIPAY_HANDLER_CODE = 120;
    /**
     * 分页加载条数
     */
    public static final int LIST_PAGER_SIZE = 10;
    /**
     * 价格筛选 (传入1从低到高）
     */
    public static final int EXPAND_FROM_SMALL_TO_BIG = 1;
    /**
     * 价格筛选（传入0从高到底）
     */
    public static final int EXPAND_FROM_BIG_TO_SMALL = 0;
    /**
     * http  code  吗
     */
    public static final int HTTP_NET_SUCCESS_CODE = 200;
    /**
     * 0未支付
     */
    public static final int ORDER_STATUS_UN_PAID = 0;
    /**
     * 2待发货
     */
    public static final int ORDER_STATUS_TO_BE_DELIVERED = 2;
    /**
     * 3已发货
     */
    public static final int ORDER_STATUS_SHIPPED = 3;
    /**
     * 4已失效
     */
    public static final int ORDER_STATUS_EXPIRED = 4;
    /**
     * 5交易成功
     */
    public static final int ORDER_STATUS_BE_EVALUATED = 5;
    /**
     * 7 处理中
     */
    public static final int ORDER_STATUS_PROCESSING = 7;
    /**
     * 8 待用户发货
     */
    public static final int ORDER_STATUS_DELIVERED = 8;
    /**
     * 9 申请已撤销
     */
    public static final int ORDER_STATUS_REVOKE = 9;
    /**
     * 10 商家已拒绝
     */
    public static final int ORDER_STATUS_REFUSED = 10;
    /**
     * 11 退款成功
     */
    public static final int ORDER_STATUS_SUCCESS = 11;
    /**
     * 添加地址订单
     */
    public static final String FILL_ORDER_CITY_ID = "fill_order_city_id";
    /**
     * wp succoss
     */
    public static final String WX_PAY_ON_SUCCESS = "wx_pay_on_success";

    /***
     * shang pin  dec
     */
    public static final String MODEL_SHOP_DEC_GO_TO_BUY = "model_shop_dec_go_to_buy";


}
