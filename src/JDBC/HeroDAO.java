package JDBC;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HeroDAO implements DAO{

    //将驱动的初始化放在构造方法中
    public HeroDAO(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    //提供一个getConnection方法返回连接，首先简化代码，其次若更改密码，只需修改此处代码
    public Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
    }

    public int getTotal(){
        int total=0;
        try(Connection c=getConnection(); Statement s=c.createStatement()){
            String sql="select count(*) from hero";
            ResultSet rs=s.executeQuery(sql);
            if(rs.next()){
                total=rs.getInt(1);
                System.out.println("total:"+total);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return total;
    }

    @Override
    public void add(Hero hero) {
        String sql="insert into hero values (null,?,?,?)";
        try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,hero.name);
            ps.setFloat(2,hero.hp);
            ps.setInt(3,hero.damage);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void update(Hero hero) {
        String sql="update hero set name = ?, hp = ?, damage = ? where id = ?";
        try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setString(1,hero.name);
            ps.setFloat(2,hero.hp);
            ps.setInt(3,hero.damage);
            ps.setInt(4,hero.id);
            ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Hero hero) {
        String sql="delete from hero where id = ?";
        try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
        ps.setInt(1,hero.id);
        ps.execute();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Hero get(int id) {
        Hero hero=null;
        String sql="select * from hero where id = ?";
        try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
            ps.setInt(1,id);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                hero=new Hero();
                hero.id=rs.getInt(1);
                hero.name=rs.getString(2);
                hero.hp=rs.getFloat(3);
                hero.damage=rs.getInt(4);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return hero;
    }

    @Override
    public List<Hero> list() {
        List<Hero> herolist=new ArrayList<>();
        String sql="select * from hero";
        try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
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

    @Override
    public List<Hero> list(int start, int count) {
        List<Hero> herolist=new ArrayList<>();
        String sql="select * from hero limit ?,?";
        try(Connection c=getConnection();PreparedStatement ps=c.prepareStatement(sql)){
        ps.setInt(1,start);
        ps.setInt(2,count);
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
