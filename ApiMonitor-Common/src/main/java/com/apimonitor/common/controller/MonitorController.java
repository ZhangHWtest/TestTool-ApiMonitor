package com.apimonitor.common.controller;


import com.apimonitor.common.model.HttpRequest;
import com.apimonitor.common.model.HttpRequestForm;
import com.apimonitor.common.model.HttpSequence;
import com.apimonitor.common.model.HttpSystem;
import com.apimonitor.common.service.HttpRequestService;
import com.apimonitor.common.service.HttpSequenceService;
import com.apimonitor.common.util.GuidGenerator;
import com.apimonitor.system.entity.Model.Findbody;
import com.apimonitor.system.entity.resultException.Result;
import com.apimonitor.system.entity.resultException.ResultCode;
import com.github.pagehelper.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
	 * @param map
	 * @return
	 */
    @PostMapping("/list")
    public Result monitorList() {
		List<Map<String,Object>> list = httpSequenceService.getMonitorList();
//		map.addAttribute("monitorList", list);
    	return new Result(ResultCode.SUCCESS,list);
    }

	/**
	 * 添加单个监控
	 * @param form
	 * @return
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
	 * @param request
	 * @return
	 */
	@GetMapping("/enableMonitor")
	public boolean  enableMonitor(HttpServletRequest request){
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

	/**
	 * 获取群组list
	 * @return
	 */
	@GetMapping("/getgroups")
	public Result addGroup(){
		return new Result(ResultCode.SUCCESS,httpSequenceService.getAllSystem());
	}

	/**
	 *新增群组
	 * @param systemName
	 * @return
	 */
	@PostMapping("/addGroup")
	public Result addGroup(@RequestParam("systemName") String systemName){
		return new Result(ResultCode.SUCCESS,systemName);
	}
    // --------------   ----------------------		----------------------------------


	
}
