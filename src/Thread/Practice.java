package Thread;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Practice {

    //先写一个方法将一个文件转化为字符串
    public static String readFile(File f) {
        try (FileReader fr = new FileReader(f)) {
            char[] all = new char[(int) f.length()];
            fr.read(all);
            return new String(all);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    //写一个方法遍历文件夹,分为两种情况，若对象为文件，开始查找；若对象为文件夹，递归进入文件夹乡下人查找
    public static void searchContent(File folder, String content) {
        if (folder.isDirectory()) {
            File[] filearray = folder.listFiles();
            for (File f : filearray) {
                searchContent(f, content);
            }
        }
        if (folder.isFile()) {
            if (folder.getName().toLowerCase().endsWith(".java")) {
                Thread t1 = new Thread() {
                    @Override
                    public void run() {
                        String fileContent = readFile(folder);
                        if (fileContent.contains(content)) {
                            System.out.printf("找到目标字符串%s，在文件夹%s\n", content, folder);
                        }
                    }
                };
                t1.start();
            }
        }
    }

    //创建随机密码的方法
    public static String generatePsw(int length) {
        char[] psw = new char[length];
        char[] pool = new char[26 + 26 + 10];
        int index = 0;
        for (int i = '0'; i <= '9'; i++) {
            pool[index] = (char) i;
            index++;
        }
        for (int i = 'a'; i <= 'z'; i++) {
            pool[index] = (char) i;
            index++;
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            pool[index] = (char) i;
            index++;
        }
        for (int i = 0; i < length; i++) {
            psw[i] = pool[(int) (Math.random() * (26 + 26 + 10))];
        }
        return new String(psw);
    }

    public static void main(String[] args) {
        //线程练习1：遍历所有文件，当遍历到一个文件是java文件时，创建一个线程去查找这个文件当内容
        //不必等待这个线程结束，继续遍历下一个文件
//        File f=new File("/Users/dingyx/IdeaProjects");
//        searchContent(f,"Hero");

        //线程练习2
        //生成一个长度是3当随机字符串
        //创建一个破解线程，使用穷举法，匹配这个密码
        //创建一个日志线程，打印都用过哪些字符串去匹配，这个日志线程设计为守护线程
        String psw = generatePsw(3);
        System.out.printf("随机生成的密码是%s\n", psw);
        //利用穷举法，破解线程将生成的所有可能密码放在一个容器中，日志线程不断的从这个容器中拿出可能密码，并打印出来
        //日志线程取密码时，若发现容器是空的就休息1s
        List<String> pswDocker = new ArrayList<>();
        Thread crackThread = new Thread() {
            @Override
            public void run() {
                String random = generatePsw(3);
                while (!random.equals(psw)) {
                    pswDocker.add(random);
                    random = generatePsw(3);
                }
                System.out.printf("穷举法匹配的结果是：%s\n", random);
            }
        };
        Thread dialogThread = new Thread() {
            @Override
            public void run() {
                while (true) {
                    if (pswDocker.size() == 0) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(pswDocker.remove(0));
                    }
                }
            }
        };
        dialogThread.setDaemon(true);
        crackThread.start();
        dialogThread.start();
    }


}
