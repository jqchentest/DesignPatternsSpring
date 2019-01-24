package com.xxbmm.adapter;

/**
 * 双向适配器
 */
public class BothwayAdapter {
    static class PowerPort220V implements Target220V {
        @Override
        public void convertTo220v() {

        }
    }

    static class PowerPort110V implements Target {
        @Override
        public void convertTo110v() {
        }
    }

    static class Adapter implements Target, Target220V {
        private Target target;
        private Target220V target220V;

        public Adapter(Target target, Target220V target220V) {
            this.target = target;
            this.target220V = target220V;
        }

        @Override
        public void convertTo110v() {
            target220V.convertTo220v();
            System.out.println("220v转110v");
        }

        @Override
        public void convertTo220v() {
            target.convertTo110v();
            System.out.println("110v转220v");
        }
    }

    public static void main(String[] args) {
        Adapter adapter = new Adapter(new PowerPort110V(), new PowerPort220V());
        adapter.convertTo110v();
        adapter.convertTo220v();
    }
}
