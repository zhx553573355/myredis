package zx.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import zx.dao.UserMapper;
import zx.pojo.User;
 
public class testMysqlController {
	// JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/myredis?useUnicode=true&characterEncoding=utf8&useSSL=false&serverTimezone=UTC";
  
    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "myredis";
    static final String PASS = "myredis";
    
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);
        
            // 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
        
            // 执行查询
            System.out.println(" 实例化Statement对象...");
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, username, sex,age FROM sys_user";
            ResultSet rs = stmt.executeQuery(sql);
        
            // 展开结果集数据库
            while(rs.next()){
                // 通过字段检索
                int id  = rs.getInt("id");
                String username = rs.getString("username");
                String sex = rs.getString("sex");
    
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 站点名称: " + username);
                System.out.print(", 站点 URL: " + sex);
                System.out.print("\n");
            }
            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            // 处理 JDBC 错误
            se.printStackTrace();
        }catch(Exception e){
            // 处理 Class.forName 错误
            e.printStackTrace();
        }finally{
            // 关闭资源
            try{
                if(stmt!=null) stmt.close();
            }catch(SQLException se2){
            }// 什么都不做
            try{
                if(conn!=null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        System.out.println("Goodbye!");
    }
    
    
    @RequestMapping("/test")
	public String test(Model model){
		String resource = "mybatis-config.xml";
        //将硬盘中的xml变成一个输入流
        InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //使用流对象创建一个会话工厂
        SqlSessionFactory sf = new SqlSessionFactoryBuilder().build(inputStream);
        //session就是程序员与数据库交互的入口
        SqlSession session = sf.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        List<User> all = mapper.selectAllUser();
        for (User item:all){
            System.out.println(item.getUserName());
        }
        session.commit();
      //关闭会话，释放资源
        session.close();
        
        return "test";
	}
    
}
