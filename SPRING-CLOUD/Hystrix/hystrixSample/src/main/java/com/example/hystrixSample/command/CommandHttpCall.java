package com.example.hystrixSample.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;
import com.netflix.hystrix.HystrixThreadPoolKey;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * 配置属性：https://github.com/Netflix/Hystrix/wiki/Configuration
 *
 * Created by liuzz on 2018/05/03
 */
public class CommandHttpCall extends HystrixCommand<String> {
    private Logger log = LoggerFactory.getLogger(CommandHttpCall.class);

    private String url;

    public CommandHttpCall(String url) {
        super(
                Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("hystrix.command.http"))
                                .andCommandKey(HystrixCommandKey.Factory.asKey("hystrix.command.http"))
                                .andThreadPoolKey(HystrixThreadPoolKey.Factory.asKey("hystrix.command.http"))
                                .andCommandPropertiesDefaults(
                                        HystrixCommandProperties
                                                .Setter()
                                                .withCircuitBreakerRequestVolumeThreshold(2)
                                                .withCircuitBreakerSleepWindowInMilliseconds(60 * 1000)
                                                .withFallbackEnabled(true)
                                                .withExecutionIsolationThreadInterruptOnTimeout(true)
                                                .withExecutionTimeoutInMilliseconds(5000)
                                )
        );
        this.url = url;
    }

    @Override
    protected String run() throws Exception {
        log.info("Execute of Command : url = {}", url);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            HttpEntity entity = response.getEntity();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(entity.getContent()));
            StringBuilder total = new StringBuilder();
            String line = bufferedReader.readLine();
            while (line != null) {
                total.append(line);
                line = bufferedReader.readLine();
            }
            return total.toString();
        }
    }

    @Override
    protected String getFallback() {
        return "failBackFor" + url;
    }
}
