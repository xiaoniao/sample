package com.juvenxu.mvnbook.helloworld.weibo;

import java.util.HashMap;
import java.util.Map;

import com.juvenxu.mvnbook.helloworld.utils.HttpUtils;

public class Publish {
	
	public static void main(String[] args) {
		
		String url = "http://m.weibo.cn/api/statuses/update";
		Map<String, String> header = new HashMap<String, String>();
		header.put("Host", "m.weibo.cn");
		header.put("Connection", "keep-alive");
		header.put("Content-Length", "83");
		header.put("Accept", "application/json, text/plain, */*");
		header.put("Origin", "http://m.weibo.cn");
		header.put("X-Requested-With", "XMLHttpRequest");
		header.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Mobile Safari/537.36");
		header.put("Content-Type", "application/x-www-form-urlencoded");
		header.put("DNT", "1");
		header.put("Referer", "http://m.weibo.cn/compose");
		header.put("Accept-Encoding", "gzip, deflate");
		header.put("Accept-Language", "zh-CN,zh;q=0.8");
		header.put("Cookie", "_T_WM=8cec3a7a41159f42c82f6b66637b93a4; ALF=1490447693; SCF=AmQtksM4Sgh7oO-AyexD263yiq3j75tzx76fpDD1DMFPa5MW1WrGSDzOW8WCgSl1T8Dkv_rz9WU6XxGk2Bj9cnU.; SUB=_2A251qpCQDeRxGeRL61ER8SfNwz2IHXVXVDDYrDV6PUJbktBeLUXTkW03TiGF8tNQGrEeOAgSPv5Qc3B7Kg..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWOPvTlduA-v-wI5.kY9gIg5JpX5o2p5NHD95QESK50eh24eKnpWs4Dqcjdi--fi-20i-88i--fi-20i-88i--fi-2EiK.4; SUHB=0P1NAfIm-XyMfZ; SSOLoginState=1487855808; M_WEIBOCN_PARAMS=lfid%3D1076032503019181%26luicode%3D20000174%26uicode%3D20000174");
		
		Map<String, String> params = new HashMap<String, String>();
		params.put("content", "今日2017年02月25日00:05:55");
		params.put("st", "1eb2f4");
		
		String result = HttpUtils.postByUrlConnection(url, header, params);
		
		System.out.println(result);
	}
}
