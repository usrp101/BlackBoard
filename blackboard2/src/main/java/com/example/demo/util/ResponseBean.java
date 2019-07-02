package com.example.demo.util;

import java.io.Serializable;

public class ResponseBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String code;
    private Object object;
    private String description;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}