package com.juvenxu.mvnbook.helloworld.weibo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.juvenxu.mvnbook.helloworld.utils.FileUtils;
import com.juvenxu.mvnbook.helloworld.utils.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 微博回答
 */
public class Ansowers {

	/**
	 * 默认所有的回答
	 */
	private static void listAnswer() {
		StringBuffer stringBuffer = new StringBuffer();
		String sinceId = "";
		int count = 0;
		while(count < 100) {
			String url = "http://m.weibo.cn/container/getIndex?containerid=231068&extparam=2146965345%7C2448067557&uid=2448067557";
			if (!sinceId.equals("")) {
				url += "&since_id=" + sinceId;
			}
			String result = HttpUtils.getByUrlConnection(url);
			FileUtils.save(result, "daguguji_answer" + sinceId);

			try {
				JSONObject jsonObject = JSONObject.fromObject(result);
				
				JSONArray jsonArray = (JSONArray) jsonObject.get("cards");
				for (Object object : jsonArray) {
					JSONObject item = (JSONObject) object;
					if (item == null) {
						stringBuffer.append("null cards");
						continue;
					}
					
					JSONArray cardGroup =  (JSONArray) item.get("card_group");
					JSONObject user = (JSONObject) cardGroup.get(0);
					JSONObject answer = (JSONObject) cardGroup.get(1);
					
					String status = user.getString("status");
					String desc = answer.getString("desc1");
					String title = answer.getString("title_sub");
					
					System.out.println(title + " : " + desc + " : " + status);
					stringBuffer.append(title + " : " + desc + " : " + status);
				}
				JSONObject cardlistInfo = (JSONObject) jsonObject.get("cardlistInfo");
				sinceId = cardlistInfo.getString("since_id");
				
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
		FileUtils.save(stringBuffer.toString(), "daguguji_answer_all");
	}
	
	/**
	 * 计算获利和围观人数
	 */
	private static void cacluuteAnswer() {
		double totalmoney = 0;
		int totalpeople = 0;
		int count = 0;
		File file = new File("/Users/liuzhuang/Desktop/daguguji2/daguguji_answer_all.txt");
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				double money = Double.valueOf(tempString.split(" : ")[1].replaceAll("问题价值￥", ""));
				int people = Integer.valueOf(tempString.split(" : ")[2].replaceAll("人已围观", ""));
				totalmoney += money;
				totalpeople += people;
				count++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
		System.out.println("money:" + totalmoney + " totalpeople:" + totalpeople + " count:" + count);
	}
	
	public static void main(String[] args) {
		listAnswer();
		cacluuteAnswer();
	}
}
