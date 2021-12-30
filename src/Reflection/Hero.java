package Reflection;

import org.w3c.dom.ls.LSOutput;

public class Hero {

    public String name;
    int hp;
    int damage;
    static String copyright;
    static{
        System.out.println("初始化copyright");
        copyright="版权有Riot公司所有";
    }

    public Hero(){

    }

    public Hero(String str){
        name=str;
    }

    public void setName(String str){
        this.name=str;
    }

    public String getName(){
        return name;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", hp=" + hp +
                '}';
    }

    public void attackHero(Hero h){
        System.out.printf("英雄%s正在攻击英雄%s\n",name,h.name);
    }
}
