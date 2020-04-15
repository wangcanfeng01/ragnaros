package com.github.wangcanfeng01.freedom.ragnaros.vo;

import java.util.List;

/**
 * 功能说明：
 * Created in 2020/4/15-23:58
 *
 * @author wangcanfeng
 * @since 2.0.0
 */
public class ChartInfo {

    private List<String> dataNames;
    private List<String> dataValues;

    public List<String> getDataNames() {
        return dataNames;
    }

    public void setDataNames(List<String> dataNames) {
        this.dataNames = dataNames;
    }

    public List<String> getDataValues() {
        return dataValues;
    }

    public void setDataValues(List<String> dataValues) {
        this.dataValues = dataValues;
    }
}
