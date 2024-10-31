package com.example.service;

public class Response <T>{
    private T data;
    private boolean isSuccess;
    private String errorMessage;

    public static<K> Response<K> newSuccess(K data){
        // TODO: 2024/10/31
         Response<K> response = new Response<>();
         response.setData(data);
         response.setSuccess(true);
         return response;
    }

    public static<Void> Response<Void> newFail(String error){
        // TODO: 2024/10/31
        Response<Void> response = new Response<>();
        response.setErrorMessage(error);
        response.setSuccess(false);
        return response;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
