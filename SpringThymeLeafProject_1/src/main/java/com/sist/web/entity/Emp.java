package com.sist.web.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

// 클래스명과 테이블명이 동일하면 => table명을 지정하지 않아도 된다
@Entity
@Data
public class Emp {

	@Id
	private int empno;
	private int sal,deptno;
	private String ename,job,hiredate,mgr,comm;
	// csv => 1) 값 중에 null이 존재 2) 날짜 => 문자열 (text)
}
