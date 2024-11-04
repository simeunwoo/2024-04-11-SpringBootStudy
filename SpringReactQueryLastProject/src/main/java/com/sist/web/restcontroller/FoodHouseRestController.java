package com.sist.web.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	// 메인 화면 데이터
}
