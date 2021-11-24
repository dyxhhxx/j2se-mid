package Thread;

public class Hero {
    public String name;
    public int hp;
    public int damage;

    public void attackHero(Hero h) {
//        try{
        //为了表示攻击需要时间，每次攻击暂停1000ms
//            Thread.sleep(1000);
//        }catch(InterruptedException e){
//            e.printStackTrace();
//        }
        h.hp -= damage;
        if (h.hp > 0) {
            System.out.printf("%s正在攻击%s，%s的血量变成了%d\n", name, h.name, h.name, h.hp);
        } else {
            System.out.printf("%s正在攻击%s，%s的血量变成了%d,%s死了\n", name, h.name, h.name, h.hp, h.name);
        }


    }

    public boolean isDead() {
        return (hp <= 0);
    }

//    public void recover() {
//        //一种是用synchronized修饰hero对象
//        synchronized (this) {
//            hp++;
//        }
//    }

    public synchronized void recover(){
        hp+=1;
        System.out.printf("%s回血1点，加血后，%s的血量是%d%n",name,name,hp);
        //通知那些等待this对象的线程（wait），可以醒过来了
        this.notify();
    }

    //一种是，用synchronized修饰方法，两种方法一样
    public synchronized void hurt() {
        while(hp<=1){
            try{
                this.wait();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        hp-=1;
        System.out.printf("%s掉血1点，减血后，%s的血量是%d%n",name,name,hp);
    }
}
