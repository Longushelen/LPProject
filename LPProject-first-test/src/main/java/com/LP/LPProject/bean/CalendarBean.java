package com.LP.LPProject.bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class CalendarBean {

	String year="" ;
	String month="";
	String date="";
	String value=""; // 검색할 날짜 저장 객체
	String schedule="";
	String schedule_detail="";
	
	public CalendarBean() {
		
	}
	public CalendarBean(String year, String month, String date) { // 날짜 정보 넣기 
		this.year = year;
		this.month = month;
		this.date = date;
	}
	public CalendarBean(String year, String month, String date, String value ) { // 날짜 검색 ???
		this.year = year;
		this.month = month;
		this.date = date;
		this.value = value;
	}
	public CalendarBean(String year, String month, String date, String schedule, String schedule_detail) { // 일정 불러오기 	
		this.year = year;			
		this.month = month; 
		this.date = date;
		this.schedule = schedule;
		this.schedule_detail = schedule_detail;
	}

	@Override
	public String toString() {
		return "calendar [year=" + year + ", month=" + month + ", date=" + date + "]";
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	public String getSchedule() {
		return schedule;
	}
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	public String getSchedule_detail() {
		return schedule_detail;
	}
	public void setSchedule_detail(String schedule_detail) {
		this.schedule_detail = schedule_detail;
	}
	
	public Map<String, Integer> day_info(CalendarBean calendar) {
		
		Map<String, Integer> dayData = new HashMap<String, Integer>();
		Calendar cal = Calendar.getInstance();
		Calendar todayCal = Calendar.getInstance();

		SimpleDateFormat ysdf = new SimpleDateFormat("yyyy");
		SimpleDateFormat msdf = new SimpleDateFormat("MM");	

		int today_year = Integer.parseInt(ysdf.format(todayCal.getTime()));
		int today_month = Integer.parseInt(msdf.format(todayCal.getTime())); // 인덱스 : 0 ~ 11 
		cal.set(Integer.parseInt(calendar.getYear()), Integer.parseInt(calendar.getMonth()), 1);
		
		int startDay = cal.getMinimum(Calendar.DATE);
		int endDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		int weekIndex = cal.get(Calendar.DAY_OF_WEEK);
				
		int search_year = Integer.parseInt(calendar.getYear());
		int search_month = Integer.parseInt(calendar.getMonth()); // index : 0 ~ 11 <- today_month == (search_month) 함수
		Map<String, Integer> changeDay_info = changeDay_info(search_year,search_month);
		
		// month change
		int today = -1; 
		//if (today_year == search_year && today_month == search_month) {
			SimpleDateFormat dsdf = new SimpleDateFormat("dd");
			today = Integer.parseInt(dsdf.format(todayCal.getTime()));
		//}
		//search_month = search_month - 1; // 초기화 <- today_month == (search_month) 함수 
		
		dayData.put("weekIndex", weekIndex);
		dayData.put("startDay", startDay);
		dayData.put("endDay", endDay);
		dayData.put("today_year", today_year);
		dayData.put("today_month", today_month-1);
		dayData.put("today", today);
		dayData.put("search_year", search_year);
		dayData.put("search_month", search_month);
		dayData.put("before_year", changeDay_info.get("before_year"));
		dayData.put("before_month", changeDay_info.get("before_month"));
		dayData.put("after_year", changeDay_info.get("after_year"));
		dayData.put("after_month", changeDay_info.get("after_month"));
		
		
		return dayData;		
	}
	//이전달 다음달 및 이전년도 다음년도
	private Map<String, Integer> changeDay_info(int search_year, int search_month){
		
		Map<String, Integer> changeDay_data = new HashMap<String, Integer>();
		int before_year = search_year;
		int before_month = search_month - 1;
		int after_year = search_year;
		int after_month = search_month + 1;
		
		if(before_month < 0){
			before_month = 11;
			before_year = search_year - 1;
		}
		
		if(after_month > 11){
			after_month = 0;
			after_year = search_year + 1;
		}
		
		changeDay_data.put("before_year", before_year);
		changeDay_data.put("before_month", before_month);
		changeDay_data.put("after_year", after_year);
		changeDay_data.put("after_month", after_month);
		
		return changeDay_data;
	}
}
