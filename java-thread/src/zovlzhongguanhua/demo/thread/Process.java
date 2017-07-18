package zovlzhongguanhua.demo.thread;

import java.io.IOException;

public class Process {

    public static void main(String[] args) {
        String command = "D:/data/.jenkins/jobs/android_jenkins_demo/workspace/gradlew.bat build -b D:/data/.jenkins/jobs/android_jenkins_demo/workspace/build.gradle && exit %%ERRORLEVEL%%";
        // command = "D:\\data\\.jenkins\\jobs\\android_jenkins_demo\\workspace\\gradlew.bat build -b D:\\data\\.jenkins\\jobs\\android_jenkins_demo\\workspace/app/build.gradle && exit %%ERRORLEVEL%%";
        // command = "D:/data/.jenkins/jobs/android_jenkins_demo/workspace/gradlew.bat";

        command = "C:\\Program Files (x86)\\Git\\bin\\git.exe rev-parse --is-inside-work-tree # timeout=10";

        System.out.println(command);
        java.lang.Process process;
        try {
            process = new ProcessBuilder()
                    .command(command)
                    .start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
