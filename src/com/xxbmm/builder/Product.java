package com.xxbmm.builder;


/**
 * 产品角色
 *
 * @author chenjunqiang 2019/1/21
 **/
public class Product {
    /**
     * 名称
     */
    private String name;
    /**
     * 颜值
     */
    private String face;
    /**
     * 力量
     */
    private Integer power;

    /**
     * 女朋友
     */
    private String girlFriend;

    public void setName(String name) {
        this.name = name;
    }

    public void setFace(String face) {
        this.face = face;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public void setGirlFriend(String girlFriend) {
        this.girlFriend = girlFriend;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", face='" + face + '\'' +
                ", power=" + power +
                ", girlFriend='" + girlFriend + '\'' +
                '}';
    }
}
