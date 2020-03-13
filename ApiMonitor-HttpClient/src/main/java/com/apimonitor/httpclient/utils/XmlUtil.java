package com.apimonitor.httpclient.utils;

public class XmlUtil {
	public static String replaceChar(String xml){
		return xml.replaceAll("&nbsp;", "&amp;nbsp;").replaceAll("&raquo;", "&amp;raquo;");
	}
}
