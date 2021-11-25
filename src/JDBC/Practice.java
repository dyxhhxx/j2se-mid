package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Practice {

    public static void main(String[] args) {

        //向数据库中插入100条数据
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        for(int i=0;i<100;i++){    //for循环也可以写在里面
            try(
                Connection c= DriverManager.getConnection("jdbc:mysql://192.168.0.101:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
                Statement s=c.createStatement();
            ){

                //注意：在写sql语句时，字符串一定要用''不能用""
                String sql="insert into hero values (null,'hero"+i+"',"+(int)(Math.random()*200)+","+(int)(Math.random()*100)+")";
                s.execute(sql);

            }catch(SQLException e){
                e.printStackTrace();
            }
        }


    }
}
