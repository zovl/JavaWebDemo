package zovlzhongguanhua.demo.collection;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ListDemo {

    private static final int SIZE = 1 * 1000 * 1000;

    private static List<Object> sArrayList = new ArrayList<>();
    private static Vector<Object> sVector = new Vector<>();
    private static Stack<Object> sStack = new Stack<>();

    private static List<Object> sSynchronizedArrayList = Collections.synchronizedList(new ArrayList<>());

    static {
        for (int i = 0; i < SIZE; i++) {
            sArrayList.add(new Object());
        }
        for (int i = 0; i < SIZE; i++) {
            sVector.add(i, new Object());
        }
        for (int i = 0; i < SIZE; i++) {
            sStack.add(new Object());
        }
        for (int i = 0; i < SIZE; i++) {
            sSynchronizedArrayList.add(new Object());
        }
    }

    public static void main(String[] args) {

        System.out.println("sArrayList size is " + sArrayList.size());
        System.out.println("sVector size is " + sVector.size());
        System.out.println("sStack size is " + sStack.size());
        System.out.println("sSynchronizedArrayList size is " + sSynchronizedArrayList.size());

        // concurentList(sArrayList, 1);
        // concurentList(sVector, 2);
        // concurentList(sStack, 3);
        concurentList(sSynchronizedArrayList, 1);
    }

    private static void concurentList(final List list, final int type) {
        final Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("newThread-" + type + "-read");
                if (type == 1) {// sArrayList
                    for (;;) {
                        list.get(list.size()/2);
                    }
                } else if (type == 2) {// sVector
                    for (;;) {
                        list.get(list.size()/2);
                    }
                } else if (type == 3) {// sVector
                    for (;;) {
                        list.get(list.size()/2);
                    }
                }
            }
        };
        final AtomicInteger size = new AtomicInteger(list.size());
        final Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("newThread-" + type + "-write");
                if (type == 1) {// sArrayList
                    for (;;) {
                        list.add(new Object());
                    }
                } else if (type == 2) {// sVector
                    for (;;) {
                        list.add(new Object());
                    }
                } else if (type == 3) {// sVector
                    for (;;) {
                        list.add(new Object());
                    }
                }
            }
        };
        new Thread(readRunnable).start();
        new Thread(readRunnable).start();
        new Thread(writeRunnable).start();
        new Thread(writeRunnable).start();
    }
}
