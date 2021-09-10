package File;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class testByteStream {

    public static void main(String[] args) {

        //文件输入流FileInputStream
        try{
            //创建基于文件的输出流
            File f=new File("/Users/dingyx/DOTA.exe");
            //通过这个输出流，就可以把数据从硬盘，读取到Java的虚拟机中来，也就是读取到内存中
            FileInputStream fis=new FileInputStream(f);
            fis.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        //文件输出流FileOutputStream
        try{
            File f1=new File("/Users/dingyx/DOTA.exe/LOL.txt");
            FileOutputStream fos=new FileOutputStream(f1);
            fos.close();
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

        //以字节流的形式向文件中写入文件
        try{
            File fo=new File("/Users/dingyx/LOL1.txt");
            byte[] data={88,89};
            FileOutputStream fos=new FileOutputStream(fo);
            fos.write(data);  //write后，若文件不存在，会自动创建；若存在，文件中原本的数据会被覆盖
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }

        //防止目录不存在，自动创建不存在的目录
//        try{
//            File fi=new File("/Users/dingyx/1/2/3/LOL.txt");
//            File dir=fi.getParentFile();
//            if(!dir.exists()){
//                dir.mkdirs();
//            }
//            byte data[]={88,89};
//            FileOutputStream fosi=new FileOutputStream(fi);
//            fosi.write(data);
//            fosi.close();
//        }catch(IOException e){
//            e.printStackTrace();
//        }

        //将文件拆成给定大小的文件
        File fo=new File("/Users/dingyx/1/2/3/LOL.txt");
        splitSize(2,fo);

        //再将拆分的文件合并
        //先找到拆分的文件
        File res=new File("/Users/dingyx/1/2/3","res.txt");
        byte[] arri=new byte[0];
        int i=0;  //已知文件格式均为txt，且结尾为顺序数字,从0开始找
        while(true){
            File fi = new File("/Users/dingyx/1/2/3", "LOL.txt-" + i);
            if(!fi.exists()){
                break;
            }
            arri=combine(arri,fi);
            i++;
        }
        //得到总的字节数组，在用字节输出流输出
        try{

            FileOutputStream fos=new FileOutputStream(res);
            fos.write(arri);
            fos.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }


    //拆分方法
    public static void splitSize(int size,File srcFile){
        if(srcFile.length()==0){
            throw new RuntimeException("空文件无法拆分！");
        }
        byte[] data=new byte[(int) srcFile.length()];
        try{
            FileInputStream fiso=new FileInputStream(srcFile);
            fiso.read(data);  //通过文件输入流读进内存中
            fiso.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        int fileNumber;
        if(srcFile.length()%size==0){
            fileNumber=(int)srcFile.length()/size;  //加(int)是因为，File.length()是long型
        }
        else{
            fileNumber=(int)srcFile.length()/size+1;
        }
        for(int i=0;i<fileNumber;i++){
            //分配文件名
            String eachFileName=srcFile.getName()+"-"+i;
            //创建文件
            File eachFile=new File(srcFile.getParent(),eachFileName);
            //找出该分文件的内容
            byte[] eachData;
            if(i!=fileNumber-1){
                eachData= Arrays.copyOfRange(data,i*size,(i+1)*size);
            }
            else {
                eachData=Arrays.copyOfRange(data,i*size,data.length);
            }
            //通过文件输出流，将数据写入分文件
            try(FileOutputStream fos=new FileOutputStream(eachFile);){
                fos.write(eachData);
                fos.close();
                System.out.printf("输出子文件%s，其大小为%d个字节\n",eachFileName, eachFileName.length());
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    //合并方法,将文件写入内存，并将所有的目标文件添加到一起
    public static byte[] combine(byte[] arr,File f) {
        //用文件输入流将文件写入内存
        byte[] arr1 = new byte[(int) f.length()];
        try {
            FileInputStream fis = new FileInputStream(f);
            fis.read(arr1);
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] newarr = new byte[arr.length + arr1.length];
        for (int i = 0; i < arr.length; i++) {
            newarr[i] = arr[i];
        }
        for (int i = 0; i < arr1.length; i++) {
            newarr[i + arr.length] = arr1[i];
        }
        return newarr;

    }
}
