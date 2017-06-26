package com.xjtushilei.utils;

import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


/**
 * @author shilei
 * @Date 2017/6/20.
 */
public class QnaMaker {

    public static void main(String[] a) {
        RestTemplate restTemplate = new RestTemplate();
        System.out.println(getAnswer(restTemplate, "今天吃什么饭"));

    }

    public static String getAnswer(RestTemplate restTemplate, String question) {
        try {
            String url = "https://westus.api.cognitive.microsoft.com/qnamaker/v2.0//knowledgebases/a90e29eb-2b9e-4995-b3d1-3917097482e6/generateAnswer";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Ocp-Apim-Subscription-Key", "3f5a37d9698744f3b40c89e2f0c94fb1");
            headers.add("Content-Type", "application/x-www-form-urlencoded");


            MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
            bodyMap.add("question", question);


            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
            String result = restTemplate.postForObject(url, requestEntity, String.class);

            Map<String, Object> answerObject = JsonPath.read(result, "$.answers[0]");
            if ((double) answerObject.get("score") > 10.0) {
                return answerObject.get("answer").toString();
            } else {
                return null;
            }
        }
        catch (Exception e){
            System.out.println("qna-maker 次数限制！");
            return null;
        }
    }

}
