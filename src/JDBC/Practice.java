package JDBC;

import java.sql.*;

public class Practice {

    public static void main(String[] args) {

//        //向数据库中插入100条数据
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try (
//                Connection c = DriverManager.getConnection("jdbc:mysql://192.168.0.101:3306/test?characterEncoding=UTF-8", "root", "dyx15896883188");
//                Statement s = c.createStatement();
//        ) {
//            for (int i = 0; i < 100; i++) {    //for循环也可以写在里面
//
//                //注意：在写sql语句时，字符串一定要用''不能用""
//                String sql = "insert into hero values (null,'hero" + i + "'," + (int) (Math.random() * 200) + "," + (int) (Math.random() * 100) + ")";
//                s.execute(sql);
//            }
//
//            //分页查询
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

//        list(0,5);

        addData("黑寡妇",200,100);



    }

    //设计一个方法进行分页查询,start表示开始页数，count表示每页显示的数据数
    public static void list(int start,int count){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        try(Connection c=DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
        Statement s=c.createStatement()){
            String sql="select * from hero limit "+start+","+count;
            ResultSet rs=s.executeQuery(sql);
            while(rs.next()){
                int id=rs.getInt(1);
                String name=rs.getString(2);
                float hp=rs.getFloat(3);
                int damage=rs.getInt(4);
                System.out.printf("%d\t%s\t%f\t%d\n",id,name,hp,damage);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }

    }

    //设计一个方法，当插入一条数据后，通过获得自增长id，得到这条数据的id，比如说是55，
    //删除这条数据的上一条，54
    //如果54不存在，删除53，以此类推直至删除上一条数据
    public static void addData(String name,float hp,int damage){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="insert into hero values (null,?,?,?)";
        try(Connection c=DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
        PreparedStatement ps=c.prepareStatement(sql)){

            ps.setString(1,name);
            ps.setFloat(2,hp);
            ps.setInt(3,damage);
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            while(rs.next()){
                int index=rs.getInt(1);
                System.out.println("新插入的数据自增长id为："+index);
                deleteData(index);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void deleteData(int index){
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="select * from hero where id = ?";
        String sql1="delete from hero where id = ?";
        try(Connection c=DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
            PreparedStatement ps=c.prepareStatement(sql);PreparedStatement ps1=c.prepareStatement(sql1)){
            ps.setInt(1,index-1);
            ResultSet rs=ps.executeQuery();
            if(rs.next()){
                System.out.println(index+"处的上一条数据的id是"+(index-1));
                ps1.setInt(1,(index-1));
                System.out.println(ps1.execute());
            }else{
                deleteData(index-1);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

}
