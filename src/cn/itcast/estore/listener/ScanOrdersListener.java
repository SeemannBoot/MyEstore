package cn.itcast.estore.listener;

import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import cn.itcast.estore.service.OrdersService;
import cn.itcast.estore.service.impl.OrdersServiceImpl;

public class ScanOrdersListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent arg0) {
		// 1创建定时器
		Timer timer = new Timer();
		// 2指定任务
		TimerTask task = new TimerTask() {
			public void run() {
				System.out.println("定时任务开启******************自动扫描过期订单!");
				System.out.println("定时任务开启******************自动扫描过期订单!");
				System.out.println("定时任务开启******************自动扫描过期订单!");
				System.out.println("定时任务开启******************自动扫描过期订单!");
				System.out.println("定时任务开启******************自动扫描过期订单!");
				OrdersService service = new OrdersServiceImpl();
				service.scanOrder();
				System.out.println("超过12个小时的未支付的订单已更改为过期状态!");
				System.out.println("超过12个小时的未支付的订单已更改为过期状态!");
				System.out.println("超过12个小时的未支付的订单已更改为过期状态!");
				System.out.println("超过12个小时的未支付的订单已更改为过期状态!");
				System.out.println("超过12个小时的未支付的订单已更改为过期状态!");
			}
		};
		// 3执行任务的策略
		timer.schedule(task, 3000, 1000*60*60);

	}
	public void contextDestroyed(ServletContextEvent arg0) {

	}

}
