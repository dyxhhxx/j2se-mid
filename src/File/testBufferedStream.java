package File;

import java.io.*;

public class testBufferedStream {
    public static void main(String[] args) {
        File f=new File("/Users/dingyx/LOL.txt");
        //使用缓存流BufferedReader读取数据，一次可以读取一行的数据，缓存流必须建立在一个存在的流的基础上
        try(FileReader fr=new FileReader(f);BufferedReader br=new BufferedReader(fr)){
            while(true){
                //一次读一行
                String line=br.readLine();
                if(line==null){
                    break;
                }
                System.out.println(line);
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        //使用缓存流PrintWriter写入数据
        File f1=new File("/Users/dingyx/LOL1.txt");
        try(FileWriter fw=new FileWriter(f1);PrintWriter pw=new PrintWriter(fw)){
            pw.println("dyxhhxx");
            pw.flush();   //强制把缓存中的数据写入硬盘，无论缓存是否已满
            pw.println("丁业煊好好学习");
            pw.flush();
            pw.println("小丁小丁好好学习");
            pw.flush();
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}
