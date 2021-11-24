package Thread;

public class TestInteraction {

    //当实际需求为：加血和减血的线程同时运行时，减血的线程发现hp=1，就停止减血，直到加血的进程

    public static void main(String[] args) {

        //方式1：故意设计减血线程频率更高
        //方式2：使用wait()和notify()进行线程交互
        //wait()：让占有this的线程等待，并临时释放占有
        //notify()：表示通知那些在wait的线程可以苏醒过来了

        final Hero gareen=new Hero();
        gareen.name="盖伦";
        gareen.hp=10;

        Thread t1=new Thread(){
            @Override
            public void run() {
                while(true){
//                    while(gareen.hp==1) {
//                        continue;
//                    }
                    gareen.hurt();
//                    System.out.printf("减血后，%s的血量是%d\n",gareen.name,gareen.hp);
                    try{
                        Thread.sleep(10);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
        Thread t2=new Thread(){
            @Override
            public void run() {
                while(true){
                    gareen.recover();
//                    System.out.printf("加血后，%s的血量是%d\n",gareen.name,gareen.hp);
                    try{
                        Thread.sleep(100);
                    }catch(InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();


        //注意：调用wait、notify以及notifyAll时可以发现，它们并不是Thread线程上的方法，而是Object上的方法
        //因为所有的Object都可以被当作同步对象，所以准确的讲，wait和notify都是同步对象上的方法
        //wait的意思是：让占用了这个对象的线程，临时释放占用，并且等待。所有调用wait的前提就是一定是在synchronized块里，否则会报错
        //notify的意思是：通知一个等待在这个同步对象的线程，你可以唤醒了，有机会占用当前对象了
        //notifyAll的意思是：通知所有的等待这个同步对象的线程，你们可以唤醒了，有机会重新占用当前对象了


    }

}
