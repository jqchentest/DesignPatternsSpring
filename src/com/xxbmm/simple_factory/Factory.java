package com.xxbmm.simple_factory;

/**
 * 工厂角色 自动创建汽车
 *
 * @author chenjunqiang 2019/1/21
 **/
public class Factory {

    public static Car getCar(String type) throws Exception {
        if ("Benz".equals(type)) {
            return new Benz();
        } else if ("Audi".equals(type)) {
            return new Audi();
        } else if ("Bmw".equals(type)) {
            return new Bmw();
        }
        return null;
//        return (Car) Class.forName("com.xxbmm.simple_factory.Factory$" + type).newInstance();
    }

    /**
     * 具体产品 宝马
     *
     * @author chenjunqiang
     **/
    public static class Bmw extends Car {

        @Override
        void drive() {
            System.out.println("宝马启动~");
        }
    }

    /**
     * 具体产品 奔驰
     *
     * @author chenjunqiang
     **/
    public static class Benz extends Car {

        @Override
        void drive() {
            System.out.println("奔驰启动~");
        }
    }

    /**
     * 具体产品 奥迪
     *
     * @author chenjunqiang
     **/
    public static class Audi extends Car {

        @Override
        void drive() {
            System.out.println("奥迪启动~");
        }
    }

    public static void main(String[] args) throws Exception {
        Car car = Factory.getCar("Benz");
        car.drive();
    }
}
