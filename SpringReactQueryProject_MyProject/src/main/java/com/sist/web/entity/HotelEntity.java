package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

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
@Entity(name="hotel")
@Data
public class HotelEntity {

	@Id
	private int hno;
	private int price,jjimcount,likecount,hit;
	private String name,address,checkin,checkout,location,poster,images;
	private String rdays,replycount;
	private double score;
	
}
