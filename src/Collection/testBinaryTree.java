package Collection;

import java.util.ArrayList;
import java.util.List;

public class testBinaryTree {
    public static void main(String[] args) {
        Node root=new Node();
        int[] intarray=new int[]{3,5,242,22,4,6,7,2,4,44,677,22,83,13,5};
        for(int i:intarray){
            root.addNode(i);
        }
//        List<Integer> ranking=root.ListTreeNode();
//        for(int i:ranking){
//            System.out.print(i+"\t");
//        }
        System.out.println(root.ListTreeNode());
    }
}

class Node{
    //左节点
    Node left;
    //右节点
    Node right;
    //值
    Object value;

    //添加新的节点
    public void addNode(int num){
        //如果当前节点为空，就直接赋值当前节点
        if(null==value){
            value=num;
        }
        //如果当前节点有值，就与当前节点的值比较，如果num较小，就向左边递归；如果右边小，就向右边递归
        else{
            if(num<=(int)value){
                if(left==null){
                    left=new Node();
                }
                left.addNode(num);
            }
            else{
                if(right==null){
                    right=new Node();
                }
                right.addNode(num);
            }
        }
    }

    //遍历二叉树,需要借助递归
    public List<Integer> ListTreeNode(){
        List<Integer> ranking=new ArrayList<>();
        //先从root的左边开始添加
        if(null!=left){
            ranking.addAll(left.ListTreeNode());
        }
        //在添加root节点本身的值
        ranking.add((int)value);
        //添加当前节点右边的值
        if(null!=right){
            ranking.addAll(right.ListTreeNode());
        }
        return ranking;
    }
}

    //practice
    //创建一个大小为40000的随机数组
    //分别用冒泡法，选择法，二叉树进行排序
    //比较哪种比较快




