package zovlzhongguanhua.demo.thread;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomIntegerDemo {

    private static final double LIMIT = Math.pow(10, 7);

    private static AtomicInteger count = new AtomicInteger(0);

    private static int n = 0;
    private static AtomicInteger m = new AtomicInteger(0);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new PrimeThreadM().start();
        }
    }

    public static class PrimeThreadN extends Thread {

        @Override
        public void run() {
            super.run();
            while ((n = n + 1) <= LIMIT) {
                if (isPrime(n)) {
                    System.out.println("n=" + n);
                }
            }
        }
    }

    public static class PrimeThreadM extends Thread {

        @Override
        public void run() {
            super.run();
            while (m.incrementAndGet() <= LIMIT) {
                if (isPrime(m.get())) {
                    System.out.println("m=" + m.get() + "/" + "count=" + count.incrementAndGet());
                }
            }
        }
    }

    public static boolean isPrime(int n) {
        if (n <= 3) {
            return n > 1;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }
}
