package com.cooklab.support_form.model;

public enum SFSource {
	SUPPORTCENTER((byte) 1, "官網表單"),
	GENERAL((byte) 2, "人工建單"),
    MAILBOX((byte) 3, "客服信箱");

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
