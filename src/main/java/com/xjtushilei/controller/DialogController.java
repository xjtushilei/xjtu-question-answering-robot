package com.xjtushilei.controller;

import com.xjtushilei.utils.QnaMaker;
import com.xjtushilei.utils.QuestionPre;
import com.xjtushilei.utils.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author shilei
 * @Date 2017/6/20.
 */
@RestController
class DialogController {

    @Autowired
    RestTemplate qnaMakerRestTemplate;

    @RequestMapping("getAnswer")
    public String getAnswer(String question) {
        String answer;
        //0. 问题预处理
        question = QuestionPre.deal(question);

        //1.搜索引擎的图谱搜索
        System.out.println(1);
        answer = Spider.getKnowledgeGraphAnswer(question);
        if (answer != null) return answer;


        //2. 微软的QnaMaker
        System.out.println(2);
        answer = QnaMaker.getAnswer(qnaMakerRestTemplate, question);
        if (answer != null) return answer;

        /*
         *3. 百度知道智能问答
         * 1.有wgt-autoask的直接回答
         * 2.没有的话计算相似度(暂时没这一步)
         */
        System.out.println(3);
        answer =Spider.getAutoAskAnswer(question);
        if (answer != null) return answer;

        /*
         *接入图灵机器人
         * 进行最后的无聊的回答
         */

        return "无可奉告!";
    }
}
