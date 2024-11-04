package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
/*
NO int 
TITLE text 
POSTER text 
MSG text 
ADDRESS text
 */
@Entity(name="location")
@Data
public class LocationEntity {

	@Id
	private int no;
	private String title,poster,msg,address;
}
