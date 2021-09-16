package Collection;

import File.Hero;

import java.util.ArrayList;

public class testArrayList {
    public static void main(String[] args) {
        //容器Arrylist用于存放对象，不用担心数组的边界问题
        ArrayList heros=new ArrayList();
        heros.add(new Hero("Garen"));
        System.out.println(heros.size());
        //容器的容量"capacity"会随着对象的增加，自动增加
        //只需要不断的往容器里添加英雄即可，不用担心出现数组的边界问题
        heros.add(new Hero("Teemo"));
        System.out.println(heros.size());

    }
}
