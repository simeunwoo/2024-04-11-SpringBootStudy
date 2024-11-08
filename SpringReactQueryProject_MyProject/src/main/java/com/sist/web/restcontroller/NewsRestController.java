package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.sist.web.entity.*;
import com.sist.web.manager.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "*")
public class NewsRestController {
   @Autowired
   private NewsSearchManager mgr;
   
   @GetMapping("/news/find/{fd}")
   public ResponseEntity<Map> news_find(@PathVariable("fd") String fd)
   {
	   Map map=new HashMap();
	   try
	   {
		   List<NewsVO> find=mgr.newsFind(fd);
		   map.put("find", find);
	   }catch(Exception ex)
	   {
		   return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
	   }
	   return new ResponseEntity<>(map,HttpStatus.OK);
   }
}