package File;

import javax.annotation.processing.Filer;
import java.io.*;

public class testDataStream {
    public static void main(String[] args) {
        File f=new File("/Users/dingyx/LOL_.txt");
        try(FileOutputStream fos=new FileOutputStream(f); DataOutputStream dos=new DataOutputStream(fos)){
            dos.writeBoolean(true);
            dos.writeInt(520);
            dos.writeUTF("dyxhhxx");
            dos.flush();
        }catch(IOException e){
            e.printStackTrace();
        }
        try(FileInputStream fis=new FileInputStream(f);DataInputStream dis=new DataInputStream(fis)){
            boolean b=dis.readBoolean();
            System.out.println(b);
            int n=dis.readInt();
            System.out.println(n);
            String s=dis.readUTF();
            System.out.println(s);
        }catch(IOException e){
            e.printStackTrace();
        }

        //使用缓存流把两个数字以以字符串的形式写到文件里，再用缓存流以字符串的形式读取出来，然后转换为两个数字
        //方法1：缓存流
        File f0=new File("/Users/dingyx/LOL.txt");
        try(FileWriter fw=new FileWriter(f0);PrintWriter pw=new PrintWriter(fw);
            FileReader fr=new FileReader(f0);BufferedReader br=new BufferedReader(fr)){

            pw.println("35@31");
            pw.flush();   //如果不将缓存直接推进硬盘，后面无法读取
            String line=br.readLine();
            String[] ss=line.split("@");
            int x=Integer.parseInt(ss[0]);   //字符串转化为数字
            int y=Integer.parseInt(ss[1]);
            System.out.println(x+" "+y);

        }catch(IOException e){
            e.printStackTrace();
        }

        //方法2：数据流
        File ff=new File("/Users/dingyx/DOTA.txt");
        try(FileOutputStream fos=new FileOutputStream(ff);
            DataOutputStream dos=new DataOutputStream(fos);
            FileInputStream fis=new FileInputStream(ff);
            DataInputStream dis=new DataInputStream(fis)){

            dos.writeInt(328);
            dos.writeInt(520);
            dos.flush();
            int x=dis.readInt();
            int y=dis.readInt();
            System.out.println(x+" "+y);

        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
