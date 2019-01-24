package com.xxbmm.publish;

/**
 * 具体观察者 微信用户
 *
 * @author chenjunqiang 2019/1/22
 **/
public class WeixinUser implements Observer {
    private String name;

    public WeixinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(String.format("%s，%s", name, message));
    }
}
