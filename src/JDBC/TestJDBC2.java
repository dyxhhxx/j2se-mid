package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TestJDBC2 {

    //ORM;DAO;数据库连接池
    //ORM:Object Relationship Database Mapping，对象和关系数据库的映射，即：一个对象对应数据库里的一条记录
    //DAO:Data Access Object,数据库访问对象，实际上就是把数据库相关的操作都封装在这个类中，其他地方看不到JDBC的代码(代码详见接口DAO和接口实现类HeroDAO）

    public static void main(String[] args) {
        Hero h=get(22);
        System.out.println(h.name);
        Hero h1=new Hero();
        h1.name="提莫";
        h1.hp=300f;
        h1.damage=150;
        System.out.println("数据库中所有的提莫");
        List<Hero> herolist=list(h1);
        for(Hero hero:herolist){
            System.out.println(hero.id);
        }
        System.out.println("删除所有名字是提莫的英雄：");
        delete(h1);
        System.out.println("再插入一个提莫英雄");
        insert(h1);
        System.out.println("将提莫的血量改为400");
        h1.hp=400f;
        updateHp(h1);


    }

    //提供方法get(int id)，返回一个Hero对象
    public static Hero get(int id){
        Hero h=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="select * from hero where id = ?";
        try(Connection c= DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                h=new Hero();
                h.id=rs.getInt(1);
                h.name=rs.getString(2);
                h.hp=rs.getFloat(3);
                h.damage=rs.getInt(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return h;
    }

    //把一个Hero对象插入到数据库中
    public static void insert(Hero h){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="insert into hero values (null,?,?,?)";
        try(Connection c=DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
        PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,h.name);
            ps.setFloat(2,h.hp);
            ps.setInt(3,h.damage);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //把这个对象对应的数据删除掉
    public static void delete(Hero h){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="delete from hero where name = ?";
        try(Connection c=DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,h.name);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //更新这条Hero对象
    public static void updateHp(Hero h){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="update hero set hp = ? where name = ?";
        try(Connection c=DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setFloat(1,h.hp);
            ps.setString(2,h.name);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    //把所有的Hero数据查询出来，转换为Hero对象后，放在一个集合中返回
    public static List<Hero> list(Hero h){
        List<Hero> herolist=new ArrayList<>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="select * from hero where name = ?";
        try(Connection c=DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
            PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,h.name);
            ResultSet rs=ps.executeQuery();
            while(rs.next()){
                Hero hero=new Hero();
                hero.id=rs.getInt("id");
                hero.name=rs.getString("name");
                hero.hp=rs.getFloat("hp");
                hero.damage=rs.getInt("damage");
                herolist.add(hero);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return herolist;
    }



}
