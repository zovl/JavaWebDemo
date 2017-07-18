package zovlzhongguanhua.demo.thread;

public class ThreadSafetyDecompiler {

    public static void main(String[] args) {
        final ThreadSafetyDecompiler t = new ThreadSafetyDecompiler();
        t.a();
        t.b();
        s();
        t.r();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                t.n();
            }
        }).start();

        t.w();
    }

    public synchronized void a() {
        System.out.println("method a!");
    }

    public void b() {
        synchronized (this) {
            System.out.println("method b!");
        }
    }

    public static synchronized void s() {
        System.out.println("method s!");
    }

    public void r() {
        synchronized (ThreadSafetyDecompiler.class) {
            System.out.println("method r!");
        }
    }

    private Object o = new Object();

    public void w() {
        synchronized (o) {
            System.out.println("method w start!");
            try {
                o.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method w end!");
            for (;;) {}
        }
    }

    public void n() {
        synchronized (o) {
            System.out.println("method n!");
            o.notify();
        }
    }
}
