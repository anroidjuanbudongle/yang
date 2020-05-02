package hxz.www.commonbase.base.mvp;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by 韩湘子 on  2019/5/11
 * Email:15901419691@163.com
 * Dec:Model 管理
 */
public final class ModelManger {

    private final ConcurrentHashMap<Class<? extends IBaseModel>, ? extends IBaseModel> DATA_CACHE;

    private ModelManger() {
        DATA_CACHE = new ConcurrentHashMap<>(8);
    }

    /**
     * @return ModelManager单例实例
     */
    public static ModelManger getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ModelManger INSTANCE = new ModelManger();
    }

    /**
     * 创建获取 Model 层实例
     * @param clazz IBaseModel 子类 class
     */
    public <M extends IBaseModel> M create(final Class<M> clazz) {

        IBaseModel model = DATA_CACHE.get(clazz);
        if (model != null) {
            return (M) model;
        }

        synchronized (DATA_CACHE) {
            model = DATA_CACHE.get(clazz);
            if (model == null) {
                try {
                    Constructor<M> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    model = constructor.newInstance();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        return (M) model;
    }

}
