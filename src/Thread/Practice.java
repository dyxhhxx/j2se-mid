package Thread;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class Practice {

    //线程练习1：遍历所有文件，当遍历到一个文件是java文件时，创建一个线程去查找这个文件当内容
    //不必等待这个线程结束，继续遍历下一个文件

    //先写一个方法将一个文件转化为字符串
    public static String readFile(File f){
        try(FileReader fr=new FileReader(f)){
            char[] all=new char[(int)f.length()];
            fr.read(all);
            return new String(all);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    //写一个方法遍历文件夹,分为两种情况，若对象为文件，开始查找；若对象为文件夹，递归进入文件夹乡下人查找
    public static void searchContent(File folder,String content){
        if(folder.isDirectory()){
            File[] filearray=folder.listFiles();
            for(File f:filearray){
                searchContent(f,content);
            }
        }
        if(folder.isFile()){
            if(folder.getName().toLowerCase().endsWith(".java")){
                Thread t1=new Thread(){
                    @Override
                    public void run() {
                        String fileContent=readFile(folder);
                        if(fileContent.contains(content)){
                            System.out.printf("找到目标字符串%s，在文件夹%s\n",content,folder);
                        }
                    }
                };
                t1.run();
            }
        }
    }

    public static void main(String[] args) {
        File f=new File("/Users/dingyx/IdeaProjects");
        searchContent(f,"Hero");
    }






}
