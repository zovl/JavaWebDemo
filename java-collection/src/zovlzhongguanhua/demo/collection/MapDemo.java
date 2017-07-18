package zovlzhongguanhua.demo.collection;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class MapDemo {

    private static final int SIZE = 1 * 1000 * 1000;

    private static Map<Object, Object> sHashMap = new HashMap<>();
    private static Map<Integer, Object> sTreeMap = new TreeMap<>();
    private static Map<Object, Object> sHashtable = new Hashtable<>();

    private static Map<Object, Object> sSynchronizedHashMap = Collections.synchronizedMap(new HashMap<>());
    private static Map<Integer, Object> sSynchronizedTreeMap = Collections.synchronizedMap(new TreeMap<>());

    static {
        for (int i = 0; i < SIZE; i++) {
            sHashMap.put(new Object(), new Object());
        }
        for (int i = 0; i < SIZE; i++) {
            sTreeMap.put(i, new Object());
        }
        for (int i = 0; i < SIZE; i++) {
            sHashtable.put(new Object(), new Object());
        }
        for (int i = 0; i < SIZE; i++) {
            sSynchronizedHashMap.put(new Object(), new Object());
        }
        for (int i = 0; i < SIZE; i++) {
            sSynchronizedTreeMap.put(i, new Object());
        }
    }

    public static void main(String[] args) {

        System.out.println("sHashMap size is " + sHashMap.size());
        System.out.println("sTreeMap size is " + sTreeMap.size());
        System.out.println("sHashtable size is " + sHashtable.size());
        System.out.println("sSynchronizedHashMap size is " + sSynchronizedHashMap.size());
        System.out.println("sSynchronizedTreeMap size is " + sSynchronizedTreeMap.size());

        // concurentMap(sHashMap, 1);
        // concurentMap(sTreeMap, 2);
        concurentMap(sHashtable, 3);
        // concurentMap(sSynchronizedHashMap, 1);
        // concurentMap(sSynchronizedTreeMap, 2);
    }

    private static void concurentMap(final Map map, final int type) {
        final Runnable readRunnable = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("newThread-" + type + "-read");
                if (type == 1) {// HashMap
                    for (;;) {
                        map.get(new Object());
                    }
                } else if (type == 2) {// TreeMap
                    for (;;) {
                        map.get(map.size()/2);
                    }
                } else if (type == 3) {// sHashtable
                    for (;;) {
                        map.get(new Object());
                    }
                }
            }
        };
        final AtomicInteger size = new AtomicInteger(map.size());
        final Runnable writeRunnable = new Runnable() {
            @Override
            public void run() {
                Thread.currentThread().setName("newThread-" + type + "-write");
                if (type == 1) {// HashMap
                    for (;;) {
                        map.put(new Object(), new Object());
                    }
                } else if (type == 2) {// TreeMap
                    for (;;) {
                        map.put(size.getAndIncrement(), new Object());
                    }
                } else if (type == 3) {// sHashtable
                    for (;;) {
                        map.put(new Object(), new Object());
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
