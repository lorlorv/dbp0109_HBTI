package model;

import java.sql.Date;

public class ChallengePost {
	private int post_id;
	private Date write_date;
	private String content;
	private String image;
	private int like_btn;
	private int group_id;
	private String writer_id;
	private String writer_name;

	public ChallengePost(int post_id, String writer_name, String writer_id, String content, String image, int like_btn) {
		this.post_id = post_id;
		this.writer_name = writer_name;
		this.writer_id = writer_id;
		this.content = content;
		this.image = image;
		this.like_btn = like_btn;
	}
	
	//add에 쓰이는 생성자
	public ChallengePost(String writer_name, String writer_id, String content, String image) {
		this.writer_name = writer_name;
		this.writer_id = writer_id;
		this.content = content;
		this.image = image;
	}
	
	public ChallengePost(int post_id, String content, String image) {
		this.post_id = post_id;
		this.content = content;
		this.image = image;
	}

	public int getPost_id() {
		return post_id;
	}

	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}

	public Date getWrite_date() {
		return write_date;
	}

	public void setWrite_date(Date write_date) {
		this.write_date = write_date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getLike_btn() {
		return like_btn;
	}

	public void setLike_btn(int like_btn) {
		this.like_btn = like_btn;
	}

	public int getGroup_id() {
		return group_id;
	}

	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}

	public String getWriter_id() {
		return writer_id;
	}

	public void setWriter_id(String writer_id) {
		this.writer_id = writer_id;
	}
	
	public String getWriter_name() {
		return writer_name;
	}

	public void setWriter_name(String writer_name) {
		this.writer_name = writer_name;
	}
}
