package cn.itcast.estore.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

import cn.itcast.estore.service.impl.OrdersServiceImpl;
import cn.itcast.estore.service.impl.OrdersServiceImplZi;
import cn.itcast.estore.utils.DBUtils;

public class OrdersServiceProxy{
	private static final OrdersService service = new OrdersServiceImpl();
	// 用于创建代理类的方法
		public static OrdersService getOrdersServiceProxy() {
			Object obj = Proxy.newProxyInstance(
					// 获取类加载器
					OrdersServiceProxy.class.getClassLoader(),
					new Class[]{OrdersService.class},
					new InvocationHandler() {
						public Object invoke(Object proxy, Method method, Object[] args)
								throws Throwable {
							Object obj = null;
							String mName = method.getName();
							// 在后期学习的spring框架中的一个核心AOP(面向切面（方面）编程)：方面（切面）:对事物的一个分类
							// AOP依据的是方法名字的前缀
							if (mName.startsWith("query") ||
									mName.startsWith("select")||
									mName.startsWith("find")) {
								obj = method.invoke(service, args);
							} else {
								Connection conn = null;
								try {
									// 获取连接对象
									conn = DBUtils.getConnection();
									// 开启事务：设置手动提交事务
									conn.setAutoCommit(false);
									/*Class[] clazzs1 = OrdersServiceImpl.class.getInterfaces();
									Class[] clazzs2 =new Class[]{OrdersServiceImpl.class};
									Class[] clazzs3 =new Class[]{OrdersService.class};
									Class[] clazzs4 =new Class[]{service.getClass()};
									Class[] clazzs7 =service.getClass().getInterfaces();
									Class[] clazzs5 =new Class[]{OrdersServiceImplZi.class,OrdersServiceImpl.class.getInterfaces()[0],OrdersServiceImpl.class.getInterfaces()[1]};
									Class[] clazzs6 =OrdersServiceImplZi.class.getInterfaces();
									
									System.out.println(clazzs1[0]+"==="+clazzs1[0]+"---"+clazzs1.length);
									System.out.println(clazzs2[0]+"---"+clazzs2.length);
									System.out.println(clazzs3[0]+"---"+clazzs3.length);
									System.out.println(clazzs4[0]+"---"+clazzs4.length);
									System.out.println(clazzs5[0]+"---"+clazzs5[1]+"---"+clazzs5[2]+"---"+clazzs5.length);
									System.out.println(clazzs6[0]+"---"+clazzs6.length);
									System.out.println(clazzs7[0]+"==="+clazzs7[1]+"---"+clazzs7.length);*/
									obj = method.invoke(service, args);
								} 
								// 在事务中，一般使用顶级异常来抓取，保证任何异常都能够被捕获到！！
								catch (Exception e) {
									e.printStackTrace();
									// 发生异常，回滚事务
									if (conn != null) {
										try {
											conn.rollback();
										} catch (SQLException e1) {
											e1.printStackTrace();
										}
									}
								} finally {
									// 提交事务
									if (conn != null) {
										try {
											conn.commit();
										} catch (SQLException e) {
											e.printStackTrace();
										}
									}
								}
							}
							return obj;
						}
					});
			return (OrdersService) obj;
		}
}
interface Inter{
	
}