package com.sist.web.dao;
import java.util.*;
import com.sist.web.entity.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationDAO extends JpaRepository<LocationEntity, Integer>{

	@Query(value="SELECT * FROM location ORDER BY no ASC "
			+ "LIMIT 0,4",nativeQuery=true)
	public List<LocationEntity> locationMainData();
}
