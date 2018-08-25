package com.juvenxu.mvnbook.helloworld.weibo;

import com.juvenxu.mvnbook.helloworld.utils.FileUtils;
import com.juvenxu.mvnbook.helloworld.utils.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class CardList {

	/**
	 * 微博列表
	 */
	public static void main(String[] args) {
		int page = 0;
		StringBuffer stringBuffer = new StringBuffer();
		while (page < 1) {
			String url = "https://m.weibo.cn/api/container/getIndex?containerid=1076032503019181&page=" + page;
			String result = HttpUtils.getByUrlConnection(url);
//			FileUtils.save(result, page);
//			String result = FileUtils.read(page);
			System.out.println(result);
			page++;
			try {
				JSONObject jsonObject = JSONObject.fromObject(result);
				JSONArray jsonArray = (JSONArray) jsonObject.get("cards");
				for (Object object : jsonArray) {
					JSONObject item = (JSONObject) object;
					if (item == null) {
						stringBuffer.append("null cards");
						continue;
					}
					JSONObject mblog = (JSONObject) item.get("mblog");
					if (mblog == null) {
						stringBuffer.append("null mblog");
						continue;
					}
					String create = String.valueOf(mblog.get("created_at"));
					String text = String.valueOf(mblog.get("text"));
					stringBuffer.append(create + " : " + text + "\r\n");
					
					System.out.println(create + " : " + text);
				}
			} catch (Exception e) {
				e.printStackTrace();
				stringBuffer.append("\r\n" + e.getMessage() + "\r\n");
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
//		FileUtils.save(stringBuffer.toString(), -1);
	}
}
