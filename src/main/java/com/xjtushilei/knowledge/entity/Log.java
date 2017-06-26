package com.xjtushilei.knowledge.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author shilei
 * @Date 2017/6/26.
 */
@Entity
public class Log {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String question;

    private String answer;

    public Log(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public Log() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
