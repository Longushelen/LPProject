package com.LP.LPProject.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.LP.LPProject.bean.CalendarBean;
import com.LP.LPProject.bean.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class CalendarDao{

	private ComboPooledDataSource dataSource;
	private JdbcTemplate template;
	@Autowired
	public CalendarDao(ComboPooledDataSource dataSource) {template = new JdbcTemplate(dataSource);}
	//-----------------------------------------------------------------------------------------------
	
	// 일정 추가	
	public int addSchedule(CalendarBean schedule) {
		String sql = "insert into dateData values (?, ?, ?, ?, ?)";
		int result = template.update(sql, schedule.getYear(), schedule.getMonth(), schedule.getDate(), schedule.getSchedule(), schedule.getSchedule_detail());
		return result;
	}
	// 일정 검색
	public CalendarBean searchSchedule(CalendarBean schedule) {
		String sql = "select * from dateData where schedule = ?";
		List<CalendarBean> dates = template.query(sql, new RowMapper<CalendarBean>() {
			@Override
			public CalendarBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				CalendarBean dd = new CalendarBean();
				dd.setYear(rs.getString("year"));
				dd.setMonth(rs.getString("month"));
				dd.setDate(rs.getString("date"));
				dd.setSchedule(rs.getString("schedule"));
				dd.setSchedule_detail(rs.getString("schedule_detail"));
				return dd;
			}
		}, schedule.getYear(), schedule.getMonth(), schedule.getDate(), schedule.getSchedule(), schedule.getSchedule_detail()); 
		if(dates.isEmpty()) {return null;}
		return dates.get(0); // 검색 값을 반환?
	}
	// 일정 수정
	public CalendarBean modifySchedule(CalendarBean schedule) {
		String sql = "update * from schedule where schedule = ?";
		List<CalendarBean> schedule_data = template.query(sql, new RowMapper<CalendarBean>() {
			@Override
			public CalendarBean mapRow(ResultSet rs, int rowNum) throws SQLException {
				CalendarBean schedule = new CalendarBean();
				schedule.setYear(rs.getString("year"));
				schedule.setMonth(rs.getString("month"));
				schedule.setDate(rs.getString("date"));
				schedule.setSchedule(rs.getString("schedule"));
				schedule.setSchedule_detail(rs.getString("schedule_detail"));
				return schedule;
			}
		}, schedule.getYear(), schedule.getMonth(), schedule.getDate(), schedule.getSchedule(), schedule.getSchedule_detail()); 
		// 가지고 온 후에 수정하기
		if(schedule_data.isEmpty()) {return null;}
		return schedule_data.get(0);
	}
	// 일정 삭제
	public int deleteSchedule(CalendarBean dateData) {
		String sql = "delete from dateData where year = ? and month = ? and date = ? and schedule = ? and schedule_detail";
		int result = template.update(sql, dateData.getYear(), dateData.getMonth(), dateData.getDate(), dateData.getSchedule());
		return result;
	}

	
}
