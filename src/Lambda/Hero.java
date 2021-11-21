package Lambda;

public class Hero  {

    public String name;
    public int hp;
    public int damage;

    public Hero(){}

    public Hero(String name, int hp,int damage) {
        this.name = name;
        this.hp = hp;
        this.damage=damage;
    }

    @Override
    public String toString(){
        return "[name="+name+",hp="+hp+",damage="+damage+"]";
    }

    public boolean matched(){
        return (hp>100&&damage<50);
    }

    public int getHp(){
        return this.hp;
    }


}
