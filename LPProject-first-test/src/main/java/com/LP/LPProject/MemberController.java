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

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.LP.LPProject.bean.Member;
import com.LP.LPProject.bean.CalendarBean;
import com.LP.LPProject.service.CalendarService;
import com.LP.LPProject.service.MemberService;

@Controller
@RequestMapping("/member") 
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MemberService service;


	
	// [ �α��� ]------------------------------------------------------------------------
	@RequestMapping(value="/memLogin", method=RequestMethod.POST)
	public ModelAndView memLogin(Member member, HttpSession session, HttpServletResponse response) {

		Member m = service.memberSearch(member);
		ModelAndView mav = new ModelAndView();
		
		if(m == null) {
			mav.addObject("res", "fail");
			mav.setViewName("/home/home");
		} else {
			session.setAttribute("member", m); // ���� ����
			
			Cookie cookie = new Cookie("memId", m.getMemId()); // ��Ű ����
			cookie.setMaxAge(5*60); // seconds (��) ����
			response.addCookie(cookie);

			mav.setViewName("redirect:/calendar/calendar");
		} 
	return mav;		
	}
	
	@RequestMapping("/memJoinForm")
	public String memJoinForm(Member member) { return "/member/memJoinForm"; }

	@RequestMapping(value="/memJoin", method=RequestMethod.POST)
	public ModelAndView memJoin(Member member) {
		// sql�� ����
		int result = service.memberRegister(member);
		
		ModelAndView mav = new ModelAndView();
		
		if(result == 0) {
			mav.addObject("res", "fail");
			mav.setViewName("/member/memJoinForm");
		}
		else {
			// ModelAndView ��ü�� ���� ���� ����
			
			mav.addObject("res", "success");
			mav.setViewName("/member/memJoinOk");
		}
		return mav;
	}

	@RequestMapping(value="/memLogout")
	public String memLogout(Member member, HttpSession session, @CookieValue(value="memId", required=false) Cookie cookie) {
			
		if(cookie != null) {
			cookie.setMaxAge(0); // ��Ű ����
		}
		
		session.invalidate(); // ���� ����
		
		return "/member/memLogout";
	}
	
	@RequestMapping("/memModifyForm")
	public ModelAndView memModifyForm(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		
		// �α����ߴ� ������ �����Ǿ��ִٸ�
		Member member = (Member)session.getAttribute("member");
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("member", service.memberSearch(member));
		mav.setViewName("/schedule/schedule");
		
		return mav;
	}
	
	@RequestMapping(value="/memModify", method=RequestMethod.POST)
	public ModelAndView memModify(Member member, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
			
		Member mem = service.memberModify(member);
		
		if(mem == null) {
			mav.addObject("res", "fail");
			mav.setViewName("/member/memModifyForm");
		}
		else {
			
			// ���� ������ ���� ���� �Ӽ��� �߰� 
			// (Ȥ�ó� �߰��� modify�� ����� ����ڿ��� ������ �� ���� �� �����ϱ�)
			session.setAttribute("member", mem);
			
			mav.addObject("res", "success");
			
			mav.addObject("memAft", mem);
			mav.setViewName("/member/memModifyOk");
		}
		
		return mav;
	}
	
	
	@RequestMapping("/memDeleteForm")
	public ModelAndView memDeleteForm(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		
		Member member = (Member) session.getAttribute("member");
		mav.addObject("member", member);
		mav.setViewName("/member/memDeleteForm");
		
		return mav;
	}
	
	@RequestMapping(value="/memDelete", method=RequestMethod.POST)
	public ModelAndView memDelete(Member member, HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		int result = service.memberRemove(member);
		
		if(result == 0) {
			mav.addObject("res", "fail");
			mav.setViewName("/member/memDeleteForm");
		}
		else {
			HttpSession session = request.getSession();
			session.invalidate();
			
			mav.addObject("res", "success");
			mav.setViewName("/member/memDeleteOk");
		}
		return mav;
	}

}
