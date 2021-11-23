package Thread;

public class KillThread extends Thread{

    //该类继承了Thread类，需要重写其run()方法
    //启动线程的方法：实例化一个KillThread对象，调用其start方法

    private Hero h1;
    private Hero h2;

    public KillThread(Hero h1,Hero h2){
        this.h1=h1;
        this.h2=h2;
    }

    public void run(){
        while(!h2.isDead()){
            h1.attackHero(h2);
        }
    }
}
