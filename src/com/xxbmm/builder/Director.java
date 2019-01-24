package com.xxbmm.builder;

/**
 * 指挥者
 *
 * @author chenjunqiang 2019/1/21
 **/
public class Director {
    private Builder builder;

    Director(Builder builder) {
        this.builder = builder;
    }

    /**
     * 产品构造方法，在该方法内，调用人类建造方法。
     */
    void construct() {
        builder.name();
        builder.face();
        builder.power();
        builder.girlFriend();
    }


    public static void main(String[] args) {
        Builder common = new CommonBuilder();
        Director director = new Director(common);
        director.construct();
        Product product = common.getProduct();
        System.out.println(product);

//        Builder superMan = new SuperManBuilder();
//        Director director = new Director(superMan);
//        director.construct();
//        Product product = superMan.getProduct();
//        System.out.println(product);
    }
}
