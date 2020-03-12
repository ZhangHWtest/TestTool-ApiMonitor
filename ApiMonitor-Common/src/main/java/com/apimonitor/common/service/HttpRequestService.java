package com.apimonitor.common.service;



import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.entity.HttpSequence;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface HttpRequestService extends IService<HttpRequest> {

	boolean enableMonitor(String guid);

    boolean deleteMonitor(String guid);
    
    boolean disableMonitor(String guid);
    

    void executeRequest(String guid);
    
    void archivedHttpData(String guid);
    
    void deleteHttpLog(String guid);
    
    public void cleanMonitorLogs(int day);
    
	public List<HttpRequest> getAllHttpRequest();
	
	public HttpRequest getHttpRequestByGuid(String guid);
	
	public List<HttpRequest> getHttpRequestListByPguid(String pguid);

	public void insertHttpRequest(HttpRequest httpRequest);
	public void updateHttpRequest(HttpRequest httpRequest);
	public void updateEnabled(HttpSequence httpSequence);
	

	public List<Map<String, Object>> getHttpRequestLogByPid(String id);
}