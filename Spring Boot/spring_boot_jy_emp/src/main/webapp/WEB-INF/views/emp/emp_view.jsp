<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<th>EMPNO</th>
			<td>${emp_view.empno}</td>
		</tr>
		<tr>
			<th>ENAME</th>
			<td>${emp_view.ename}</td>
		</tr>
		<tr>
			<th>JOB</th>
			<td>${emp_view.job}</td>
		</tr>
		<tr>
			<th>MGR</th>
			<td>${emp_view.mgr}</td>
		</tr>
		<tr>
			<th>HIREDATE</th>
			<td>${emp_view.hiredate}</td>
		</tr>
		<tr>
			<th>SAL</th>
			<td>${emp_view.sal}</td>
		</tr>
		<tr>
			<th>COMM</th>
			<td>${emp_view.comm}</td>
		</tr>
		<tr>
			<th>DEPTNO</th>
			<td>${emp_view.deptno}</td>
		</tr>
	</table>
</body>
</html>