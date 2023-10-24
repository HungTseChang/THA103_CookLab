package com.cooklab.support_form.model;

public enum SFStatus {
	UNCHECKED((byte) 0, "未處理"),
	ONPROCESS((byte) 1, "處理中"),
    CASECLOSED((byte) 2, "已結案");

    private final Byte value;
    private final String desc;

    SFStatus(Byte value, String desc) {
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
