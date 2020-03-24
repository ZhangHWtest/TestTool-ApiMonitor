package com.apimonitor.httpclient.entity.model;


import lombok.Data;

@Data
public class FindMonitorForm {

    private String monitorName;
    private Integer pageNum;
    private Integer pageSize;
}
