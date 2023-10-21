package com.cooklab.support_form.model;

public enum SFCategory {
	RECIPE(1, "食譜問題"),
	FORUM(2, "討論區問題"),
    ORDER(3, "訂單問題"),
	PRODUCT(4, "商品問題");

    private final int value;
    private final String desc;

    SFCategory(int value, String desc) {
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
