package Thread;

public class Hero {
    public String name;
    public int hp;
    public int damage;

    public void attackHero(Hero h){
        try{
            //为了表示攻击需要时间，每次攻击暂停1000ms
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        h.hp-=damage;
        if(h.hp>0){
            System.out.printf("%s正在攻击%s，%s的血量变成了%d\n",name,h.name,h.name,h.hp);
        }else{
            System.out.printf("%s正在攻击%s，%s的血量变成了%d,%s死了\n",name,h.name,h.name,h.hp,h.name);
        }


    }

    public boolean isDead(){
        return (hp<=0);
    }
}
