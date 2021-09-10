package File;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class testCharStream {
    public static void main(String[] args) {

        //使用字符流读取字符
        File f1=new File("/Users/dingyx/LOL1.txt");
        try(FileReader fr=new FileReader(f1)){
            char[] arr=new char[(int)f1.length()];
            fr.read(arr);
            for(char a:arr){
                System.out.println(a);
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        //使用字符流写入字符
        File f2=new File("/Users/dingyx/LOL2.txt");
        try(FileWriter fw=new FileWriter(f2)){
//            char[] arr={'D','Y','X','H','H','X','X'};
            String slogan="dyxhhxx";
            char[] arr=slogan.toCharArray();
            fw.write(arr);
        }catch(IOException e){
            e.printStackTrace();
        }

        //文件加密
//        File f3=new File("/Users/dingyx/LOL.txt");
//        char[] arr=new char[(int)f3.length()];
//        try(FileReader fr=new FileReader(f3)){
//            fr.read(arr);
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//        for(char c:arr){
//            System.out.print(c);
//        }
//        for(int i=0;i<arr.length;i++){
//            if(arr[i])
//        }
    }

}
