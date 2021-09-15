package Exception;

import java.io.Serializable;

public class Hero {
    private static final long serialVersionUID=1L;
    public float hp;
    public String name;

    //自定义异常EnemyHeroIsDeadException，当敌方英雄血量为0时，抛出异常
    class EnemyHeroIsDeadException extends Exception implements Serializable {
        public EnemyHeroIsDeadException(){

        }
        public EnemyHeroIsDeadException(String msg){
            super(msg);
        }
    }

    public void attackHero(Hero h) throws EnemyHeroIsDeadException{
        if(h.hp==0){
            //将异常的message变为String的内容
            throw new EnemyHeroIsDeadException(h.name+"已经挂掉，技能无法指定");
        }
    }

    public static void main(String[] args) {
        Hero garen=new Hero();
        garen.name="garen";
        garen.hp=600f;

        Hero teemo=new Hero();
        teemo.name="teemo";
        teemo.hp=0;

        try{
            garen.attackHero(teemo);
        }catch(EnemyHeroIsDeadException e){
            System.out.println("异常的具体原因为："+e.getMessage());  //通过默认的getMessage方法，得到msg
            e.printStackTrace();
        }
    }


}
