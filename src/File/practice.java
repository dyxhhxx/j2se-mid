package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class practice {

//    复制文件，将源文件srcFile复制到目标文件destFile
//    public static void copyFile(File srcFile, File destFile){
//        System.out.printf("srcFile.length=%d\n",srcFile.length());
//        System.out.printf("destFile.length=%d\n",destFile.length());
//        byte[] temp1=new byte[(int)srcFile.length()];
//        byte[] temp2=new byte[(int)destFile.length()];
//        byte[] res=new byte[(int)(srcFile.length()+destFile.length())];
//        try(FileInputStream fis1=new FileInputStream(srcFile);
//            FileInputStream fis2=new FileInputStream(destFile);
//            FileOutputStream fos=new FileOutputStream(destFile)){
//            //现将两个文件读入两个字节数组
//            fis1.read(temp1);
//            fis1.close();
//            fis2.read(temp2);
//            fis2.close();
//            //再将两个数组拼成一个数组
//            for(int i=0;i<temp2.length;i++){
//                System.out.println(temp2[i]);
//                res[i]=temp2[i];
//            }
//            System.out.println();
//            for(int i=0;i<temp1.length;i++){
//                System.out.println(temp1[i]);
//                res[i+temp2.length]=temp1[i];
//            }
//            //将结果数组写入文件
//            fos.write(res);
//            fos.flush();
//
//        }catch(IOException e){
//            e.printStackTrace();
//        }
//    }
    public static void copyFileByPath(String srcFilePath,String destFilePath){
        File f1=new File(srcFilePath);
        File f2=new File(destFilePath);
        byte[] buffer=new byte[1024];
        try(FileInputStream fis=new FileInputStream(f1);
            FileOutputStream fos=new FileOutputStream(f2)){
            while (true) {
                int actuallyReaded = fis.read(buffer);
                if (actuallyReaded == -1) {
                    break;
                }
                fos.write(buffer,0,actuallyReaded);
                fos.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    //复制文件夹(利用递归判断文件是文件夹还是文件）
    public static void copyFolder(String srcFolderPath,String destFolderPath ){

        //遍历srcFolder将所有文件复制到destFolder
        File f1=new File(srcFolderPath);
        File f2=new File(destFolderPath);
        //判断源文件是否存在
        if(f1.exists()){
            return;
        }
        //判断源文件是否是文件夹
        if(f1.isDirectory()){
            return;
        }
        //判断目标文件夹是否是文件
        if(f2.isFile()){
            return;
        }
        //目标文件夹若不存在，就创建
        if(!f2.exists()){
            f2.mkdirs();
        }

        //遍历源文件夹
        File[] fa=f1.listFiles();
        for(File f: fa){
            //如果是文件，直接复制并赋名
            if(f.isFile()){
                File f0=new File(destFolderPath,f.getName());
                copyFileByPath(f.getAbsolutePath(),f0.getAbsolutePath());
            }
            //如果是文件夹就递归
            if(f.isDirectory()){
                File f0=new File(destFolderPath,f.getName());
                copyFolder(f.getAbsolutePath(),f0.getAbsolutePath());
            }
        }
    }

    //查找文件内容（同样要用到递归）
    public static void searchFile(File Folder,String search){

        //判断文件夹是否存在
        if(!Folder.exists()){
            return;
        }
        //判断文件夹是否是文件
        if(Folder.isFile()){
            return;
        }
        //将文件夹中的所有文件遍历出来
        File[] fa=Folder.listFiles();
        for(File f0:fa){
            if(f0.isDirectory()){
                File f1=new File(f0.getParent(),f0.getName());
                searchFile(f0,search);
            }
            if(f0.isFile()){
                
            }
        }

    }


    public static void main(String[] args) {
//        File f1=new File("/Users/dingyx/LOL.txt");
//        File f2=new File("/Users/dingyx/LOL2.txt");
//        copyFile(f1,f2);
//        copyFileByPath("/Users/dingyx/LOL1.txt","/Users/dingyx/LOL2.txt");
        copyFolder("/Users/dingyx/1","/Users/dingyx/2");
    }
}
