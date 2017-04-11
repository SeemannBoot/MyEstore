package annotation;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;

public class ParseDataSource {
	@DataSource(driver = "com.mysql.jdbc.Driver", 
			url = "jdbc:mysql:///estore", 
			username = "root", 
			password = "123")
	public static Connection getConnection(){
		Class clazz = ParseDataSource.class;
		Method[] methods = clazz.getMethods();
		for (Method method : methods) {
			DataSource annotation = method.getAnnotation(DataSource.class);
			if(annotation != null){
				String driver = annotation.driver();
				String url = annotation.url();
				String username = annotation.username();
				String password = annotation.password();
				try {
					Class.forName(driver);
					Connection connection = DriverManager.getConnection(url, username, password);
					return connection;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}
