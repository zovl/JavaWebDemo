package zovlzhongguanhua.demo.thread;

import java.util.Random;

/**
 * volatile的读与写
 */
public class VolatileDemo {

    public static final long TIME = 1*1000*1000;

    public static void main(String[] args) {

        VolatileInteger object = new VolatileInteger();
        /**A线程写*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < TIME; j++) {
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    object.write(j + 1, new Random().nextBoolean());
                }
            }
        }).start();
        /**B线程读*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int j = 0; j < TIME * 2; j++) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    object.read();
                }
            }
        }).start();
    }

    public static class VolatileInteger {

        private int a = 0;
        private volatile boolean flag = false;

        /*A线程写*/
        public void write(int a, boolean flag) {
            this.a = a;
            System.out.println("write: a=" + a);
            this.flag = flag;
        }

        /*B线程读*/
        public void read() {
            // System.out.println("read: a=" + a);

            if (flag) {
                System.out.println("read: a=" + a);
            }
        }
    }

    public static class VolatileObject {

        private Object o = new Object();
        private volatile boolean flag = false;

        /*A线程写*/
        public void write(int a, boolean flag) {
            this.o.a = a;
            System.out.println("write: o.a=" + a);
            this.flag = flag;
        }

        /*B线程读*/
        public void read() {
            // System.out.println("read: o.a=" + o.a);

            if (flag) {
                System.out.println("read: o.a=" + o.a);
            }
        }
    }

    public static class Object {
        public int a = 0;
    }
}
