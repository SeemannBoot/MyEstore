package cn.itcast.estore.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.estore.domain.PCD;
import cn.itcast.estore.utils.DBUtils;
import flexjson.JSONSerializer;

@SuppressWarnings("serial")
public class LoadServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pid = request.getParameter("pid");
		QueryRunner qr = new QueryRunner(DBUtils.getDataSource());
		String sql = "select * from province_city_district where pid=?";
		try {
			List<PCD> list = qr.query(sql, new BeanListHandler<PCD>(PCD.class), pid);
			JSONSerializer serializer = new JSONSerializer().exclude("*.class","pid");
			String json = serializer.serialize(list);
			response.getWriter().write(json);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
