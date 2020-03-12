package com.apimonitor.common.controller;


import com.apimonitor.common.entity.HttpRequest;
import com.apimonitor.common.entity.HttpRequestForm;
import com.apimonitor.common.entity.HttpSequence;
import com.apimonitor.common.service.HttpRequestService;
import com.apimonitor.common.service.HttpSequenceService;
import com.apimonitor.common.util.GuidGenerator;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 改进controller
 */
@RestController
@RequestMapping("/monitor")
public class MonitorController {
	protected static final Logger LOGGER = LoggerFactory.getLogger(MonitorController.class);

	@Autowired
	private HttpSequenceService httpSequenceService;
	
	@Autowired
	private HttpRequestService httpRequestService;

	/**
	 * 获取监控列表
	 */
    @PostMapping("/list")
    public Result monitorList() {
		List<Map<String,Object>> list = httpSequenceService.getMonitorList();
//		map.addAttribute("monitorList", list);
    	return new Result(ResultCode.SUCCESS,list);
    }

	/**
	 * 添加单个监控
	 */
    @PostMapping("/saveSingle")
	public Result saveSingleMonitor(@RequestBody HttpRequestForm form){
		try{
			//添加
			if(StringUtil.isEmpty(form.getGuid())){
				HttpSequence httpSequence = HttpSequence.getHttpSequence(form);
				httpSequence.setGuid(GuidGenerator.generate());

				HttpRequest httpRequest = HttpRequest.getHttpRequest(form);
				httpRequest.setGuid(GuidGenerator.generate());
				httpRequest.setPguid(httpSequence.getGuid());

				httpSequenceService.insert(httpSequence);
				httpRequestService.insertHttpRequest(httpRequest);
			}else{
				//修改
				HttpSequence httpSequence = HttpSequence.getHttpSequence(form);
				HttpRequest httpRequest = HttpRequest.getHttpRequest(form);
				httpSequenceService.update(httpSequence);
				httpRequestService.updateHttpRequest(httpRequest);
			}
			return new Result(ResultCode.SUCCESS);
		}catch(Exception e){
			LOGGER.error("/saveSingle错误日志",e);
			return new Result(ResultCode.FAIL);
		}
	}

	/**
	 * 启用停用监控按钮
	 */
	@GetMapping("/enabled")
	public Result  enableMonitor(@RequestParam(name = "guid") String guid,
								  @RequestParam(name = "enabled") String enabled){
		if("true".equals(enabled)){
			httpRequestService.disableMonitor(guid);
			return new Result(ResultCode.SUCCESS);
		}else{
			httpRequestService.enableMonitor(guid);
			return new Result(ResultCode.SUCCESS);
		}

	}

	/**
	 * api请求log，合并monitorLog
	 */
	@GetMapping("/apiAndMonitorLog")
	public Result apiAndMonitorLog(HttpServletRequest request){
		String guid = request.getParameter("guid");
		String name = request.getParameter("name");
		List<Map<String,Object>> sequenceLists = httpSequenceService.getLogByGuid(guid);
//		for(Map<String,Object> sequenceMap :sequenceLists){
//			for(String id : sequenceMap.keySet()){
//				Object value = sequenceMap.get(id);
//				List<Map<String, Object>> requestLists = httpRequestService.getHttpRequestLogByPid(value.toString());
//			}
//		}

		return new Result(ResultCode.SUCCESS,sequenceLists);
	}

	/**
	 * 监控log
	 */
	@RequestMapping("/monitorLog")
	public String monitorLog(ModelMap map, HttpServletRequest request) {
		String guid = request.getParameter("guid");
		String name = request.getParameter("name");
		List<Map<String,Object>> list = httpSequenceService.getLogByGuid(guid);
		map.addAttribute("sequencelogs", list);
		map.addAttribute("sequenceName", name);
		return "monitor_log";
	}


	/**
	 * 获取群组list
	 */
	@GetMapping("/getgroups")
	public Result addGroup(){
		return new Result(ResultCode.SUCCESS,httpSequenceService.getAllSystem());
	}

	/**
	 *新增群组
	 */
	@PostMapping("/addGroup")
	public Result addGroup(@RequestParam("systemName") String systemName){
		return new Result(ResultCode.SUCCESS,systemName);
	}
    // --------------   ----------------------		----------------------------------


	
}
