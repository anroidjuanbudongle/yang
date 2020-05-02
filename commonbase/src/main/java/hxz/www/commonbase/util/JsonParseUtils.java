package hxz.www.commonbase.util;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * Created by 韩湘子 on  2019/4/30
 * Email:15901419691@163.com
 * Dec:捕捉Json解析异常,避免出现由此引起 的crash
 */
public class JsonParseUtils {
    private static Gson gson;

    static {
        gson = new Gson();
    }

    public static Gson getGson() {
        return gson;
    }

    /**
     * 对象转成json
     *
     * @param <T>
     */
    public static <T> String obj2JsonStr(T t, String def) {
        try {
            return gson.toJson(t);
        } catch (Exception e) {
            Log.e("json解析异常" + t, Log.getStackTraceString(e));
        }
        return def;
    }

    /**
     * json转成对象
     *
     * @param <T>
     */
    public static <T> T json2Obj(String json, Class<T> clazz) {
        try {
            return gson.fromJson(json, clazz);
        } catch (Exception e) {
            Log.e("json解析异常" + json, Log.getStackTraceString(e));
        }
        return null;
    }

    /**
     * json字符直接转成json对象
     */
    public static <T> T json2Object(String jsonString, Class<T> cls) {
        T t = null;
        try {
            Gson gson = new Gson();
            t = gson.fromJson(jsonString, cls);
        } catch (Exception e) {
            Log.e("json解析异常" + jsonString, Log.getStackTraceString(e));
        }
        return t;
    }

    public static String getString(JSONObject json, String key) {
        return getString(json, key, "");
    }

    public static String getString(String json, String key) {
        return getString(getJSONObject(json), key, "");
    }


    public static String getString(JSONObject json, String key, String def) {
        String value = def;
        if (canParse(json, key)) {
            try {
                value = json.getString(key);
            } catch (Exception e) {
                Log.e("json解析异常" + json, Log.getStackTraceString(e));
            }
        }

        return value;
    }

    public static int getInt(JSONObject json, String key) {
        return getInt(json, key, -1);
    }

    public static int getInt(String json, String key) {
        return getInt(JsonParseUtils.getJSONObject(json), key, -1);
    }

    public static int getInt(JSONObject json, String key, int def) {
        int value = def;
        if (canParse(json, key)) {
            try {
                value = json.getInt(key);
            } catch (Exception e) {
                Log.e("getInt=", String.valueOf(Log.getStackTraceString(e)));
            }
        }

        return value;
    }

    public static long getLong(JSONObject json, String key) {
        return getLong(json, key, -1);
    }

    public static long getLong(String json, String key) {
        return getLong(getJSONObject(json), key, -1);
    }

    public static long getLong(JSONObject json, String key, long def) {
        long value = def;
        if (canParse(json, key)) {
            try {
                value = json.getLong(key);
            } catch (Exception e) {
                Log.e("getInt=", Log.getStackTraceString(e));
            }
        }

        return value;
    }

    public static JSONObject getJSONObject(JSONArray array, int index) {
        return getJSONObject(array, index, new JSONObject());
    }

    public static JSONObject getJSONObject(JSONArray array, int index, JSONObject def) {
        JSONObject returnObj = def;
        if (array != null && array.length() > 0) {
            try {
                returnObj = array.getJSONObject(index);
            } catch (Exception e) {
                Log.e("getJSONObject=", Log.getStackTraceString(e));
            }
        }
        return returnObj;
    }

    public static JSONObject getJSONObject(JSONObject json, String key) {
        return getJSONObject(json, key, new JSONObject());
    }

    public static JSONObject getJSONObject(JSONObject json, String key, JSONObject def) {
        JSONObject returnObj = def;
        if (canParse(json, key)) {
            try {
                returnObj = json.getJSONObject(key);
            } catch (Exception e) {
                Log.e("getJSONObject=", Log.getStackTraceString(e));
            }
        }
        return returnObj;
    }

    public static JSONArray getJSONArray(JSONObject json, String key) {
        return getJSONArray(json, key, new JSONArray());
    }

    public static JSONArray getJSONArray(String json, String key) {
        return getJSONArray(getJSONObject(json), key, new JSONArray());
    }

    public static JSONArray getJSONArray(JSONObject json, String key, JSONArray def) {
        JSONArray array = def;
        if (canParse(json, key)) {
            try {
                array = json.getJSONArray(key);
            } catch (Exception e) {
                Log.e("getJSONArray=", Log.getStackTraceString(e));
            }
        }
        return array;
    }


    public static JSONObject getJSONObject(String json) {
        return getJSONObject(json, new JSONObject());
    }

    public static JSONObject getJSONObject(String json, JSONObject def) {
        JSONObject jsonObject = def;
        try {
            jsonObject = new JSONObject(json);
        } catch (Exception e) {  //不只是JsonException，还可能是别的异常
            Log.e("json解析异常" + json, Log.getStackTraceString(e));
        }
        return jsonObject;
    }

    public static JSONArray getJSONArray(String json) {
        return getJSONArray(json, new JSONArray());
    }

    public static JSONArray getJSONArray(String json, JSONArray def) {
        JSONArray jsonArray = def;
        try {
            jsonArray = new JSONArray(json);
        } catch (Exception e) {
            Log.e("json解析异常" + json, Log.getStackTraceString(e));
        }
        return jsonArray;
    }

    /**
     * 判断数据是否可以解析，可以则return true;
     */
    private static boolean canParse(JSONObject json, String key) {
        if (json != null || TextUtils.isEmpty(key)) {
            return json.has(key);
        }
        return false;
    }

    public static <T> List<T> parseJsonArray2List(JSONArray jsonArray, Class<T> clazz) {
        try {
            List<T> list = new ArrayList<T>();
            if (jsonArray == null) return list;
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = JsonParseUtils.getJSONObject(jsonArray, i, null);
                if (jsonObject == null) continue;
                T t = (T) JsonParseUtils.json2Obj(jsonObject.toString(), clazz);
                list.add(t);
            }
            return list;
        } catch (java.lang.Exception exception) {
            Log.e("json解析异常 ", Log.getStackTraceString(exception));
        }
        return null;
    }

    public static List<Integer> parseJsonArray2IntegerList(JSONArray jsonArray) {
        try {
            List<Integer> list = new ArrayList<Integer>();
            if (jsonArray == null) return list;
            for (int i = 0; i < jsonArray.length(); i++) {
                int anInt = 0;
                try {
                    anInt = jsonArray.getInt(i);
                } catch (Exception e) {
                    Log.e("json解析异常 ", Log.getStackTraceString(e));
                }
                list.add(anInt);
            }
            return list;
        } catch (java.lang.Exception exception) {
            Log.e("json解析异常 ", Log.getStackTraceString(exception));
        }
        return null;
    }

    public static List<String> parseJsonArray2StringList(JSONArray jsonArray) {
        try {
            List<String> list = new ArrayList<String>();
            if (jsonArray == null) return list;
            for (int i = 0; i < jsonArray.length(); i++) {
                String anInt = "";
                try {
                    anInt = jsonArray.getString(i);
                } catch (Exception e) {
                    Log.e("json解析异常 ", Log.getStackTraceString(e));
                }
                list.add(anInt);
            }
            return list;
        } catch (java.lang.Exception exception) {
            Log.e("json解析异常 ", Log.getStackTraceString(exception));
        }
        return null;
    }

    public static void putValue(JSONObject json, String key, Object value) {
        try {
            json.put(key, value);
        } catch (Exception e) {
            Log.e("json解析异常 ", Log.getStackTraceString(e));
        }
    }

    public static Map<String, Object> jsonToMap(String jsonString) throws JSONException {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Map<String, Object> resultMap = new HashMap<String, Object>();
            Iterator<String> iter = jsonObject.keys();
            String key = null;
            Object value = null;
            while (iter.hasNext()) {
                key = iter.next();
                value = jsonObject.get(key);
                resultMap.put(key, value);
            }
            return resultMap;
        } catch (java.lang.Exception exception) {
            Log.e("json解析异常 ", Log.getStackTraceString(exception));
        }
        return null;
    }

    public static Map<String, String> json2Map(String jsonString) throws JSONException {
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            Map<String, String> resultMap = new HashMap<String, String>();
            Iterator<String> iter = jsonObject.keys();
            String key = null;
            String value = null;
            while (iter.hasNext()) {
                key = iter.next();
                value = jsonObject.get(key).toString();
                resultMap.put(key, value);
            }
            return resultMap;
        } catch (java.lang.Exception exception) {
            Log.e("json解析异常 ", Log.getStackTraceString(exception));
        }
        return null;
    }

    public static double getDouble(String json, String key) {
        return getDouble(getJSONObject(json), key, 0.0);
    }

    public static double getDouble(JSONObject json, String key) {
        return getDouble(json, key, 0.0);
    }

    public static double getDouble(JSONObject json, String key, double def) {
        double value = def;
        if (canParse(json, key)) {
            try {
                value = json.getDouble(key);
            } catch (Exception e) {
                Log.e("getInt=", Log.getStackTraceString(e));
            }
        }
        return value;
    }
}
