package com.xjtushilei.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shilei
 * @Date 2017/6/21.
 */
public class QuestionPre {
    public static void main(String[] a) {
        String question = "你们学校计算机专业怎么样";
        System.out.println(deal(question));

    }

    public static String deal(String question) {
        List<String> key = new ArrayList();
        key.add("西安交通大学");
        key.add("xjtu");
        key.add("XJTU");
        key.add("西安交大");
        key.add("交大");
        key.add("交通大学");
        key.add("西交大");

        for (String str : key) {
            if (question.contains(str)) return question;
        }


        List<String> replaceKey = new ArrayList();
        replaceKey.add("你们学校");
        replaceKey.add("贵校");
        replaceKey.add("学校");
        for (String string : replaceKey) {
            if (question.contains(string)) question = question.replace(string, "西安交通大学");
            return question;
        }
        return question;
    }
}
