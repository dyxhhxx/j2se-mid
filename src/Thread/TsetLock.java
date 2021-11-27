package Thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TsetLock {

    //Lock和synchronized的区别
    //Lock是一个接口，而synchronized是Java中的关键字，synchronized是内置的语言实现，Lock是代码层面的实现
    //Lock可以选择性的获取锁，如果一段时间获取不到，可以放弃；synchronized不行，会一根筋一直获取下去。借助Lock的这个特性，可以更方便的避免死锁
    //synchronized在发生异常和同步快结束是，会自动释放锁，而Lock必须手动释放，所以如果忘记了释放锁，一样会造成死锁

    public static void main(String[] args) {

        //与synchronized(someObject)类似，lock()方法，表示当前线程占用lock对象，一旦占用，其他线程就不能占用了
        //与synchronized不同的是，一旦synchronized块结束，就会自动释放someObject对象的占用。而lock必须调用unlock方法进行手动释放
        //为了保证释放的执行，往往会把unlock方法finally块中执行
        Lock lock=new ReentrantLock();
        Condition condition=lock.newCondition();
        Thread t1=new Thread(){
            @Override
            public void run() {
                boolean locked=false;
                try{
                    log("线程启动");
                    log("试图占有对象：lock");

//                    lock.lock();
                    //synchronized是不占用到不罢休的，会一直试图占用下去，与synchronized钻牛角尖的方式不同的是，Lock接口还提供了一个trylock方法
                    //trylock会在指定时间范围内试图占用，如果在给定时间段内还没占用到，会放弃占用
                    //注意：因为trylock有可能成功有可能失败，在最后unlock时需要判断是否占用成功，否则有可能抛出异常
                    locked=lock.tryLock(5, TimeUnit.SECONDS);

                    if(locked) {
                        log("占有对象：lock");
                        log("进行5秒的业务操作");
                        Thread.sleep(5000);
                        log("唤醒等待中的线程");
                        condition.signalAll();
                    }else{
                        log("经过1是秒钟，线程t1还没有占有对象，放弃占有");
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    if(locked) {
                        log("释放对象：lock");
                        lock.unlock();
                    }
                }
                log("线程结束");
            }
        };
        t1.setName("t1");
//        t1.start();

        Thread t2=new Thread(){
            @Override
            public void run() {
                try{
                    log("线程启动");
                    log("试图占有对象：lock");

                    lock.lock();

                    log("占有对象：lock");
                    log("进行5秒的业务操作");
                    Thread.sleep(5000);
                    //Lock接口还提供了类似wait和notify的方法
                    //首先通过lock对象得到一个Condition对象，然后分别调用这个Condition对象的：await，signal，signalAll方法
                    log("临时释放对象lock，并等待");
                    condition.await();
                    log("重新占有对象lock，并进行5秒的业务操作");
                    Thread.sleep(5000);

                }catch(InterruptedException e){
                    e.printStackTrace();
                }finally{
                    log("释放对象：lock");
                    lock.unlock();
                }
                log("线程结束");
            }
        };
        t2.setName("t2");
        t2.start();


        try{
            //让t1比t2晚两秒启动
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        t1.start();



    }

    public static String now(){
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public static void log(String msg){
        System.out.printf("%s %s %s %n",now(),Thread.currentThread().getName(),msg);
    }


}
