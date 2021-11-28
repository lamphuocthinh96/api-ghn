package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class BaseResponse<T> {

    private boolean success;

    private int status;

    private String message;

    private T data;

    public static <T> BaseResponse success(T data) {
        BaseResponse<T> response = new BaseResponse<>();
        response.setData(data);
        response.setMessage("Thành công");
        response.setSuccess(true);
        response.setStatus(0);
        return response;
    }
}
