package org.example.basis;

public class R<T> {
    private Integer code;
    private String message;
    private Boolean success;
    private T data;

    public R(T data) {
        this(200,"",true,data);
    }

    public R(Integer code, String message, Boolean success, T data) {
        this.code = code;
        this.message = message;
        this.success = success;
        this.data = data;
    }


    public static <T> R<T> setData(T data) {
        return new R<>(data);
    }
}
