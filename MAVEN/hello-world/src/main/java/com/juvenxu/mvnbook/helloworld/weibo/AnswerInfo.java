package com.juvenxu.mvnbook.helloworld.weibo;

import com.juvenxu.mvnbook.helloworld.utils.FileUtils;
import com.juvenxu.mvnbook.helloworld.utils.HttpUtils;

public class AnswerInfo {

	public static void main(String[] args) {

		StringBuffer stringBuffer = new StringBuffer();
		long a = 600000000000000000L;
		while (a < 699999999999999999L) {
			String url = "http://media.weibo.cn/wenda?id=1022%3A2310" + a;
			String result = HttpUtils.getByUrlConnection(url);
			if (!result.contains("内容获取失败,请稍后再试")) {
				System.out.println(result);
				stringBuffer.append(a);
			}
		}
		FileUtils.save(stringBuffer.toString(), "answerinfo");
	}
}
