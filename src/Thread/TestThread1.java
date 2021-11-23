package Thread;

public class TestThread1 {
    public static void main(String[] args) {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 620;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 455;
        leesin.damage = 80;

        //只要提莫不死，盖伦一直攻击提莫
//        while(!teemo.isDead()){
//            gareen.attackHero(teemo);
//        }

        //只要赏金不死，李青一直攻击赏金
//        while(!bh.isDead()){
//            leesin.attackHero(bh);
//        }
        //上面的代码执行结果是按照顺序的，即必须等盖伦攻击直至提莫死亡后，赏金攻击的线程才会开始执行

        //下面创建多线程，使攻击同时进行

        //第一种方法：继承线程类
        //先实例化线程对象，调用其start方法
        //注意：也可以调用run方法，但无法开启多线程，必须等run方法执行完成后，后面等线程才会执行
        //但start方法一经执行，相当于jvm另外开启一个线程执行该方法的同时，后面的代码同时执行
//        KillThread t1=new KillThread(gareen,teemo);
//        KillThread t2=new KillThread(leesin,bh);
//        t1.start();
//        t2.run();

        //第二种方法：实现Runnable接口
//        Battle b1=new Battle(gareen,teemo);
//        Battle b2=new Battle(leesin,bh);
//        new Thread(b1).start();
//        new Thread(b2).start();

        //第三种方法：使用匿名类继承Thread，重写run方法，直接在run方法中写业务代码（匿名类好处，可以方便的访问外部的局部变量）
        Thread t1 = new Thread() {
            public void run() {
                while (!teemo.isDead()) {
                    gareen.attackHero(teemo);
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                while (!bh.isDead()) {
                    leesin.attackHero(bh);
                }
            }
        };
        t1.start();
        ;
        t2.start();


    }

}
