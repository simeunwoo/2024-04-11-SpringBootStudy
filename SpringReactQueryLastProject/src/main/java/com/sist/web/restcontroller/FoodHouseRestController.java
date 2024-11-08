package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.sist.web.dao.*;
import com.sist.web.entity.*;

@RestController
@CrossOrigin(origins="*")
public class FoodHouseRestController {

	@Autowired // 스프링에서 메모리 할당이 된 경우에만 사용 가능
	private FoodHouseDAO fDao;
	/*
	 * 	클래스는 반드시 메모리 할당 후에 사용 => new
	 * 		new의 단점 : 결합성이 강한 프로그램 (영향력 多)
	 * 
	 * 	1. <bean> : 라이브러리 클래스를 메모리 할당하는 경우 => 공통으로 사용되는 경우
	 * 		@Bean
	 * 	2. 어노테이션 이용 : 개발자가 주로 사용하는 방식
	 * 		@Component : 일반 클래스 => ~Manager : Open API
	 * 		@Repository : DAO => 데이터베이스 연동
	 * 			Oracle / MySQL / ElasticSearch / MongoDB / NoSQL
	 * 			한개의 테이블 연동
	 * 			라이브러리 : MyBatis / JPA
	 * 		@Service : BI (통합 => DAO가 여러개인 경우에 주로 통합하여 사용)
	 * 			게시판 / 댓글 / 예약 / 찜하기 ...
	 * 		@Controller : 웹 파일 제어 => 최근에는 거의 사라짐 (유지 보수에는 아직 많이 존재)
	 * 			최근 : Front와 Back이 분리
	 * 				Front : React/Vue => Router
	 * 				Back : 데이터를 JSON을 변경하여 전송
	 * 		@RestController : JSON으로 전송 => 다른 언어와 연동
	 * 			SpringBoot <===> Kotlin (모바일) / Flutter (Dart)
	 * 		@ControllerAdvice : 공통 예외 처리
	 * 		@RestControllerAdvice : 공통 예외 처리
	 * 
	 * 	1) web.xml / server.xml
	 * 		web.xml : 어떤 프레임워크를 사용할지 => Spring
	 * 			연결 파일 => application_*.xml
	 * 		server.xml : 경로 확인 => <Context> => SpringFramework
	 * 	======================================================== SpringBoot : 임베디드 tomcat 자체 처리
	 * 	2) 동작
	 * 		사용자 =======> DispatcherServlet ===> HandlerMapping (@Controller / @RestController)
	 * 		       요청
	 * 		       .do
	 * 		       /
	 * 
	 * 		@Controller : 사용자가 요청한 URI를 찾는다
	 * 			URI : @GetMapping / @PostMapping / @RequestMapping
	 * 			RestApi
	 * 				1> Get => SELECT
	 * 				2> Post => INSERT
	 * 				3> Put => UPDATE
	 * 				4> Delete => DELETE
	 * 				====================> return을 전송 => JSON
	 * 													=> 파일명 ~> Forward : 데이터 전송
	 * 													=> redirect ~> Redirect : 이전 화면으로 이동 (데이터 전송 불가능)
	 *  			=> ?를 사용하지 않는다
	 *  			=> /10
	 *  			=> board / list / {page}
	 *  			=> PathVariable
	 *  			=> 에러와 동시에 데이터를 전송하는 방식
	 *  				=> ResponseEntity => 실무에 활용
	 *
	 *	React-Query
	 *		{isLoading,error,data,reflush:함수명}=useQuery()
	 */
	
	// 맛집 목록
	@GetMapping("/food/list/{page}")
	public ResponseEntity<Map> food_list(@PathVariable("page") int page)
	{
		Map map=new HashMap();
		try
		{
			int rowSize=12;
			int start=(rowSize*page)-rowSize;
			List<FoodHouseVO> fList=fDao.foodListData(start);
			int count=(int)fDao.count();
			int totalpage=(int)(Math.ceil(count/12.0));
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("fList", fList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
			// {} => []
			/*
			 * 	return
			 * 		=> Map => {} : 여러개를 동시에 모아서 전송
			 * 		=> List => [{},{},{}...]
			 * 		=> VO => {}
			 */
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			// 404, 500, 415, 405, 400 ...
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK); // 200 (정상 수행)
	}
	
	// React에서 => /food/find/${page}/${address}
	@GetMapping("/food/find/{page}/{address}")
	public ResponseEntity<Map> food_find(@PathVariable("page") int page,
			@PathVariable("address") String address)
	{
		Map map=new HashMap();
		try
		{
			int rowSize=12;
			int start=(page-1)*rowSize;
			List<FoodHouseVO> fList=fDao.foodFindData(start, address);
			int totalpage=fDao.foodFindTotalPage(address);
			
			final int BLOCK=10;
			int startPage=((page-1)/BLOCK*BLOCK)+1;
			int endPage=((page-1)/BLOCK*BLOCK)+BLOCK;
			if(endPage>totalpage)
				endPage=totalpage;
			
			map.put("fList", fList);
			map.put("curpage", page);
			map.put("totalpage", totalpage);
			map.put("startPage", startPage);
			map.put("endPage", endPage);
		}catch(Exception ex)
		{
			// {isLoading,isError,error,data}
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
			/*
			 * 	(***) 500 : 소스 에러
			 * 	(***) 404 : 파일이 없는 경우
			 * 	(***) 400 : Bad Request => 데이터 전송이 틀린 경우
			 * 	415 : 한글 변환 문제
			 * 	403 : 접근 거부
			 */
		}
		
		return new ResponseEntity<>(map,HttpStatus.OK);
	}
	
	@GetMapping("food/detail/{fno}")
	public ResponseEntity<FoodHouseEntity> food_detail(@PathVariable("fno") int fno)
	{
		FoodHouseEntity vo=new FoodHouseEntity();
		try
		{
			vo=fDao.findByFno(fno);
			vo.setHit(vo.getHit()+1); // 조회 수 증가
			fDao.save(vo);
			
			vo=fDao.findByFno(fno);
		}catch(Exception ex)
		{
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>(vo,HttpStatus.OK);
	}
}
