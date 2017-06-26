package com.xjtushilei.controller;

import com.xjtushilei.knowledge.entity.Log;
import com.xjtushilei.knowledge.entity.Qna;
import com.xjtushilei.knowledge.repository.LogRepository;
import com.xjtushilei.knowledge.repository.QnaRepository;
import com.xjtushilei.utils.QnaMaker;
import com.xjtushilei.utils.QuestionPre;
import com.xjtushilei.utils.Spider;
import com.xjtushilei.utils.Tuling123;
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

    @Autowired
    RestTemplate tuLingRestTemplate;

    @Autowired
    QnaRepository qnaRepository;

    @Autowired
    LogRepository logRepository;

    @RequestMapping("getAnswer")
    public String getAnswer(String question) {
        String questionBak = question;
        String answer = null;
        int step = 1;

        // 缓存处理
        Qna qna = qnaRepository.findByQuestion(question);
        if (qna != null) return qna.getAnswer();

        try {
            //0. 问题预处理
            question = QuestionPre.deal(question);

            //1.搜索引擎的图谱搜索
            System.out.println(1 + ":搜索引擎的图谱搜索");
            answer = Spider.getKnowledgeGraphAnswer(question);
            if (answer != null) return answer;

             /*
             *3. 百度知道智能问答
             * 1.有wgt-autoask的直接回答
             * 2.没有的话计算相似度(暂时没这一步)
             */
            System.out.println(2 + ":百度知道智能问答");
            step = 2;
            answer = Spider.getAutoAskAnswer(question);
            if (answer != null) return answer;


            //2. 微软的QnaMaker
            System.out.println(3 + ":微软的QnaMaker");
            step = 3;
            answer = QnaMaker.getAnswer(qnaMakerRestTemplate, question);
            if (answer != null) return answer;


            /*
             *接入图灵机器人
             * 进行最后的无聊的回答
             */
            System.out.println(4 + ":接入图灵机器人");
            step = 4;
            answer = Tuling123.getAnswer(tuLingRestTemplate, question);
            if (answer != null) return answer;
            return "无可奉告!";
        } finally {
            //记录缓存
            if (step != 4) qnaRepository.save(new Qna(questionBak, answer, step));
            //记录日志
            logRepository.save(new Log(questionBak,answer));


        }
    }
}
