package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {

	//필드
	@Autowired
	private GuestBookDao guestBookDao;
	
	//생성자
	//메소드(게터세터)
	//메소드(일반)
	
	//리스트+등록폼
	@RequestMapping(value="/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) { 
		System.out.println("[GuestBookController.addList]");
		
		//Dao 열어주기
		//GuestBookDao guestBookDao = new GuestBookDao();
		
		//Dao의 메소드로 데이터 가져오기
		List<GuestBookVo> guestList = guestBookDao.getGuestList();
		System.out.println(guestList);
		
		//ModelAndView 에서 Model 공간에 담기 --> 디스패처서블릿에 전달-> 리퀘스트 어트리뷰트 영역 저장
		model.addAttribute("gList", guestList);
		
		//ModelAndView 에서 View 공간
		return "/WEB-INF/views/addList.jsp";
	}
	
	
	//등록
	@RequestMapping(value="/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String add(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("[GuestBookController.add]");
		//@ModelAttribute 사용시 --> Vo에 기본생성자 확인!
		
		//Dao 열어주기
		//GuestBookDao gBookDao = new GuestBookDao();
		
		//Dao 메소드 이용해서 등록메소드 사용
		int count = guestBookDao.guestInsert(guestBookVo);
		
		//view 리다이렉트
		return "redirect:/addList";
	}
	
	
	//삭제품
	@RequestMapping(value="/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @RequestParam("no") int no) {
		System.out.println("[GuestBookController.deleteForm]");
		
		//ModelAndView 에서 Model 공간에 담기 --> 디스패처서블릿에 전달-> 리퀘스트 어트리뷰트 영역 저장
		//폼으로 전달하고 폼에서 no를 가지고 있다가 서밋할때 딜리트로 보내주기위함
		model.addAttribute("no", no);
		
		//View 공간
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
	
	//삭제
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no, @RequestParam("password") String password) {
		
		System.out.println(no + ", " + password);
	
		//다오열기
		//GuestBookDao gBookDao = new GuestBookDao();
		
		//다오 메소드 이용해서 삭제
		int count = guestBookDao.goodDelete(no, password);
		
		return "redirect:/addList";
	}
	
}
