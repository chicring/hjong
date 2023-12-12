package com.hjong.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class FileException extends RuntimeException{
    private String msg;

    public FileException(String msg) {
        super();

        this.msg = msg;

    }
}