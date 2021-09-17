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

        //初始化一个Hero集合，里面放100个Hero对象，通过遍历删除名字编号是8的倍数的对象
        //先初始化集合，并放100个Hero对象
        List<Hero> HeroList=new ArrayList<>();
        for (int i=0;i<100;i++){
            HeroList.add(new Hero("hero"+i));
        }
        //尝试利用while循环，遍历集合，同时删除编号为8的倍数的对象
        //下面的代码会报错，因为不能够在使用Iterator和增强型for循环的同时删除数据，否则会抛出ConcurrentModificationException异常
//        for(Hero h:HeroList){
//            int id=Integer.parseInt(h.name.substring(4));
//            HeroList.remove(h);
//        }
        List<Hero> DeleteHeroList=new ArrayList<>();
        for(Hero h:HeroList){
            int id=Integer.parseInt(h.name.substring(4));
            if(id%8==0){
                DeleteHeroList.add(h);
            }
        }
        //再将找到的编号删除
        //存在问题，删除前面的元素时会影响集合长度，导致后面出错
        System.out.println("找到的所有id是：");
//        for(Integer id:IdList){
//            System.out.println(id);
//            int id1=id;
//            HeroList.remove(id1);
//        }
        for(Hero h:DeleteHeroList){
            System.out.println(h.name.substring(4));
            HeroList.remove(h);
        }
        //再遍历HeroList集合，验证删除结果
        System.out.println("删除后的集合为：");
        for(Hero h:HeroList){
            System.out.println(h);
        }
        System.out.printf("集合的长度是%d\n",HeroList.size());
    }
}
