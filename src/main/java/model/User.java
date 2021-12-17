package model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable {
	private String user_id;
	private String password;
	private String name;
	private String descr;
	private String image;
	private int group_id;
	private int hbti_id;
	private String login_date;
	private boolean is_leader;

	public User() {
	}

	public User(String user_id, String password) {
		super();
		this.user_id = user_id;
		this.password = password;
	}

	public User(String user_id, String password, String name, String descr, String image) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.name = name;
		this.descr = descr;
		this.image = image;
	}

	// group_id x
	public User(String user_id, String password, String name, String descr, String image, int hbti_id) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.name = name;
		this.descr = descr;
		this.image = image;
		this.hbti_id = hbti_id;
	}

	public User(String user_id, String password, String name, String descr, String image, int group_id, int hbti_id) {
		super();
		this.user_id = user_id;
		this.password = password;
		this.name = name;
		this.descr = descr;
		this.image = image;
		this.group_id = group_id;
		this.hbti_id = hbti_id;
	}

	public User(String user_id, String name, String image, String login_date) {
		this.user_id = user_id;
		this.name = name;
		this.image = image;
		this.login_date = login_date;
		is_leader = false;
	}

	public String getLogin_date() {
		return login_date;
	}

	public void setLogin_date(String login_date) {
		this.login_date = login_date;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public int getHbti_id() {
		return hbti_id;
	}

	public void setHbti_id(int hbti_id) {
		this.hbti_id = hbti_id;
	}

	public boolean isIs_leader() {
		return is_leader;
	}

	public void setIs_leader(boolean is_leader) {
		this.is_leader = is_leader;
	}

	public boolean matchPassword(String password) {
		if (password == null) {
			return false;
		}
		return this.password.equals(password);
	}

	public boolean isSameUser(String userid) {
		return this.user_id.equals(userid);
	}

	@Override
	public String toString() {
		return "User [userId=" + user_id + ", password=" + password + ", name=" + name + ", userdesc=" + descr
				+ ", img= " + image + ", is_leader= " + is_leader + "]";
	}
}
