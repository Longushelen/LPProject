package com.LP.LPProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.LP.LPProject.bean.Member;
import com.LP.LPProject.dao.MemberDao;

@Service
public class MemberService {
	
	@Autowired
	MemberDao dao;
	
	public int memberRegister(Member member) {
		return dao.memberInsert(member);
	}
	public Member memberSearch(Member member) {
		return dao.memberSelect(member);
	}
	public Member memberModify(Member member) {
		int result = dao.memberUpdate(member);
		if(result == 0) { return null; }
		return member;
	}
	public int memberRemove(Member member) {
		return dao.memberDelete(member);
	}
}
