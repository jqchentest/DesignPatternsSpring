package com.xxbmm.builder;

/**
 * 具体建造者  超人
 *
 * @author chenjunqiang 2019/1/21
 **/
public class SuperManBuilder implements Builder {

    private Product product = new Product();

    @Override
    public void name() {
        product.setName("龙");
    }

    @Override
    public void face() {

        product.setFace("帅气的");
    }

    @Override
    public void power() {
        product.setPower(9999);
    }

    @Override
    public void girlFriend() {
        product.setGirlFriend("有三个");
    }

    @Override
    public Product getProduct() {
        return product;
    }
}
