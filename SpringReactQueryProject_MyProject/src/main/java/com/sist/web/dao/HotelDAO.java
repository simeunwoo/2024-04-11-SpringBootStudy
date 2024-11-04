package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface HotelDAO extends JpaRepository<HotelEntity, Integer>{

	@Query(value="SELECT hno,name,price,address,poster,score "
			+ "FROM hotel ORDER BY score DESC "
			+ "LIMIT 0,5",nativeQuery=true)
	public List<HotelVO> HotelTop4();
	
	public HotelEntity findByHno(int hno);
	// SELECT * FROM hotel WHERE hno=?
	
	@Query(value="SELECT hno,name,price,address,checkin,checkout,"
			+ "poster,likecount,hit,score "
			+ "FROM hotel ORDER BY hno ASC "
			+ "LIMIT :start,10",nativeQuery=true)
	public List<HotelVO> hotelListData(@Param("start") int start);
	
	@Query(value="SELECT hno,name,price,address,checkin,checkout,"
			+ "poster,likecount,hit,score "
			+ "FROM hotel WHERE address LIKE CONCAT('%',:address,'%') ORDER BY hno ASC "
			+ "LIMIT :start,10",nativeQuery=true)
	public List<HotelVO> hotelFindData(@Param("start") int start,@Param("address") String address);
	
	@Query(value="SELECT CEIL(COUNT(*)/10.0) "
			+ "FROM hotel "
			+ "WHERE address LIKE CONCAT('%',:address,'%')")
	public int hotelFindTotalPage(@Param("address") String address);
	
	/*
	HNO int 
NAME text 
PRICE int 
ADDRESS text 
CHECKIN text 
CHECKOUT text 
LOCATION text 
POSTER text 
IMAGES text 
RDAYS text 
JJIMCOUNT int 
LIKECOUNT int 
HIT int 
SCORE double 
REPLYCOUNT text
	 */
}
