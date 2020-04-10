package com.github.wangcanfeng01.freedom.ragnaros.vo;

/**
 * @author wangcanfeng
 * @description 返回结构体
 * @date Created in 16:19-2020/4/7
 * @since 2.0.0
 */
public class RagnarosResponse<T> {
    private String code;

    private String msg;

    private T data;

    public RagnarosResponse() {
        this.data = null;
        this.code = "0";
        this.msg = "success";
    }

    public RagnarosResponse(T data) {
        this.data = data;
        this.code = "0";
        this.msg = "success";
    }

    public static <T> RagnarosResponse<T> ok() {
        return new RagnarosResponse<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}