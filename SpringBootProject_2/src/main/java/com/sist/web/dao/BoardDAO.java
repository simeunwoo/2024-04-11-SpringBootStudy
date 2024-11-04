package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// * => Entity / 직접 지정 => Data
// JPA => 장점 : 적은 코딩으로 사용 가능, 단점 : 가독성 Bad
// => 우리나라 : MyBatis 선호 (MyBatis : JPA = 8:2)
@Repository
public interface BoardDAO extends JpaRepository<BoardEntity, Integer>{

	public BoardEntity findByNo(int no); // 상세 보기
	// SELECT no,name,subject,content,regdate,hit FROM board WHERE no=?
	// insert(save), update(save), delete(delete), count
	
	@Query(value="SELECT no,name,subject,content,date_format(regdate,'%Y-%m-%d') as regdate,hit "
			+ "FROM board ORDER BY no DESC "
			+ "LIMIT :start,10",nativeQuery=true)
	public List<BoardData> boardListData(@Param("start") int start);
}
