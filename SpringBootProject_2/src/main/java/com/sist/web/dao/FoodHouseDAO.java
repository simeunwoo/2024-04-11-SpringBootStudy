package com.sist.web.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;

import com.sist.web.entity.FoodHouseData;
import com.sist.web.entity.FoodHouseEntity;
/*
 * 	MySQL
 * 	1. 페이징 => LIMIT 시작, 개수
 * 	2. LIKE => '%'||?||'%' => CONCAT('%',?,'%')
 * 	3. DATE => DATETIME => sysdate : now()
 * 	4. NVL => isnull
 * 
 * 	(MySQL = MariaDB) : 사실상 동일 => 3306 (driver 동일)
 * 
 * 	=> 오라클 / MySQL
 * 	=> NUMBER / int, double
 * 	=> VARCHAR2 / varchar
 * 	=> CLOB / text
 * 	=> DATE / datatime
 */
@Repository
public interface FoodHouseDAO extends JpaRepository<FoodHouseEntity, Integer>{

	// 목록 출력
	@Query(value="SELECT fno,poster,name FROM project_food_house ORDER BY fno ASC "
			+ "LIMIT :start,12",nativeQuery=true)
	public List<FoodHouseData> foodListData(@Param("start") int start); // #{start}
	
	
	// 상세 보기
	public FoodHouseEntity findByFno(int fno); // SELECT * FROM project_food_house WHERE fno=?
	
	// Hit 증가 = update (save())
	
	// 검색
	
	// CRUD => 게시판
	
}
