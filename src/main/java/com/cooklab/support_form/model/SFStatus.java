package com.cooklab.support_form.model;

public enum SFStatus {
	UNCHECKED(0, "未處理"),
	ONPROCESS(1, "處理中"),
    CASECLOSED(2, "已結案");

    private final int value;
    private final String desc;

    SFStatus(int value, String desc) {
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
