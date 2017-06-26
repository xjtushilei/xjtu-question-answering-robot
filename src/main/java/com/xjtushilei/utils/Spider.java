package com.xjtushilei.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

/**
 * @author shilei
 * @Date 2017/6/19.
 */
public class Spider {

    public static void main(String[] args) {
        System.setProperty("phantomjs.binary.path", Spider.class.getClassLoader().getResource("").getPath() + "phantomjs/phantomjs.exe");

//        System.out.println(getKnowledgeGraphAnswer(""));
//        System.out.println(getBestAnswer("https://zhidao.baidu.com/question/221723946.html?loc_ans=610699068"));
        System.out.println(getAutoAskAnswer("西安交大的食堂怎么样"));
    }

    public static String getKnowledgeGraphAnswer(String question) {
        String answer;
        try {
            question = URLEncoder.encode(question, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Document doc;
        WebDriver driver = null;

        try {

            driver = new PhantomJSDriver();
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

            //百度
            driver.get("http://www.baidu.com/s?ie=utf-8&wd=" + question);
            doc = Jsoup.parse(driver.getPageSource());
            answer = doc.select(".op_exactqa_s_answer").size() != 0 ? doc.select(".op_exactqa_s_answer").first().text()
                    : null;
            if (answer != null) return answer;

            //搜狗
            driver.get("https://www.sogou.com/web?query=" + question);
            doc = Jsoup.parse(driver.getPageSource());
            answer = doc.select(".proInfoBox h4").size() != 0 ? doc.select(".proInfoBox h4").first().text()
                    : null;
            if (answer != null) return answer;

//            //bing.com
//            driver.get("http://cn.bing.com/search?q=" + question);
//            doc = Jsoup.parse(driver.getPageSource());
//            answer = doc.select(".msnv2_component_padding").size() !=0 ?doc.select(".msnv2_component_padding").get(1)
//                    .text()
//                    :null;
//            if (answer!=null){
//                return answer;
//            }

            return null;
        }
        finally {
            if (driver != null) {
                driver.close();
            }
            if (driver != null) {
                driver.quit();
            }

        }
    }
    public static String getAutoAskAnswer(String question) {
        String answer;
        try {
            question = URLEncoder.encode(question, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        Document doc;

        try {
            doc = Jsoup.connect("http://zhidao.baidu.com/search?&word="+question).get();
        } catch (IOException e) {
            try {
                doc = Jsoup.connect("http://zhidao.baidu.com/search?&word="+question).get();
            } catch (IOException e1) {
                try {
                    doc = Jsoup.connect("http://zhidao.baidu.com/search?&word="+question).get();
                } catch (IOException e2) {
                    return null;
                }
            }
        }
        /*
         * 1.智能回答
         */
        answer = doc.select("#wgt-autoask").size() != 0 ? getBestAnswer(doc.select("#wgt-autoask > dl > dt > a").attr("abs:href")): null;
        if (answer != null) return answer;
//        /*
//         * 2.第一个答案(至少250条结果才能进行计算)
//         */
//        String span = doc.select("#wgt-picker > div.picker-header > span").text();
//        int count = Integer.valueOf(span.substring(1,span.indexOf("条")));
//        if (count>250){
//            answer = doc.select("#wgt-list > dl:nth-child(1) > dt > a").size() != 0 ? getBestAnswer(doc.select("#wgt-list > dl:nth-child(1) > dt > a").attr("abs:href")): null;
//        }
//        if (answer != null) return answer;
        return null;


    }

    public static String getBestAnswer(String url) {
        try {
            return Jsoup.connect(url).get().select(".bd.answer pre").text();
        } catch (IOException e) {
            return null;
        }

    }


}
