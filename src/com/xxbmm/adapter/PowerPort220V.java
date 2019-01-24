package com.xxbmm.adapter;

/**
 * 源角色 原有的插头220v
 */
public class PowerPort220V {
    /**
     * 原有插头只能输出220V
     */
    public void OutputTo220v() {
    }
}

/**
 * 适配器角色 220v插头适配器
 */
class Adapter220V extends PowerPort220V implements Target {
    @Override
    public void convertTo110v() {
        System.out.println("类适配器");
        this.OutputTo220v();
        System.out.println("220v转换成110v电压 可以使用了");
    }

    public static void main(String[] args) {
        Target target = new Adapter220V();
        target.convertTo110v();
    }
}

/**
 * 对象适配器角色 220v插头适配器
 */
class Adapter220V2 implements Target {

    private PowerPort220V powerPort220V = new PowerPort220V();

    @Override
    public void convertTo110v() {
        System.out.println("对象适配器");
        powerPort220V.OutputTo220v();
        System.out.println("220v转换成110v电压 可以使用了");
    }

    public static void main(String[] args) {
        Target target = new Adapter220V2();
        target.convertTo110v();
    }
}

