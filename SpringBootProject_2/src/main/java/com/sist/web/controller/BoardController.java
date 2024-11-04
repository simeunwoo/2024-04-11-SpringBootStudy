package com.sist.web.controller;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BoardController {

	@Autowired
	private BoardDAO dao;
	
	@GetMapping("board/list")
	public String board_list(String page,Model model)
	{
		if(page==null)
			page="1";
		int curpage=Integer.parseInt(page);
		int rowSize=10;
		int start=(rowSize*curpage)-rowSize;
		
		List<BoardData> list=dao.boardListData(start);
		int count=(int)dao.count();
		int totalpage=(int)(Math.ceil(count/10.0));
		
		model.addAttribute("list", list);
		model.addAttribute("curpage", curpage);
		model.addAttribute("totalpage", totalpage);
		
		return "board/list";
	}
	
	@GetMapping("board/insert")
	public String board_insert()
	{
		return "board/insert";
	}
	
	@PostMapping("board/insert_ok")
	public String board_insert_ok(BoardEntity vo)
	{
		dao.save(vo);
		
		return "redirect:/board/list";
	}
	/*
	 * 	Spring MVC 구조를 이용하고 있다
	 * 	       =======
	 *         미리 등록 (web.xml) => 없어졌다 (= 자동으로 세팅됨)
	 *         클래스 등록도 자동 설정
	 *         application-context.xml ... => application.properties / application.yml로 간편화 
	 * 
	 * 	사용자 요청 ===(board/detail)===> DispatcherServlet ===> HandlerMapping
	 * 		===> @Controller 클래스 안에 (@GetMapping/@PostMapping) 밑에 있는 메소드 수행
	 *		===> ViewResolver ===(Model=>전송)===> HTML
	 */
	
	@GetMapping("board/detail")
	public String board_detail(int no,Model model)
	{
		BoardEntity vo=dao.findByNo(no);
		vo.setHit(vo.getHit()+1);
		dao.save(vo);
		// 조회 수 증가
		vo=dao.findByNo(no);
		model.addAttribute("vo", vo);
		
		return "board/detail";
	}
	
	@GetMapping("board/delete")
	public String board_delete(int no,Model model)
	{
		model.addAttribute("no", no);
		
		return "board/delete";
				
	}
	
	@PostMapping("board/delete_ok")
	@ResponseBody // 비밀 번호 관련 / script, html, json
	public String board_delete_ok(int no,String pwd)
	{
		BoardEntity vo=dao.findByNo(no);
		
		String result="";
		if(pwd.equals(vo.getPwd()))
		{
			result="<script>"
					+ "location.href=\"/board/list\""
					+ "</script>";
			dao.delete(vo);
			// insert / update / delete
			// save() 안에 insert, update 존재
			// save => no 값 있는 경우
		}
		else
		{
			result="<script>"
					+ "alert(\"Password Fail\");"
					+ "history.back();"
					+ "</script>";
		}
		
		return result;
	}
	
	@GetMapping("board/update")
	public String board_update(int no,Model model)
	{
		BoardEntity vo=dao.findByNo(no);
		
		model.addAttribute("vo", vo);
		
		return "board/update";
	}
	
	@PostMapping("board/update_ok")
	@ResponseBody // 비밀 번호 관련 => @RestController에서도 사용 가능 (@ResponseBody를 사용하는 지는 모르겠음)
	public String board_update_ok(BoardEntity vo)
	{
		String result="";
		BoardEntity dbvo=dao.findByNo(vo.getNo());
		if(vo.getPwd().equals(dbvo.getPwd()))
		{
			vo.setHit(dbvo.getHit()); // 조회 수 유지 목적
			dao.save(vo);
			
			// 수정
			result="<script>"
					+ "location.href=\"/board/detail?no="+vo.getNo()+"\""
					+ "</script>";
		}
		else
		{
			// back()
			result="<script>"
					+ "alert(\"Password Fail\");"
					+ "history.back();"
					+ "</script>";
		}
		
		return result;
	}
}
