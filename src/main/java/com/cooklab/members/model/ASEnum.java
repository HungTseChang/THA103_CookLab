package com.cooklab.members.model;

	public enum ASEnum {
		PUBLIC(0, "公開"),
		PRIVATE(1, "未公開"),
		DRAFT(2, "草稿"),
		DELETE(3,"隱藏");

	    private final int value;
	    private final String desc;

	    ASEnum(int value, String desc) {
	        this.value = value;
	        this.desc = desc;
	    }

	    public int getValue() {
	        return value;
	    }

	    public String getDesc() {
	        return desc;
	    }
	}

