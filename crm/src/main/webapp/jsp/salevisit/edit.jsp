﻿<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<TITLE>修改拜访记录</TITLE> 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="${pageContext.request.contextPath }/css/Style.css" type=text/css rel=stylesheet>
<LINK href="${pageContext.request.contextPath }/css/Manage.css" type=text/css
	rel=stylesheet>
<script type="text/javascript" src="${pageContext.request.contextPath }/js/loadSelect.js"></script>

<META content="MSHTML 6.00.2900.3492" name=GENERATOR>
</HEAD>
<BODY>
	<FORM id=form1 name=form1
		action="${pageContext.request.contextPath }/SaleVisitAction_updateSaleVisit"
		method="post"    >
		<!-- 隐藏域回显当前编辑的拜访记录id -->
		<input  type="hidden"  name="visit_id" value="${saleVisit.visit_id}"  /> 

		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_019.jpg"
						border=0></TD>
					<TD width="100%" background="${pageContext.request.contextPath }/images/new_020.jpg"
						height=20></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath }/images/new_021.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15 background=${pageContext.request.contextPath }/images/new_022.jpg><IMG
						src="${pageContext.request.contextPath }/images/new_022.jpg" border=0></TD>
					<TD vAlign=top width="100%" bgColor=#ffffff>
						<TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
							<TR>
								<TD class=manageHead>当前位置：拜访记录管理 &gt;修改</TD>
							</TR>
							<TR>
								<TD height=2></TD>
							</TR>
						</TABLE>
						
						<TABLE cellSpacing=0 cellPadding=5  border=0>
						
							<TR>
								<td>所属客户：</td>
								<td >
								<input type="hidden" name="customer.cust_id" style="WIDTH: 180px" id="cust_id" value="${saleVisit.customer.cust_id}" />
								<input type="text"  style="WIDTH: 180px" id="cust_name" value="${saleVisit.customer.cust_name}"/>
									<input type="button" value="选择客户" onclick="window.open('${pageContext.request.contextPath }/CustomerAction_list?select=true','','left=540px, top=350px,width=600,height=300')"/>
								</td>
								<td>拜访时间 ：</td>
								<td  >
									<INPUT class=textbox id="visit_time" type="date" 
														style="WIDTH: 180px" maxLength=50 name="visit_time" value="${saleVisit.visit_time}" >
								</td>
							</TR>
							
							<TR>
								
								<td>被拜访人 ：</td>
								<td >
								<INPUT class=textbox id=sChannel2 type="text"
														style="WIDTH: 180px" maxLength=50 name="visit_interviewee" value="${saleVisit.visit_interviewee}" 
														 >
								</td>
								<td>拜访地址：</td>
								<td>
								<INPUT class=textbox id=sChannel2
														style="WIDTH: 180px" maxLength=50 name="visit_addr" value="${saleVisit.visit_addr}">
								</td>
							</TR>
							
							<TR>
								
								
								<td>拜访详情 ：</td>
								<td>
								<INPUT class=textbox 
														style="WIDTH: 180px" maxLength=50 name="visit_detail" value="${saleVisit.visit_detail}">
								</td>
								<td>下次拜访时间：</td>
								<td>
								<INPUT class=textbox id="visit_nexttime"  type="date"
														style="WIDTH: 180px" maxLength=50 name="visit_nexttime" value="${saleVisit.visit_nexttime}" >
								</td>
							</TR>
							<tr>
								<td rowspan=2>
								<INPUT class=button id=sButton2 type="submit"
														value="保存 " name=sButton2>
								</td>
							</tr>
							
						</TABLE>
						
						
					</TD>
					<TD width=15 background="${pageContext.request.contextPath}/images/new_023.jpg">
					<IMG src="${pageContext.request.contextPath}/images/new_023.jpg" border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
		<TABLE cellSpacing=0 cellPadding=0 width="98%" border=0>
			<TBODY>
				<TR>
					<TD width=15><IMG src="${pageContext.request.contextPath}/images/new_024.jpg"
						border=0></TD>
					<TD align=middle width="100%"
						background="${pageContext.request.contextPath}/images/new_025.jpg" height=15></TD>
					<TD width=15><IMG src="${pageContext.request.contextPath}/images/new_026.jpg"
						border=0></TD>
				</TR>
			</TBODY>
		</TABLE>
	</FORM>
</BODY>
</HTML>
