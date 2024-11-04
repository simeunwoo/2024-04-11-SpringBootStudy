package com.sist.web.restcontroller;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin(origins="*")
public class MainRestController {

	@Autowired
	private HotelDAO hDao;
	@Autowired
	private LocationDAO lDao;
	
	@GetMapping("/main")
	public ResponseEntity<Map> main_data()
	{
		Map map=new HashMap();
		try
		{
			List<HotelVO> hList=hDao.HotelTop4();
			List<LocationEntity> lList=lDao.locationMainData();
			map.put("oneData", hList.get(0));
			List<HotelVO> twoData=new ArrayList<HotelVO>();
			for(int i=1;i<=4;i++)
			{
				twoData.add(hList.get(i));
			}
			List<LocationEntity> threeData=new ArrayList<LocationEntity>();
			for(int i=5;i<=8;i++)
			{
				threeData.add(lList.get(i));
			}
			
			map.put("oneData", hList.get(0));
			map.put("twoData", twoData);
			map.put("threeData", threeData);
			map.put("lList", lList);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
}