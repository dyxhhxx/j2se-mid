package Exception;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class testException {
    public static void main(String[] args) {
        File f=new File("D:/LOL.exe");
//        new FileInputStream(f);

        //try、catch处理异常
        try{
            System.out.println("试图打开文件LOL.exe");
            new FileInputStream(f);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Scanner s=new Scanner(System.in);
            System.out.println("请输入一个日期(以年-月-日的格式)：");
            String str=s.next();
            Date d=sdf.parse(str);
            System.out.println("打开成功");}
//        }catch(FileNotFoundException e){
//            System.out.println("文件不存在！");
//            e.printStackTrace();
//        }catch(ParseException e){
//            System.out.println("日期格式错误！");
//            e.printStackTrace();
//        }
        catch(FileNotFoundException|ParseException e){
            if(e instanceof FileNotFoundException){
                System.out.println("文件不存在！");
            e.printStackTrace();
            }
            if(e instanceof ParseException){
                System.out.println("日期格式错误！");
            }
        }
        finally{
            System.out.println("无论文件是否存在，都会执行的代码");   //finally保证例如数据库最后的关闭，无论中间是否出现异常
        }
        //以上为可查异常，还有运行时异常（没有try catch也不会报错）如：任何除数不能为0，下表越界异常，空指针异常
        //还有错误Error，指的是系统级别的异常，通常是内存用光了（默认设置下，java程序启动时最大可使用16m的内存，如果把内存用完了
        //就会报错OutOfMemoryError（例如不停给StringBuffer追加字符）


    }
}
