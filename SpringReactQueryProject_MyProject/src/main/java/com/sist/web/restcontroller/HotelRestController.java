package com.sist.web.restcontroller;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class HotelRestController {

	@Autowired
	private HotelDAO hDao;
	
	@GetMapping("/hotel/list/{page}")
	public ResponseEntity<Map> hotel_list(@PathVariable("page") int page)
	{
		Map map=new HashMap();
		try
		{
			int rowSize=12;
			int start=(rowSize*page)-rowSize;
			List<HotelVO> hList=hDao.hotelListData(start);
			int count=(int)hDao.count();
			int totalpage=(int)(Math.ceil(count/12.0));
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("hList", hList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("/hotel/find/{page}/{address}")
	public ResponseEntity<Map> hotel_find(@PathVariable("page") int page,
			@PathVariable("address") String address)
	{
		Map map=new HashMap();
		try
		{
			int rowSize=12;
			int start=(page-1)*rowSize;
			List<HotelVO> hList=hDao.hotelFindData(start, address);
			int totalpage=hDao.hotelFindTotalPage(address);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("hList", hList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("hotel/detail/{hno}")
	public ResponseEntity<HotelEntity> hotel_detail(@PathVariable("hno") int hno)
	{
		HotelEntity vo=new HotelEntity();
		try
		{
			vo=hDao.findByHno(hno);
			vo.setHit(vo.getHit()+1);
			hDao.save(vo);
			
			vo=hDao.findByHno(hno);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
}
