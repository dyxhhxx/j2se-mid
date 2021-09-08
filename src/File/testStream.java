package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class testStream {

    public static void main(String[] args) {

        //文件输入流FileInputStream
        try{
            //创建基于文件的输出流
            File f=new File("/Users/dingyx/DOTA.exe");
            //通过这个输出流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中
            FileInputStream fis=new FileInputStream(f);
        }catch(IOException e){
            e.printStackTrace();
        }

        //文件输出流FileOutputStream
        try{
            File f1=new File("/Users/dingyx/DOTA.exe/LOL.txt");
            FileOutputStream fos=new FileOutputStream(f1);
        }catch(IOException e){
            e.printStackTrace();
        }

        //以字节流的形式读取文件内容
        try{
            File f0=new File("/Users/dingyx/LOL.txt");
            FileInputStream fis0=new FileInputStream(f0);
            byte[] all=new byte[(int) f0.length()];
            fis0.read(all);
            for(byte item:all){
                System.out.println(item);
            }
            fis0.close();
        }catch(IOException e){
            e.printStackTrace();
        }



    }
}
