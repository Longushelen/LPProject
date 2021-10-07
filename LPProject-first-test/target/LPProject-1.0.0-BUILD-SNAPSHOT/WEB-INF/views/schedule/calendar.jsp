<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="com.LP.LPProject.bean.*"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>

<%
Calendar cal = Calendar.getInstance(); 
int y = cal.get(Calendar.YEAR); // 현재 연도
int m = cal.get(Calendar.MONTH); // 현재 월 
int d = cal.get(Calendar.DATE);

int preMonthDay = cal.getActualMaximum(Calendar.DATE); // 이전 달 마지막 날짜 
int curMonthWeek = cal.get(Calendar.DAY_OF_WEEK); // 현재 월의 1일의 요일 (현재 9월의 1일 값: 수요일, 4)
int endDay = cal.getActualMaximum(Calendar.DATE); // 현재 월의 마지막 날
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//ko" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<script src="https://kit.fontawesome.com/546b6cf0a8.js" crossorigin="anonymous"></script>
<link rel="stylesheet" type=text/css href="/LPProject/resources/style_calendar.css" />
<title>Main</title>
</head>

<%

	String today_year = request.getParameter("year");
	String today_month = request.getParameter("month");
%>
<body>
	<!-- 로고영역 -->
	<div class="leftBox">
		<div class="logo">로고영역</div>

		<!-- 현재 연/월 -->
		<div class="year-month">
			<%=y%>&nbsp;<%=m+1%>&nbsp;<%=d%>
			<%=today_year%>&nbsp; <%=today_month %>	
		</div>
		<!-- 연간 이벤트 d-day-->
		<div class="box-dDay">d-day 영역</div>
		<input type="date">
		<!-- 검색창 -->
		<div class="box-search">
			<i class="fas fa-search" style="overflow: visible;"></i>
			<form action="#" name="frm-search">
				<!-- autocompleate: 자동완성 허용 -->
				<input type="search" name="search" autocomplete="on" >
			</form>
		</div>

		<!-- 위젯 창 -->
		<div class="box-widget">
			위젯 영역
		</div>
		<iframe src = "/schedule/wizet/wizet"></iframe>	

	</div>
	

	<table>

		<!-- 	 달력 요일  --> 
			<tr>
			<td><div class="dayOfWeek">일</div></td>
			<td><div class="dayOfWeek">월</div></td>
			<td><div class="dayOfWeek">화</div></td>
			<td><div class="dayOfWeek">수</div></td>
			<td><div class="dayOfWeek">목</div></td>
			<td><div class="dayOfWeek">금</div>
			<td><div class="dayOfWeek">토</div>
			</tr>

		<c:forEach var="dateList" items="${dateList}" varStatus="date_status"> 
			<!-- 
			<c:choose>
				<c:when test="${dateList.value =='today'}">
				<td id ="오늘 스타일 지정">
					<div >
						<button type="button" class="dateBtn">
							<div class="nextDate"> ${dateList.date} </div>
						</button>
					</div>
				</td>
				</c:when>
				<c:when test="${date_status.index == 6}">
				<td id ="토요일 스타일 지정">
					<div >
						<button type="button" class="dateBtn">
							<div class="nextDate">${dateList.date} </div>
						</button>
					</div>
				</td>
				</c:when>
				
				<c:when test="${date_status.index == 7}">
				<td id ="일요일 스타일 지정">
					<div >
						<button type="button" class="dateBtn">
							<div class="nextDate">${dateList.date} </div>
						</button>
					</div>
				</td>
				</c:when>
				
				<c:otherwise>
				
				<td id ="그 외">
					<div >
						<button type="button" class="dateBtn">
							<div class="nextDate">${dateList.date} </div>
						</button>
					</div>
				</td>
				
				</c:otherwise>
			</c:choose>
			 -->
			 <div >
				<button type="button" class="dateBtn">
					<div class="nextDate">${dateList.date} </div>
				</button>
			</div>
		</c:forEach>

	</table>
	

	<!-- 오른쪽바 -->
	<div class="rightBox">
		<!-- 세팅 버튼 -->
		<div class="emptyBox"></div>
		<div class="wrapRightBtn">
		<button class="btn-setting">
			<i class="fas fa-cog"></i>
		</button>
		<!-- 전 월 이동 -->
		<button class="btn-prevMonth">
			<i class="fas fa-angle-up"></i>
		</button>
		<!-- 현재 날짜 이동 -->
		<button class="btn-thisMonth">
			<i class="far fa-calendar-minus"></i>
		</button>
		<!-- 다음 월 이동 -->
		<button class="btn-nextMonth">
			<i class="fas fa-angle-down"></i>
		</button>
		</div>
	</div>

</body>
</html>