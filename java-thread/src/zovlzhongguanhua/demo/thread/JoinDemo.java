package zovlzhongguanhua.demo.thread;

public class JoinDemo {

    public static void main(String[] args) {

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Thread thread = new JoinThread();
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (;;) {}
    }

    public static class JoinThread extends Thread {

        public JoinThread() {
            super("newThread-JoinThread");
        }

        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
