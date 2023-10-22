package com.cooklab.support_form.model;

public enum SFSource {
	SUPPORTCENTER(1, "官網表單"),
	GENERAL(2, "人工建單"),
    MAILBOX(3, "客服信箱");

    private final int value;
    private final String desc;

    SFSource(int value, String desc) {
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
