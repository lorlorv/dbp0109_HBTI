package model;

import java.io.Serializable;

@SuppressWarnings("serial")

public class HBTI implements Serializable{
	private String name;
	private int hbti_id;
	private int goodHbti;
	private int badHbti;
	private String icon;
	private String good_descr;
	private String bad_descr;
	private String exercise;
	
	
	public HBTI() {
		super();
	}
	
	
	public HBTI(String name, int hbti_id, int goodHbti, int badHbti, String icon, String good_descr, String bad_descr, String exercise) {
		super();
		this.name = name;
		this.hbti_id = hbti_id;
		this.goodHbti = goodHbti;
		this.badHbti = badHbti;
		this.icon = icon;
		this.good_descr = good_descr;
		this.bad_descr = bad_descr;
		this.exercise = exercise;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHbti_id() {
		return hbti_id;
	}
	public void setHbti_id(int hbti_id) {
		this.hbti_id = hbti_id;
	}
	public int getGoodHbti() {
		return goodHbti;
	}
	public void setGoodHbti(int goodHbti) {
		this.goodHbti = goodHbti;
	}
	public int getBadHbti() {
		return badHbti;
	}
	public void setBadHbti(int badHbti) {
		this.badHbti = badHbti;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getGood_descr() {
		return good_descr;
	}
	public void setGood_descr(String good_descr) {
		this.good_descr = good_descr;
	}
	public String getBad_descr() {
		return bad_descr;
	}
	public void setBad_descr(String bad_descr) {
		this.bad_descr = bad_descr;
	}


	public String getExercise() {
		return exercise;
	}


	public void setExercise(String exercise) {
		this.exercise = exercise;
	}
	
	
	
	

}