package Collection;

import File.Hero;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;


public class BasicCommandsOfArrayList {

    public static void main(String[] args) {
        //增加add
        //第一种是直接add对象，将其加在最后
        ArrayList heros = new ArrayList();
        for (int i = 0; i < 5; i++) {
            heros.add(new Hero("hero" + i));
        }
        System.out.println(heros);  //此处重写了Hero类的toString方法，使其直接输出name属性
        //第二种是在指定位置add对象
        Hero SuperHero = new Hero("IronMan");
        heros.add(3, SuperHero);
        System.out.println(heros);

        //contains判断对象是否在容器中。判断标准为是否是同一个对象，而不是name是否相同
        System.out.println(heros.contains(SuperHero));
        System.out.println(heros.contains(new Hero("hero1")));

        //通过get获取指定位置的对象，如果输入的下标越界，就会报错
        System.out.println(heros.get(3));
//        System.out.println(heros.get(6));  //会报错

        //indexOf用于获取一个对象在ArrayList的位置。与contains相同，判断标准是对象是否相同，而非name属性是否相同
        System.out.println(heros.indexOf(SuperHero));
        System.out.println(heros.indexOf(new Hero("hero1")));   //不存在会返回-1

        //remove用于把对象从ArrayList中删除
        //remove可以根据下标删除ArrayList中的元素
        heros.remove(2);
        System.out.println(heros);
        //也可以根据对象删除
        heros.remove(SuperHero);
        System.out.println(heros);

        //set用于替换指定位置的元素
        Hero SuperHero1 = new Hero("CaptainAmerican");
        heros.set(0, SuperHero1);
        System.out.println(heros);

        //size用于获取ArrayList的大小
        System.out.println(heros.size());

        //toArray可以将一个ArrayList对象转化为数组
//        Hero[] heroarray=new Hero[heros.size()];
        //需要传递一个Hero数组类型的对象给toArray()，否则只会转化为Object类型的数组
        Hero[] heroarray = (Hero[]) heros.toArray(new Hero[]{});
        for (Hero h : heroarray) {
            System.out.print(h + "\t");
        }
        System.out.println();

        //addAll将另一个容器的所有对象都加入进来
        ArrayList superhero = new ArrayList();
        superhero.add(new Hero("Thor"));
        superhero.add(new Hero("BlackWidow"));
        superhero.add(new Hero("QuickSliver"));
        System.out.println(superhero);
        heros.addAll(superhero);
        System.out.println(heros);

        //clear清空一个ArrayList
        superhero.clear();
        System.out.println(superhero);


        //practice

        //如果需要判断集合中是否存在一个name属性是"hero1"的对象，应该怎么做
//        Hero hero1=new Hero("hero1");
//        System.out.println(heros.contains(hero1));   //这样做是不行的，因为这个hero1对象也是新创建的
        for (int i = 0; i < heros.size(); i++) {
            Hero h = (Hero) heros.get(i);
            if (h.name.equals("hero1")) {
                System.out.println("找到name为hero1的对象，其位置在" + i);
                break;
            }
        }

        //利用ArrayList实现MyStringBuffer


    }

}
