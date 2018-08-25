package com.juvenxu.mvnbook.helloworld.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Http 工具类
 * 
 * @author GeNing
 * @since 2016.05.12
 * 
 */
public class HttpUtils {
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return String 所代表远程资源的响应结果
	 */
	public static String postByUrlConnection(String url, Map<String, String> header, Map<String, String> params) {
		String result = "";
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			URL realUrl = new URL(url);

			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();
			 connection.setConnectTimeout(4000);
       connection.setReadTimeout(10000);
       
			// 设置请求属性
			if (header != null) {
				for (String key : header.keySet()) {
					connection.setRequestProperty(key, header.get(key));
				}
			}
			// 发送POST请求必须设置如下两行
			connection.setDoOutput(true);
			connection.setDoInput(true);
            // 获取URLConnection对象对应的输出流
            out = new PrintWriter(connection.getOutputStream());
            // 发送请求参数
            
            StringBuffer stringBuffer = new StringBuffer();
            for (String key : params.keySet()) {
				if (stringBuffer.length() != 0) {
					stringBuffer.append("&");
				}
				stringBuffer.append(key);
				stringBuffer.append("=");
				stringBuffer.append(params.get(key));
			}
            System.out.println(stringBuffer);
            out.print(stringBuffer.toString());
            // flush输出流的缓冲
            out.flush();
			// 建立实际的连接
			// connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}

		} catch (Exception e) {
			System.out.println("发送POST请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return String 所代表远程资源的响应结果
	 */
	public static String getByUrlConnection(String url, Map<String, String> header) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);

			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();

			 connection.setConnectTimeout(4000);
       connection.setReadTimeout(4000);
       
			// 设置请求属性
			if (header != null) {
				for (String key : header.keySet()) {
					connection.setRequestProperty(key, header.get(key));
				}
			}
			
			// 建立实际的连接
			connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "gbk"));

			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}

		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	/**
	 * 向指定URL发送GET方法的请求
	 * 
	 * @param url
	 *            发送请求的URL
	 * @return String 所代表远程资源的响应结果
	 */
	public static String getByUrlConnection(String url) {
		String result = "";
		BufferedReader in = null;
		try {
			URL realUrl = new URL(url);

			// 打开和URL之间的连接
			URLConnection connection = realUrl.openConnection();

			// 设置通用的请求属性

       connection.setConnectTimeout(4000);
       connection.setReadTimeout(4000);
			 connection.setRequestProperty("accept", "*/*");
			 connection.setRequestProperty("connection", "Keep-Alive");
			 connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			 
//			connection.setRequestProperty("Host", "m.weibo.cn");
//			connection.setRequestProperty("Connection", "keep-alive");
//			connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");
//			connection.setRequestProperty("Accept", "application/json, text/plain, */*");
//			connection.setRequestProperty("DNT", "1");
//			connection.setRequestProperty("Referer", "https://m.weibo.cn/u/2503019181");
////			connection.setRequestProperty("Accept-Encoding", "gzip, deflate, sdch");
//			connection.setRequestProperty("Accept-Language", "zh-CN,zh;q=0.8");
//			connection.setRequestProperty("Cookie", "ALF=1499788080; SCF=AmQtksM4Sgh7oO-AyexD263yiq3j75tzx76fpDD1DMFP_s_RLFM4oUqBSKt9HIhgvpmWpkln1j3nm7IVnBWmcpQ.; SUB=_2A250ORZhDeRhGeRL61ER8SfNwz2IHXVXxboprDV6PUJbktBeLWfHkW0-vP60DQPq-PFUNCJecd-AMmRluA..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWOPvTlduA-v-wI5.kY9gIg5JpX5o2p5NHD95QESK50eh24eKnpWs4Dqcj_i--fiK.7iKyWi--ciKn4iK.0i--ciKnRiK.7i--ci-zRi-20i--ci-zRi-20; SUHB=0idqxtckZ2gpLD; SSOLoginState=1497196081; _T_WM=b2cece6a1de30c956f9f9349f92a314f; M_WEIBOCN_PARAMS=featurecode%3D20000180%26luicode%3D10000011%26lfid%3D1005051565668374%26uicode%3D20000174");

			

//GET /api/container/getIndex?containerid=1076032503019181&page=3 HTTP/1.1
//Host: m.weibo.cn
//Connection: keep-alive
//Accept: application/json, text/plain, */*
//X-Requested-With: XMLHttpRequest
//User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36
//DNT: 1
//Referer: https://m.weibo.cn/u/2503019181
//Accept-Encoding: gzip, deflate, sdch, br
//Accept-Language: zh-CN,zh;q=0.8
//Cookie: ALF=1499788080; SCF=AmQtksM4Sgh7oO-AyexD263yiq3j75tzx76fpDD1DMFP_s_RLFM4oUqBSKt9HIhgvpmWpkln1j3nm7IVnBWmcpQ.; SUB=_2A250ORZhDeRhGeRL61ER8SfNwz2IHXVXxboprDV6PUJbktBeLWfHkW0-vP60DQPq-PFUNCJecd-AMmRluA..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWOPvTlduA-v-wI5.kY9gIg5JpX5o2p5NHD95QESK50eh24eKnpWs4Dqcj_i--fiK.7iKyWi--ciKn4iK.0i--ciKnRiK.7i--ci-zRi-20i--ci-zRi-20; SUHB=0idqxtckZ2gpLD; SSOLoginState=1497196081; _T_WM=b2cece6a1de30c956f9f9349f92a314f; M_WEIBOCN_PARAMS=featurecode%3D20000180%26luicode%3D10000011%26lfid%3D1076032503019181%26fid%3D1076032503019181%26uicode%3D10000011
//

			
			
			// 建立实际的连接
			connection.connect();

			// 定义 BufferedReader输入流来读取URL的响应
			in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

			String line = "";
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}

		} catch (Exception e) {
			System.out.println("发送GET请求出现异常！" + e);
			e.printStackTrace();
		} finally {
			// 使用finally块来关闭输入流
			try {
				if (in != null)
					in.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
}
