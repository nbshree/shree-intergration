package com.shree.intergration.common.base.id.generator;


import com.shree.intergration.common.base.id.LiteSnowflakeId;
import com.shree.intergration.common.base.singleton.AbstractSingleton;

/**
 * LiteSnowflakeId生成器便捷单例
 *
 * @author Darkprayer
 */
public class LiteSnowflakeIdGenerator {

    public static final AbstractSingleton<LiteSnowflakeId> HOLDER = new AbstractSingleton<LiteSnowflakeId>() {
        @Override
        protected LiteSnowflakeId newInstance() {
            return new LiteSnowflakeId(0);
        }
    };

    public static long nextId() {
        return HOLDER.getInstance().nextId();
    }
}
