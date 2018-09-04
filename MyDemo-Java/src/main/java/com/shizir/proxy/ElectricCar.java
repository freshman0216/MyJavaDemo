package com.shizir.proxy;

import java.lang.annotation.Repeatable;

/**
 * 电能车类，实现Rechargable，Vehicle接口
 * @author 石子儿
 */
public class ElectricCar implements Rechargable, Vehicle {
    @Override
    public void recharge() {
        System.out.println("Electric Car is Recharging...");
    }

    @Override
    public void drive() {
        System.out.println("Electric Car is Moving silently...");
    }
}
