package com.xxbmm.publish;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体被观察者 小程序
 * @author chenjunqiang 2019/1/22
 **/
public class XiaochengxuSubject implements Subject {
    /**
     * 储存订阅小程序的微信用户
     */
    private List<Observer> userList = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        userList.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        userList.remove(observer);
    }

    @Override
    public void notify(String message) {
        for (Observer observer : userList) {
            observer.update(message);
        }
    }
}
