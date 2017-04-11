<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	session.invalidate();
	request.getRequestDispatcher("/login.jsp").forward(request, response);
%>
