<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<h1>memJoinOk</h1>
	
	ID: ${member.getMemId()} <br/>
	PW: ${member.memPw} <br/>
	RePw: ${member.memRePw} <br/>
	Name: ${member.memName} <br/>
	Gender: ${member.memGender} <br/>
	Date of Birthday: ${member.memDoB} <br/>
	Email: ${member.memEmail} <br/>
	NickName: ${member.memNickName}
	 
	<P>The time on the server is ${serverTime}. </P> 
	<a href="${cp}">Go to Main</a>
</body>
</html>