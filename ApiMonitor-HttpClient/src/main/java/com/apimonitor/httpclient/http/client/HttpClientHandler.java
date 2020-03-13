package com.apimonitor.httpclient.http.client;

import com.alibaba.fastjson.JSONPath;
import com.apimonitor.httpclient.entity.ApiRequest;
import com.apimonitor.httpclient.entity.ApiRequestLog;
import com.apimonitor.httpclient.entity.JobInfo;
import com.apimonitor.httpclient.entity.model.Application;
import com.apimonitor.httpclient.utils.StrUtils;
import com.apimonitor.httpclient.utils.XmlUtil;
import com.github.pagehelper.StringUtil;
import com.google.common.xml.XmlEscapers;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;


public class HttpClientHandler {

	protected static final Logger LOGGER = LoggerFactory.getLogger(HttpClientHandler.class);
	// Convert mill seconds to second unit
	protected static final int MS_TO_S_UNIT = 1000;
	// Normal http response code
	protected static final String NORMAL_RESPONSE_CODE = "200";
	// https prefix
	protected static final String HTTPS = "https";

	protected static HttpsTrustManager httpsTrustManager = new HttpsTrustManager();

	private ApiRequestHandle apiRequestHandle;
	
	private HttpEntity httpEntity;

	private JobInfo jobInfo;

	private ApiRequest apiRequest;

	protected String output;

	
	public HttpClientHandler(ApiRequest apiRequest,  ApiRequestHandle apiRequestHandle) {
		this.apiRequest = apiRequest;
		this.apiRequestHandle = apiRequestHandle;
	}

	public void httpEntity(HttpEntity httpEntity) {
		this.httpEntity = httpEntity;
	}

	/*
	 * Convert response as string
	 */
	protected String responseAsString(CloseableHttpResponse response) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			response.getEntity().writeTo(baos);
			return new String(baos.toByteArray(), Application.ENCODING);
		} catch (IOException e) {
			throw new IllegalStateException(e);
		}
	}
	
	
	public ApiRequestLog execute(){
		int start = (int)System.currentTimeMillis();
		String statusCode = null;
		String body = null;
		try{
			CloseableHttpResponse response = sendRequest();
			statusCode = String.valueOf(getStatusCode(response));
			body = getResponseBody(response);
			response.close();
			validResponse(body, statusCode);
			handleVariables(body);
		}catch(Exception e){
			appendMessage(e.toString());
			LOGGER.error("Send request to url[" + apiRequest.getApiUrl() + "] failed", e);
		}

		ApiRequestLog apiRequestLog = new ApiRequestLog();
		apiRequestLog.setCostTime((int)System.currentTimeMillis() - start);
		if(StringUtil.isEmpty(output)){
			apiRequestLog.setStatus(0);
		}

		apiRequestLog.setStatusCode(statusCode);
		apiRequestLog.setResponseBody(body);
		apiRequestLog.setLog(output);
		return apiRequestLog;
	}
	

	protected CloseableHttpResponse sendRequest() throws ClientProtocolException, IOException {
		RequestBuilder builder = createRequestBuilder();
		addRequestParams(builder);
		setHttpEntity(builder);
		HttpUriRequest request = builder.setUri(apiRequest.getApiUrl()).build();
		setHeaders(request);
		CloseableHttpClient client = createHttpClient();
		return client.execute(request);
	}

	protected String getResponseBody(CloseableHttpResponse httpResponse) throws ParseException, IOException{
		if (httpResponse == null)return null;
		HttpEntity entity = httpResponse.getEntity();
		if(entity == null)return null;
		String webPage = EntityUtils.toString(entity,"UTF-8");
		return webPage;
	}
	
	protected int getStatusCode(CloseableHttpResponse httpResponse){
		int status = httpResponse.getStatusLine().getStatusCode();
		return status;
	}
	
	protected void validResponse(String body, String statusCode) throws Exception {
		switch (apiRequest.getConditionType()) {
		case 1:
			if (StringUtil.isEmpty(body) || !body.contains(apiRequest.getConditionBody())) {
				appendMessage(apiRequest.getApiUrl() + " doesn't contain "
						+ XmlEscapers.xmlContentEscaper().escape(apiRequest.getConditionBody()));
			}
			break;
		case 2:
			if (StringUtil.isEmpty(body) || body.contains(apiRequest.getConditionBody())) {
				appendMessage(apiRequest.getApiUrl() + " contains "
						+ XmlEscapers.xmlContentEscaper().escape(apiRequest.getConditionBody()));
			}
			break;
		case 3:
			if (!statusCode.equals(apiRequest.getConditionBody())) {
				appendMessage("Invalid status: " + apiRequest.getApiUrl() + " required: " + apiRequest.getConditionBody() + ", received: " + statusCode);
			}
			break;
		default:
			if (!"200".equals(statusCode)) {
				appendMessage("Invalid status: " + apiRequest.getApiUrl() + " required: " + 200 + ", received: " + statusCode);
			}
		break;
		}
		
	}



	protected void handleVariables(String body) throws Exception {
		if(StringUtil.isEmpty(body))return;

		HashMap<String, String> variables = StrUtils.getStringToMap(apiRequest.getVariables());
		Integer type = apiRequest.getResultType();
		if(variables == null || type == null)return;
		if( apiRequest.getResultType()==2){
			for (String variableName : variables.keySet()) {
				String variablePath = variables.get(variableName);
				Object variableValue = JSONPath.read(body, variablePath);
				this.apiRequestHandle.setVariables(variableName, variableValue.toString());
			}
			
		}else if(apiRequest.getResultType()==1){
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			org.w3c.dom.Document doc = builder.parse(IOUtils.toInputStream(XmlUtil.replaceChar(body), "UTF-8"));
			XPathFactory xPathfactory = XPathFactory.newInstance();
			for (String variableName : variables.keySet()) {
				String variablePath = variables.get(variableName);

				XPath xpath = xPathfactory.newXPath();
				XPathExpression expr = xpath.compile(variablePath);
				// TODO Retrieve whole XML fragment
				String variableValue = expr.evaluate(doc);
				this.apiRequestHandle.setVariables(variableName, variableValue);
			}
		}
		
	}

	// ----------------		----------------

	
	public String getOutput() {
		return output;
	}

	protected void appendMessage(String message) {
		if (output == null) {
			output = "";
		}
		if (message != null && !message.trim().isEmpty()) {
			output += message;
		}
	}
	
	protected void addRequestParams(RequestBuilder builder) {
		HashMap<String, String> map = StrUtils.getStringToMap(apiRequest.getApiParameters());

		if(map==null || map.size()==0)return;
		for (String key : map.keySet()) {
			String val = map.get(key);
			builder.addParameter(key, useVariable(val));
		}
	}
	
	protected String useVariable(String val){
		if(!val.startsWith("$$")){
			return val;
		}else{
			String result = this.apiRequestHandle.getVariables(val);
			return result == null ? val : result;
		}
	}

	
	protected void setHttpEntity(RequestBuilder builder) {
		if (this.httpEntity != null) {
			builder.setEntity(this.httpEntity);
		}
	}

	protected void setHeaders(HttpUriRequest request) {
		HashMap<String, String> map = StrUtils.getStringToMap(apiRequest.getApiHeaders());
		if(map==null || map.size()==0)return;
		for (String key : map.keySet()) {
			request.addHeader(key, map.get(key));
		}
	}

	protected CloseableHttpClient createHttpClient() {
		final RequestConfig requestConfig = requestConfig();
		HttpClientBuilder httpClientBuilder;
		if (isHttps()) {
			// Support SSL
			SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(createSSLContext());
			httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig)
					.setSSLSocketFactory(sslConnectionSocketFactory);
		} else {
			httpClientBuilder = HttpClients.custom().setDefaultRequestConfig(requestConfig);
		}
		if(apiRequestHandle!=null){
			httpClientBuilder.setDefaultCookieStore(apiRequestHandle.cookieStore);
		}
		return httpClientBuilder.build();
	}

	private RequestConfig requestConfig() {
		final int maxConnMillSeconds = apiRequest.getMaxConnectionSeconds() * MS_TO_S_UNIT;
		return RequestConfig.custom().setSocketTimeout(maxConnMillSeconds).setConnectTimeout(maxConnMillSeconds).build();
	}

	private SSLContext createSSLContext() {
		try {
			SSLContext sslContext = SSLContext.getInstance("TLS");
			sslContext.init(null, new HttpsTrustManager[] { httpsTrustManager }, null);
			return sslContext;
		} catch (Exception e) {
			throw new IllegalStateException("Create SSLContext error", e);
		}
	}

	protected boolean isHttps() {
		return apiRequest.getApiUrl().toLowerCase().startsWith(HTTPS);
	}

	protected RequestBuilder createRequestBuilder() {
		if (apiRequest.getApiMethod()==1) {
			return RequestBuilder.get();
		} else if (apiRequest.getApiMethod()==3) {
			return RequestBuilder.post();
		} else if (apiRequest.getApiMethod()==2) {
			return RequestBuilder.head();
		} else if (apiRequest.getApiMethod()==4) {
			return RequestBuilder.put();
		} else if (apiRequest.getApiMethod()==5) {
			return RequestBuilder.delete();
		} else {
			return null;
		}

	}

	/**
	 * Default X509TrustManager implement
	 */
	private static class HttpsTrustManager implements X509TrustManager {

		@Override
		public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			// ignore
		}

		@Override
		public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {
			// ignore
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			return null;
		}
	}
}