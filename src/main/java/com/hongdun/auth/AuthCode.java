package com.hongdun.auth;

/**
 * 权限类型枚举
 *
 * @author zhang
 * @date 2019-03-01 上午 10:26
 */
public enum AuthCode {
    /**
     * 允许所有权限
     */
    ALL("all", "允许访问", "允许访问"),

    ADDUSER("addUser", "新增用户", "新增用户"),
    DISABLE_USER("disableUser", "暂停用户", "暂停用户"),
    DEL_USER("delUser", "删除用户", "删除用户"),
    RESET_USER("resetUser", "重置用户", "重新启用用户");

    /**
     * 权限编码
     */
    private String authCode;

    /**
     * 权限名称
     */
    private String authName;

    /**
     * 权限描述
     */
    private String authDesc;


    /**
     * 权限构造函数
     *
     * @param authCode 权限编码
     * @param authName 权限名称
     * @param authDesc 权限描述
     */
    AuthCode(String authCode, String authName, String authDesc) {
        this.authCode = authCode;
        this.authName = authName;
        this.authDesc = authDesc;
    }

    /**
     * 权限构造函数
     *
     * @param authCode 权限编码
     * @param authName 权限名称
     */
    AuthCode(String authCode, String authName) {
        this.authCode = authCode;
        this.authName = authName;
    }


    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }

    public String getAuthName() {
        return authName;
    }

    public void setAuthName(String authName) {
        this.authName = authName;
    }

    public String getAuthDesc() {
        return authDesc;
    }

    public void setAuthDesc(String authDesc) {
        this.authDesc = authDesc;
    }
}
