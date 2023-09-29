package com.cooklab.hashtag.model;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "hashtag")
public class HashtagVO implements java.io.Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hashtag_no", insertable = false ,updatable = false)
	private Integer hashtagNO;
	@Column(name = "hashtag_name")
	private String hashtangName;
	@Column(name = "search_count")
	private Integer searchCount;
	@Column(name = "use_count")
	private Integer useCount;
	@Column(name = "created_timestamp", insertable = false ,updatable = false)
	private Timestamp createdStamp;
	
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
	public Timestamp getCreateStamp() {
		return createdStamp;
	}
	public void setCreateStamp(Timestamp createdStamp) {
		this.createdStamp = createdStamp;
	}
	@Override
	public String toString() {
		return "HashtagVO [hashtagNO=" + hashtagNO + ", hashtangName=" + hashtangName + ", searchCount=" + searchCount
				+ ", useCount=" + useCount + ", createStamp=" + createdStamp + "]";
	}
	
}
