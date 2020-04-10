package com.github.wangcanfeng01.freedom.ragnaros.utils;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 * @author wangcanfeng
 * @description 小数计算器
 * @Date Created in 9:26-2019/6/5
 */
public class CalculateUtils {

    /**
     * 默认的精确度
     */
    public static final int DEFAULT_PRECISION = 2;

    private CalculateUtils() {
        // 私有构造函数
    }


    /**
     * 功能描述: x+y,并做四舍五入，保留指定位数的小数
     *
     * @param x         被加数
     * @param y         加数
     * @param precision 精确到小数点后几位
     * @return:java.lang.String
     * @since: v1.0
     * @Author:wangcanfeng
     * @Date: 2019/6/5-10:23
     */
    public static <T extends Number> String addition(T x, T y, int precision) {
        //  x和y都不能为空
        if (null == x || null == y) {
            return null;
        }
        BigDecimal dX = new BigDecimal(x.toString());
        BigDecimal dY = new BigDecimal(y.toString());
        BigDecimal result = dX.add(dY).setScale(precision, RoundingMode.HALF_UP);
        return result.toString();
    }

    /**
     * 功能描述: x-y,并做四舍五入，保留指定位数的小数
     *
     * @param x         被减数
     * @param y         减数
     * @param precision 精确到小数点后几位
     * @return:java.lang.String
     * @since: v1.0
     * @Author:wangcanfeng
     * @Date: 2019/6/5-10:24
     */
    public static <T extends Number> String minus(T x, T y, int precision) {
        //  x和y都不能为空
        if (null == x || null == y) {
            return null;
        }
        BigDecimal dX = new BigDecimal(x.toString());
        BigDecimal dY = new BigDecimal(y.toString());
        BigDecimal result = dX.subtract(dY).setScale(precision, RoundingMode.HALF_UP);
        return result.toString();
    }

    /**
     * 功能描述: x*y,并做四舍五入，保留指定位数的小数
     *
     * @param x         被乘数
     * @param y         乘数
     * @param precision 精确到小数点后几位
     * @return:java.lang.String
     * @since: v1.0
     * @Author:wangcanfeng
     * @Date: 2019/6/5-10:24
     */
    public static <T extends Number> String multiplication(T x, T y, int precision) {
        //  x和y都不能为空
        if (null == x || null == y) {
            return null;
        }
        BigDecimal dX = new BigDecimal(x.toString());
        BigDecimal dY = new BigDecimal(y.toString());
        BigDecimal result = dX.multiply(dY).setScale(precision, RoundingMode.HALF_UP);
        return result.toString();
    }

    /**
     * 功能描述: x/y,并做四舍五入，保留指定位数的小数
     *
     * @param x         被除数
     * @param y         除数
     * @param precision 精确到小数点后几位
     * @return:java.lang.String
     * @since: v1.0
     * @Author:wangcanfeng
     * @Date: 2019/6/5-10:24
     */
    public static <T extends Number> String division(T x, T y, int precision) {
        // x和y都不能为空
        if (null == x || null == y) {
            return null;
        }
        // 除数不能为0,为0直接返回对应精度的0值
        if (Objects.equals(y, 0)) {
            return appendZero(0, precision);
        }
        BigDecimal dX = new BigDecimal(x.toString());
        BigDecimal dY = new BigDecimal(y.toString());
        BigDecimal result = dX.divide(dY, precision, RoundingMode.HALF_UP);
        return result.toString();
    }


    /**
     * 功能描述: 对一个数做精度处理
     *
     * @param x         源数据
     * @param precision 精确到小数点后几位
     * @return:java.lang.String
     * @since: v1.0
     * @Author:wangcanfeng
     * @Date: 2019/6/5-10:10
     */
    public static <T extends Number> String round(T x, int precision) {
        // 这里不支持源数据为空
        if (null == x) {
            return null;
        }
        return addition(x, 0, precision);
    }


    /**
     * 功能描述: 给一个对象后面补足0，一般做界面显示用
     *
     * @param x         给这个x后面拼接上0
     * @param precision 精确到小数点后几位
     * @return:java.lang.String
     * @since: v1.0
     * @Author:wangcanfeng
     * @Date: 2019/6/5-9:50
     */
    public static <T extends Number> String appendZero(T x, int precision) {
        // 如果精度小于等于0，则直接源数据返回
        if (precision <= 0) {
            return x + "";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(x).append(".");
        for (int i = 0; i < precision; i++) {
            sb.append("0");
        }
        return sb.toString();
    }


}