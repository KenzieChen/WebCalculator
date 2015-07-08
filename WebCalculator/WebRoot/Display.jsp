<%@ page language="java" import="java.util.*,javax.script.*;" pageEncoding="GBK"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%	
	String bestX= (String)request.getSession().getAttribute("bestX"); 
	String bestY = (String)request.getSession().getAttribute("bestY");
	String UpEquation=(String)request.getSession().getAttribute("UpEquation");
	String LEquation=(String)request.getSession().getAttribute("LEquation");
	String LConstraint=(String)request.getSession().getAttribute("LConstraint");
	String UpConstraint=(String)request.getSession().getAttribute("UpConstraint");
	String uFitness=(String)request.getSession().getAttribute("uFitness");
	String lFitness=(String)request.getSession().getAttribute("lFitness");
	
	if(bestX==null || bestY==null || UpEquation ==null ||LEquation==null ||LConstraint==null ||UpConstraint==null || uFitness==null ||lFitness==null ){
		bestX = bestY = UpEquation = LEquation = LConstraint = uFitness = UpConstraint = lFitness = "0"; 
	}

//	String dbestFitness=(String)request.getSession().getAttribute("dbestFitness");
//	System.out.println(bestX);
//	System.out.println(UpEquation);
//	System.out.println(DEquation);
 %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>双层规划计算器</title>
    

  </head>
  
  <body>

  <form action="Calculate.jsp" method="get">
	
  	<div>
  		上层函数（决策变量X）：<br>
  		求解上层函数的<select name="uf">
       					<option value="ufmax" selected="selected">最大值</option>
       					<option value="ufmin">最小值</option>
       			</select><br>
       	<table>
       		<tr>
       			<td>
       				上层函数：<br>
       				<textarea cols="50" rows="10"  name="UpEquation"><%=UpEquation %></textarea>
       			</td>
       			<td>
       				上层约束：<br>
       				<textarea cols="50" rows="10"  name="UpConstraint"><%=UpConstraint %></textarea>
       			</td>
       		</tr>
       	</table>
  		
  	</div>
  	<div>
  	  <br><br>下层函数（决策变量Y）：<br>
  		 	求解下层函数的	<select name="df">
       				<option value="dfmax" selected="selected">最大值</option>
       				<option value="dfmin">最小值</option>
       			</select><br>
       	<table>
       		<tr>
       			<td>
       				下层函数：<br>
       				<textarea cols="50" rows="10"  name="LEquation"><%=LEquation %></textarea><br>
       			</td>
       				
       			<td>
       				下层约束：<br>
       				<textarea cols="50" rows="10"  name="LConstraint"><%=LConstraint%></textarea>
       			</td>
       		</tr>
       	</table>
  	</div>
  	<input type="submit" value="开始计算">  
  	  <table>
  	<tr>
  		<th>计算结果</th>
  	</tr>
  	<tr>
  		<td>X的最优值:<input type="text" name="X" size=50 value="<%=bestX %>"></td>
  		<td>上层最佳函数值:<input type="text" name="uFitness" size=50 value="<%=uFitness %>"></td>
  	</tr>
  	<tr>
  		<td>Y的最优值:<input type="text" name="Y" size=50 value="<%=bestY %>"></td>
  		<td>下层最佳函数值:<input type="text" name="Y" size=50 value="<%=lFitness %>"></td>
  	</tr>
  </table>
	<input type="reset" value="初始化"> 
  	                    	
  </form>
  
  


  </body>
</html>
