package com.cooklab.support_form.model;

public enum SFSource {
	SUPPORTFORM((byte) 1, "官網表單"),
	RESPONDER((byte) 2, "人工建單"),
    TEXTCHAT((byte) 3, "文字客服");

    private final Byte value;
    private final String desc;

    SFSource(Byte value, String desc) {
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
