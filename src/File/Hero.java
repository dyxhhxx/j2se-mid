package File;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;

public class Hero implements Serializable {
    public int hp;
    public String name;
    private static final long serialVersionUID=1l;

    public Hero(){

    }

    public Hero(String name){
        this.name=name;
    }

    public Hero(int hp){this.hp=hp;};

    @Override
    public String toString(){
        return "[name:"+this.name+" hp:"+this.hp+"]";
    }

    public static void main(String[] args) {
        String s="ajbdjhsab";
        byte[] s1=s.getBytes(StandardCharsets.UTF_8);
        System.out.println(new String(s1));
    }
}
