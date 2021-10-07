package com.LP.LPProject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.LP.LPProject.bean.CalendarBean;
import com.LP.LPProject.bean.Member;
import com.LP.LPProject.service.CalendarService;

@Controller
@RequestMapping("/calendar") 

public class CalendarController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@Autowired
	CalendarService service;

	// -------------------------------------------------------------------
	@RequestMapping(value="/insertData", method=RequestMethod.POST)
	public ModelAndView insertData(CalendarBean schedule) {
		// sql문 실행
		int result = service.saveCalendar(schedule);
		
		ModelAndView mav = new ModelAndView();
		
		if(result == 0) {
			mav.addObject("res", "fail");
			mav.setViewName("/schedule/insertData");
		} else {
			mav.addObject("res", "success");
			mav.setViewName("/schedule/schedule");
		}
		return mav;
	}
	// -------------------------------------------------------------------
	@RequestMapping(value = "/calendar", method = RequestMethod.GET)
	public String calendar(Model model, CalendarBean schedule, HttpServletResponse response ){

		Calendar cal = Calendar.getInstance();
		CalendarBean calendarData; // 날짜 삽입 시 (임시 변수)
		
		// 현재 날짜 -> 빈에 넣기
		if(schedule.getDate().equals("") && schedule.getMonth().equals("")){
			schedule = new CalendarBean(String.valueOf(cal.get(Calendar.YEAR)), String.valueOf(cal.get(Calendar.MONTH)),String.valueOf(cal.get(Calendar.DATE)));
		}

		// CalendarBean에 저장된 값 가지고 오기
		Map<String, Integer> today_value = schedule.day_info(schedule);
		List<CalendarBean> dateList = new ArrayList<CalendarBean>();
		
		// 날짜 삽입
		// 1. 빈공간 전월 일자로 채우기 
		int beforemonth = Integer.parseInt(schedule.getMonth()) - 1; // 1. 전월 value
		Calendar cal_beforemonth = Calendar.getInstance(); // 2. 현재 기준 달
		cal_beforemonth.set(Integer.parseInt(schedule.getYear()), beforemonth, 1); // 3. [ 2 <- 1 ] 적용
		int beforemonth_endDay = cal_beforemonth.getActualMaximum(Calendar.DATE); // 4. 전월의 최대 일자 구하기
		
		int index = beforemonth_endDay - today_value.get("weekIndex") + 2; // today_value.get("weekIndex")의 시작 인덱스 1
		for(int i = 1; i < today_value.get("weekIndex"); i++){	
			calendarData= new CalendarBean(schedule.getYear(), String.valueOf(beforemonth), String.valueOf(index));
			dateList.add(calendarData);
			index++;
		}
		// 2. 현재 날짜 삽입
		for (int i = 1; i <= today_value.get("endDay"); i++) {
			calendarData = new CalendarBean(schedule.getYear(), schedule.getMonth(), String.valueOf(i));
			dateList.add(calendarData);			
		}
		// 3. 빈공간 담월 일자로 채우기
		int aftermonth = Integer.parseInt(schedule.getMonth())+1; 
		Calendar cal_aftermonth = Calendar.getInstance(); 
		cal_aftermonth.set(Integer.parseInt(schedule.getYear()), aftermonth, 1);

		index = 7-dateList.size()%7;
		if(dateList.size()%7!=0){
			for (int i = 1; i <= index; i++) {
				calendarData= new CalendarBean(schedule.getYear(), String.valueOf(aftermonth), String.valueOf(i));
				dateList.add(calendarData);
			}
		}

		for(int i = 0; i < dateList.size(); i ++) {
			logger.info(dateList.get(i).toString());
		}
		
		//배열에 담음
		model.addAttribute("dateList", dateList);		//날짜 데이터 배열
		model.addAttribute("today_value", today_value);
		
		return "calendar/calendar";
			
	}
}

