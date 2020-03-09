package com.apimonitor.common.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import com.apimonitor.common.service.HttpRequestService;
import com.apimonitor.common.service.HttpSequenceService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/monitor")
public class ApiMonitorController {

	@Autowired
	private HttpSequenceService httpSequenceService;
	
	@Autowired
	private HttpRequestService httpRequestService;
	
//    @RequestMapping("/")
//    public String main(ModelMap map) {
//		List<Map<String,Object>> list = httpSequenceService.getMonitorList();
//		map.addAttribute("monitorList", list);
//    	return "monitor_list";
//    }
    
    @PostMapping("/list")
    public Result monitorList(ModelMap map) {
		List<Map<String,Object>> list = httpSequenceService.getMonitorList();
		map.addAttribute("monitorList", list);
    	return new Result(ResultCode.SUCCESS,list);
    }


	@ResponseBody
	@RequestMapping(value="/enableMonitor",method= RequestMethod.GET)
	public boolean  enableMonitor(HttpServletRequest request, HttpServletResponse response){
		String guid = request.getParameter("guid");
		String enabled = request.getParameter("enabled");
		boolean b;
		if("true".equals(enabled)){
			b = httpRequestService.disableMonitor(guid);
		}else{
			b = httpRequestService.enableMonitor(guid);
		}
		return b;
	}
    // --------------   ----------------------		----------------------------------

    @RequestMapping("/monitorLog")
    public String monitorLog(ModelMap map, HttpServletRequest request) {
    	String guid = request.getParameter("guid");
    	String name = request.getParameter("name");
		List<Map<String,Object>> list = httpSequenceService.getLogByGuid(guid);
		map.addAttribute("sequencelogs", list);
		map.addAttribute("sequenceName", name);
    	return "monitor_log";
    }

	@ResponseBody
    @RequestMapping(value="/apiLog",method= RequestMethod.GET)
    public List<Map<String, Object>>  apiLog(HttpServletRequest request, HttpServletResponse response){
		String id = request.getParameter("id");
		List<Map<String, Object>> list = httpRequestService.getHttpRequestLogByPid(id);
        return list;
    }

	
	@ResponseBody
    @RequestMapping(value="/deleteMonitor",method= RequestMethod.GET)
    public boolean  deleteMonitor(HttpServletRequest request, HttpServletResponse response){
		String guid = request.getParameter("guid");
		boolean b = httpRequestService.deleteMonitor(guid);
        return b;
    }
	
}
