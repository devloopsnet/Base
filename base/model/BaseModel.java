package com.base.model;

public class BaseModel {
    public int status;
    public String message;
    public String error;


    public BaseModel() {
    }

    public Data data;

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public Data getData() {
        return data;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {

    }
}
