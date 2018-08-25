package com.juvenxu.mvnbook.helloworld.weibo;

import java.util.HashMap;
import java.util.Map;

import com.juvenxu.mvnbook.helloworld.utils.FileUtils;
import com.juvenxu.mvnbook.helloworld.utils.HttpUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Follows {
	
	// 2146965345 和菜头
	
	private static String[] ids = new String[] {"2146965345" };

	/**
	 * 读取某人关注的人
	 */
	public static void main(String[] args) {
		for (String id : ids) {
			int page = 0;
			int maxPage = 1;
			StringBuffer allResult = new StringBuffer();
			
			while (page <= maxPage) {
				String url = "http://m.weibo.cn/container/getSecond?containerid=100505" + id + "_-_FOLLOWERS&jumpfrom=weibocom";
				if (page != 0) {
					url += "&page=" + page;
				}
				
				Map<String, String> map = new HashMap<String, String>();
				map.put("Host", "m.weibo.cn");
				map.put("Connection", "keep-alive");
				map.put("Accept", "application/json, text/plain, */*");
				map.put("X-Requested-With", "XMLHttpRequest");
				map.put("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Mobile Safari/537.36");
				map.put("DNT", "1");
				map.put("Referer", "http://m.weibo.cn/p/second?containerid=1005052146965345_-_FOLLOWERS&jumpfrom=weibocom");
				map.put("Accept-Language", "zh-CN,zh;q=0.8");
				map.put("Cookie", "ALF=1489931880; SCF=AmQtksM4Sgh7oO-AyexD263yiq3j75tzx76fpDD1DMFPKbfMoNiv5E8JECA5iKa-Zl9p1EgCc8atLetCJOuT6pk.; SUB=_2A251o0QlDeRxGeRL61ER8SfNwz2IHXVXbGxtrDV6PUJbktBeLXLkkW2KUTFyMqokl8soX6x3ydPyVAeBxw..; SUBP=0033WrSXqPxfM725Ws9jqgMF55529P9D9WWOPvTlduA-v-wI5.kY9gIg5JpX5o2p5NHD95QESK50eh24eKnpWs4Dqcjdi--fi-20i-88i--fi-20i-88i--fi-2EiK.4; SUHB=0HTgi27tmbcTEm; SSOLoginState=1487352950; _T_WM=8cec3a7a41159f42c82f6b66637b93a4; M_WEIBOCN_PARAMS=featurecode%3D20000180%26oid%3D4076615099365817%26luicode%3D10000012%26lfid%3D1005052146965345_-_FOLLOWERS");
				
				String result = HttpUtils.getByUrlConnection(url, map);
				FileUtils.save2(result, "D:\\MAC_PROJECT\\project\\hello-world\\txt\\" + id + "follwers", "page" + page + ".txt");
				
				try {
					JSONObject jsonObject = JSONObject.fromObject(result);
					
					JSONArray jsonArray = (JSONArray) jsonObject.get("cards");
					for (Object object : jsonArray) {
						if (object == null) {
							continue;
						}
						JSONObject card = (JSONObject) object;
						JSONObject user = card.getJSONObject("user");
						String name = user.getString("screen_name");
						String desc = user.getString("description");
						String image = user.getString("cover_image_phone");
						String mid = user.getString("id");
						String followcount = user.getString("follow_count");
						String followerCount = user.getString("followers_count");
						allResult.append("name:" + name + " desc:" + desc + " image:" + image + " mid:" + mid + " followcount:" + followcount + " followerCount:" + followerCount + "\r\n");
						//System.out.println(mid + "-" + followerCount);
					}
					maxPage = jsonObject.getInt("maxPage");
					System.out.println("maxPage : " + maxPage);
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				page++;
			}
			FileUtils.save2(allResult.toString(), "D:\\MAC_PROJECT\\project\\hello-world\\txt\\" + id + "follwers", "page-all" + ".txt");
		}
	}
}
