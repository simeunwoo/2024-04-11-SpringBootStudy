package com.sist.web.entity;

public interface BoardData {

	public int getNo();
	public String getName();
	public String getSubject();
	public String getContent();
	public String getRegdate();
	public int getHit();
	// SELECT no,name,subject,content,regdate,hit FROM board
}
