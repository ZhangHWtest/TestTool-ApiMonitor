package com.apimonitor.common.http.demo;

import org.apache.http.client.methods.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ClientCRUD {

	public final static void main(String[] args) throws Exception {
		// 创建一个客户端
		CloseableHttpClient httpclient1 = HttpClients.createDefault();
		CloseableHttpClient httpclient2= HttpClients.createDefault();
		CloseableHttpClient httpclient3 = HttpClients.createDefault();
		CloseableHttpClient httpclient4 = HttpClients.createDefault();
		try {
			//创建实例
			HttpGet httpGet = new HttpGet("http://localhost:8080/emp");
			HttpPost httpPost = new HttpPost("http://localhost:8080/emp");
			HttpPut HttpPut = new HttpPut("http://localhost:8080/emp");
			HttpDelete httpdelete = new HttpDelete("http://localhost:8080/emp/1001");
			//发送请求
			CloseableHttpResponse response1 = httpclient1.execute(httpGet);
			CloseableHttpResponse response2 = httpclient2.execute(httpPost);
			CloseableHttpResponse response3 = httpclient3.execute(HttpPut);
			CloseableHttpResponse response4 = httpclient4.execute(httpdelete);
			try {
				System.out.println(EntityUtils.toString(response1.getEntity()));
				System.out.println(EntityUtils.toString(response2.getEntity()));
				System.out.println(EntityUtils.toString(response3.getEntity()));
				System.out.println(EntityUtils.toString(response4.getEntity()));
			} finally {
				response1.close();
				response2.close();
				response3.close();
				response4.close();
			}
		} finally {
			httpclient1.close();
			httpclient2.close();
			httpclient3.close();
			httpclient4.close();
		}
	}
}