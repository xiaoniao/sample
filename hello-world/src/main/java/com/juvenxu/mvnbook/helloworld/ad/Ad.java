package com.juvenxu.mvnbook.helloworld.ad;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import com.juvenxu.mvnbook.helloworld.utils.HttpUtils;

public class Ad {

  public static void main(String[] args) throws UnsupportedEncodingException {

    int page = 0;
    while (page < 21) {
//      String url = "http://t66y.com/thread0806.php?fid=22&search=&type=1&page=" + page;
      String url = "http://t66y.com/thread0806.php?fid=4&search=&page=" + page;
      Map<String, String> header = new HashMap<String, String>();
      header.put("Host", "t66y.com");
      header.put("Connection", "keep-alive");
      header.put("Upgrade-Insecure-Requests", "1");
      header.put("User-Agent",
          "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_12_5)AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110Safari/537.36");
      header.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8;charset=utf-8");
      header.put("DNT", "1");
      header.put("Referer", "http://t66y.com/thread0806.php?fid=22&search=&type=1&page=" + page);
      header.put("Accept-Encoding", "deflate, sdch");
      header.put("Accept-Language", "zh-CN,zh;q=0.8");
      header.put("Cookie",
          "__cfduid=decf08a79acb65a5a531b3d2960159b451499489989; PHPSESSID=5gfhaebdpqidfbac04d0hcl3l1; UM_distinctid=15d20922282557-08f139eae08c2e-30677908-fa000-15d2092228380a; CNZZDATA950900=cnzz_eid%3D333288657-1499488739-http%253A%252F%252Ft66y.com%252F%26ntime%3D1499488739");
      String result = HttpUtils.getByUrlConnection(url, header);

//      System.out.println(result);
      if (result.contains("比基尼") || result.contains("bikini")) {
        System.out.println(page);
      }
      page++;

      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
