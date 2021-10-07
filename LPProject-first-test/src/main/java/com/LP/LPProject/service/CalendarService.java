package com.LP.LPProject.service;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LP.LPProject.bean.CalendarBean;
import com.LP.LPProject.bean.Member;
import com.LP.LPProject.dao.CalendarDao;
import com.LP.LPProject.dao.MemberDao;

@Service
public class CalendarService {
	
	@Autowired
	CalendarDao dao;
	
	public int saveCalendar(CalendarBean schedule) { // ����
		return dao.addSchedule(schedule);
	}

	public CalendarBean searchCalendar(CalendarBean schedule) { // �˻�
		return dao.searchSchedule(schedule);
	}

	public CalendarBean modifyCalendar(CalendarBean schedule) { // ����
		return dao.modifySchedule(schedule);
	}

	public int removeCalendar(CalendarBean schedule) { // ����
		return dao.deleteSchedule(schedule);
	}
	
}
