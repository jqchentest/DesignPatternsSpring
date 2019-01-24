package com.xxbmm.expression;

/**
 *
 * @author chenjunqiang 2019/1/21
 **/
public class TerminalExpression implements Expression {
    @Override
    public boolean interpret(String context) {
        return false;
    }
}
