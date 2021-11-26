package JDBC;

import java.sql.*;

public class TestJDBC1 {

    //预编译PreparedStatement；executeUpdate；获得自增长id getGeneratedKeys；获取表的元数据；事务

    public static void main(String[] args) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        String sql="insert into hero values (null,?,?,?)";
        String sql0="select * from hero where name = ?";
        try(Connection c= DriverManager.getConnection("jdbc:mysql://10.68.186.225:3306/test?characterEncoding=UTF-8","root","dyx15896883188");
            PreparedStatement ps=c.prepareStatement(sql); Statement s=c.createStatement()){

            ps.setString(1,"提莫");
            ps.setFloat(2,300f);
            ps.setInt(3,100);
            ps.execute();

            //PreparedStatement的优点：
            //1、Statement需要进行字符串拼接，可读性和维护性差，PreparedStatement使用参数设置，可读性好，不易犯错
            //2、预编译机制使PreparedStatement的性能更快
            //3、防止SQL注入式攻击
            String name="'提莫'";
            String name1="提莫";
            String sql1="select * from hero where name = "+name;
            //此时因为OR 1=1恒成立，就会把所有的数据都筛选出来
            ResultSet rs=s.executeQuery(sql1);
            while(rs.next()){
                String getname=rs.getString("name");
                System.out.println(getname);
            }

            System.out.println("------");

            //向preparedstatement中补充时，不用添加''，否则反而会筛选不出来
//            ps.setString(1,name1);
//            ResultSet rs_ps=ps.executeQuery();
//            while(rs_ps.next()){
//                System.out.println(rs_ps.getString(2));
//            }

            //execute和executeUpdate
            //相同点：都可以进行增删改
            //不同点1：execute可以执行查询语句，然后通过getResultSet，把结果集取出来，executeUpdate不能进行查询操作
            //不同点2：execute()返回值为boolean值(true表示执行的是查询语句，false表示执行的是增删改语句），executeUpdate()返回值为int，代表有多少数据收到影响
            String sqlSelect="select * from hero where name = '提莫'";
            String sqlUpdate="update hero set hp = 500 where name ='提莫'";
            System.out.println(s.execute(sqlSelect));
            ResultSet rsSelect=s.getResultSet();
            while(rsSelect.next()){
                System.out.println(rsSelect.getInt(1));
            }
            s.executeUpdate(sqlUpdate);

            //获取自增长id
            //在Statement通过execute或者executeUpdate执行完插入语句后，Mysql会为新插入的数据分配一个自增长id（前提是这个表的id设为自增长，
            //在MySQL建表时，AUTO_INCREMENT就表示自增长）
            //但无论是execute还是executeUpdate都不会返回这个自增长id是多少，需要通过Statement但getGeneratedKeys获取该id
            rs=ps.getGeneratedKeys();
            while(rs.next()){
                System.out.println(rs.getInt(1));
            }

            //获取表的元数据
            //查看数据库层面的元数据
            //即数据库服务器版本，驱动版本，都有那些数据等等
            DatabaseMetaData dbmd=c.getMetaData();
            //获取数据库服务器产品名称
            System.out.println("数据库产品名称：\t"+dbmd.getDatabaseProductName());
            //获取数据库服务器产品版本号
            System.out.println("数据库产品版本：\t"+dbmd.getDatabaseProductVersion());
            //获取数据库服务器用作类别和表名之间的分隔符，如test.user
            System.out.println("数据库和表分隔符：\t"+dbmd.getCatalogSeparator());
            //获取驱动版本
            System.out.println("驱动版本：\t"+dbmd.getDriverVersion());
            System.out.println("可用的数据库列表：\t");
            //获取数据库名称
            ResultSet rs2=dbmd.getCatalogs();
            while(rs2.next()){
                System.out.println("数据库名称：\t"+rs2.getString(1));
            }

            //事物的概念
            //mysql默认业务自动提交，当几条sql语句中某一条语句存在语法错误，正确指令还是会执行
            //关闭自动提交后(c.setAutoCommit(false),但在最后要加上c.commit()手动提交)，即在事务的前提下，在事物中的所有操作，要么都成功，要么都失败
            //但在MySQL中，只有表的类型是INNODB时，才支持事务


        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}
