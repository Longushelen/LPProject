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
	<h1> memDeleteOk </h1>
	<!-- ID: ${mem.memId} <br/> -->
	<!-- JSTL에서 사용하면 자동으로 getter 호출 -->
	
	ID: ${member.getMemId()} <br/>
	PW: ${member.memPw} <br/>
	REPW: ${member.memRePw} <br/>
	Email: ${member.memEmail} <br/>
	NickName: ${member.memNickName}
	
	Name: ${member.memName} <br/>
	Gender: ${member.memGender} <br/>
	Date of Birthday: ${member.memDoB} <br/>
	 
	<P>  The time on the server is ${serverTime}. </P>
	<a href="${cp}">Go to Main</a>
</body>
</html>