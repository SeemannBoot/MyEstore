package cn.itcast.estore.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;

import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.UserService;
import cn.itcast.estore.service.impl.UserServiceImpl;

@SuppressWarnings("serial")
public class RegServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//1判断验证码是否正确
		String code = request.getParameter("captcha");
		String code2 = (String)request.getSession().getAttribute("code");
		//if(code != null && !code.equals("") && code.equalsIgnoreCase(code2))
		if(StringUtils.isNotBlank(code) && code.equalsIgnoreCase(code2)){
			
		} else {
			request.setAttribute("msg", "验证码错误!");
			request.getRequestDispatcher("/register.jsp").forward(request, response);
		}
		//2获取并封装用户对象
		User user= new User();
		try {
			BeanUtils.populate(user, request.getParameterMap());
			//3调用Servise
			UserService service = new UserServiceImpl();
			int result = service.register(user);
			//4处理返回结果  1成功  2重名  3异常
			if(result == 1) {
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			} else if(result ==2) {
				request.setAttribute("msg", "用户名已存在");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			} else {
				request.setAttribute("msg", "注册失败");
				request.getRequestDispatcher("/register.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
