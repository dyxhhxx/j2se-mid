package Thread;

import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestPool {
    //根据生产者消费者模式设计一个线程池

    int poolsize;
    //任务池
    LinkedList<Runnable> tasks=new LinkedList<>();

    //初始化十个线程
    public TestPool(int size){
        this.poolsize=size;
        //首先明白一点，这些线程必须对其目标，即任务池，保持同步！！！
        synchronized (tasks){
            for(int i=0;i<poolsize;i++){
                new taskConsumeThread("任务消费线程"+i).start();
            }
        }
    }

    //向任务池中添加任务
    public void addTask(Runnable r){
        synchronized (tasks){
            tasks.addLast(r);
            //唤醒所有待命的线程，开始抢任务了！
            tasks.notifyAll();
        }
    }

    //定义每个线程对象
    class taskConsumeThread extends Thread{

        public taskConsumeThread(String name){
            super(name);
        }

        //线程要执行的任务
        Runnable task;

        @Override
        public void run() {
            System.out.printf("线程%s已启动\n",this.getName());
            while(true){
                synchronized (tasks){
                    while(tasks.isEmpty()){
                        try{
                            tasks.wait();
                        }catch(InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                    //首先抢到任务的线程，u确定自己的任务后，将其占用的任务从任务列表中去除
                    task=tasks.removeLast();
                    //此时，该线程已经取走一个任务，把剩下的兄弟们叫醒，接着分任务！
                    tasks.notifyAll();
                }
                //接下来不需要在synchronized内执行了，因为任务task已经取到
                System.out.printf("线程%s已经取到任务\n",this.getName());
                task.run();
            }
            //这里的代码永远无法执行，因为while true
        }
    }

    public static void main(String[] args) throws InterruptedException{

        //java自带线程池
//        ThreadPoolExecutor threadPool=new ThreadPoolExecutor(10,15,60, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());
//        threadPool.execute(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("任务1");
//            }
//        });


        TestPool pool=new TestPool(10);  //启动10个线程

        //创建任务,并分配任务
//        for(int i=0;i<100;i++){
//            Runnable task=new Runnable() {
//                @Override
//                public void run() {
//                    //实际任务内容
//                    System.out.println("任务启动");
//                }
//            };
//            pool.addTask(task);
//            try{
//                Thread.sleep(10);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//        }

        //模拟一个情景：每个任务执行的时间都是1s，刚开始是间隔1s向线程池添加任务，然后间隔时间越来越短，执行任务的线程还没结束，新的任务就来了
        int sleeptime=1000;
        while(true){
            pool.addTask(new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务");
                    //一下控制任务执行时间
                    try{
                        Thread.sleep(900);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            });
            //以下控制任务生成时间
            try{
                Thread.sleep(sleeptime);
                sleeptime=sleeptime>100?sleeptime-100:sleeptime;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }

        //java提供线程池

    }


}




