<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${res == 'fail'}">
		<script>
			alert("[WRONG ID] ID IS INCORRECT")
		</script>
	</c:if>
	
	<h1>Member Modify</h1>
	<form action="${cp}/member/memModify" method="post">
		ID : <input type="text" name="memId" ><br />
		PW : <input type="password" name="memPw" ><br />
		REPW : <input type="password" name="memRePw" ><br />
		EMAIL : <input type="text" name="memEmail" ><br />
		NickName : <input type="text" name="memNickName" maxlength="10" > <br />
		
		NAME : <input type="text" name=memName ><br /> 
		GENDER : <input type="text" name="memGender" maxlength="5"><br />
		Date of Birthday: <input type="text" name=memDoB ><br /> 
		<input type="submit" value="Join" ><br />
		<input type="reset" value="Cancel" ><br />
		<input type="submit" value="Modify" >
		<input type="reset" value="Cancel" >
	</form>
	<a href="${cp}">Go to Main</a>
	<a href="${cp}">Go to My Schedule</a>
</body>
</html>