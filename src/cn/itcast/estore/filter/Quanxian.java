package cn.itcast.estore.filter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.estore.domain.User;

public class Quanxian implements Filter{
	private List<String> userList = new ArrayList<String>();
	private List<String> adminList = new ArrayList<String>();
	public void init(FilterConfig config) throws ServletException {
		//初始化时获取配置文件的信息
		String userPath = config.getServletContext().getRealPath("WEB-INF/classes/user.txt");
		try {
			BufferedReader reader = new BufferedReader(new FileReader(userPath));
			String line = null;
			while((line = reader.readLine()) != null){
				userList.add(line);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//普通类加载配置文件   类加载器
		try {
			ClassLoader loader = Quanxian.class.getClassLoader();
			InputStream is = loader.getResourceAsStream("admin.txt");
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = reader.readLine()) != null){
				adminList.add(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		//req.getServletContext().getContextPath();
		String uri = req.getRequestURI();
		int index = req.getContextPath().length() + 1;
		uri = uri.substring(index);//项目下的各种servlet 和jsp 路径
		boolean inUser = userList.contains(uri);
		boolean inAdmin = adminList.contains(uri);
		if(inUser || inAdmin){
			//判断是否登录
			User user = (User) req.getSession().getAttribute("loginUser");
			if (user == null) {
				request.getRequestDispatcher("/login.jsp").forward(request,response);
				return;
			}
			else if("user".equals(user.getRole()) && inUser){
				chain.doFilter(req, resp);
			}
			else if("admin".equals(user.getRole()) && inAdmin){
				chain.doFilter(req, resp);
			}
			else {
				throw new RuntimeException("您没有访问权限!");
			}
		} else {
			//不处理,放行
			chain.doFilter(req, resp);
		}
	}

	
	public void destroy() {
	}


}
