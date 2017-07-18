package zovlzhongguanhua.demo.thread;

/**
 * 同步关键字
 */
public class SynchronizedDemo2 {

    public static void main(String[] args) {

        // 线程A, B 读同一个阻塞的空循环方法

        SyncObject obj = new SyncObject();
        new SyncThreada(obj).start();
        new SyncThreadb(obj).start();
    }

    public static class SyncThreada extends Thread {

        private SyncObject syncObject;

        public SyncThreada(SyncObject syncObject) {
            super("newThread-a");
            this.syncObject = syncObject;
        }

        @Override
        public void run() {
            super.run();

            if (syncObject != null) {
                syncObject.exec();
            }
        }
    }

    public static class SyncThreadb extends Thread {

        private SyncObject syncObject;

        public SyncThreadb(SyncObject syncObject) {
            super("newThread-b");
            this.syncObject = syncObject;
        }

        @Override
        public void run() {
            super.run();

            if (syncObject != null) {
                syncObject.exec();
            }
        }
    }

    public static class SyncObject {

        public synchronized void exec() {
            System.out.println("exec: " + Thread.currentThread().getName() + "--start");
            for (;;) {}
            // System.out.println("exec: " + Thread.currentThread().getName() + "--end");
        }
    }
}
