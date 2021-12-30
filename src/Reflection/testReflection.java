package Reflection;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class testReflection {
    public static void main(String[] args) {

        String classname = "Reflection.Hero";

        try {
            //无论是用什么途径获取类对象，都会导致静态属性被初始化，而且只会执行一次
            //因此在调用jdbc时，获取Driver类即可初始化
            Class pClass1 = Class.forName(classname);
            Class pClass2 = Hero.class;
            Class pClass3 = new Hero().getClass();
            System.out.println(pClass1 == pClass2);
            System.out.println(pClass1 == pClass3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //通过反射机制构造对象
        //此时，随着配置文件里内容的变化，就会得到不同的对象
        //而源代码不需要任何的变化，只需要修改配置文件，就可以导致程序的逻辑变化，这是一种*基于配置的编程思想*
        //Spring框架中的IOC和DI的底层就是基于这样的机制实现的
        try {
            Class pClass = Class.forName(classname);
            Constructor c = pClass.getConstructor();
            Hero h = (Hero) c.newInstance();
            h.name = "teemo";
            h.hp = 300;
        } catch (Exception e) {
            e.printStackTrace();
        }

        //利用反射机制，通过配置文件获取对象
//        Hero h_t = getHero();
//        System.out.println(h_t);

        //利用反射机制修改属性的值
        Hero h1 = new Hero();
        h1.name = "garen";  //普通方式
        System.out.println(h1.name);
        try {
            Field f1 = h1.getClass().getDeclaredField("name");
            //此处另一个getField方法只能获取public的，包括从父类继承来的字段
            //getDeclaredField可以获取本类所有的字段，包括private的，但是不能获取继承来的字段，注：只能获取private的字段，但不能访问该字段的值，除非加上setAccessible（true）
            f1.set(h1, "leesin");
            System.out.println(h1.name);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //利用反射机制调用方法
        try {
            Method m1 = h1.getClass().getMethod("setName", String.class);
            m1.invoke(h1, "李青");
            System.out.println(h1.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        File f=new File("/Users/dingyx/hero.config.txt");
        try(FileReader fr=new FileReader(f)){
            char[] c=new char[(int)f.length()];
            fr.read(c);
            String temp=new String(c);
            String[] temparray=temp.split("\n");
            for(int i=0;i<4;i++){
                System.out.println(temparray[i]);
            }
            Class obj1=Class.forName(temparray[0]);
            Class obj2= Class.forName(temparray[2]);
            Constructor c1=obj1.getConstructor();
            Constructor c2=obj2.getConstructor();
            APHero aph=(APHero)c1.newInstance();
            ADHero adh=(ADHero)c2.newInstance();
            Field f1=obj1.getField("name");
            Field f2=obj2.getField("name");
            Method m1=obj1.getMethod("attackHero",Hero.class);
            f1.set(aph,temparray[1]);
            f2.set(adh,temparray[3]);
            System.out.println(aph.name);
            System.out.println(adh.name);
            m1.invoke(aph,adh);
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    //利用反射机制，通过配置文件获取对象
    public static Hero getHero() {
        File f = new File("/Users/dingyx/hero.config.txt");
        try (FileReader fr = new FileReader(f)) {
            String classname = null;
            char[] ca = new char[(int) f.length()];
            fr.read(ca);
            classname = new String(ca);
            Class res = Class.forName(classname);
            Constructor c = res.getConstructor();
            Hero h = (Hero) c.newInstance();
            return h;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
