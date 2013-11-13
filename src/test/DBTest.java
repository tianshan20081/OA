/**
 * 
 */
package test;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Oct 30, 2013 3:58:38 PM
 * 
 */
public class DBTest
{
	public static void main(String[] args) {
		Connection con = null; // 创建用于连接数据库的Connection对象
		try {
			Class.forName("com.mysql.jdbc.Driver");// 加载Mysql数据驱动

			con = DriverManager.getConnection("jdbc:mysql://192.168.7.6:3306/test", "root", "root");// 创建数据连接

		} catch (Exception e) {
			System.out.println("数据库连接失败" + e.getMessage());
		}
		System.out.println(con);
	}

}
