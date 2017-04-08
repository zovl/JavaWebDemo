package zovlzhongguanhua.demo.thread;

public class ThreadDemo {

    public static void main(String[] args) {

        newThread();
        newRunnable();
    }

    private static void newThread() {
        new Thread() {

            @Override
            public void run() {
                super.run();
                setName("newThread");
                System.out.println("Thread：" + Thread.currentThread().getName() + " is started");
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread：" + Thread.currentThread().getName() + " is finished");
            }
        }.start();
    }

    private static void newRunnable() {
        new Thread(new Runnable() {

            @Override
            public void run() {

                Thread.currentThread().setName("newRunnable");
                System.out.println("Thread：" + Thread.currentThread().getName() + " is started");
                try {
                    Thread.sleep(3 * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread：" + Thread.currentThread().getName() + " is finished");
            }
        }).start();
    }
}
