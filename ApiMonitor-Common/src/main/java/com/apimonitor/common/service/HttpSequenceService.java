package com.apimonitor.common.service;



import com.apimonitor.common.model.HttpSequence;
import com.apimonitor.common.model.HttpSequenceLog;
import com.apimonitor.common.model.HttpSystem;

import java.util.List;
import java.util.Map;

public interface HttpSequenceService {
	public HttpSequence getByGuid(String guid);

	public void archived(String guid);
	public void updateEnabled(HttpSequence httpSequence);
	public void insert(HttpSequence httpSequence);
	public void update(HttpSequence httpSequence);
	public List<Map<String, Object>> getMonitorList();
	public List<Map<String, Object>> getLogByGuid(String guid);
	public boolean addHttpSystem(String group);
	public List<HttpSystem> getAllSystem();
	
	public void insertLog(HttpSequenceLog httpSequenceLog);
	public void deleteLog(String pguid);
	
	public void cleanLog(int day);
}
