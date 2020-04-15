package com.github.wangcanfeng01.freedom.ragnaros.vo;

/**
 * 功能说明：图表项
 * Created in 2020/4/15-23:23
 *
 * @author wangcanfeng
 * @since 1.2
 */
public class NameAndValue {
    private  String name;

    private  String value;

    public NameAndValue() {
    }

    public NameAndValue(String name, String value) {
        this.name = name;
        this.value = value;
    }


    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
