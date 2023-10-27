package com.cooklab.news.model;

import java.sql.Timestamp;

public class NewsDTO {
	private String newsTitle;
	private String newsContent;
	private Timestamp newsTime;

	public NewsDTO() {
		super();
	}

	public NewsDTO(String newsTitle, String newsContent, Timestamp newsTime) {
		super();
		this.newsTitle = newsTitle;
		this.newsContent = newsContent;
		this.newsTime = newsTime;
	}

	public String getNewsTitle() {
		return newsTitle;
	}

	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}

	public String getNewsContent() {
		return newsContent;
	}

	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}

	public Timestamp getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Timestamp newsTime) {
		this.newsTime = newsTime;
	}

}
