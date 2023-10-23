package com.cooklab.notify_center.model;

public enum NCRead {
	UNREAD(0, "未讀取"),
	READ(1, "已讀取"),
	HIDDEN(2, "隱藏");

    private final int value;
    private final String desc;

    NCRead(int value, String desc) {
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
