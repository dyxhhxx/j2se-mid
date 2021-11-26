package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionPool {

    List<Connection> connections=new ArrayList<>();
    int size;

    //ConnectionPool构造方法约定了这个连接池一共有多少连接
    public ConnectionPool(int size){
        this.size=size;
        init();
    }

    //在init()初始化方法中，创建了size条连接
    public void init(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            for(int i=0;i<size;i++){
                Connection c= DriverManager.getConnection("jdbc:mysql://10.68.186.225/test?characterEncoding=UTF-8","root","dyx15896883188");
                connections.add(c);
            }
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //getConnection，判断是否为空，如果是空的就wait等待，否则就借用一条连接出去
    public synchronized Connection getConnection(){
        while(connections.isEmpty()){
            try{
                this.wait();   //此处不能写connections.wait()
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
        Connection c=connections.remove(0);
        return c;
    }

    //returnConnection，在使用完毕后，归还这个连接到连接池，并且在归还完毕后，调用notifyAll唤醒等待的线程
    public synchronized void returnConnection(Connection c){
        connections.add(c);
        this.notifyAll();
    }

}

class WorkThread extends Thread{
    private ConnectionPool cp;

    public WorkThread(ConnectionPool cp,String name){
        super(name);
        this.cp=cp;
    }

    @Override
    public void run() {
        Connection c=cp.getConnection();
        System.out.println(this.getName()+"：\t获得了一条连接，并开始工作");
        try(Statement s=c.createStatement()){
            Thread.sleep(1000);
            s.execute("select * from hero");
        }catch(SQLException|InterruptedException e){
            e.printStackTrace();
        }
        cp.returnConnection(c);
    }
}

class TestConnectionPool{
    public static void main(String[] args) {
        ConnectionPool cp=new ConnectionPool(3);
        for(int i=0;i<100;i++){
            new WorkThread(cp,"working thread"+i).start();
        }
    }
}


