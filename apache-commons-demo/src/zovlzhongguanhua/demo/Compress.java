package zovlzhongguanhua.demo;

import org.apache.commons.compress.archivers.ArchiveException;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.apache.commons.compress.archivers.zip.ZipFile;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.util.Enumeration;

public class Compress {

    public static void main(String[] args) {

        File originalFile = new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + "lpds_4cace7161069b.mp4");

        String targetName = null;
        if (originalFile != null) {
            int dot = originalFile.getName().lastIndexOf('.');
            if ((dot >-1) && (dot < (originalFile.getName().length()))) {
                targetName = originalFile.getName().substring(0, dot);
            }
        }
        File targetFile = new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + targetName + ".zip");

        /*压缩文件*/

        zipFile(originalFile);

        /*解压文件*/

        unzipFile(targetFile);
    }

    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------
    // -----------------------------------------------------------------------------------------------------------------

    private static void zipFile(File originalFile) {
        // 目标文件
        String targetName = null;
        if (originalFile != null && originalFile.exists()) {
            int dot = originalFile.getName().lastIndexOf('.');
            if ((dot >-1) && (dot < (originalFile.getName().length()))) {
                targetName = originalFile.getName().substring(0, dot);
            }
        }
        File targetFile = new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + targetName + ".zip");
        // 删除已存在文件
        if (targetFile.exists()) {
            try {
                targetFile.delete();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 压缩文件
        ArchiveStreamFactory factory = new ArchiveStreamFactory();
        ZipArchiveOutputStream outputStream = null;
        try {
            outputStream = (ZipArchiveOutputStream) factory.createArchiveOutputStream("zip",
                    new FileOutputStream(targetFile));
        } catch (ArchiveException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (outputStream != null && originalFile.exists()) {
            outputStream.setEncoding("UTF8");
            ZipArchiveEntry entry = new ZipArchiveEntry(originalFile.getName());
            FileInputStream inputStream = null;
            try {
                outputStream.putArchiveEntry(entry);
                inputStream = new FileInputStream(originalFile);
                IOUtils.copy(inputStream, outputStream, 1024);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.closeArchiveEntry();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void unzipFile(File originalFile) {
        // 解压文件
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(originalFile, "UTF8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Enumeration<ZipArchiveEntry> enumeration = zipFile.getEntries();
        ZipArchiveEntry entry;
        while (enumeration.hasMoreElements()) {
            entry = enumeration.nextElement();
            // 目标文件
            File targetFile = new File(System.getProperty("user.dir") + File.separator + "file" + File.separator + entry.getName());
            InputStream inputStream = null;
            OutputStream outputStream = null;
            try {
                inputStream = zipFile.getInputStream(entry);
                outputStream = new FileOutputStream(targetFile);
                IOUtils.copy(inputStream, outputStream);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            zipFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
