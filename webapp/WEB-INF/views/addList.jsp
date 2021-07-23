<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="add" method="get">
	<table border="1">
		<colgroup>
			<col style="width: 40px;">
			<col style="width: 120px;">
			<col style="width: 80px;">
			<col style="width: 120px;">
		</colgroup>
		<tr>
			<td>이름</td>
			<td><input type="text" name="name" value=""></td>
			<td>비밀번호</td>
			<td><input type="password" name="password" value=""></td>
		</tr>
		
		<tr>
			<td colspan="4" height="150" ><textarea name ="content"></textarea></td>
		</tr>
		<tr>
			<td colspan = "4"><button type ="submit">확인</button></td>
		</tr>
	</table>
	
	</form>
	
	
	
	<c:forEach items="${requestScope.gList }" var="gBookVo">
	
		<br>
		<table border="1">
			<colgroup>
				<col style="width: 30px;">
				<col style="width: 90px;">
				<col style="width: 300px;">
				<col style="width: 60px;">
			</colgroup>
			<tr>
				<td>${gBookVo.no }</td>
				<td>${gBookVo.name }</td>
				<td>${gBookVo.regdate }</td>
				<td><a href="${pageContext.request.contextPath}/deleteForm?no=${gBookVo.no }">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4" height="60">첫번째 방명록내용<br>${gBookVo.content }</td>
			</tr>
		</table>
	
	</c:forEach>

</body>
</html>