package com.shizir.annotation;

/**
 * @description: 分表策略类型
 * @author: shizir
 * @create: 2018-06-19
 **/
public enum TableSplitStrategy {
    /**
     * 城市简称
     */
    CityShortInDB,
    /**
     * 取余
     */
    ModByID,
}
