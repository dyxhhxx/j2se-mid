package File;

import java.io.*;

public class testObjectStream {
    public static void main(String[] args) {
        //创建Hero对象
        //要吧Hero对象直接保存在文件上，必须让Hero类实现Serializable接口
        Hero h=new Hero();
        h.name="garen";
        h.hp=600;

        //准备一个文件保存该对象
        File f=new File("/Users/dingyx/Hearthstone.hs");
        //将对象写入文件，并再次读取
        try(FileOutputStream fos=new FileOutputStream(f);
            ObjectOutputStream oos=new ObjectOutputStream(fos);
            FileInputStream fis=new FileInputStream(f);
            ObjectInputStream ois=new ObjectInputStream(fis)){

            oos.writeObject(h);
            Hero h1=(Hero) ois.readObject();
            System.out.println(h1.name);
            System.out.println(h1.hp);

        }catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }

    }
}
