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
public class Qna {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String question;

    private String answer;

    private int step;

    public Qna(String question, String answer, int step) {
        this.question = question;
        this.answer = answer;
        this.step = step;
    }

    public Qna() {
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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }
}
