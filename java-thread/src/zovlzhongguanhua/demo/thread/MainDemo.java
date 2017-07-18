package zovlzhongguanhua.demo.thread;

public class MainDemo {

    public static void main(String[] args) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new WorkThread();
        thread.start();
    }

    public static class WorkThread extends Thread {

        public WorkThread() {
            super("newThread-WorkThread");
        }

        @Override
        public void run() {
            super.run();
            for (;;) {}
        }
    }
}
