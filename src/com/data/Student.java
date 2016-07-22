package com.data;

public class Student {
	private String stuNumber;
	private String stuName;
	private String stuSex;
	private int stuAge;
	private String stuClass;
	private String stuCall;
	
	private float stuSQL;
	private float stuJava;
	private float stuSystem;
	private float stuEnglish;
	private float stuPE;
	private float stuComputer;
	
	public Student(String number,String name,String sex,int age,String clas,String call,
			float sql,float java,float system,float english,float pe,float computer){
		this.stuNumber = number;
		this.stuName = name;
		this.stuSex = sex;
		this.stuAge = age;
		this.stuClass = clas;
		this.stuCall = call;
		
		this.stuSQL = sql;
		this.stuJava = java;
		this.stuSystem = system;
		this.stuEnglish = english;
		this.stuPE = pe;
		this.stuComputer = computer;
	}

	public String getStuNumber() {
		return stuNumber;
	}

	public String getStuName() {
		return stuName;
	}

	public String getStuSex() {
		return stuSex;
	}

	public int getStuAge() {
		return stuAge;
	}

	public String getStuClass() {
		return stuClass;
	}

	public String getStuCall() {
		return stuCall;
	}

	public float getStuSQL() {
		return stuSQL;
	}

	public float getStuJava() {
		return stuJava;
	}

	public float getStuSystem() {
		return stuSystem;
	}

	public float getStuEnglish() {
		return stuEnglish;
	}

	public float getStuPE() {
		return stuPE;
	}

	public float getStuComputer() {
		return stuComputer;
	}	
}
