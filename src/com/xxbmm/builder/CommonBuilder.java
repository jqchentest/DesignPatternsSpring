package com.xxbmm.builder;

/**
 * 具体建造者  普通人
 *
 * @author chenjunqiang 2019/1/21
 **/
public class CommonBuilder implements Builder {

    private Product product = new Product();

    @Override
    public void name() {
        product.setName("地精");
    }

    @Override
    public void face() {
        product.setFace("丑陋的");
    }

    @Override
    public void power() {
        product.setPower(2);
    }

    @Override
    public void girlFriend() {
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
