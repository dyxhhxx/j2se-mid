package Thread;

public class TestThread {
    public static void main(String[] args) {
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 620000;
        gareen.damage = 50;

        Hero teemo = new Hero();
        teemo.name = "提莫";
        teemo.hp = 300000;
        teemo.damage = 30;

        Hero bh = new Hero();
        bh.name = "赏金猎人";
        bh.hp = 500000;
        bh.damage = 65;

        Hero leesin = new Hero();
        leesin.name = "盲僧";
        leesin.hp = 450005;
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
//        Thread t1 = new Thread() {
//            public void run() {
//                while (!teemo.isDead()) {
//                    gareen.attackHero(teemo);
//                }
//            }
//        };
//        Thread t2 = new Thread() {
//            public void run() {
//                while (!bh.isDead()) {
//                    leesin.attackHero(bh);
//                }
//            }
//        };
//        t1.start();
//        ;
//        t2.start();

        //-------------------------------------------------------------------------------------------------

        //常见的线程方法

        //当前线程暂停
        //sleep()可能会抛出InterruptedException异常，因为当前线程sleep时，可能会被终止，这时就会抛出异常
//        Thread t1=new Thread(){
//            @Override
//            public void run() {
//                int seconds=0;
//                while(true){
//                    try{
//                        Thread.sleep(1000);
//                    }catch(InterruptedException e){
//                        e.printStackTrace();
//                    }
//                    System.out.printf("游戏已经运行了%d秒\n",seconds++);
//                }
//            }
//        };
//        t1.start();
//
//        //加入到当前线程
//        //主线程：所有进程至少会有一个主线程，即main方法开始运行时，就会又一个主线程开始运行
//        //当执行join()时，即在主线程加入该线程时，主线程会等待该线程运行结束，才往下运行,但已经开始运行但线程不会停止
//        Thread t2=new Thread(){
//            @Override
//            public void run() {
//                while(!teemo.isDead()){
//                    gareen.attackHero(teemo);
//                }
//            }
//        };
//        t2.start();
//        //代码执行到这里，一直是main线程在运行
//        try{
//            //此处将t2加入到主线程后，只有t2线程结束后，main线程才会继续运行
//            t2.join();
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
//        Thread t3=new Thread(){
//            @Override
//            public void run() {
//                while(!bh.isDead()){
//                    leesin.attackHero(bh);
//                }
//            }
//        };
//        t3.start();  //只有t2结束后，主线程继续运行，才会执行t3的代码

        //线程优先级
        //准备工作，将Hero类中attackHero方法中线程暂停的指令删掉
//        Thread t4=new Thread(){
//            @Override
//            public void run() {
//                while(!teemo.isDead()){
//                    gareen.attackHero(teemo);
//                }
//            }
//        };
//        Thread t5=new Thread(){
//            @Override
//            public void run() {
//                while(!bh.isDead()){
//                    leesin.attackHero(bh);
//                }
//            }
//        };
//        t5.setPriority(Thread.MAX_PRIORITY);
//        t4.setPriority(Thread.MIN_PRIORITY);
//        t4.start();
//        t5.start();

        //临时暂停
//        Thread t6=new Thread(){
//            @Override
//            public void run() {
//                while(!teemo.isDead()){
//                    gareen.attackHero(teemo);
//                }
//            }
//        };
//        Thread t7=new Thread(){
//            @Override
//            public void run() {
//                while(!bh.isDead()){
//                    Thread.yield();
//                    leesin.attackHero(bh);
//                }
//            }
//        };
//        t6.setPriority(5);
//        t7.setPriority(5);
//        t7.start();
//        t6.start();

        //守护线程:当一个进程里全部都是守护线程时，结束当前进程
        //守护线程通常用来做日志，性能统等功能
        Thread t8=new Thread(){
            @Override
            public void run() {
                while(!teemo.isDead()){
                    gareen.attackHero(teemo);
                }
            }
        };
        t8.setDaemon(true);
        t8.start();  //不会执行

    }

}
