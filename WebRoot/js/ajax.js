function getXMLHttpRequest() {
	var xmlhttp;
	// 如果XMLHttpRequest不存在，则返回一个undefined
	if (window.XMLHttpRequest) {
		// code for IE7+, Firefox, Chrome, Opera, Safari
		xmlhttp = new XMLHttpRequest();
	} else {
		// code for IE6, IE5
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	return xmlhttp;
}
/*
 * options: 是一个对象
 * {
 * 	method:post|get, // 请求方式
 *  url: 'xxxServlet', // 请求地址
 *  cache: 是否使用缓存，默认true,仅当请求方式为get请求时有效！
 *  params: {a:1,b:2,c:3}"a=1&b=2&c=3"
 *  async: true|false // 同步false还是异步true
 *  callback: function
 * }
 */

function getType(data) {
	var type = Object.prototype.toString.call(data);
	//"".substring(start, end); // 从指定索引开始，到指定索引结束
	//"".substr(start, length); // 从指定索引开始，取几个字符
	type = type.substr(8);
	type = type.substr(0, type.length - 1);
	return type.toLowerCase();
}

function ajax(options) {
	if (!options.url) return;
	
	// 1. 获取核心对象
	var XHR = getXMLHttpRequest();
	// 2. 监听服务器返回状态
	XHR.onreadystatechange = function() {
		if (XHR.readyState==4 && XHR.status==200) {
	    	var result = XHR.responseText;
	    	// 判断参数是否为函数类型
	    	if (getType(options.callback) === "function") {
	    		options.callback(result);
	    	}
		}
	};
	
	// 控制请求方式一定为get或post,并且默认是get
	if (options.method === undefined) {
		options.method = "GET";
	}
	// 将方法全部转大写
	options.method = options.method.toUpperCase();
	// 如果不是get也不是post,默认get
	if (options.method !== "GET" && options.method !== "POST") {
		options.method = "GET";
	}
	
	// 对参数类型进行判断，如果是对象类型，将对象类型转换为字符串的格式！
	if (getType(options.params) === "object") {
		var obj = options.params;
		var str = "";
		for (key in obj) {
			str += "&" + key + "=" + obj[key];
		}
		options.params = str.substr(1);
	}
	
	
	// 判断是否为get请求：将参数拼接在url的后面
	if (options.method === "GET") {
		// 如果有参数，将参数拼接在地址后面
		if (options.params) {
			if (options.url.indexOf("?") > -1) {
				options.url += "&" + options.params;
			} else {
				options.url += "?" + options.params;
			}
		}
		// 如果使用缓存，在地址后面拼接随机数
		if (options.cache === false) {
			if (options.url.indexOf("?") > -1) {
				options.url += "&t=" + Math.random();
			} else {
				options.url += "?t=" + Math.random();
			}
		}
	}
	// 默认true: xxx !== false
	// 默认false: xxx === true
	
	// 3. 发送请求
	XHR.open(options.method, options.url, options.async !== false);
	if (options.method === "POST") {
		XHR.setRequestHeader("Content-type","application/x-www-form-urlencoded");
		XHR.send(options.params);
	} else {
		XHR.send();
	}
}


