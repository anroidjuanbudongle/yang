package hxz.www.commonbase.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

import static android.os.FileUtils.copy;

/**
 * Date:2019/5/27 17:55
 * Info: Ui 常用工具类
 *
 * @author andy
 */
public class UiUtils {


    /**
     * 等比压缩
     *
     * @param bitmap 压缩目标
     * @param isDraw 重新绘制
     * @param weight 目标 宽
     * @param height 目标 高
     * @return
     */
    public static Bitmap compressImg(Bitmap bitmap, boolean isDraw, int weight, int height) {

        float bitmapWidth = bitmap.getWidth();
        float bitmapHeight = bitmap.getHeight();

        float scaleWidth = weight / bitmapWidth;
        float scaleHeight = height / bitmapHeight;


        Matrix matrix = new Matrix();
        //长和宽放大缩小的比例
        matrix.postScale(scaleWidth, scaleHeight);

        Bitmap mBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (isDraw) {
            bitmap.recycle();
        }

        return mBitmap;
    }


    /**
     * dp转px
     *
     * @param context
     * @param dpVal
     * @return
     */
    public static int dp2px(Context context, float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     *
     * @param context
     * @param spVal
     * @return
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     *
     * @param context
     * @param pxVal
     * @return
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 读取本机json
     *
     * @param fileName
     * @param context
     * @return
     */
    public static String getJson(String fileName, Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        AssetManager assetManager = context.getAssets();
        try {
            BufferedReader bf = new BufferedReader(new InputStreamReader(assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();

    }

    /**
     * 设置手机号 159****1520
     *
     * @param phoneNum
     * @return
     */
    public static String setSafePhone(String phoneNum) {
        return phoneNum.substring(0, 3) + "****" + phoneNum.substring(7, phoneNum.length());
    }

    /**
     * 设置银行开 4444  4444   4444   4444  333格式
     *
     * @param cardNum
     * @param editText
     * @param watcher
     */
    public static void setBankCard(String cardNum, EditText editText, TextWatcher watcher) {
        String str = cardNum.toString().trim().replace(" ", "");
        String result = "";
        if (str.length() >= 4) {
            editText.removeTextChangedListener(watcher);
            for (int i = 0; i < str.length(); i++) {
                result += str.charAt(i);
                if ((i + 1) % 4 == 0) {
                    result += " ";
                }
            }
            if (result.endsWith(" ")) {
                result = result.substring(0, result.length() - 1);
            }
            editText.setText(result);
            editText.addTextChangedListener(watcher);
            editText.setSelection(editText.getText().toString().length());//焦点到输入框最后位置
        }
    }


    public static Bitmap GetLocalOrNetBitmap(String url) {
        Bitmap bitmap = null;
        InputStream in = null;
        BufferedOutputStream out = null;
        try {
            in = new BufferedInputStream(new URL(url).openStream(), 2 * 1024);
            final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
            out = new BufferedOutputStream(dataStream, 2 * 1024);
            copy(in, out);
            out.flush();
            byte[] data = dataStream.toByteArray();
            bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
            data = null;
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static String getAssetsCacheFile(Context context, String fileName) {
        File cacheFile = new File(context.getCacheDir(), fileName);
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            try {
                FileOutputStream outputStream = new FileOutputStream(cacheFile);
                try {
                    byte[] buf = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buf)) > 0) {
                        outputStream.write(buf, 0, len);
                    }
                } finally {
                    outputStream.close();
                }
            } finally {
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cacheFile.getAbsolutePath();
    }

    /**
     * 获取用户当前手机IP
     *
     * @param context
     * @return
     */
    public static String getIPAddress(Context context) {
        NetworkInfo info = ((ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        if (info != null && info.isConnected()) {
            if (info.getType() == ConnectivityManager.TYPE_MOBILE) {//当前使用2G/3G/4G网络
                try {
                    for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                        NetworkInterface intf = en.nextElement();
                        for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                            InetAddress inetAddress = enumIpAddr.nextElement();
                            if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                return inetAddress.getHostAddress();
                            }
                        }
                    }
                } catch (SocketException e) {
                    e.printStackTrace();
                }

            } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {//当前使用无线网络
                WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());//得到IPV4地址
                return ipAddress;
            }
        } else {
            return "current  user  no  net!!!";
        }
        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    public static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." +
                ((ip >> 8) & 0xFF) + "." +
                ((ip >> 16) & 0xFF) + "." +
                (ip >> 24 & 0xFF);
    }

    /**
     * 获取当前月份
     *
     * @return
     */
    public static String getCurrentYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 时间戳转时间
     *
     * @param s
     * @return
     */
    public static String stampToDate(String s) {
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

}
