package com.example.springbootdemo01.common;

public class CacheException extends RuntimeException{

    private static final int CACHE_ERROR=9003;

    private static final long serialVersionUID = -2678203134198782909L;

    public static final String MESSAGE = "缓存异常";

    private int code;

    public CacheException() {
        super(MESSAGE);
    }

    public CacheException(String message) {
        super(message);
        this.setCode(CACHE_ERROR);
    }

    public CacheException(int code, String message) {
        super(message);
        this.setCode(code);
    }

    public CacheException(String message, Throwable cause) {
        super(message, cause);
        this.setCode(CACHE_ERROR);
    }

    public CacheException(int code, String message, Throwable cause) {
        super(message, cause);
        this.setCode(code);
    }

    public CacheException(Throwable cause) {
        super(cause);
        this.setCode(CACHE_ERROR);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
