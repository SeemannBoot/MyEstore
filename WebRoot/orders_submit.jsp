<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="Generator" content="ECSHOP v2.7.3" />
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<title>提交订单</title>
<%@include file="inc/common_head.jsp"%>
<script type="text/javascript">
	window.addEventListener("load", function() {
		var pp = document.getElementById("province");
		var cc = document.getElementById("city");
		var dd = document.getElementById("district");
		ajax({
			url: "load",
			params: {pid: -1},
			callback: function(result) {
				// 将json字符串转换成js对象
				var arr = JSON.parse(result);
				for(var i = 0; i < arr.length; i++) {
					pp.appendChild(new Option(arr[i].name, arr[i].id));
				}
			}
		});
		// 使用js给下拉框绑定事件
		pp.onchange = function() {
			ppp.value = province.options[this.selectedIndex].innerHTML;
			// 清空市和县的下拉框
			dd.length = 1;
			cc.length = 1;
			// 当没选择区域的时候不去请求后台
			if (this.value)
			ajax({
				url: "load",
				params: {pid: this.value},
				callback: function(result) {
					// 将json字符串转换成js对象
					var arr = JSON.parse(result);
					for(var i = 0; i < arr.length; i++) {
						cc.appendChild(new Option(arr[i].name, arr[i].id));
					}
				}
			});
		}
		// 使用js给下拉框绑定事件
		cc.onchange = function() {
			ccc.value = city.options[this.selectedIndex].innerHTML;
			// 清空市和县的下拉框
			dd.length = 1;
			// 当没选择区域的时候不去请求后台
			if (this.value){
				ajax({
					url: "load",
					params: {pid: this.value},
					callback: function(result) {
						// 将json字符串转换成js对象
						var arr = JSON.parse(result);
						for(var i = 0; i < arr.length; i++) {
							dd.appendChild(new Option(arr[i].name, arr[i].id));
						}
					}
				});
			}
		}
		dd.onchange = function() {
			ddd.value = district.options[this.selectedIndex].innerHTML;
		}
	});
</script>
</head>
<body>
	<%@include file="inc/header.jsp"%>
	<div class="block clearfix"><div class="AreaR">
	<div class="block box"><div class="blank"></div>
		<div id="ur_here">
			当前位置: <a href="index.jsp">首页</a><code>&gt;</code>购物流程
		</div>
	</div><div class="blank"></div><div class="box"><div class="box_1">
	<div class="userCenterBox boxCenterList clearfix" style="_height:1%;">
	<form action="submitOrdersServlet" method="post">
		<!---------收货人信息开始---------->
		<h5><span>收货人信息</span></h5>
		<table width="100%" align="center" border="0" cellpadding="5"
			cellspacing="1" bgcolor="#dddddd">
			<tr>
				<td bgcolor="#ffffff" align="right" width="120px">区域信息：</td>
				<td bgcolor="#ffffff">
					<!-- 省 -->
					<select id="province">
						<option value="">-- 请选择省 --</option>
					</select>&nbsp;&nbsp;&nbsp;
					<!-- 市 -->
					<select id="city">
						<option value="">-- 请选择市 --</option>
					</select>&nbsp;&nbsp;&nbsp;
					<!-- 县(区) -->
					<select id="district">
						<option value="">-- 请选择县(区) --</option>
					</select>
					<input type="hidden" id="ppp" name="province" value=""/>
					<input type="hidden" id="ccc" name="city" value=""/>
					<input type="hidden" id="ddd" name="district" value=""/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">详细地址：</td>
				<td bgcolor="#ffffff">
					<input style="width:347px;" id="detailAddress" name="dAddress"/>
				</td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">邮政编码：</td>
				<td bgcolor="#ffffff"><input id="zipcode" name="zipcode"/></td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">收货人姓名：</td>
				<td bgcolor="#ffffff"><input id="name" name="name"/></td>
			</tr>
			<tr>
				<td bgcolor="#ffffff" align="right">联系电话：</td>
				<td bgcolor="#ffffff"><input id="tel" name="tel"/></td>
			</tr>
		</table>
		<!---------收货人信息结束---------->
		
		<!----------商品列表开始----------->
		<div class="blank"></div>
		<h5><span>商品列表</span></h5>
		<table width="100%" border="0" cellpadding="5" cellspacing="1"
			bgcolor="#dddddd">
			<tr>
				<th width="30%" align="center">商品名称</th>
				<th width="22%" align="center">市场价格</th>
				<th width="22%" align="center">商品价格</th>
				<th width="15%" align="center">购买数量</th>
				<th align="center">小计</th>
			</tr>
			<c:set var="total" value="0"/>
			<c:forEach items="${clist }" var="c">
			<c:set var="total" value="${total + c.goods.estoreprice * c.buynum}"/>
			<tr>
				<td>
					<a href="javascript:;" class="f6">${c.goods.name }</a>
				</td>
				<td>${c.goods.marketprice }元</td>
				<td>${c.goods.estoreprice }元</td>
				<td align="center">1</td>
				<td>${c.goods.estoreprice * c.buynum}元</td>
			</tr>
			</c:forEach>
			<tr>
				<td colspan="5" style="text-align:right;padding-right:10px;font-size:25px;">
					商品总价&nbsp;<font color="red">&yen;${total }</font>元
					<input type="submit" value="提交订单" class="btn" />
				</td>
			</tr>
		</table>
		<!----------商品列表结束----------->
	</form>
	</div></div></div></div></div>
	<%@include file="inc/footer.jsp"%>
</body>
</html>