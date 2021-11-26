package JDBC;

import java.sql.*;

public class TestJDBC {

    //增删改查基本操作

    public static void main(String[] args) {

//        Connection c=null;
//        Statement s=null;
//        try{
//            //驱动类com.mysql.jdbc.Driver就在mysql-connector-java-5.0.8-bin.jar中
//            //Class.forName()是把这个类加载到JVM中，加载的时候，就会执行其中的静态初始化块，完成驱动的初始化相关工作
//            //如果忘了导入包，就会抛出ClassNotFoundException
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("数据库驱动成功！");
//            //建立与数据库导Connection连接
//            //这里需要提供：
//            //数据库所处于ip：127.0.0.1（本机）
//            //数据库导端口号：3306（mysql专用端口号）
//            //数据库名称h2j
//            //编码方式UTF-8
//            //账号：root
//            //密码
//            c= DriverManager.getConnection("jdbc:mysql://192.168.0.101:3306/test?characterEncoding=UTF-8",
//                    "root","dyx15896883188");
//            System.out.println("连接成功，获取连接对象："+c);
//            //创建Statement
//            //注意：使用的是java.sql.Statement
//            //而不是com.mysql.jdbc.Statement;
//            s=c.createStatement();
//            System.out.println("获取Statement对象："+s);
//            //执行SQL语句
//            //先准备sql语句，注意：字符串要用单引号
//            String sql="insert into hero values(null,"+"'提莫'"+","+313.0f+","+50+")";
////            String sql="insert into hero values(null,'提莫',313,50)";   //也可以
//            s.execute(sql);
//            System.out.println("插入执行语句成功");
//
//        }catch (ClassNotFoundException e){
//            e.printStackTrace();
//        }catch (SQLException e){
//            e.printStackTrace();
//        }finally{
//
//            //数据库的连接是有限资源，相关操作结束后，养成关闭数据库的好习惯
//            //先关闭Statement
//            if(s!=null){
//                try{
//                    s.close();
//                }catch(SQLException e){
//                    e.printStackTrace();
//                }
//            }
//            //后关闭Connection
//            if(c!=null){
//                try{
//                    c.close();
//                }catch(SQLException e){
//                    e.printStackTrace();
//                }
//            }
//        }

        //总结：以上共分为五步
        //1、初始化驱动，类加载
        //2、建立与数据库的连接Connection
        //3、创建Statement
        //4、执行sql语句
        //5、结束时，关闭Statement和Connection


        //也可以用try-with-resource的方式自动关闭连接
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("数据库驱动成功");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (Connection c1 = DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8", "root", "dyx15896883188");
             Statement s1 = c1.createStatement();) {
            //增
            String sql = "insert into hero values (5,'盖伦',600,100)";
//            s1.execute(sql);
            //删
            sql = "delete from hero where id=4";
//            s1.execute(sql);
            //改
            sql = "update hero set name = 'name 5' where id=3";
//            s1.execute(sql);
            //查
            sql = "select * from hero";
            ResultSet rs = s1.executeQuery(sql);   //执行查询语句，并把结果返回给ResultSet
            while (rs.next()) {
                int id = rs.getInt("id");   //可以用字段名
                String name = rs.getString(2);   //也可以使用字段的顺序,java中唯二从1开始的结构，另外一个是preparedstatement
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d\n", id, name, hp, damage);
            }
            rs.close();  //不一定要在这里关闭ResultSet，因为Statement关闭时，会自动关闭ResultSet

            //判断密码是否正确
            String name = "dashen";
            String password = "thisispassword";
            sql = "select * from user where name = '" + name + "' and passward = '" + password + "'";
            ResultSet rs1 = s1.executeQuery(sql);
            if (rs1.next()) {
                System.out.println("账号密码正确");
            } else {
                System.out.println("账号密码错误");
            }

            //获取总数
            sql = "select * from hero";
            ResultSet rs2 = s1.executeQuery(sql);
            int total = 0;
            while (rs2.next()) {
                total++;
            }
            System.out.printf("表hero中共有%d条数据\n", total);
            //或
            sql = "select count(*) from hero";
            ResultSet rs3 = s1.executeQuery(sql);
            while (rs3.next()) {
                total = rs3.getInt(1);
            }
            System.out.printf("表hero中共有%d条数据\n", total);


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
