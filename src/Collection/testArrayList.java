package Collection;

import File.Hero;

import java.util.ArrayList;
import java.util.List;

public class testArrayList {
    public static void main(String[] args) {
        //容器Arraylist用于存放对象，不用担心数组的边界问题
        ArrayList heros=new ArrayList();
        heros.add(new Hero("Garen"));
        System.out.println(heros.size());
        //容器的容量"capacity"会随着对象的增加，自动增加
        //只需要不断的往容器里添加英雄即可，不用担心出现数组的边界问题
        heros.add(new Hero("Teemo"));
        System.out.println(heros.size());

        //ArrayList是是实现了接口List
        //常见的写法是会把引用声明为接口List类型，注意是java.util.List而不是java.awt.List
        //接口引用指向子类对象（多态）
        List heroes=new ArrayList();
        heroes.add(new Hero("VN"));
        System.out.println(heroes.size());

        //对于不使用泛型的容器，可以往里面放Hero对象，也可以放Item对象
        heros.add(new Item("三项之力"));
        heros.add(new Hero("EZ"));
        //虽然存放的时候没有问题，在对象转型是会出现问题,尤其是容器内对象太多，不确定要取出位置的对象类型
        Hero h=(Hero)heros.get(0);
        //因此需要借助泛型Generic
        //声明容器时，就指定了这种容器只能放Hero对象，放其他的类型对象会报错
        List<Hero> generichero=new ArrayList<>();
        generichero.add(new Hero("Garen"));
//        generichero.add(new Item("red"));  //会报错
        //可以放指定对象类型的子类
        //当声明泛型后，在取出对象是也不需要在进行转型了，因为里面放的肯定是指定对象类型或其子类
        //        Hero h2=heros.get(2);  //会报错
        Hero h1=generichero.get(2);

        //如果想让指定容器中可存放多种对象类型，可让这些类都继承同一个接口，指定泛型时，指定类型为该接口即可
    }
}
