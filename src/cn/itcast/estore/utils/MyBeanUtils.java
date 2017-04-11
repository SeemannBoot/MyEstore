package cn.itcast.estore.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
@SuppressWarnings("all")
public class MyBeanUtils {

	public static Object populate(Object obj, HttpServletRequest request) {
		Class clazz = obj.getClass();
		Field[] fileds = clazz.getDeclaredFields();
		for (Field field : fileds) {
			String fName = field.getName();
			String mName = "set"+(fName.charAt(0)+"").toUpperCase()+fName.substring(1);
			String value = request.getParameter(fName);
			try {
				Method method = clazz.getMethod(mName, field.getType());
				method.invoke(obj, value);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return obj;
	}
}
