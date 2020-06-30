package com.shree.intergration.common.base.id.generator;


import com.shree.intergration.common.base.id.MichaelliaoId;
import com.shree.intergration.common.base.singleton.AbstractSingleton;

/**
 * MichaelliaoId生成器便捷单例
 *
 * @author Darkprayer
 */
public class MichaelliaoIdGenerator {

    public static final AbstractSingleton<MichaelliaoId> HOLDER = new AbstractSingleton<MichaelliaoId>() {
        @Override
        protected MichaelliaoId newInstance() {
            return new MichaelliaoId();
        }
    };

    public static long nextId() {
        return HOLDER.getInstance().nextId();
    }
}
