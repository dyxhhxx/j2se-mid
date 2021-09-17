package Collection;


import File.Hero;

import java.util.LinkedList;

public class myStack implements Stack {

    LinkedList<Hero> hll=new LinkedList<>();

    @Override
    public void push(Hero h) {
        hll.addLast(h);
    }

    @Override
    public void pull() {
        hll.removeLast();
    }

    @Override
    public Hero peek() {
        return hll.getLast();
    }



    public static void main(String[] args) {
        myStack ms=new myStack();
        ms.push(new Hero("Ironman"));
        ms.push(new Hero("BlackWidow"));

    }
}
