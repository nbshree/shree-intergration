package com.shree.intergration.common.base.singleton;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 单例抽象类
 *
 * @param <T> 单例对象类型
 * @author Darkprayer
 */
public abstract class AbstractSingleton<T> {

    private final AtomicReference<T> ref = new AtomicReference<T>();

    public T getInstance() {
        T instance = ref.get();
        if (instance == null) {
            synchronized (this) {
                if (ref.get() == null) {
                    instance = newInstance();
                    ref.set(instance);
                } else {
                    instance = ref.get();
                }
            }
        }
        return instance;
    }

    /**
     * 单例对象的初始化方法
     *
     * @return 单例对象实例
     */
    protected abstract T newInstance();
}
