package com.xxbmm.expression;


/**
 * 抽象表达式，声明一个所有的具体表达式都需要实现的抽象接口；
 * 这个接口主要是一个interpret()方法，称做解释操作。
 *
 * @author chenjunqiang 2019/1/21
 **/
public interface Expression {

    boolean interpret(String context);
}
