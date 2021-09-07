package File;

import java.io.File;
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


        File f=new File("/Users/dingyx/DOTa.exe");
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

        //
    }

}
