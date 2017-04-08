package zovlzhongguanhua.demo.util;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigData {

    public static void main(String[] args) {

        long a = System.currentTimeMillis();
        BigInteger.valueOf(1234567).pow(1234567);
        long b = System.currentTimeMillis();
        System.out.println("BigInteger time is " + (b - a) / 1000l + " seconds");

        long c = System.currentTimeMillis();
        BigDecimal.valueOf(123456789.123456789f).pow(123456);
        long d = System.currentTimeMillis();
        System.out.println("BigDecimal time is " + (d - c) / 1000l + " seconds");
    }
}