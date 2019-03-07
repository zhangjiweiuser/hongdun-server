package com.hongdun.entity;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;

/**
 * Created by robin on 2016/10/10.
 */
public class Response<T> {

    /**
     * 成功
     */
    private static final int CODE_SUCCESS = 0;
    /**
     * 失败
     */
    private static final int CODE_ERROR = -1;

    private static Map<String, String> messageMap = new ConcurrentHashMap<>();

    static {
        messageMap.put("0", "");
        messageMap.put("-1", "Bad Request");
        messageMap.put("6", "invalid params");
        messageMap.put("400", "Bad Request");
        messageMap.put("401", "NotAuthorization");
        messageMap.put("405", "Method Not Allowed");
        messageMap.put("406", "Not Acceptable");
        messageMap.put("500", "Internal Server Error");

        messageMap.put("1000", "[服务器]运行时异常");
        messageMap.put("1001", "[服务器]空值针异常");
        messageMap.put("1002", "[服务器]数据类型转换异常");
        messageMap.put("1003", "[服务器]IO异常");
        messageMap.put("1004", "[服务器]未知方法异常");
        messageMap.put("1005", "[服务器]数组越界异常");
        messageMap.put("1006", "[服务器]网络异常");

        messageMap.put("2001", "文件大小超限");
    }

    /**
     * 成功
     */
    private boolean success;
    /**
     * 返回信息
     */
    private T msg;
    /**
     * code码
     */
    private int code;

    public Response() {
    }

    public Response(boolean success, T msg, int code) {
        this.success = success;
        this.msg = msg;
        this.code = code;
    }

    public Response(boolean success, T msg) {
        this.success = success;
        this.msg = msg;
    }
    public static Response retParam(int code, Object data) {
        if (6 == code && null != data) {
            return new Response(0 == code, data.toString(), code);
        } else {
            return new Response(0 == code, messageMap.get("" + code) == null ? JSON.toJSONString(data) : messageMap.get("" + code), code);
        }
    }

    public static <T> Response<T> error(int code, T message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setSuccess(false);
        response.setMsg(message);
        return response;
    }

    public static <T> Response<T> success(int code, T message) {
        Response<T> response = new Response<>();
        response.setCode(code);
        response.setSuccess(true);
        response.setMsg(message);
        return response;
    }

    public static <T> Response<T> success(T message) {
        return success(0, message);
    }

    public Response Response(boolean success, T msg) {
        this.success = success;
        this.msg = msg;
        return this;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getMsg() {
        return msg;
    }

    public void setMsg(T msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
