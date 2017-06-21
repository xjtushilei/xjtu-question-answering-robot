package com.xjtushilei.utils;

import com.jayway.jsonpath.JsonPath;
import org.jsoup.Jsoup;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;


/**
 * @author shilei
 * @Date 2017/6/20.
 */
public class Test {

    public static void main(String[] a) throws IOException {
        System.out.println(Jsoup.connect("https://zhidao.baidu.com/search?&word=%CE%F7%B0%B2%BD%BB%B4%F3%B5%C4%BC%C6%CB%E3%BB%FA%CF%B5" +
                "%D4%F5%C3%B4%D1%F9")
                .get()
                .select("#wgt-autoask").text());

    }


}
