package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class practice {

    //复制文件，将源文件srcFile复制到目标文件destFile
    public static void copyFile(File srcFile, File destFile){
        byte[] temp1=new byte[(int)srcFile.length()];
        byte[] temp2=new byte[(int)destFile.length()];
        byte[] res=new byte[(int)(srcFile.length()+destFile.length())];
        try(FileInputStream fis1=new FileInputStream(srcFile);
            FileInputStream fis2=new FileInputStream(destFile);
            FileOutputStream fos=new FileOutputStream(destFile)){
            //现将两个文件读入两个字节数组
            fis1.read(temp1);
            fis2.read(temp2);
            //再将两个数组拼成一个数组
            for(int i=0;i<temp2.length;i++){
                System.out.print(temp2[i]);
                res[i]=temp2[i];
            }
            System.out.println();
            for(int i=0;i<temp1.length;i++){
                System.out.print(temp1[i]);
                res[i+temp2.length]=temp1[i];
            }
            //将结果数组写入文件
            fos.write(res);

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File f1=new File("/Users/dingyx/LOL1.txt");
        File f2=new File("/Users/dingyx/LOL-1.txt");
        copyFile(f1,f2);
    }
}
