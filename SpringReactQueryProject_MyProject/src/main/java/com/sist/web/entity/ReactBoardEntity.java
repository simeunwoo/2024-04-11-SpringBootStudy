package com.sist.web.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.Data;

/*
no int AI PK 
name varchar(50) 
subject varchar(2000) 
content text 
pwd varchar(10) 
regdate datetime 
hit int
 */
@Entity(name="reactBoard")
@Data
public class ReactBoardEntity {

	@Id
	private int no;
	
	private String name;
	private String subject;
	private String content;
	
	@Column(insertable=true,updatable=false)
	private String pwd;
	
	@Column(insertable=true,updatable=false)
	private String regdate;
	
/*	@PrePersist
	public String regdate()
	{
		return new
	}*/
}
