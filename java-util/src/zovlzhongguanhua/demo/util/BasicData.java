package zovlzhongguanhua.demo.util;

public class BasicData {

    public static void main(String[] args) {

        integer();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void integer() {
        Integer.valueOf(123456);
        Integer.valueOf("123456");
        Integer.parseInt("123456");
        Integer.parseUnsignedInt("123456");
        Integer.decode("123456");
        Integer.getInteger("123456");
        new Integer(123456);

        Integer integer = new Integer(123456789);

        byte b = integer.byteValue();
        double d = integer.doubleValue();
        float f = integer.floatValue();
        int i = integer.intValue();
        long l = integer.longValue();
        short s = integer.shortValue();

        String toString = integer.toString();
        int compareTo = integer.compareTo(new Integer(123456));

        int min = Integer.min(1, 3);
        int max = Integer.max(1, 3);
        int sum = Integer.sum(1, 10);
        int bitCount = Integer.bitCount(123456);
        int highestOneBit = Integer.highestOneBit(123456);
        int lowestOneBit = Integer.lowestOneBit(123456);
        int compare = Integer.compare(1, 3);
        int compareUnsigned = Integer.compareUnsigned(-1, -3);
        int numberOfLeadingZeros = Integer.numberOfLeadingZeros(12036025);
        int numberOfTrailingZeros = Integer.numberOfTrailingZeros(12036025);
        int reverse = Integer.reverse(123456);
        int signum = Integer.signum(123456);
    }
}