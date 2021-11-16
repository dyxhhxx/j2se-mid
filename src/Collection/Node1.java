package Collection;

//重写二叉树

import File.Hero;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Node1 {
    Node1 left;
    Node1 right;
    Object value;

    //插入数据
    //借助递归，从头节点开始，向左右比较
    public void addNode(int num){
        //先看头节点是否有值
        if(value==null){
            value=num;
        }
        //之后递归比较
        else{
            if((int)value>=num){
                if(left==null){
                    left=new Node1();
                }
                left.addNode(num);
            }else{
                if(right==null){
                    right=new Node1();
                }
                right.addNode(num);
            }
        }
        return;
    }

    //遍历二叉树
    //借助递归,从当前节点开始，显示左边节点--当前节点--右边节点
    public List<Integer> showNode(){
        List<Integer> ranking=new ArrayList<>();
        if(left!=null){
            ranking.addAll(left.showNode());
        }
        if(value!=null){
            ranking.add((Integer)value);
        }
        if(right!=null){
            ranking.addAll(right.showNode());
        }
        return ranking;
    }

    public static void main(String[] args) {
        Node1 bt=new Node1();
        int[] a={3,52,654,21,86,235,6,4};
        for(int i=0;i<a.length;i++){
            bt.addNode(a[i]);
        }
        List<Integer> ranking=new ArrayList<>();
        ranking=bt.showNode();
        for(int i:ranking){
            System.out.print(i+" ");
        }
        System.out.println();
        //先初始化一个40000元素大小的数组
        int[] total=new int[40000];
        for(int i=0;i<total.length;i++){
            total[i]=(int)(Math.random()*100000);
        }
        //二叉树排序
        long start=System.currentTimeMillis();
       Node1 NumGroup=new Node1();
        for(int i:total){
            NumGroup.addNode(i);
        }
        long end=System.currentTimeMillis();
        List<Integer> treeresult=new ArrayList<>();
        treeresult=NumGroup.showNode();
//        for(int i:treeresult){
//            System.out.println(i);
//        }
        long timeoftree=end-start;
        //冒泡法排序
        int[] total1=total;
        start=System.currentTimeMillis();
        for(int j=total1.length-1;j>0;j--){
            for(int i=0;i<j;i++){
                int x;
                if(total1[i]>total1[i+1]){
                    x=total1[i];
                    total1[i]=total1[i+1];
                    total1[i+1]=x;
                }
            }
        }
        end=System.currentTimeMillis();
        long timeofbubbling=end-start;
        //选择法
        int[] total2=total;
        start=System.currentTimeMillis();
        for(int i=0;i<total2.length-1;i++){
            for(int j=i+1;j<total2.length;j++){
                if(total2[i]>total2[j]){
                    int x;
                    x=total2[i];
                    total2[i]=total2[j];
                    total2[j]=x;
                }
            }
        }
        end=System.currentTimeMillis();
        long timeofselection=end-start;
        //判断三种方法生成的列表是否相同(缩小规模或用Arrays.equals()）
        List<Integer> bubblingresult=new ArrayList<>();
        for(int i:total1){
            bubblingresult.add(i);
        }
        System.out.println("----------------");
//        for(int i:bubblingresult){
//            System.out.println(i);
//        }
//        System.out.println(Arrays.equals(timeofbubbling,timeoftree));
        System.out.println(timeoftree);
        System.out.println(timeofbubbling);
        System.out.println(timeofselection);
//        Integer[] result1=(Integer[]) treeresult.toArray();
//        int[] result2=new int[result1.length];
//        for(int i=0;i<result2.length;i++){
//            result2[i]=result1[i];
//        }
        System.out.println(Arrays.equals(total1,total2));
//        System.out.println(Arrays.equals(total1,result2));

    }
}
