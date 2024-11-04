package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.entity.*;
import com.sist.web.dao.*;

@RestController
@CrossOrigin(origins="*")
public class HotelRestController {

	@Autowired
	private HotelDAO hDao;
	
	@GetMapping("hotel/list_react")
	public Map hotel_list(int page)
	{
		   Map map=new HashMap();
		   int start=(page*10)-10;
		   List<HotelVO> list=hDao.hotelListData(start);
		   int count=(int)hDao.count();
		   int totalpage=(int)(Math.ceil(count/10.0));
		   final int BLOCK=10;
		   int startPage=((page-1)/BLOCK*BLOCK)+1;
		   int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		   if(endPage>totalpage)
			   endPage=totalpage;
		   
		   map.put("list", list);
		   map.put("curpage", page);
		   map.put("totalpage", totalpage);
		   map.put("startPage", startPage);
		   map.put("endPage", endPage);
		   
		   return map;
	}
	
	@GetMapping("hotel/detail_react")
	public HotelEntity hotel_detail(int hno)
	{
		HotelEntity vo=hDao.findByHno(hno);
		vo.setHit(vo.getHit()+1);
		hDao.save(vo);
		vo=hDao.findByHno(hno);
		
		return vo;
	}
	
	@GetMapping("hotel/find_react")
	public Map hotel_find(int page,String address)
	{
		Map map=new HashMap();
		int start=(page*10)-10;
		List<HotelVO> list=hDao.hotelFindData(start,address);
		int totalpage=hDao.hotelFindTotalPage(address);
		final int BLOCK=10;
		int startPage=((page-1)/BLOCK*BLOCK)+1;
		int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
		if(endPage>totalpage)
			endPage=totalpage;
				   
		map.put("list", list);
		map.put("curpage", page);
		map.put("totalpage", totalpage);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("address", address);
		   
		return map;
		   
	   }
}
