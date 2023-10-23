package com.cooklab.notify_center.model;

public enum NCType {
	SYSTEM((byte) 0, "系統通知"),
	RECIPE((byte) 1, "食譜通知"),
	FORUM((byte) 2, "討論區通知"),
	SHOP((byte) 3, "商城通知");

    private final Byte value;
    private final String desc;

    NCType(Byte value, String desc) {
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
