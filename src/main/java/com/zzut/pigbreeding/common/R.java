package com.zzut.pigbreeding.common;

import lombok.Data;

@Data
public class R<T> {
    private Integer code;
    private Object data;
    private String msg;

    public  R<T> packing(Object object,String msg,Integer code){
        R<T> result = new R<>();
        result.setData(object);
        result.setMsg(msg);
        result.setCode(code);
        return result;
    }
}
