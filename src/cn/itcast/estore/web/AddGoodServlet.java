package cn.itcast.estore.web;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.itcast.estore.domain.Goods;
import cn.itcast.estore.domain.User;
import cn.itcast.estore.service.GoodsService;
import cn.itcast.estore.service.impl.GoodsServiceImpl;
import cn.itcast.estore.utils.DirUtils;
import cn.itcast.estore.utils.UUIDUtils;

@SuppressWarnings("serial")
public class AddGoodServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//判断是否登录
		User user = (User) request.getSession().getAttribute("loginUser");
		if (user == null) {
			request.getRequestDispatcher("/login.jsp").forward(request,response);
			return;
		}
		//文件上传  解析表单   封装good对象
		DiskFileItemFactory factory = new DiskFileItemFactory();//工厂类
		ServletFileUpload upload = new ServletFileUpload(factory);//解析表单对象
		//中文乱码解决
		upload.setHeaderEncoding("utf-8");
		Goods good = new Goods();
		try {
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				if(item.isFormField()){
					//普通表单项
					String name = item.getFieldName();
					String value = item.getString("utf-8");
					BeanUtils.setProperty(good, name, value);
				} else {
					//文件表单项
					//uuid重新生成图片文件名称
					String filename = item.getName();
					String extName = filename.substring(filename.lastIndexOf("."));
					filename = UUIDUtils.getUUID() + extName;
					//文件夹目录分离
					String path = request.getServletContext().getRealPath("");
					String savepath = "/upload";
					String dir = DirUtils.createDirs(20, 2);
					//String imgurl = path + savepath + dir + "/" + filename;
					File dirFile = new File(path + savepath + dir);
					if(!dirFile.exists()){
						dirFile.mkdirs();
					}
					//图片最终路径
					File file = new File(dirFile, filename);
					try {
						item.write(file);
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						item.delete();//删除临时文件
					}
					String imgurl = savepath + dir + "/" + filename;
					BeanUtils.setProperty(good, "imgurl", imgurl);
					//调用service添加商品
					GoodsService service = new GoodsServiceImpl();
					service.addGood(good);
					//跳转到查询商品的servlet
					request.getRequestDispatcher("/queryGoodsServlet").forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
