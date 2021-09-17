package Collection;

import File.Hero;
import com.sun.security.jgss.GSSUtil;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class testLinkedList {
    public static void main(String[] args) {

        //LinkedList与ArrayList均继承了List接口，因此诸如add，remove，contains等方法，LinkedList均可使用，下面是其一些特殊用法

        //双向链表-Deque
        LinkedList<Hero> ll=new LinkedList();
        //双向链表结构可以很方便的在头尾插入数据
        ll.addLast(new Hero("IronMan"));
        ll.addFirst(new Hero("Thor"));
        ll.addLast(new Hero("BlackWidow"));
        System.out.println(ll);
        //查看头部和尾部元素
        System.out.println("尾部元素是："+ll.getLast());
        System.out.println("头部元素是："+ll.getFirst());
        System.out.println(ll);
        //取出头部和尾部元素
        ll.removeLast();
        ll.removeFirst();
        //查看不会删除元素，取出会删除元素
        System.out.println(ll);

        //队列-Queue
        //Queue是FIFO先进先出的集合，常用方法有：offer在最后添加元素；poll取出第一个元素；peek查看第一个元素
        List<Hero> HeroList=new LinkedList<>();
        Queue<Hero> q=new LinkedList<>();
//        HeroList.offer(new Hero("CaptainAmerican"));
        q.offer(new Hero("CaptainAmerican"));
        q.poll();
        for(int i=0;i<5;i++){
            q.offer(new Hero("hero"+i));
        }
        System.out.println(q);
        q.poll();
        System.out.println(q);
        System.out.println(q.peek());




    }


}




