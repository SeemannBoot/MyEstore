<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>My JSP 'good_add.jsp' starting page</title>
  </head>
  
  <body>
    <form action="addGoodServlet" method="post" enctype="multipart/form-data">
    	商品名称:<input type="text" name="name"/><br/>
    	市场价:<input type="text" name="marketprice"/><br/>
    	商城价:<input type="text" name="estoreprice"/><br/>
    	商品分类: <select name="category">
    				<option>图书</option>
    				<option>电子</option>
    				<option>手机</option>
    				<option>衣服</option>
    			</select><br/>
    	商品库存:<input type="text" name="num"/><br/>
    	图片路径:<input type="file" name="upFile"/><br/>
    	商品描述:<textarea rows="5" cols="30" name="discription"></textarea><br/>
    	<button>添加商品</button>
    </form>
  </body>
</html>
