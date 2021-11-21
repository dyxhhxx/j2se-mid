package Lambda;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testAggregate {

    public static void main(String[] args) {

        List<Hero> heroList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            heroList.add(new Hero("hero" + i, (int) (Math.random() * 200), (int) (Math.random() * 200)));
        }
        System.out.println("通过聚合方式筛选出的对象：");
        //Stream和管道的概念
        //Stream不同于结构化的Collection，是一系列元素
        //管道是指一系列的聚合操作
        //管道又分为三个部分：管道源，中间操作，结束操作
        heroList
                //产生的stream并不会对集合本身产生影响
                .stream()
                //每个中间操作又会返回一个stream
                .filter(hero -> hero.hp > 100 && hero.damage < 50)
                .forEach(hero -> System.out.println(hero));
        //对数组的操作，利用Arrays.stream()方法开启stream
        System.out.println("对数组的操作：");
        Hero[] heroArray=heroList.toArray(new Hero[heroList.size()]);
        Arrays.stream(heroArray)
                .filter(hero -> hero.hp>100&&hero.damage<50)
                .forEach(hero -> System.out.println(hero));
        System.out.println(heroList.size()); //size没变说明聚合操作不论是筛选还是排序并不会改变对象本身
        //下面是利用管道的各种中间操作
        System.out.println("去除重复数据：");
        heroList.stream()
                .distinct()   //去除标准是看equals
                .forEach(hero -> System.out.println(hero));
        System.out.println("按照血量排序：");
        heroList.stream()
                .sorted((h1,h2)->h1.hp>h2.hp?1:-1)  //Lambda表达式
                .forEach(hero -> System.out.println(hero));
        System.out.println("保留前3个：");
        heroList.stream()
                .limit(3)
                .forEach(hero -> System.out.println(hero));
        System.out.println("忽略前3个：");
        heroList.stream()
                .skip(3)
                .forEach(hero -> System.out.println(hero));
        System.out.println("转换为double的stream");
        heroList.stream()
                .limit(3)  //可以有多个中间操作
                .mapToDouble(Hero::getHp)
                .forEach(hero -> System.out.println(hero));
        System.out.println("转换为任意类型的stream：");
        heroList.stream()
//                .map(Hero::getHp)
                .map((h)->h.name+"-"+h.hp+"-"+h.damage)
                .forEach(hero -> System.out.println(hero));

        //以上为不同的中间操作，均利用forEach为结束操作
        //进行结束操作后，流就会关闭。因此结束操作返回的不是stream，而是int、float、String、Collection或者像forEach返回void
        //结束操作才会真正的遍历对象，前面的操作也在这时才真正被执行
        //常见的结束操作如下：
        System.out.println("遍历集合中的每个数据");
        heroList.stream()
                .forEach(hero -> System.out.println(hero));
        System.out.println("返回一个数组");
        Object[] heros=heroList.stream()
                .toArray();
        System.out.println(Arrays.toString(heros));
        System.out.println("返回伤害值最高的英雄");
        Hero h=heroList.stream()
                .max((h1,h2)->h1.damage-h2.damage)
                .get();
        System.out.println(h);
        System.out.println("流中的数据总数");
        long count=heroList.stream()
                .count();
        System.out.println(count);
        System.out.println("第一个英雄");
        Hero firsthero=heroList.stream()
                .findFirst()
                .get();
        System.out.println(firsthero);
    }
}
