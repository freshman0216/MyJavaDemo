package com.shizir.annotation;

import java.lang.annotation.*;

/**
 * @description: 分表注解
 * @author: shizir
 * @create: 2018-06-19
 **/
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(TableSplits.class)
@Inherited
@Documented
public @interface TableSplit {

    /**
     * 分表策略
     */
    public TableSplitStrategy strategy();

    /**
     * 取余方式分表的key（被除数）
     */
    public String modByIDStrategyKey() default "AgentID";

    /**
     * 取余方式分表的value（除数）
     */
    public int modByIDStrategyValue() default 1000;
}
