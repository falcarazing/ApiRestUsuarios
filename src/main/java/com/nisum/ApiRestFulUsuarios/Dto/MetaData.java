package com.nisum.ApiRestFulUsuarios.Dto;

import lombok.Data;

import java.util.Date;

@Data
public class MetaData {
    private String status="true";
    private Integer http_code=200;
    private String date_time=new Date().toString();
    private String message="success";
}
