package com.cooklab.hashtag.model;

import java.sql.Date;

public class HashtagVO implements java.io.Serializable{
	private Integer hashtagNO;
	private String hashtangName;
	private Integer searchCount;
	private Integer useCount;
	private Date createStamp;
	
	public Integer getHashtagNO() {
		return hashtagNO;
	}
	public void setHashtagNO(Integer hashtagNO) {
		this.hashtagNO = hashtagNO;
	}
	public String getHashtangName() {
		return hashtangName;
	}
	public void setHashtangName(String hashtangName) {
		this.hashtangName = hashtangName;
	}
	public Integer getSearchCount() {
		return searchCount;
	}
	public void setSearchCount(Integer searchCount) {
		this.searchCount = searchCount;
	}
	public Integer getUseCount() {
		return useCount;
	}
	public void setUseCount(Integer useCount) {
		this.useCount = useCount;
	}
	public Date getCreateStamp() {
		return createStamp;
	}
	public void setCreateStamp(Date createStamp) {
		this.createStamp = createStamp;
	}
	@Override
	public String toString() {
		return "HashtagVO [hashtagNO=" + hashtagNO + ", hashtangName=" + hashtangName + ", searchCount=" + searchCount
				+ ", useCount=" + useCount + ", createStamp=" + createStamp + "]";
	}
	
}
