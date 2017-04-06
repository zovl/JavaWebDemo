package zovlzhongguanhua.demo.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

public class IOfile {

    public static void main(String[] args) {

        write();
        read();
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void read() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "scanner"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        String readString;
        int i = 0;
        while (scanner.hasNextLine()) {
            ++i;
            readString = scanner.nextLine();
            System.out.println(i + " lines：" + readString);
        }
        scanner.close();
    }

    private static void write() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "writer"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.println("This is printed words");
        writer.write("This is writed words");
        writer.flush();
        writer.close();
    }
}
