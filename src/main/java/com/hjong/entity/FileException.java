package com.hjong.entity;

import lombok.Data;

@Data
public class FileException extends RuntimeException{
    private String msg;

    public FileException(String msg) {
        super();

        this.msg = msg;

    }
}