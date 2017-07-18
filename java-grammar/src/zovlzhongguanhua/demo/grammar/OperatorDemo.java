package zovlzhongguanhua.demo.grammar;

public class OperatorDemo {

    public static void main(String[] args) {

        String a = Long.toBinaryString(254632);
        String b = Long.toBinaryString(226545);
        int c = 6;
        int d = 3;
        int e = 254632;
        int f = 226545;

        System.out.println("a     :" + a);
        System.out.println("b     :" + b);
        System.out.println("c     :" + c);
        System.out.println("d     :" + d);

        /*算术运算符*/
        System.out.println("6+3   :" + (6+3));
        System.out.println("6-3   :" + (6-3));
        System.out.println("6*3   :" + (6*3));
        System.out.println("6/3   :" + (6/3));
        System.out.println("8%3   :" + (8%3));
        System.out.println("c++   :" + (c++));
        System.out.println("c--   :" + (c--));
        System.out.println("++c   :" + (++c));
        System.out.println("--c   :" + (--c));

        /*关系运算符*/
        System.out.println("6==3  :" + (6==3));
        System.out.println("6!=3  :" + (6!=3));
        System.out.println("6>3   :" + (6>3));
        System.out.println("6<3   :" + (6<3));
        System.out.println("6>=3  :" + (6>=3));
        System.out.println("6<=3  :" + (6<=3));

        /*位运算符*/
        System.out.println("a&b   :" + Long.toBinaryString(toBinary(a)&toBinary(b)));
        System.out.println("a|b   :" + Long.toBinaryString(toBinary(a)|toBinary(b)));
        System.out.println("~a    :" + Long.toBinaryString(~toBinary(a)));
        System.out.println("a>>2  :" + Long.toBinaryString(toBinary(a)>>2));
        System.out.println("a<<2  :" + Long.toBinaryString(toBinary(a)<<2));
        System.out.println("a>>>2 :" + Long.toBinaryString(toBinary(a)>>>2));

        /*赋值运算符*/
        System.out.println("c=2   :" + (c=2));
        System.out.println("c+=2  :" + (c+=2));
        System.out.println("c-=2  :" + (c-=2));
        System.out.println("c*=2  :" + (c*=2));
        System.out.println("c/=2  :" + (c/=2));
        System.out.println("c%=2  :" + (c%=2));
        System.out.println("e&=f  :" + Long.toBinaryString(e&=f));
        System.out.println("e|=f  :" + Long.toBinaryString(e|=f));
        System.out.println("e>>=f :" + Long.toBinaryString(e>>=f));
        System.out.println("e<<=f :" + Long.toBinaryString(e<<=f));
    }

    private static long toBinary(String number) {
        return Long.valueOf(number, 2);
    }
}
