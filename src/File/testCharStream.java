package File;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class testCharStream {
    public static void main(String[] args) {

        //字节流：每次读写一个字节，当传输的资源文件中有中文时，就会出现乱码，读写的单位是byte，在InputStream和OutputStream中单向流动
        //字符流：每次读写两个字节，有中文等时，使用该流可以正确传输显示中文，读写等单位是char，在Reader和Writer中单向流动
        //字符流与字节流原理相同，只是处理的单位长度不同，后缀是Stream是字节流，后缀是Reader/Writer是字符流

        //字节流一般用来处理图像、视频、音频、PPT、Word等文件，字符流一般用于处理纯文本文件，如txt文件
        //字节流本身没有缓冲区，字符流有缓冲区，效率高

        //InputStream只需记住两个子类：FileInputStream和BufferedInputStream
        //OutputStream同上，有三个重要方法，write(int b)写入一个字节;write(byte[] b)写入数组b中的所有字节
          // ;write(byte[] b,int off, int len写入数组b从off位置开始长度为len的所有字节)，还有flush()强制将缓冲区所有数据写入硬盘
        //Reader重要子类有：FileReader,BufferedReader,InputStreamReader,read()三种方法和上上面一样
        //


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
        File f3=new File("/Users/dingyx/LOL.txt");
        char[] arr=new char[(int)f3.length()];
        try(FileReader fr=new FileReader(f3)){
            fr.read(arr);
        }catch(IOException e){
            e.printStackTrace();
        }
        for(char c:arr){
            System.out.print(c);
        }
        System.out.println();
        for(int i=0;i<arr.length;i++){
            if(arr[i]=='0'){
                arr[i]='9';
            }
            else if('0'<arr[i]&&arr[i]<='9'){
                arr[i]=(char)(arr[i]-1);
            }
            else if(arr[i]=='A'){
                arr[i]='Z';
            }
            else if('A'<arr[i]&&arr[i]<='Z'){
                arr[i]=(char)(arr[i]-1);
            }
            else if(arr[i]=='a'){
                arr[i]='z';
            }
            else if('a'<arr[i]&&arr[i]<='z'){
                arr[i]=(char)(arr[i]-1);
            }
            //Chracter.isLetterOrDigit()方法会将中文判定为字母
//            if(Character.isLetterOrDigit(arr[i])) {
//                switch (arr[i]) {
//                    case '9':
//                        arr[i] = '0';
//                        break;
//                    case 'Z':
//                        arr[i] = 'A';
//                        break;
//                    case 'z':
//                        arr[i] = 'a';
//                        break;
//                    default:
//                        arr[i]++;
//                        break;
//                }
//            }
        }
        System.out.println(new String(arr));
        File f3_=new File("/Users/dingyx/LOL_.txt");
        try(FileWriter fw=new FileWriter(f3_)){
            fw.write(arr);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
