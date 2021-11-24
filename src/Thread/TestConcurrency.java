package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestConcurrency {

    //多线程的同步问题指，多个线程同时修改同一个数据时，可能导致的问题
    public static void main(String[] args) {

        final Object someObject = new Object();
        Hero gareen = new Hero();
        gareen.name = "盖伦";
        gareen.hp = 66000;
        gareen.damage = 50;
        System.out.printf("%s的初始血量是%d\n", gareen.name, gareen.hp);
        //创建多个线程同时修改gareen的hp
        int size = 10000;
        Thread[] addThreads = new Thread[size];
        Thread[] reduceThreads = new Thread[size];
        //创建size个线程增加盖伦的血量
        for (int i = 0; i < addThreads.length; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    //用synchronized修饰，保证线程同步
                    synchronized (someObject) {
                        gareen.recover();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
            t.start();
            addThreads[i] = t;
        }
        //创建size个线程减少盖伦的血量
        for (int i = 0; i < addThreads.length; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    synchronized (someObject) {
                        gareen.hurt();
                    }
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            t.start();
            reduceThreads[i] = t;
        }
        //理论上当上面两个线程结束时，盖伦的血量应不变，下面验证一下
        //先等待两个线程全部执行结束
        for (Thread t : addThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        for (Thread t : reduceThreads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //代码执行到这里，可以保证两个线程全部执行结束，因为t.join()会使main线程暂停，只有所有的t线程结束后，才会重新回到main线程
        System.out.printf("%s现在的血量是%d\n", gareen.name, gareen.hp);  //打印结果发现，盖伦血量变化了

        //同步问题出现的原因是，增加运算还没来得及返回hp的值时，减少线程就开始运算，本来一对增减运算后，hp的值不变，现在hp变为减少
        //此时返回的值是一个错误的数据，业务上被称为脏数据
        //解决思路：在增加线程访问hp时，其他线程不允许访问hp
        //关键字synchronized，表示当前线程独占对象someObject（注意：synchrozized只能修饰对象或方法）
        // 如果有其他线程试图占有对象someObject，就会等待，直至当前线程释放someObject的占用
        //因此，为了达到同步的效果，必须使用同一个同步对象
//        synchronized (someObject){
//            //该代码快中的代码只有占有了someObject后才可以执行
//        }
        Thread t1 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(now() + "t1线程已运行");
                    System.out.println(now() + this.getName() + "线程试图占有对象：someObject");
                    synchronized (someObject) {
                        System.out.println(now() + this.getName() + "线程已经占有对象：someObject");
                        Thread.sleep(5000);
                        System.out.println(now() + this.getName() + "线程释放对象：someObject");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t1.setName("t1");
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    System.out.println(now() + "t2线程已经运行");
                    System.out.println(now() + this.getName() + "线程试图占有对象：someObject");
                    synchronized (someObject) {
                        System.out.println(now() + this.getName() + "线程已经占有对象:someObject");
                        Thread.sleep(5000);
                        System.out.println(now() + this.getName() + "线程释放对象:someObject");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        t2.setName("t2");
        t2.start();

    }

    public static String now() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }


}
