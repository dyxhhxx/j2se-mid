package Thread;

import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {

    //i++这个操作不是一个线程安全的操作，因为一共分三个原子操作
    //因此用原子类保证线程安全

    private static int value=0;
    private static AtomicInteger atomicValue=new AtomicInteger(0);
    public static void main(String[] args) {

        Thread[] tl=new Thread[100000];
        for(int i=0;i< tl.length;i++){
            tl[i]=new Thread(){
                @Override
                public void run() {
                    value++;
                }
            };
            tl[i].start();
        }
        for(Thread t:tl){
            try{
                t.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }

        Thread[] tl1=new Thread[100000];
        for(int i=0;i<tl1.length;i++){
            tl1[i]=new Thread(){
                @Override
                public void run() {
                    atomicValue.addAndGet(1);
                }
            };
            tl1[i].start();
        }
        for(Thread t:tl1){
            try{
                t.join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println(value);
        System.out.println(atomicValue.intValue());
    }
}
