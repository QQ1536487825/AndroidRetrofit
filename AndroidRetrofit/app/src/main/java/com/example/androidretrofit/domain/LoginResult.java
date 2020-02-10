package com.example.androidretrofit.domain;

public class LoginResult {

    /**
     * success : true
     * code : 10000
     * message : 评论成功:这是评论内容
     * data : null
     */

    private boolean success;
    private int code;
    private String message;
    private Object data;

    @Override
    public String toString() {
        return "LoginResult{" +
                "success=" + success +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
