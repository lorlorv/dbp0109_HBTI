package model;

public class Group {

	private int group_id;
	private String name;
	private String create_date;
	private String icon;
	private String descr;
	
	public Group(int group_id, String name, String descr, String icon) {
		this.group_id = group_id;
		this.name = name;
		this.descr = descr;
		this.create_date = icon;
	}
}
