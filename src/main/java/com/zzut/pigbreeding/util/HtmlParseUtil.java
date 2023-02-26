package com.zzut.pigbreeding.util;

import cn.hutool.extra.tokenizer.TokenizerEngine;
import cn.hutool.extra.tokenizer.TokenizerUtil;
import com.zzut.pigbreeding.pojo.PigPrice;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class HtmlParseUtil {
    @Value("${url.pigPrice}")
    private String url;
    public List<PigPrice> getPigPrice() throws IOException {
        /**
         * 获取不到ajax的信息 ->需要模拟浏览器的请求
         */
        /**
         * 解析网页，返回的Document就是浏览器Document对象
         */
        Document parse = Jsoup.parse(new URL(url), 30000);
        Element elementById = parse.getElementById("zhujia");
        /**
         * 获取所有的p元素
         */
        Elements element1 = elementById.getElementsByTag("b");
        Elements element2 = elementById.getElementsByTag("i");



        String s = element1.text();
        String s2 = element2.text();
        String[] s1 = s.split(" ");
        String[] s3 = s2.split(" ");

        PigPrice pigPrice1 = new PigPrice("外三元",Float.parseFloat(s1[3]),s3[0]);
        PigPrice pigPrice2 = new PigPrice("内三元",Float.parseFloat(s1[4]),s3[1]);
        PigPrice pigPrice3 = new PigPrice("土杂猪",Float.parseFloat(s1[5]),s3[2]);


        List<PigPrice> pigPrices = new ArrayList<>();
        pigPrices.add(pigPrice1);
        pigPrices.add(pigPrice2);
        pigPrices.add(pigPrice3);
        return pigPrices;

    }
}
