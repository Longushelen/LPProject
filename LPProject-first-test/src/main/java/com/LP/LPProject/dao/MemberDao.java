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

import com.LP.LPProject.bean.Member;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository 
public class MemberDao  {

	private ComboPooledDataSource dataSource; // 커넥팅 풀 연결
	private JdbcTemplate template; // ${} 를 사용해서 정보 가져오기
	@Autowired // 회원가입
	public MemberDao(ComboPooledDataSource dataSource) {template = new JdbcTemplate(dataSource);}
	
	public int memberInsert(Member member) {
		String sql = "insert into member (memId, memPw, memRePw, memName, memGender, memDoB, memEmail, memNickName) values (?, ?, ?, ?, ?, ?, ?, ?)";
		int result = template.update(sql, member.getMemId(), member.getMemPw(), member.getMemRePw(), member.getMemName(), member.getMemGender(), member.getMemDoB(), member.getMemEmail(), member.getMemNickName());
		return result;
	}
	// 회원정보 변경
	public int memberUpdate(Member member) {
		String sql = "update member set memPw=?, memRePw=?, memEmail = ?, memName = ?, memGender = ? memDoB = ? where memId = ?";
		int result = template.update(sql, member.getMemPw(), member.getMemRePw(), member.getMemEmail(), member.getMemName(), member.getMemGender(), member.getMemDoB(), member.getMemId());
		return result;
	}
	// 회원정보 가져오기
	public Member memberSelect(Member member) {

		String sql = "select * from member where memId = ? and memPw = ?";
		List<Member> members;

		members = template.query(sql, new RowMapper<Member>() {

			@Override
			public Member mapRow(ResultSet rs, int rowNum) 
					throws SQLException {

				Member mem = new Member();
				mem.setMemId(rs.getString("memId"));
				mem.setMemPw(rs.getString("memPw"));
				mem.setMemEmail(rs.getString("memEmail"));
				mem.setMemName(rs.getString("memName"));
				mem.setMemNickName(rs.getString("memNickName"));
				mem.setMemDoB(rs.getString("memDoB"));
				mem.setMemGender(rs.getString("memGender"));
				return mem;
			}

		}, member.getMemId(), member.getMemPw());
		
		if(members.isEmpty()) return null;
		return members.get(0);
	}
	// 회원 탈퇴 신청
	public int memberDelete(Member member) {
		String sql = "delete from member where memId = ? and memPw = ?";
		int result = template.update(sql, member.getMemId(), member.getMemPw());
		return result;
	}
}
