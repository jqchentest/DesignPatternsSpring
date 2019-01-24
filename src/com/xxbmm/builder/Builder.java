package com.xxbmm.builder;

/**
 * 抽象建造者
 *
 * @author chenjunqiang 2019/1/21
 **/
public interface Builder {
    void name();

    void face();

    void power();

    void girlFriend();

    Product getProduct();
}
