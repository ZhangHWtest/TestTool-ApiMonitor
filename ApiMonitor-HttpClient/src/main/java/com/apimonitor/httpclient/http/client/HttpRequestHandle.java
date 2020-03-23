package com.apimonitor.httpclient.http.client;

import com.apimonitor.httpclient.entity.HttpRequest;
import com.apimonitor.httpclient.http.utils.HttpClientResult;
import com.apimonitor.httpclient.http.utils.HttpClientUtils;
import com.google.gson.Gson;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class HttpRequestHandle {

	private HttpRequest httpRequest;

	public HttpRequestHandle(HttpRequest httpRequest) {
		this.httpRequest = httpRequest;
	}

	/**
	 * 发起请求
	 *
	 */
	public HttpClientResult execute() throws Exception {

		Gson gson = new Gson();
		HttpClientResult httpClientResult = new HttpClientResult();
		System.out.println(httpRequest);

		String url = httpRequest.getHttpUrl();
		Map<String, Object> handerMap = new HashMap<>();
		handerMap = gson.fromJson(httpRequest.getHttpHeaders(), handerMap.getClass());
		Map<String, Object> bodyMap = new HashMap<>();
		bodyMap = gson.fromJson(httpRequest.getHttpBody(), bodyMap.getClass());

		if (httpRequest.getHttpMethod() == 0) {
			return HttpClientUtils.doGet(url,handerMap,bodyMap);

		} else if (httpRequest.getHttpMethod() == 1) {
			return HttpClientUtils.doPost(url,handerMap,bodyMap);
		}
		System.out.println("---------开始轮询接口--------- \t\n---------" + httpClientResult + LocalDateTime.now() + "--------------");
		return null;

	}






}
