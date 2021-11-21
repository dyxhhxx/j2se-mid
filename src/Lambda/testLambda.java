package Lambda;

import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class testLambda {

    //通过比较三种筛选集合的方法，学习Lambda表达式
    //第一种方法，写一个普通方法
    //第二种方法，借助匿名类：首先准备一个heroChecker接口，在其中准备一个test方法，通过匿名类的方式，实现这个接口
    //第三种方法，借助Lambada表达式，同样是实现接口，但更加简化

    public static void main(String[] args) {
        List<Hero> heroList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            heroList.add(new Hero("hero" + i, (int) (Math.random() * 200), (int) (Math.random() * 200)));
        }
        //通过普通方法
        System.out.println("通过普通方法");
        filter1(heroList);
        //通过匿名类
        System.out.println("通过匿名类");
        heroChecker checker = new heroChecker() {
            @Override
            public boolean test(Hero h) {
                return (h.hp > 100 && h.damage < 50);
            }
        };
        filter2(heroList, checker);
        //借助Lambda表达式
        System.out.println("借助Lambda表达式");
        filter2(heroList, h -> h.hp > 100 && h.damage < 50);
        //Lambda表达式调用静态方法（方法一样，方法名不一样，即业务逻辑相同）
        System.out.println("Lambda表达式调用静态方法：");
        filter2(heroList, (h) -> testLambda.testHero(h));
        //Lambda表达式调用静态方法的简化（方法一样，方法名不一样，即业务逻辑相同）
        System.out.println("Lambda表达式调用静态方法的简化；");
        filter2(heroList, testLambda::testHero);
        //Lambda表达式引用对象方法（方法一样，方法名不一样，即业务逻辑相同）
        System.out.println("Lambda表达式引用对象方法");
        testLambda tl = new testLambda();
        filter2(heroList, tl::testHero1);
        //Lambda表达式引用容器中对象的方法（方法一样，方法名不一样，即业务逻辑相同）
        System.out.println("Lambda表达式引用容器中对象的方法");
        filter2(heroList, h -> h.matched());
        filter2(heroList, Hero::matched);
        //Lambda表达式引用构造器
//        Supplier<List> s=new Supplier<List>() {
//            @Override
//            public List get() {
//                return new ArrayList();
//            }
//        };
        //匿名类
        List l1=getList(new Supplier<List>() {
            //以下都为该方法
            @Override
            public List get() {
                return new ArrayList();
            }
        });
        //Lambda表达式
        List l2=getList(()->new ArrayList());
        //引用构造器
        List l3=getList(ArrayList::new);
    }

    //普通方法
    public static void filter1(List<Hero> heroList) {
        for (Hero h : heroList) {
            if (h.hp > 100 && h.damage < 50) {
                System.out.println(h);
            }
        }
    }

    //借助接口  将算法与实际对象分离
    public static void filter2(List<Hero> heroList, heroChecker Checker) {
        for (Hero h : heroList) {
            if (Checker.test(h)) {
                System.out.println(h);
            }
        }
    }

    public static boolean testHero(Hero h) {
        return (h.hp > 100 && h.damage < 50);
    }

    public boolean testHero1(Hero h) {
        return (h.hp > 100 && h.damage < 50);
    }


    //借助系统接口Supplier
    public static List getList(Supplier<List> s){
        return s.get();
    }
}
