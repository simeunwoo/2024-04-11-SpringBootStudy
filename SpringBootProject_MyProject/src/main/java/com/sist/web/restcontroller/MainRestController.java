package com.sist.web.restcontroller;
import java.util.*;
import com.sist.web.entity.*;
import com.sist.web.dao.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*")
public class MainRestController {

	@Autowired
	private HotelDAO hDao;
	@Autowired
	private LocationDAO lDao;
	
	@GetMapping("main/main_react")
	public Map MainData(){
		Map map=new HashMap();
		List<HotelVO> hList=hDao.HotelTop4();
		List<LocationEntity> lList=lDao.locationMainData();
		HotelEntity hvo=hDao.findByHno(77);
		
		map.put("hList", hList);
		map.put("lList", lList);
		map.put("hvo", hvo);
		
		return map;
	}
	
}
