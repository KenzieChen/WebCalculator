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
    
    <title>˫��滮������</title>
    

  </head>
  
  <body>

  <form action="Calculate.jsp" method="get">
	
  	<div>
  		�ϲ㺯�������߱���X����<br>
  		����ϲ㺯����<select name="uf">
       					<option value="ufmax" selected="selected">���ֵ</option>
       					<option value="ufmin">��Сֵ</option>
       			</select><br>
       	<table>
       		<tr>
       			<td>
       				�ϲ㺯����<br>
       				<textarea cols="50" rows="10"  name="UpEquation"><%=UpEquation %></textarea>
       			</td>
       			<td>
       				�ϲ�Լ����<br>
       				<textarea cols="50" rows="10"  name="UpConstraint"><%=UpConstraint %></textarea>
       			</td>
       		</tr>
       	</table>
  		
  	</div>
  	<div>
  	  <br><br>�²㺯�������߱���Y����<br>
  		 	����²㺯����	<select name="df">
       				<option value="dfmax" selected="selected">���ֵ</option>
       				<option value="dfmin">��Сֵ</option>
       			</select><br>
       	<table>
       		<tr>
       			<td>
       				�²㺯����<br>
       				<textarea cols="50" rows="10"  name="LEquation"><%=LEquation %></textarea><br>
       			</td>
       				
       			<td>
       				�²�Լ����<br>
       				<textarea cols="50" rows="10"  name="LConstraint"><%=LConstraint%></textarea>
       			</td>
       		</tr>
       	</table>
  	</div>
  	<input type="submit" value="��ʼ����">  
  	  <table>
  	<tr>
  		<th>������</th>
  	</tr>
  	<tr>
  		<td>X������ֵ:<input type="text" name="X" size=50 value="<%=bestX %>"></td>
  		<td>�ϲ���Ѻ���ֵ:<input type="text" name="uFitness" size=50 value="<%=uFitness %>"></td>
  	</tr>
  	<tr>
  		<td>Y������ֵ:<input type="text" name="Y" size=50 value="<%=bestY %>"></td>
  		<td>�²���Ѻ���ֵ:<input type="text" name="Y" size=50 value="<%=lFitness %>"></td>
  	</tr>
  </table>
	<input type="reset" value="��ʼ��"> 
  	                    	
  </form>
  
  


  </body>
</html>
