package com.apimonitor.common.util;

public class XmlUtil {
	public static String replaceChar(String xml){
		return xml.replaceAll("&nbsp;", "&amp;nbsp;").replaceAll("&raquo;", "&amp;raquo;");
	}
}
