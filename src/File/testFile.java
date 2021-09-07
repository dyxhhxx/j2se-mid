package File;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

public class testFile {
    public static void main(String[] args) {

        //创建一个文件对象的3种方法
        //绝对路径
        File f1=new File("/usr/dingyx/LOL.exe");
        System.out.println("f1的绝对路径："+f1.getAbsolutePath());
        //相对路径，相比于工作目录，在IDE中就是项目目录
        File f2=new File("LOL.exe");
        System.out.println("f2的绝对路径"+f2.getAbsolutePath());
        //把f1作为父目录创建文件
        File f3=new File(f1,"LOL.exe");
        System.out.println("f3的绝对路径："+f3.getAbsolutePath());


        File f=new File("/Users/dingyx/DOTA.exe");
        System.out.println("当前文件是："+f);
        //文件是否存在
        System.out.println("判断是否存在："+f.exists());
        //是否是文件夹
        System.out.println("判断是否是文件夹："+f.isDirectory());
        //是否是文件（非文件夹）
        System.out.println("判断是否是文件："+f.isFile());
        //文件长度
        System.out.println("获取文件长度："+f.length());
        //文件最后修改时间
        System.out.println("获取文件最后修改时间："+new Date(f.lastModified()));
        //设置文件最后修改时间为1970.1.1 08:00:00
        f.setLastModified(0);
        System.out.println("获取文件最后修改时间："+new Date(f.lastModified()));
        //文件重命名，对象名不会改变，带硬盘上的文件名会改变
        File f0=new File("/Users/dingyx/DOTA.exe");
        f.renameTo(f0);
        System.out.println("修改后的文件名为："+f);

        //方法2
        File ff=new File("/Users/dingyx/IdeaProjects");
        //以字符串数组的形式，返回当前文件夹下的所有文件（不包含子文件夹及子文件）
        String file[]=ff.list();  //返回值为字符串数组
        System.out.println(Arrays.toString(file));
        //以文件数组的形式，返回当前文件夹下的所有文件（不包含子文件夹及子文件）
        File[] fs=ff.listFiles();  //文件形式即路径
        System.out.println(Arrays.toString(fs));
        //以字符串形式返回获取所在文件夹
        System.out.println(ff.getParent());
        //以文件形式返回获取所在文件夹
        System.out.println(ff.getParentFile());
//        //创建文件夹，如果父文件夹不存在，创建就无效
//        File ff1=new File("/Users/dingyx/0907");
//        ff1.mkdir();
//        //创建一个空文件夹，如果父文件夹不存在，就会创建父文件夹
//        File ff2=new File("/Users/dingyx/0908/0907");
//        ff2.mkdirs();
        //创建一个空文件，如果父文件夹不存在，就会抛出异常
        File ff3=new File("/Users/dingyx/LOL.txt");
        try{ff3.createNewFile();}   //此处会有IOException，需要try catch一下
        catch(IOException e){
            e.printStackTrace();
        }
        //创建一个空文件之前，通常都会创建父目录
        ff3.getParentFile().mkdirs();

        //列出所有的盘符
        ff3.listRoots();
        //删除文件
        ff3.delete();
        //JVM结束的时候，删除文件，通常用于临时文件的删除
        ff3.deleteOnExit();

        System.out.println("-----------------------------------");

        //遍历usr目录下的所有文件（不需要遍历子目录），找出这个目录下的最大和最小文件
        File tf=new File("/Users/dingyx/IdeaProjects/j2se/src/array");
        File af[]=tf.listFiles();
        long minsize=Integer.MAX_VALUE;
        long maxsize=0;
        File maxfile=null;
        File minfile=null;
        for(File files:af){
            if(files.isDirectory()){
                continue;
            }
            if(files.length()>maxsize){
                maxsize=files.length();
                maxfile=files;
            }
            if(files.length()<minsize){
                minsize=files.length();
                minfile=files;
            }
        }
        System.out.printf("最大的文件是%s，其大小是%d字节\n最小的文件是%s，其大小是%d字节",maxfile.getAbsolutePath(),maxsize
        ,minfile.getAbsolutePath(),minsize);


    }

}
