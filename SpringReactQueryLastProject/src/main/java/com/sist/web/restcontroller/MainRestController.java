package com.sist.web.restcontroller;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins="*") // 모든 포트(port) 허용
public class MainRestController {

	@Autowired
	private FoodHouseDAO fDao;
	@Autowired
	private RecipeDAO rDao;
	
	@GetMapping("/main")
	public ResponseEntity<Map> main_data()
	{
		Map map=new HashMap();
		try
		{
			List<FoodHouseVO> fList=fDao.foodHitTop9();
			List<RecipeEntity> rList=rDao.recipeMainData();
			map.put("fList", fList);
			map.put("rList", rList);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
		// HttpStatus.OK => 200 => 정상 수행
	}
}
