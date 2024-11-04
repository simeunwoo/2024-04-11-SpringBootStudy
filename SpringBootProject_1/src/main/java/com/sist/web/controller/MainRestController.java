package com.sist.web.controller;
import java.util.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// 자동 처리 : ObjectMapper => JSON
@RestController
public class MainRestController {

	@GetMapping("/names")
	public List<String> main_names()
	{
		List<String> list=new ArrayList<>();
		list.add("홍길동");
		list.add("홍길순");
		list.add("홍길자");
		
		return list;
	}
}
