package com.liuzhuang.thread.forkjoin;

import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by liuzhuang on 2018/1/6.
 */
public class HttpUtils {

    private static RequestConfig requestConfig = RequestConfig.custom()
            .setSocketTimeout(5000).setConnectTimeout(5000).build();

    public static void main(String[] args) throws Exception {
        System.out.println(doPost("http://10.0.21.170:8088/student/page", "{\n" +
                "\t\"currentPage\": \"1\",\n" +
                "\t\"pageSize\": \"10\",\n" +
                "\t\"status\": 0,\n" +
                "\t\"search\": \"\",\n" +
                "\t\"type\": \"1\"\n" +
                "}"));
    }


    /**
     *
     * @param url
     * @param json
     * @return
     * @throws Exception
     */
    public static String doPost(String url, String json) throws Exception {
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);

        StringEntity s = new StringEntity(json.toString());
        s.setContentEncoding("UTF-8");
        s.setContentType("application/json");
        httpPost.setEntity(s);

        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse httpResp = httpclient.execute(httpPost);
        try {
            int statusCode = httpResp.getStatusLine().getStatusCode();
            // 判断是够请求成功
            if (statusCode == HttpStatus.SC_OK) {
//                System.out.println("状态码:" + statusCode);
//                System.out.println("请求成功!");
                // 获取返回的数据
                result = EntityUtils.toString(httpResp.getEntity(), "UTF-8");
            } else {
//                System.out.println("状态码:" + httpResp.getStatusLine().getStatusCode());
//                System.out.println("HttpPost方式请求失败!");
            }
        } finally {
            httpResp.close();
            httpclient.close();
        }
        return result;
    }
}
