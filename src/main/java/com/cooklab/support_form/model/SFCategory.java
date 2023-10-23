package com.cooklab.support_form.model;

public enum SFCategory {
	RECIPE((byte) 1, "食譜問題"),
	FORUM((byte) 2, "討論區問題"),
    ORDER((byte) 3, "訂單問題"),
	PRODUCT((byte) 4, "商品問題");

    private final Byte value;
    private final String desc;

    SFCategory(Byte value, String desc) {
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
