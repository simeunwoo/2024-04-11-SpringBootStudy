package com.sist.web.dao;
import com.sist.web.entity.*;
import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefDAO extends JpaRepository<ChefEntity, String>{

	public ChefEntity findByChef(String chef);
}
