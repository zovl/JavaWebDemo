package zovlzhongguanhua.demo.thread;

public class YieldDemo {

    public static void main(String[] args) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new YieldThread();
        thread.start();
    }

    public static class YieldThread extends Thread {

        public YieldThread() {
            super("newThread-YieldThread");
        }

        @Override
        public void run() {
            super.run();
            Thread.yield();
            for (;;) {}
        }
    }
}
