package com.shree.intergration.common.base.id.generator;


import com.shree.intergration.common.base.id.SnowflakeId;
import com.shree.intergration.common.base.singleton.AbstractSingleton;

/**
 * MichaelliaoId生成器便捷单例
 *
 * @author Darkprayer
 */
public class SnowflakeIdGenerator {

    public static final AbstractSingleton<SnowflakeId> HOLDER = new AbstractSingleton<SnowflakeId>() {
        @Override
        protected SnowflakeId newInstance() {
            return new SnowflakeId(0, 0);
        }
    };

    public static long nextId() {
        return HOLDER.getInstance().nextId();
    }
}
