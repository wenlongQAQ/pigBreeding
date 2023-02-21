package com.zzut.pigbreeding.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
@Component
public class SendRequestWeatherUtil {

    @Value("${url.weather}")
    private String url;
    public  String sendGet() throws IOException {
        //创建一个缓存
        StringBuffer result = new StringBuffer();
        //拼接参数，使其变成完整的url资源访问路径
        String urlName =url+"?key=" +
                "8a80331f7cbf643a71a345ab7b5f88f2" +
                "&city=410108" +
                "&extensions=base" +
                "&output=JSON";
        //创建一个读取字符流
        BufferedReader in = null;
        //创建URL对象
        URL url1 = new URL(urlName);
        //创建http连接对象
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        //设置连接超时时间
        connection.setConnectTimeout(5000);
        //设置读取超时时间
        connection.setReadTimeout(5000);
        //设置请求头的内容
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "keep-alive");
        connection.setRequestProperty("user-agent",
                "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.106 Safari/537.36");
        //注意：这行代码设置请求方式根本不起作用，我已经测试过了，感觉他只是给后台head中的信息
        connection.setRequestProperty("method", "POST");
        //注意：这行代码才是决定请求方式，你配置了就会起效果，默认是get方式，你不配置这个，就是按get方式传的
        connection.setRequestMethod("GET");
        //connection.setRequestMethod("POST");

        //向给定的地址发送请求
        connection.connect();

        //查看服务器返回的东西
        //要访问成功才行
        if (connection.getResponseCode() == 200) {
            //拿到访问成功后返回的信息流
            in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }
            return result.toString();
        }
        return result.toString();
    }
}
