package com.apimonitor.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.apimonitor.common.http.client.HttpSequenceHandle;
import com.apimonitor.common.entity.modelForm.APITestForm;
import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.entity.modelForm.HttpRequestForm;
import com.apimonitor.common.entity.HttpRequestLog;
import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.entity.HttpSequence.MonitorType;
import com.apimonitor.common.entity.HttpSystem;
import com.apimonitor.common.entity.modelForm.MonitorFrequency;
import com.apimonitor.common.service.HttpRequestService;
import com.apimonitor.common.service.HttpSequenceService;
import com.apimonitor.common.util.GuidGenerator;
import com.github.pagehelper.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MonitorEditController {

	protected static final Logger LOGGER = LoggerFactory.getLogger(MonitorEditController.class);

	@Autowired
	private HttpSequenceService httpSequenceService;
	
	@Autowired
	private HttpRequestService httpRequestService;


    @RequestMapping("/addSingleMonitor")
    public String addSingleMonitor(ModelMap map) {
    	HttpRequestForm form = new HttpRequestForm();
    	map.put("form", form);
    	return "monitor_add_single";
    }
    
	@ResponseBody
    @RequestMapping(value="/addGroup",method= RequestMethod.POST)
    public boolean addGroup(@RequestParam("systemName") String systemName){
		return httpSequenceService.addHttpSystem(systemName);
    }

	@ResponseBody
    @RequestMapping(value="/getGroups")
    public List<HttpSystem> addGroup(){
		return httpSequenceService.getAllSystem();
    }


    @RequestMapping("/editSingleMonitor")
    public String editSingleMonitor(ModelMap map, HttpServletRequest request) {
    	String guid = request.getParameter("guid");
    	HttpSequence httpSequence = httpSequenceService.getByGuid(guid);
    	List<HttpRequest> list = httpRequestService.getHttpRequestListByPguid(guid);
    	HttpRequest httpRequest = list.get(0);
    	HttpRequestForm form = HttpRequestForm.getHttpRequestForm(httpSequence, httpRequest);
    	map.put("form", form);
    	return "monitor_add_single";
    }

    @RequestMapping("/editSequenceMonitor")
    public String addSequenceMonitor(ModelMap map, HttpServletRequest request) {
    	String guid = request.getParameter("guid");
    	HttpSequence httpSequence;
    	if(StringUtil.isEmpty(guid)){
    		httpSequence = new HttpSequence();
    		httpSequence.setType(MonitorType.SEQUENCE);
    	}else{

        	httpSequence = httpSequenceService.getByGuid(guid);
        	List<HttpRequest> list = httpRequestService.getHttpRequestListByPguid(guid);
        	httpSequence.setHttpRequest(list);
    	}
    	map.put("form", httpSequence);
    	List<HttpSystem> systems = httpSequenceService.getAllSystem();
    	map.put("systems", systems);
    	map.put("frequencys", MonitorFrequency.values());
    	return "monitor_add_sequence";
    }


	
	@ResponseBody
    @RequestMapping(value="/testApi",method= RequestMethod.POST)
    public ArrayList<APITestForm> testApi(@ModelAttribute HttpSequence httpSequence){
		try{
			 ArrayList<APITestForm> list = new ArrayList<APITestForm>();
	    	//执行接口探测
	    	HttpSequenceHandle httpSequenceHandle = new HttpSequenceHandle(httpSequence);
	    	httpSequenceHandle.execute();
	    	List<HttpRequest> requests = httpSequence.getHttpRequest();
	    	for(int i=0;i<requests.size();i++){
	    		HttpRequest request = requests.get(i);
	    		HttpRequestLog log = httpSequenceHandle.httpRequestLogList.get(i);
		    	APITestForm form = new APITestForm();
		    	form.setCostTime(log.getCostTime());
		    	form.setLog(log.getLog());
		    	form.setResponseBody(log.getResponseBody());
		    	form.setSort(request.getSort());
		    	form.setStatus(log.isStatus()?"成功":"失败");
		    	form.setStatusCode(log.getStatusCode());
		    	form.setUrl(request.getUrl());
		    	HashMap<String, String> map = request.getVariablesMap();
				StringBuffer varables = new StringBuffer();
				if(map!=null){
					for (String key : map.keySet()) {
						varables.append(key).append("=").append(httpSequenceHandle.getVariables(key));
					}
				}
		    	form.setVariables(varables.toString());
		    	list.add(form);
	    	}
	    	return list;
		}catch(Exception e){
			LOGGER.error("错误日志",e);
			return null;
		}
	}
}
