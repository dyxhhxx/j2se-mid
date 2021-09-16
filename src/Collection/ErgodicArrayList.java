package Collection;

import File.Hero;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ErgodicArrayList {
    public static void main(String[] args) {

        //用for循环遍历
        List<Hero> heroes=new ArrayList();
        for(int i=0;i<5;i++){
            heroes.add(new Hero("hero"+i));
        }
        System.out.println("--------------for循环---------------");
        for(int i=0;i<heroes.size();i++){
            System.out.println(heroes.get(i));
        }

        //增强型for循环
        System.out.println("-----------------增强型for循环------------------");
        for(Hero h:heroes){
            System.out.println(h);
        }

        //迭代器遍历
        System.out.println("------------------使用while的迭代器-----------------");
        Iterator<Hero> it=heroes.iterator();
        while(it.hasNext()){
            Hero h=it.next();
            System.out.println(h);
        }
        System.out.println("------------------使用for的迭代器-----------------");
        for(Iterator<Hero> iterator=heroes.iterator();iterator.hasNext();){
            Hero h=iterator.next();
            System.out.println(h);
        }


    }
}
