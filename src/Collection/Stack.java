package Collection;

import File.Hero;

public interface Stack {
    //入栈
    public void push(Hero h);
    //出栈
    public void pull();
    //查看最后一个元素
    public Hero peek();
}
