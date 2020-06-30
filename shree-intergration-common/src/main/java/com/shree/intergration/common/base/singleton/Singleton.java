package com.shree.intergration.common.base.singleton;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 公共单例集合
 *
 * @param <T> 单例对象类型
 * @author Darkprayer
 */
public class Singleton<T> {

    /***
     * 采用ConcurrentHashMap集合进行存储对象类型单例
     * key：ClassType对象的类型
     * value：Objectl对象的实例
     */
    private static final ConcurrentMap<Class, Object> map = new ConcurrentHashMap<>();

    /***
     *
     * @param type  传入进行实例化对象的类型
     * @return T 即返回对象的实例化
     */
    public static <T> T getInstance(Class<T> type) {
        Object ob = map.get(type);

        try {
            if (ob == null) {
                synchronized (map) {
                    if (map.get(type) == null) {
                        ob = type.newInstance();
                        map.put(type, ob);
                    } else {
                        ob = map.get(type);
                    }
                }
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return (T) ob;
    }

    /***
     * 该方法 是从map中移除对象实例化操作
     * @param type 传入进行实例化对象的类型
     */
    public static <T> void remove(Class<T> type) {
        map.remove(type);

    }

    /**
     * 清除所有Singleton对象
     */
    public static void destroy() {
        map.clear();
    }
}
