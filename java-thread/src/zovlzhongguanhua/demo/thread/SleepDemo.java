package zovlzhongguanhua.demo.thread;

public class SleepDemo {

    public static void main(String[] args) {

        new SleepThread().start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static class SleepThread extends Thread {

        public SleepThread() {
            super("newThread-SleepThread");
        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
