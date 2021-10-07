<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>

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

<body>
	<!-- 로고영역 -->
	<div class="leftBox">
		<div class="logo"></div>

		<!-- 현재 연/월 -->
		<div class="wrapDateBtn">
				<input type="text" class="dateBtn" value="${today_value.search_year} ${today_value.search_month+1}">
		</div>
		<!-- 연간 이벤트 d-day-->
		<div class="box-dDay">
			<div> </div>
			<div> </div>
			<div> </div>
		</div>
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
		<jsp:include page= "./wizet/wizet.jsp"></jsp:include>

	</div>

	<div class="calendarBox">
		<div class="week">
			<!-- 달력 요일 -->
			<div class="dayOfWeek">일</div>
			<div class="dayOfWeek">월</div>
			<div class="dayOfWeek">화</div>
			<div class="dayOfWeek">수</div>
			<div class="dayOfWeek">목</div>
			<div class="dayOfWeek">금</div>
			<div class="dayOfWeek">토</div>
		</div>

		<!-- 달력 숫자 -->
		<div class="date">		
			<c:forEach var="dateList" items="${dateList}" varStatus="date_status"> 
				<div class="wrapDateBtn">
					<button type="button" class="dateBtn">
						<div class="prevDate">${dateList.date}</div>
					</button>
				</div>
			</c:forEach>
		</div>
	</div>
	<!-- calendarBox -->
	

	<!-- 오른쪽바 -->
	<div class="rightBox">
		<!-- 세팅 버튼 -->
		<div class="emptyBox"></div>
		<div class="wrapRightBtn">
		<button class="btn-setting">
			<i class="fas fa-cog"></i>
		</button>

		<!-- 전 월 이동 -->
		<button class="btn-prevMonth" >
			<a href="./calendar?year=${today_value.before_year}&month=${today_value.before_month}">
				<i class="fas fa-angle-up"></i>
			</a>
		</button>
		
		<!-- 현재 날짜 이동 -->
		<button class="btn-thisMonth">
			<a href="./calendar?year=${today_value.today_year}&month=${today_value.today_month}">
				<i class="far fa-calendar-minus"></i>
			</a>
		</button>
		<!-- 다음 월 이동 -->
		<button class="btn-nextMonth">
			<a href="./calendar?year=${today_value.after_year}&month=${today_value.after_month}">
				<i class="fas fa-angle-down"></i>
			</a>
		</button>
		
		
		</div>
	</div>

</body>
</html>