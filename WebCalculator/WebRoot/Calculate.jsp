<%@ page language="java"
 	import="org.jenetics.*,org.jenetics.util.*,main.*,java.util.Arrays" 
 	contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 
 
<%
	org.jenetics.util.Concurrency.setConcurrency(0);// 使用单线程
	
	String UpEquation = request.getParameter("UpEquation");
	String LEquation = request.getParameter("LEquation");
	String UpConstraint = request.getParameter("UpConstraint");
	String LConstraint = request.getParameter("LConstraint");
	System.out.println(UpEquation);
	System.out.println(LEquation);
	System.out.println(LConstraint);
%>



<%	
	Upper up = new Upper();
	up.setuEquation(UpEquation);
	up.setlEquation(LEquation);
	up.setStrCons(LConstraint);
	up.setUstrCons(UpConstraint);
	up.UCal();
	double[] dbbestX=up.getBestX();
	double[] dbbestY=up.getBestY();
	double dbuFitness=up.getuFitness();
	double dblFitness=up.getlFitness();
	String bestX=Arrays.toString(dbbestX);
	String bestY=Arrays.toString(dbbestY);
	String uFitness = String.valueOf(dbuFitness);
	String lFitness = String.valueOf(dblFitness);

%>
<% 

	request.getSession().setAttribute("bestX", bestX);
	request.getSession().setAttribute("bestY", bestY);
	request.getSession().setAttribute("UpEquation", UpEquation);
	request.getSession().setAttribute("LEquation", LEquation);
	request.getSession().setAttribute("LConstraint", LConstraint);
	request.getSession().setAttribute("UpConstraint", UpConstraint);
	request.getSession().setAttribute("uFitness", uFitness);
	request.getSession().setAttribute("lFitness", lFitness);

	response.sendRedirect("Display.jsp");

 %>    



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>计算中...</title>

  </head>

  <body>

  
  </body>
</html>

